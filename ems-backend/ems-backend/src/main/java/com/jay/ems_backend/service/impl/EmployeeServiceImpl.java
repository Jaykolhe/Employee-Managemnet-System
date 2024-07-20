package com.jay.ems_backend.service.impl;

import com.jay.ems_backend.dto.EmployeeDto;
import com.jay.ems_backend.entity.Department;
import com.jay.ems_backend.entity.Employee;
import com.jay.ems_backend.exception.ResourseNotFoundException;
import com.jay.ems_backend.mapper.EmployeeMapper;
import com.jay.ems_backend.repository.DepartmentRepository;
import com.jay.ems_backend.repository.EmployeeRepository;
import com.jay.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl  implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(()->
                        new ResourseNotFoundException("Department is not Exists With Id:" +employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeID) {
       Employee employee = employeeRepository.findById(employeeID)
                .orElseThrow(()-> new ResourseNotFoundException("Employee is not Exists with Given ID : " +employeeID));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> Allemployee = employeeRepository.findAll();
        return Allemployee.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

       Employee employee = employeeRepository.findById(employeeId)
               .orElseThrow(()-> new ResourseNotFoundException("Employee is Not exist With Given ID"));

       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setLastName(updatedEmployee.getLastName());
       employee.setEmail(updatedEmployee.getEmail());
        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(()->
                        new ResourseNotFoundException("Department is not Exists With Id:" +updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);
       Employee updatedEmployeeObj = employeeRepository.save(employee);
        return  EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeID) {

        Employee employee = employeeRepository.findById(employeeID)
                .orElseThrow(()-> new ResourseNotFoundException("Employee is Not exist With Given ID"));
       employeeRepository.deleteById(employeeID);
    }

}
