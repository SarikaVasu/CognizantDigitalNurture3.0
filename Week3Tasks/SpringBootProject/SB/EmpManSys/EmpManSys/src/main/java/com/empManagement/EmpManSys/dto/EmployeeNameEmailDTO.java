package com.empManagement.EmpManSys.dto;

import lombok.Data;

//class based projection
//DTO (Data Transfer Object) class with a constructor that matches the fields you want to fetch. This is useful when you need more control over the data, such as performing calculations or transforming the data during the fetch process.
//class-based projections, you can use @Value and constructor expressions to control the data fetched and perform transformations on the fly.

@Data
public class EmployeeNameEmailDTO {
    private final String name;
    private final String email;

    public EmployeeNameEmailDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }

}

//use in repo
