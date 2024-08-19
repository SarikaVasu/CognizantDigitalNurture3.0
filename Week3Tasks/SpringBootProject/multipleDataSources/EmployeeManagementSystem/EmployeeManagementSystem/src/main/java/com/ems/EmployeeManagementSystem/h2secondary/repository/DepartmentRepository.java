package com.ems.EmployeeManagementSystem.h2secondary.repository;

import com.ems.EmployeeManagementSystem.h2secondary.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}