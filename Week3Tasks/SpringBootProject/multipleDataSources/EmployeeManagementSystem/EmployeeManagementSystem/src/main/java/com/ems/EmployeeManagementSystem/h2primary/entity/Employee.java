package com.ems.EmployeeManagementSystem.h2primary.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//auditing
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;


}
