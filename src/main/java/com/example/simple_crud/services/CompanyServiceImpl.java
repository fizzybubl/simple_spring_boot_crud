package com.example.simple_crud.services;

import com.example.simple_crud.dtos.CompanyDto;
import com.example.simple_crud.repositories.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyDto> getAllCompanies(int page, int perPage) {
        return companyRepository.findAll(PageRequest.of(page, perPage)).stream().map(CompanyDto::new).toList();
    }

    public CompanyDto getCompanyById(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No company with id: %s", id)));
        return new CompanyDto(company);
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        var company = companyRepository.save(companyDto.of());
        companyDto.setId(company.getId());
        return companyDto;
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No company with id: %s", id)));
        if (companyDto.getAddress() != null) {
            company.setAddress(companyDto.getAddress());
        }
        company.setName(companyDto.getName());
        companyRepository.save(company);
        return new CompanyDto(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return !companyRepository.existsById(id);
    }
}
