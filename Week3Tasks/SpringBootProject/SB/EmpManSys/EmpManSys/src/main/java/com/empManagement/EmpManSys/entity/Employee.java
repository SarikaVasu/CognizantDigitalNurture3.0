package com.empManagement.EmpManSys.entity;

import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

//Named queries are predefines jpql queries, can be defined at entity lvl, can be reused at diff parts of app
// execute named queries in repository interfaces or directly in the service layer.
@Data
@Entity
@EntityListeners(AuditingEntityListener.class) //auditing
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

    //auditing purpose *******
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDate lastModifiedDate;
    //*********

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
