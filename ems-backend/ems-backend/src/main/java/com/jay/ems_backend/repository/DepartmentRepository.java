package com.jay.ems_backend.repository;

import com.jay.ems_backend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department , Long> {
}
