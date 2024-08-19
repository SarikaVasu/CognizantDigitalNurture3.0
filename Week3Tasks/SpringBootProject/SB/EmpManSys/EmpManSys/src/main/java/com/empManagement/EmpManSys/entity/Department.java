package com.empManagement.EmpManSys.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data //getters, setters, toString, equals, hashCode,
@Entity
@EntityListeners(AuditingEntityListener.class)
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
    private Integer id;
    private String name;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<String> getEmployeesName;
}
