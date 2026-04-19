package com.file.controller;
import com.file.dto.DbConnectionRequest;
import com.file.entity.DbConnection;
import com.file.service.DbConnectionService;
import com.file.service.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/db")
public class DbConnectionController {

    @Autowired
    private DbConnectionService dbService;

    @Autowired
    private ScannerService scannerService;

    @PostMapping("/connect")
    public DbConnection createConnection(@RequestBody DbConnectionRequest request) {
        return dbService.createAndTestConnection(request);
    }

    @PostMapping("/scan/{connectionId}")
    public String runScan(
            @PathVariable Long connectionId,
            @RequestParam String tableName
    ) {
        scannerService.runScan(connectionId, tableName);
        return "Scan Completed Successfully";
    }


}
