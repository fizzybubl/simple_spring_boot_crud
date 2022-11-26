package com.example.simple_crud.services;

import com.example.simple_crud.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees(int page, int perPage);
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(EmployeeDto employee, Long id);
    boolean deleteEmployee(Long id);
}
