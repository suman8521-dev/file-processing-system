package com.file.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "db_connection")
public class DbConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String host;
    private String port;
    private String databaseName;

    private String username;

    // IMPORTANT: encrypted store karo
    private String password;
    private LocalDateTime lastScannedAt;

    private Integer scanCount;
    private String status; // SUCCESS / FAILED
}
