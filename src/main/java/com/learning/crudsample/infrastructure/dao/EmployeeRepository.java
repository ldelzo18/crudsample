package com.learning.crudsample.infrastructure.dao;

import com.learning.crudsample.infrastructure.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
