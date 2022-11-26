package com.example.simple_crud.controllers;

import com.example.simple_crud.dtos.EmployeeDto;
import com.example.simple_crud.models.Company;
import com.example.simple_crud.models.Employee;
import com.example.simple_crud.models.embeddables.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static Employee employee;
    private static Company company;

    @BeforeAll
    static void setUpClass() {
        company = new Company("Company Name 1",
                new Address("Street", "800203", "City", "Country"));
        company.setId(3000000L);
        employee = new Employee("First Name 1", "Last Name 1", company);
    }

    @Test
    void getAllEmployees() throws Exception {
        mockMvc.perform(get("/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    void getEmployeeById() throws Exception {
        final long id = 3000000;
        mockMvc.perform(get("/employee/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("First name")));
    }

    @Test
    void createEmployee() throws Exception {
        EmployeeDto dto = new EmployeeDto(employee);
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.companyId", is(employee.getCompany().getId().intValue())));
    }

    @Test
    void createEmployeeWithNonExistingCompany() throws Exception {
        final long nonExistingCompanyId = 128491264L;
        EmployeeDto dto = new EmployeeDto(employee);
        dto.setCompanyId(nonExistingCompanyId);
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(String.format("No company with id: %s", nonExistingCompanyId))));
    }

    @Test
    void updateEmployee() throws Exception {
        EmployeeDto dto = new EmployeeDto(employee);
        dto.setFirstName("Updated First Name");
        dto.setCompanyId(3000001L);
        mockMvc.perform(put("/employee/{id}", 3000001)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(dto.getFirstName())))
                .andExpect(jsonPath("$.companyId", is(dto.getCompanyId().intValue())));
    }


    @Test
    void updateEmployeeWithNonExistingCompany() throws Exception {
        final long nonExistingCompanyId = 128491264L;
        EmployeeDto dto = new EmployeeDto(employee);
        dto.setFirstName("Updated First Name");
        dto.setCompanyId(nonExistingCompanyId);
        mockMvc.perform(put("/employee/{id}", 3000001)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(String.format("No company with id: %s", nonExistingCompanyId))));
    }


    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete("/employee/{id}", 3000002)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}