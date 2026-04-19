package com.file.repo;

import com.file.entity.DbConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbConnectionRepository extends JpaRepository<DbConnection,Long> {
}
