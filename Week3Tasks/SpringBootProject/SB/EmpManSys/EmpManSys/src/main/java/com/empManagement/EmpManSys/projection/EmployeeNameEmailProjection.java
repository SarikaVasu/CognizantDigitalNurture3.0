package com.empManagement.EmpManSys.projection;

//projection - fetch only req columns
//interface based: uses getter methods
//class based: uses dto
public interface EmployeeNameEmailProjection {
    String getName();
    String getEmail();
}

//use it in repo
