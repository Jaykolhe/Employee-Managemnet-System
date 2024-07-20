package com.jay.ems_backend.controller;

import com.jay.ems_backend.dto.EmployeeDto;
import com.jay.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    //Add Employee Rest Api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Get EmployeeById Rest API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable  Long id){

        EmployeeDto getEmployee = employeeService.getEmployeeById(id);
        return  ResponseEntity.ok(getEmployee);
    }

    //Get AllEmployee Rest API
    //@GetMapping("/all")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> AllEmployee = employeeService.getAllEmployee();
        return ResponseEntity.ok(AllEmployee);
    }

    //Update Employee Rest Api
    //@PutMapping("/update/{id}")
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable  Long id,
                                                      @RequestBody EmployeeDto updatedEmployee){

       EmployeeDto employeeDto = employeeService.updateEmployee(id,updatedEmployee);
       return ResponseEntity.ok(employeeDto);
    }


    //Delete EmployById

    //@DeleteMapping("/delete/{id}")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeByID(@PathVariable Long id){

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}
