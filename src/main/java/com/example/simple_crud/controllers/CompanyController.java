package com.example.disertatie_v4.controllers;

import com.example.disertatie_v4.dtos.CompanyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyController {

    ResponseEntity<List<CompanyDto>> getAllCompanies(int page, int perPage);
    ResponseEntity<CompanyDto> getCompanyById(Long id);
    ResponseEntity<CompanyDto> createCompany(CompanyDto companyDto);
    ResponseEntity<CompanyDto> updateCompany(Long id, CompanyDto companyDto);
    ResponseEntity<Boolean> deleteCompany(Long id);
}
