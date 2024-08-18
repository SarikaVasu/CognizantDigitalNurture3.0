package com.empManagement.EmpManSys.dto;

//@Value and constructor expressions to control the data fetched and perform transformations on the fly.

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data //getters
public class EmployeeCustomDTO {
    private final String name;
    private final String email;
    private final String departmentName;

    public EmployeeCustomDTO(String name, String email, @Value("#{target.department.name}") String departmentName) {
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
    }
    //This EmployeeCustomDTO fetches the name and email fields from the Employee entity and the name field from the related Department entity using the @Value annotation.
}
//use in repo
