package com.empManagement.EmpManSys.controller;

import com.empManagement.EmpManSys.entity.Employee;
import com.empManagement.EmpManSys.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository empRepo;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return empRepo.save(employee);
    }

    @GetMapping("/employees")
    public Optional<List<Employee>> getEmployees() {
        return Optional.of(empRepo.findAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = empRepo.findById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = empRepo.findById(id);
        if (employee.isPresent()) {
            Employee updatedEmployee = employee.get();
            updatedEmployee.setName(employeeDetails.getName());
            updatedEmployee.setEmail(employeeDetails.getEmail());
            updatedEmployee.setDepartment(employeeDetails.getDepartment());
            empRepo.save(updatedEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        if (empRepo.existsById(id)) {
            empRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // handle pagination and sorting
    //page no starts from 0
    @GetMapping("/employees/paginated")
    public Page<Employee> getPaginatedEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer departmentId,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        if (name != null) {
            return empRepo.findByNameContaining(name, pageable);
        }

        if (departmentId != null) {
            return empRepo.findByDepartmentId(departmentId, pageable);
        }

        return empRepo.findAll(pageable);
    }
}
