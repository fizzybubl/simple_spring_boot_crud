package com.example.simple_crud.controllers;

import com.example.simple_crud.dtos.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface EmployeeController {

    ResponseEntity<List<EmployeeDto>> getAllEmployees(int page, int perPage);
    ResponseEntity<EmployeeDto> getEmployeeById(Long id);
    ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto);
    ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto);
    ResponseEntity<Boolean> deleteEmployee(Long id);
}
