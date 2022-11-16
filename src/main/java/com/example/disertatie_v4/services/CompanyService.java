package com.example.disertatie_v4.services;

import com.example.disertatie_v4.dtos.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies(int page, int perPage);
    CompanyDto getCompanyById(Long id);
    CompanyDto createCompany(CompanyDto company);
    CompanyDto updateCompany(CompanyDto company, Long id);
    boolean deleteCompany(Long id);
}
