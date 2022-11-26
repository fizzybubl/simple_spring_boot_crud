package com.example.simple_crud.repositories;

import com.example.simple_crud.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
