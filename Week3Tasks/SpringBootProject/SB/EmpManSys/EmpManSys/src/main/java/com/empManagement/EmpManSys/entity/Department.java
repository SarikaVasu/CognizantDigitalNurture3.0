package com.empManagement.EmpManSys.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data //getters, setters, toString, equals, hashCode,
@Entity
@Table(name = "departments")
@NamedQueries({
        @NamedQuery(
                name = "Department.findByName",
                query = "SELECT d FROM Department d WHERE d.name = :name"
        ),
        @NamedQuery(
                name = "Department.findAllOrderedByName",
                query = "SELECT d FROM Department d ORDER BY d.name ASC"
        )
})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<String> getEmployeesName;
}
