package com.example.disertatie_v4.controllers;

import com.example.disertatie_v4.dtos.CompanyDto;
import com.example.disertatie_v4.models.Company;
import com.example.disertatie_v4.models.embeddables.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private static final CompanyDto companyDto = new CompanyDto(new Company("Company Name",
            new Address("Street", "800203", "City", "Country")));


    @Test
    void getAllCompanies() throws Exception {
        mockMvc.perform(get("/company")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    void getCompanyById() throws Exception {
        final long id = 3000000;
        mockMvc.perform(get("/company/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Company Name 99999")));
    }

    @Test
    void getCompanyByNonexistentId() throws Exception {
        final long id = 142515;
        mockMvc.perform(get("/company/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(String.format("No company with id: %s", id))));
    }

    @Test
    void createCompany() throws Exception {
        String json = mapper.writeValueAsString(companyDto);
        mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(companyDto.getName())))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void createCompanyWithInvalidData() throws Exception {
        CompanyDto dto = new CompanyDto(companyDto.of());
        dto.setName("");
        var invalidAddress = new Address();
        invalidAddress.setCity(" ");
        invalidAddress.setCountry(companyDto.getAddress().getCountry());
        invalidAddress.setStreet(companyDto.getAddress().getStreet());
        invalidAddress.setPostcode(companyDto.getAddress().getPostcode());
        dto.setAddress(invalidAddress);
        String json = mapper.writeValueAsString(dto);
        mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", is("must not be blank")))
                .andExpect(jsonPath("$['address.city']", is("must contain only letters")));
    }

    @Test
    void updateCompany() throws Exception {
        final long id = 3000001;
        CompanyDto dto = new CompanyDto(companyDto.of());
        dto.setName("Company Updated");
        var updatedAddress = new Address();
        updatedAddress.setCity("Updated City");
        updatedAddress.setCountry(companyDto.getAddress().getCountry());
        updatedAddress.setStreet(companyDto.getAddress().getStreet());
        updatedAddress.setPostcode(companyDto.getAddress().getPostcode());
        dto.setAddress(updatedAddress);
        String json = mapper.writeValueAsString(dto);
        mockMvc.perform(put("/company/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$['address']['city']", is(dto.getAddress().getCity())));
    }

    @Test
    void deleteCompany() throws Exception {
        final long id = 3000002;
        mockMvc.perform(delete("/company/{id}", id))
                .andExpect(status().isNoContent());
    }
}