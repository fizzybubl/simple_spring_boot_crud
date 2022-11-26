package com.example.simple_crud.repositories;

import com.example.simple_crud.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
