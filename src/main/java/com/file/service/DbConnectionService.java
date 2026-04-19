package com.file.service;
import com.file.dto.DbConnectionRequest;
import com.file.entity.DbConnection;
import com.file.repo.DbConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class DbConnectionService {

    @Autowired
    private DbConnectionRepository repository;

    public DbConnection createAndTestConnection(DbConnectionRequest req) {

        // PostgreSQL JDBC URL
        String url = "jdbc:postgresql://"
                + req.host + ":"
                + req.port + "/"
                + req.databaseName;

        try {
            Connection connection = DriverManager.getConnection(
                    url,
                    req.username,
                    req.password
            );

            if (connection != null && !connection.isClosed()) {

                DbConnection db = new DbConnection();
                db.setName(req.name);
                db.setHost(req.host);
                db.setPort(req.port);
                db.setDatabaseName(req.databaseName);
                db.setUsername(req.username);
                // encrypt password before saving
                db.setPassword(req.password);
                db.setStatus("SUCCESS");

                return repository.save(db);
            }

        } catch (Exception e) {
            DbConnection db = new DbConnection();
            db.setName(req.name);
            db.setStatus("FAILED");
            repository.save(db);

            throw new RuntimeException("PostgreSQL Connection Failed", e);
        }

        throw new RuntimeException("PostgreSQL Connection Failed");
    }
}
