package com.empManagement.EmpManSys.service;

import com.empManagement.EmpManSys.dto.EmployeeCustomDTO;
import com.empManagement.EmpManSys.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeCustomDTO> getCustomEmployeeData() {
        return employeeRepository.findEmployeeCustomData();
    }


}
