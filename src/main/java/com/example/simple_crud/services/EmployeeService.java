package com.example.disertatie_v4.services;

import com.example.disertatie_v4.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees(int page, int perPage);
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(EmployeeDto employee, Long id);
    boolean deleteEmployee(Long id);
}
