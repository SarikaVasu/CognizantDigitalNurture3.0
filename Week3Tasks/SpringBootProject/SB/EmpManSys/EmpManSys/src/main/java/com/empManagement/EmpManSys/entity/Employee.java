package com.empManagement.EmpManSys.entity;

import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import lombok.Data;

//Named queries are predefines jpql queries, can be defined at entity lvl, can be reused at diff parts of app
// execute named queries in repository interfaces or directly in the service layer.
@Data
@Entity
@Table(name = "employees")
@NamedQuery(
        name = "Employee.findByEmail",
        query = "SELECT e FROM Employee e WHERE e.email = :email"
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "department_id")

    private Department department;
}
