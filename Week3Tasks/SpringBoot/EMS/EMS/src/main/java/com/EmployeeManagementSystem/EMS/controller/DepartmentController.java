package com.EmployeeManagementSystem.EMS.controller;

import com.EmployeeManagementSystem.EMS.entity.Department;
import com.EmployeeManagementSystem.EMS.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;


    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    // Get a department by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
//        Department department = departmentRepository.findById(id).orElse(null);
//        if (department == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(department);
//    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    // Update a department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        department.setName(departmentDetails.getName());
        Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
//        if (!departmentRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        department.setId(id);
//        Department updatedDepartment = departmentRepository.save(department);
//        return ResponseEntity.ok(updatedDepartment);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
//        if (!departmentRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        departmentRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    // Delete a department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        departmentRepository.delete(department);
        return ResponseEntity.ok().build();
    }

}
