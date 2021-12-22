package com.example.backend.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException() {
        super("Employee not found");
    }

}
