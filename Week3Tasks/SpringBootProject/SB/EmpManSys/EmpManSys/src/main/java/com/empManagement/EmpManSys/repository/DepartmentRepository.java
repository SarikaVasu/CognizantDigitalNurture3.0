package com.empManagement.EmpManSys.repository;

import com.empManagement.EmpManSys.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
    List<Department> findByNameContaining(String nameFragment);

    //implementation of named query
    @Query(name = "Department.findByName")
    Optional<Department> findByNameNamed(@Param("name") String name);

    @Query(name = "Department.findAllOrderedByName")
    List<Department> findAllOrderedByName();
}


