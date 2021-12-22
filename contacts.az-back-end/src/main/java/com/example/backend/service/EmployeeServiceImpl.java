package com.example.backend.service;

import com.example.backend.dao.EmployeeDto;
import com.example.backend.entity.EmployeeEntity;
import com.example.backend.mapper.EmployeeMapper;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.util.EmployeeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeUtil employeeUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeUtil employeeUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeUtil = employeeUtil;
    }


    @Override
    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> students = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> {
            students.add(EmployeeMapper.toDto(e));
        });
            List<EmployeeDto> list = new ArrayList<>();
        for (int i = students.size()-1; i >=0; i--) { 
            list.add(students.get(i));
        }
        return list;
    }
    
    @Override
    public EmployeeDto getEmployee(Integer id) {
        EmployeeEntity entity = employeeUtil.employeeExists(id);
        return EmployeeMapper.toDto(entity);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        EmployeeEntity entity = employeeRepository.save(EmployeeMapper.toEntity(dto));
        dto.setId1(entity.getId());
        return dto;
    }

    @Override
    public EmployeeDto editEmployee(Integer id, EmployeeDto dto) {
        employeeUtil.employeeExists(dto.getId1());
        employeeRepository.save(EmployeeMapper.toEntity(dto));
        return dto;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
