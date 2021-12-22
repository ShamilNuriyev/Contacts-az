package com.example.backend.util;

import com.example.backend.Exception.EmployeeNotFoundException;
import com.example.backend.entity.EmployeeEntity;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeUtil {

    private final EmployeeRepository employeeRepository;

    public EmployeeUtil(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity employeeExists(Integer id) {
        Optional<EmployeeEntity> entityOptional = employeeRepository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        return entityOptional.get();
    }
}
