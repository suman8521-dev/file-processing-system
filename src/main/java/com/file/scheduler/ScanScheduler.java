package com.file.scheduler;
import com.file.entity.DbConnection;
import com.file.repo.DbConnectionRepository;
import com.file.service.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ScanScheduler {

    @Autowired
    private DbConnectionRepository repo;

    @Autowired
    private ScannerService scannerService;

    @Scheduled(fixedDelay = 3000) // every 5 min
    public void runScheduledScan() {

        List<DbConnection> connections = repo.findAll();

        for (DbConnection db : connections) {

            try {
                scannerService.runScan(db.getId(), "ALL_TABLES");

                db.setLastScannedAt(java.time.LocalDateTime.now());
                db.setScanCount(
                        db.getScanCount() == null ? 1 : db.getScanCount() + 1
                );

                repo.save(db);

            } catch (Exception e) {
                db.setStatus("FAILED");
                repo.save(db);
            }
        }
    }
}