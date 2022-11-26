package com.example.simple_crud.services;

import com.example.simple_crud.dtos.EmployeeDto;
import com.example.simple_crud.models.Company;
import com.example.simple_crud.models.Employee;
import com.example.simple_crud.models.embeddables.Address;
import com.example.simple_crud.repositories.CompanyRepository;
import com.example.simple_crud.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    private static Company company;
    private static Employee employee;
    private static Employee employeeToBeUpdated;

    private EmployeeService employeeService;

    @BeforeAll
    static void setUpClass() {
        company = new Company(1L, "Company 1", new Address("Al Davila", "800203", "Galati", "Romania"));
        employee = new Employee(1L, "Dragos", "Sandica", company);
        employeeToBeUpdated = new Employee(10L, "Dragos", "Sandica", company);
    }

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository, companyRepository);
    }

    @Test
    @DisplayName("It should retrieve all employees")
    void getAllEmployees() {
        when(employeeRepository.findAll(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(List.of(employee)));
        assertNotEquals(employeeService.getAllEmployees(0, 5).size(), 0);
    }

    @Test
    @DisplayName("It should retrieve the employee with given id")
    void getEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        assertEquals(employeeService.getEmployeeById(1L), new EmployeeDto(employee));
    }

    @Test
    void createEmployee() {
        // given
        var employeeToBeCreated = new Employee("Dragos", "Sandica", company);
        var employeeToBeCreatedDto = new EmployeeDto(employeeToBeCreated);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(employeeRepository.save(employeeToBeCreated)).thenReturn(employee);
        // when
        var savedEmployee = employeeService.createEmployee(employeeToBeCreatedDto);
        // then
        assertEquals(savedEmployee, new EmployeeDto(employee));
    }

    @Test
    void updateEmployee() {
        // TODO: Check how to improve the Entity - Dto transformations and where to put them: in controller? in service?
        //  in dto class?
        var newCompany = new Company(2L, "Company the second", new Address("Alejandro", "900825", "Fernandio", "Spain"));
        var newEmployeeData = new Employee(10L, "Dragos", "Miguel", newCompany);
        var newEmployeeDataDto = new EmployeeDto(newEmployeeData);
        when(companyRepository.findById(2L)).thenReturn(Optional.of(newCompany));
        when(employeeRepository.findById(10L)).thenReturn(Optional.of(employeeToBeUpdated));
        when(employeeRepository.save(newEmployeeData)).thenReturn(newEmployeeData);
        var updatedEmployee = employeeService.updateEmployee(newEmployeeDataDto, 10L);
        assertEquals(updatedEmployee, new EmployeeDto(newEmployeeData));
    }

    @Test
    void deleteEmployee() {
    }
}