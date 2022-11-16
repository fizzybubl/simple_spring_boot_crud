package com.example.disertatie_v4.services;

import com.example.disertatie_v4.dtos.CompanyDto;
import com.example.disertatie_v4.models.Company;
import com.example.disertatie_v4.models.embeddables.Address;
import com.example.disertatie_v4.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


// TODO: check how unit tests are usually written (using argument captor, using when-then or given-will
//  if my repository mocking is alright or my test will always pass, even if code is broken

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;
    private CompanyService companyService;
    private Address address;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl(companyRepository);
        address = new Address("Al Davila", "800203", "Galati", "Romania");
    }

    @Test
    @DisplayName("It should return all companies")
    void getAllCompanies() {
        // given
        when(companyRepository.findAll(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(List.of(new Company())));
        // when
        var companies = companyService.getAllCompanies(0, 5);
        // then
        assertNotEquals(companies.size(), 0);
    }

    @Test
    @DisplayName("It should return company with given id")
    void getCompanyById() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(new Company(1L, "Company 1", address)));
        assertEquals(companyService.getCompanyById(1L), new CompanyDto(new Company(1L, "Company 1", address)));
    }

    @Test
    @DisplayName("It should create a new company")
    void createCompany() {
        // given
        var payloadCompany = new Company("Company 1", address);
        var payload = new CompanyDto(payloadCompany);
        var company = new Company(1L, "Company 1", address);
        when(companyRepository.save(payloadCompany)).thenReturn(company);
        // when
        var savedCompany = companyService.createCompany(payload);
        // then
        verify(companyRepository).save(payloadCompany);
        assertThat(savedCompany).isEqualTo(new CompanyDto(company));
    }

    @Test
    void updateCompany() {
        // given
        var newAddress = new Address("Nicolae Florescu", "142077", "Galati", "Romania");
        var payload = new CompanyDto(new Company("Company 2", newAddress));
        var companyToBeSaved = new Company(1L, "Company 2", newAddress);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(new Company(1L, "Company 1", address)));
        when(companyRepository.save(companyToBeSaved)).thenReturn(companyToBeSaved);
        //when
        var updatedCompany = companyService.updateCompany(payload, 1L);
        // then
        verify(companyRepository).save(companyToBeSaved);
        assertEquals(updatedCompany.getName(), payload.getName());
        assertThat(updatedCompany.getAddress()).isEqualTo(newAddress);
    }

    @Test
    void deleteCompany() {
    }
}