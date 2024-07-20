package com.jay.ems_backend.service.impl;

import com.jay.ems_backend.dto.DepartmentDto;
import com.jay.ems_backend.entity.Department;
import com.jay.ems_backend.exception.ResourseNotFoundException;
import com.jay.ems_backend.mapper.DepartmentMapper;
import com.jay.ems_backend.repository.DepartmentRepository;
import com.jay.ems_backend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourseNotFoundException("Department is not Exists with Given ID : " +departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> allDepartments = departmentRepository.findAll();
        return allDepartments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
       Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourseNotFoundException("Department is Not exist With Given ID"));

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

        Department updatedDepartmentObj = departmentRepository.save(department);
        return  DepartmentMapper.mapToDepartmentDto(updatedDepartmentObj);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
       Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourseNotFoundException("Department is Not exist With Given ID"));
        departmentRepository.deleteById(departmentId);
    }


}
