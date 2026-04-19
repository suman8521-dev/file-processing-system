package com.file.service;
import com.file.entity.DbConnection;
import com.file.repo.DbConnectionRepository;
import com.file.utility.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ScannerService {

    @Autowired
    private DbConnectionRepository dbRepo;

    public void runScan(Long connectionId, String tableName) {

        DbConnection db = dbRepo.findById(connectionId)
                .orElseThrow(() -> new RuntimeException("DB not found"));

        String url = "jdbc:postgresql://" + db.getHost() + ":" + db.getPort() + "/" + db.getDatabaseName();

        try (Connection conn = DriverManager.getConnection(url, db.getUsername(), db.getPassword())) {

            String sql = "SELECT * FROM " + tableName;

            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {

                boolean updated = false;

                for (int i = 1; i <= columnCount; i++) {

                    String columnName = meta.getColumnName(i);
                    String value = rs.getString(i);

                    if (value != null && isPII(columnName, value)) {

                        String masked = maskValue(columnName, value);
                        String encrypted = CryptoUtil.encryptS(value);

                        // store encrypted version (recommended)
                        rs.updateString(i, encrypted);
                        updated = true;
                    }
                }

                if (updated) {
                    rs.updateRow();
                }
            }

            // update scan time
            db.setLastScannedAt(java.time.LocalDateTime.now());
            dbRepo.save(db);

        } catch (Exception e) {
            throw new RuntimeException("Scan failed", e);
        }
    }
    private boolean isPII(String column, String value) {

        column = column.toLowerCase();

        return column.contains("email")
                || column.contains("phone")
                || column.contains("aadhaar")
                || column.contains("pan");
    }

    private String maskValue(String column, String value) {

        if (column.contains("email")) {
            return value.replaceAll("(.{2}).+(@.+)", "$1***$2");
        }

        if (column.contains("phone")) {
            return "******" + value.substring(value.length() - 4);
        }

        return "****";
    }

    private void updateValuePostgres(Connection conn,
                                     String table,
                                     String column,
                                     String value,
                                     ResultSetMetaData meta,
                                     ResultSet rs) throws Exception {

        // Assume first column is PRIMARY KEY (you should improve this later)
        String pkColumn = meta.getColumnName(1);
        String pkValue = rs.getString(1);

        String sql = "UPDATE " + table + " SET " + column + " = ? WHERE " + pkColumn + " = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, value);
        ps.setString(2, pkValue);

        ps.executeUpdate();
    }
    }