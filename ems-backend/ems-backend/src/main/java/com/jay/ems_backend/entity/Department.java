package com.jay.ems_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="department_name")
    private String departmentName;

    @Column(name="department_description")
    private String departmentDescription;

    public Department() {
    }

    public Department(Long id, String departmentName, String departmentDescription) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
    }


}
