package com.example.disertatie_v4.repositories;

import com.example.disertatie_v4.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
