package com.example.simple_crud.services;

import com.example.simple_crud.dtos.EmployeeDto;
import com.example.simple_crud.models.Company;
import com.example.simple_crud.models.Employee;
import com.example.simple_crud.repositories.CompanyRepository;
import com.example.simple_crud.repositories.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees(int page, int perPage) {
        return employeeRepository.findAll(PageRequest.of(page, perPage)).stream().map(EmployeeDto::new).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No employee with this id: %s", id)));
        return new EmployeeDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Company company = companyRepository.findById(employeeDto.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No company with id: %s", employeeDto.getCompanyId())));
        Employee employee = employeeRepository.save(employeeDto.of(company));
        employeeDto.setId(employee.getId());
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No employee with this id: %s", id)));
        if (!Objects.equals(employee.getCompany().getId(), employeeDto.getCompanyId())) {
            Company company = companyRepository.findById(employeeDto.getCompanyId())
                    .orElseThrow(() -> new NoSuchElementException(
                            String.format("No company with id: %s", employeeDto.getCompanyId())));
            employee.setCompany(company);
        }
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.existsById(id);
    }
}
