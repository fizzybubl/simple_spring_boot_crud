package com.example.disertatie_v4.controllers;

import com.example.disertatie_v4.dtos.CompanyDto;
import com.example.disertatie_v4.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    public CompanyControllerImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies(@RequestParam(name="page", defaultValue = "0") int page,
                                                            @RequestParam(name="perPage", defaultValue = "5") int perPage) {
        return new ResponseEntity<>(companyService.getAllCompanies(page, perPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.createCompany(companyDto), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.updateCompany(companyDto, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCompany(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.deleteCompany(id), HttpStatus.NO_CONTENT);
    }
}
