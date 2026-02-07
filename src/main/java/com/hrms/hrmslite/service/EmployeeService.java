package com.hrms.hrmslite.service;

import com.hrms.hrmslite.entity.Employee;
import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    void deleteEmployee(String id, String requesterRole); // <-- changed Long -> String
}
