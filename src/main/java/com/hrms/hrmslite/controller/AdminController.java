package com.hrms.hrmslite.controller;

import com.hrms.hrmslite.entity.Attendance;
import com.hrms.hrmslite.entity.Role;
import com.hrms.hrmslite.entity.User;
import com.hrms.hrmslite.repository.UserRepository;
import com.hrms.hrmslite.service.AttendanceService;
import com.hrms.hrmslite.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepo;
    private final EmployeeService service;

    private final AttendanceService attendanceService; // add this

    public AdminController(UserRepository userRepo, EmployeeService service, AttendanceService attendanceService) {
        this.userRepo = userRepo;
        this.service = service;
        this.attendanceService = attendanceService; // assign here
    }

    @PostMapping("/create-hr")
    public User createHr(@RequestParam String username) {

        if (userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User hr = new User();
        hr.setUsername(username);
        hr.setPassword("hr123");
        hr.setRole(Role.HR);

        return userRepo.save(hr);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(
            @PathVariable String id, // <-- changed Long -> String
            @RequestParam String requesterRole) {

        service.deleteEmployee(id, requesterRole);
        return "Employee deleted successfully";
    }

}
