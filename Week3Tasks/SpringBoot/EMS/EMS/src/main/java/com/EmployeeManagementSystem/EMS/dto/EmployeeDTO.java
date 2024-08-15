package com.EmployeeManagementSystem.EMS.dto;
//class based projection to define dto data transfer object to rep data struct required

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;

    public EmployeeDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
//use in repo
