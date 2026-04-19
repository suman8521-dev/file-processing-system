package com.file.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ScanJob {

    @Id
    @GeneratedValue
    private Long id;

    private Long dbConnectionId;

    private String scanType; // FULL_DB / TABLE

    private String tableName;

    private String status; // PENDING / RUNNING / DONE / FAILED

    private java.time.LocalDateTime startedAt;

    private java.time.LocalDateTime completedAt;
}