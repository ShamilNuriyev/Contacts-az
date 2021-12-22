package com.example.backend.service;

import com.example.backend.dao.EmployeeDto;

import java.util.List;

public interface EmployeeService{
    List<EmployeeDto> getEmployees();

    EmployeeDto getEmployee(Integer id);

    EmployeeDto createEmployee(EmployeeDto customerDto);

    EmployeeDto editEmployee(Integer id, EmployeeDto customerDto);

    void deleteEmployee(Integer id);
}
