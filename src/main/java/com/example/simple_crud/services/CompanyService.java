package com.example.simple_crud.services;

import com.example.simple_crud.dtos.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies(int page, int perPage);
    CompanyDto getCompanyById(Long id);
    CompanyDto createCompany(CompanyDto company);
    CompanyDto updateCompany(CompanyDto company, Long id);
    boolean deleteCompany(Long id);
}
