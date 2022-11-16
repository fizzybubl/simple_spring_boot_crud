package com.example.disertatie_v4.controllers;

import com.example.disertatie_v4.dtos.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface EmployeeController {

    ResponseEntity<List<EmployeeDto>> getAllEmployees(int page, int perPage);
    ResponseEntity<EmployeeDto> getEmployeeById(Long id);
    ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto);
    ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto);
    ResponseEntity<Boolean> deleteEmployee(Long id);
}
