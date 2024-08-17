package com.empManagement.EmpManSys.repository;

import com.empManagement.EmpManSys.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
}


