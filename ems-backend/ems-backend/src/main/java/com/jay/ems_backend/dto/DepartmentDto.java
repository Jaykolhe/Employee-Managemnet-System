package com.jay.ems_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {

    private Long id;

    private String departmentName;
    private String departmentDescription;

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String departmentName,  String departmentDescription) {
        this.departmentName = departmentName;
        this.id = id;
        this.departmentDescription = departmentDescription;
    }


}
