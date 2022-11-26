package com.example.simple_crud.controllers;

import com.example.simple_crud.dtos.CompanyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyController {

    ResponseEntity<List<CompanyDto>> getAllCompanies(int page, int perPage);
    ResponseEntity<CompanyDto> getCompanyById(Long id);
    ResponseEntity<CompanyDto> createCompany(CompanyDto companyDto);
    ResponseEntity<CompanyDto> updateCompany(Long id, CompanyDto companyDto);
    ResponseEntity<Boolean> deleteCompany(Long id);
}
