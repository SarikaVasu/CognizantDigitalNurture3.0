package com.empManagement.EmpManSys.repository;

import com.empManagement.EmpManSys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //by using proper naming format jpa allows u to create functions without need to write logic
    List<Employee> findByName(String name);
    List<Employee> findByDepartmentId(int departmentId);
//    List<Employee> findByEmail(String email);
    List<Employee> findByDepartmentIdAndName(int departmentId, String name);



//    @Query to define custom query using jpql
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmail(@Param("email") String email);

    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    //Named queries are predefines jpql queries, can be defined at entity lvl, can be reused at diff parts of app
    //implementation of named query
    //use these in controllers fro operations on data or resources
    @Query(name = "Employee.findByEmail")
    Employee findByEmailNamed(@Param("email") String email);
}
