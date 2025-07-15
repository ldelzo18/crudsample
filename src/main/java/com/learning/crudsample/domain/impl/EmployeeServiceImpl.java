package com.learning.crudsample.domain.impl;

import com.learning.crudsample.domain.EmployeeService;
import com.learning.crudsample.infrastructure.dao.EmployeeRepository;
import com.learning.crudsample.infrastructure.entity.Employee;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(emp-> new EmployeeDTO(
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail()))
                .toList();
    }
}
