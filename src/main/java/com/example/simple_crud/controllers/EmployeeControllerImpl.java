package com.example.simple_crud.controllers;

import com.example.simple_crud.dtos.EmployeeDto;
import com.example.simple_crud.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(name="page", defaultValue = "0") int page,
                                                             @RequestParam(name="perPage", defaultValue = "5") int perPage) {
        return new ResponseEntity<>(employeeService.getAllEmployees(page, perPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.NO_CONTENT);
    }
}
