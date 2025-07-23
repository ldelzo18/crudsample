package com.learning.crudsample.infrastructure.repository;

import com.learning.crudsample.infrastructure.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    boolean existsByEmployeeCode(String employeeCode);

    Optional<Employee> deleteByEmployeeCode(String employeeCode);
}
