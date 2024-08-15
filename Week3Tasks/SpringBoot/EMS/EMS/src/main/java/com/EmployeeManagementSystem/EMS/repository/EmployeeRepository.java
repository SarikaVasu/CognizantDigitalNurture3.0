package com.EmployeeManagementSystem.EMS.repository;

import com.EmployeeManagementSystem.EMS.dto.EmployeeDTO;
import com.EmployeeManagementSystem.EMS.entity.Employee;
import com.EmployeeManagementSystem.EMS.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //*************************************
    //method query
    // Derived query method to find employees by name
    //query euivalent to SELECT * FROM employees WHERE name = ?;
    List<Employee> findByName(String name);

    // Derived query method to find employees by email
    Employee findByEmail(String email);

    // Derived query method to find employees by department name
    // SELECT * FROM employees e JOIN departments d ON e.department_id = d.id WHERE d.name = ?;
    List<Employee> findByDepartmentName(String departmentName);

    // Check if an employee exists by email
    boolean existsByEmail(String email);

    // Count employees by department name
    long countByDepartmentName(String departmentName);

    // Delete employees by department name
    void deleteByDepartmentName(String departmentName);
    //**********************************

    //**********************************
    //custom JPQL query
//    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
//    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);
//
//    @Query("SELECT e FROM Employee e WHERE e.email = :email")
//    Employee findEmployeeByEmail(@Param("email") String email);

    //custom native SQL query
    @Query(value = "SELECT * FROM employees WHERE email = :email", nativeQuery = true)
    Employee findEmployeeByEmailNative(@Param("email") String email);
    //***********************************

    //*****************************
    //execute a named query
//    @Query(name = "Employee.findByDepartmentName")
//    List<Employee> findEmployeesByDepartmentName(String departmentName);

    @Query(name = "Employee.findByEmail")
    Employee findEmployeeByEmail(String email);
    //******************************

    // Method for pagination
    Page<Employee> findAll(Pageable pageable);

    // Method with pagination and sorting
    //Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

    // Use projection in query
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<EmployeeProjection> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    // Use class-based projection in query
//    @Query("SELECT new com.EmployeeManagementSystem.EMS.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e WHERE e.department.name = :departmentName")
//    List<EmployeeDTO> findEmployeeDTOsByDepartmentName(@Param("departmentName") String departmentName);

    // Constructor expression for class-based projection
    @Query("SELECT new com.EmployeeManagementSystem.EMS.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e WHERE e.department.name = :departmentName")
    List<EmployeeDTO> findEmployeeDTOsByDepartmentName(@Param("departmentName") String departmentName);
}
