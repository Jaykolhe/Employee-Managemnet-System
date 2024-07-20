package com.jay.ems_backend.controller;

import com.jay.ems_backend.dto.DepartmentDto;
import com.jay.ems_backend.dto.EmployeeDto;
import com.jay.ems_backend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin ("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    //Create Department
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
      DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    //Get DepartmentById
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){
        DepartmentDto getDepartment = departmentService.getDepartmentById(id);

        return ResponseEntity.ok(getDepartment);
    }

    //Get AllDepartments
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> allDepartments= departmentService.getAllDepartments();
        return ResponseEntity.ok(allDepartments);
    }

    //Update department
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto updatedDepartment){
        DepartmentDto departmentDto = departmentService.updateDepartment(id,updatedDepartment);

        return ResponseEntity.ok(departmentDto);
    }

    //Delete Department
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long id){
        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department Deleted SuccessFully");
    }
}
