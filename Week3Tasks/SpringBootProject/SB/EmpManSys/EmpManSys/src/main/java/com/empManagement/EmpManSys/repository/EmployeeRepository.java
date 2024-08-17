package com.empManagement.EmpManSys.repository;

import com.empManagement.EmpManSys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByDepartmentName(String departmentName);
    List<Employee> findByEmail(String email);
}
