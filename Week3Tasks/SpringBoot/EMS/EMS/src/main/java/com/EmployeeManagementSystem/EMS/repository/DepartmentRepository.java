package com.EmployeeManagementSystem.EMS.repository;

import com.EmployeeManagementSystem.EMS.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query method to find a department by name
    Department findByName(String name);
}
