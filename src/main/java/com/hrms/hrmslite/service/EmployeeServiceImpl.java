package com.hrms.hrmslite.service;

import com.hrms.hrmslite.entity.Employee;
import com.hrms.hrmslite.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public void deleteEmployee(String id, String requesterRole) { // <-- changed Long -> String
        if (!requesterRole.equals("HR") && !requesterRole.equals("ADMIN")) {
            throw new RuntimeException("Access Denied !! You Are Not The Right Person To Perform This Action");
        }
        repository.deleteById(id);
    }
}
