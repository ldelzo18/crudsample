package com.learning.crudsample.domain.impl;

import com.learning.crudsample.domain.EmployeeService;
import com.learning.crudsample.infrastructure.dao.EmployeeRepository;
import com.learning.crudsample.infrastructure.entity.Employee;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import com.learning.crudsample.presentation.dto.EmployeeRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
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
                .map(emp -> new EmployeeDTO(
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail(),
                        emp.getEmployeeCode()))
                .toList();
    }

    @Override
    public EmployeeDTO findByEmployeeCode(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode)
                .map(emp -> new EmployeeDTO(
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail(),
                        emp.getEmployeeCode()))
                .orElse(null);
    }

    @Override
    @Transactional
    public EmployeeDTO create(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = Employee.builder()
                .firstName(employeeRequestDTO.firstName())
                .lastName(employeeRequestDTO.lastName())
                .email(employeeRequestDTO.email())
                .build();

        Employee saved = employeeRepository.save(employee);

        String code = String.format("EMP-%04d", saved.getId());
        saved.setEmployeeCode(code);
        employeeRepository.save(saved);

        return new EmployeeDTO(
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail(),
                saved.getEmployeeCode()
        );
    }

}
