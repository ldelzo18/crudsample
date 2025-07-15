package com.learning.crudsample.infrastructure.dao;

import com.learning.crudsample.infrastructure.entity.Employee;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeCode(String employeeCode);
}
