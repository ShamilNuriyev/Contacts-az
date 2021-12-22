package com.example.backend.controller;

import com.example.backend.dao.EmployeeDto;
import com.example.backend.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:63342",allowCredentials = "true")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Integer id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto Dto) {
        return employeeService.createEmployee(Dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public EmployeeDto editEmployee(@PathVariable Integer id,
                                    @RequestBody EmployeeDto dto) {
        return employeeService.editEmployee(id, dto);
    }
}

