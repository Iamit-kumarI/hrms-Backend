package com.hrms.hrmslite.controller;

import com.hrms.hrmslite.dto.EmployeeDto;
import com.hrms.hrmslite.entity.Employee;
import com.hrms.hrmslite.entity.Role;
import com.hrms.hrmslite.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee createEmployee(
            @RequestBody @Valid EmployeeDto dto,
            @RequestParam Role requesterRole
    ) {

        // Only HR or ADMIN can create employees
        if (requesterRole != Role.HR && requesterRole != Role.ADMIN) {
            throw new RuntimeException("Only HR or ADMIN can create employees");
        }

        // Map DTO to MongoDB Employee entity
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        // If HR/ADMIN wants to assign role, else default to EMPLOYEE
        employee.setRole(dto.getRole() != null ? dto.getRole().name() : Role.EMPLOYEE.name());

        return service.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return service.getAllEmployees();
    }
}
