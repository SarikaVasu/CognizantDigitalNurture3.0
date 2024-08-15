package com.EmployeeManagementSystem.EMS.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    Long getId();
    String getName();
    String getEmail();

    //projections that need computed values. Computes a value by combining or formatting fields.
    @Value("#{target.name + ' (' + target.email + ')'}")
    String getNameWithEmail();
}

//use projection interface in repository
