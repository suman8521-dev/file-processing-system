package com.file.dto;
import lombok.Data;

@Data
public class DbConnectionRequest {
    public String name;
    public String host;
    public String port;
    public String databaseName;
    public String username;
    public String password;
}
