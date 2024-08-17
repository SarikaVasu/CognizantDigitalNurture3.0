package com.empManagement.EmpManSys.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data //getters, setters, toString, equals, hashCode,
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
}
