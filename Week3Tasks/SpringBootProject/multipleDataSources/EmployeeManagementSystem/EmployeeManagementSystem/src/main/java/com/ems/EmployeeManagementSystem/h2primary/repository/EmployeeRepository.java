package com.ems.EmployeeManagementSystem.h2primary.repository;

import com.ems.EmployeeManagementSystem.h2primary.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}