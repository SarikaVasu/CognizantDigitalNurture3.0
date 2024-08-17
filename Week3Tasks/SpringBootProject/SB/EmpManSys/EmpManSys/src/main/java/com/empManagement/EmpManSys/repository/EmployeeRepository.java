package com.empManagement.EmpManSys.repository;

import com.empManagement.EmpManSys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByDepartmentName(String departmentName);
    List<Employee> findByEmail(String email);
}
