package com.learning.crudsample.application.service;

import com.learning.crudsample.infrastructure.repository.EmployeeRepository;
import com.learning.crudsample.infrastructure.entity.Employee;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import com.learning.crudsample.presentation.dto.EmployeeRequestDTO;
import com.learning.crudsample.presentation.dto.EmployeeUpdateRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void deleteByEmployeeCode(String employeeCode) {
        if (!employeeRepository.existsByEmployeeCode(employeeCode)) {
            throw new EntityNotFoundException("Employee with code " + employeeCode + " not found!");
        }
        employeeRepository.deleteByEmployeeCode(employeeCode);
    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        Optional<Employee> optEmployee = employeeRepository.findByEmployeeCode(employeeUpdateRequestDTO.employeeCode());

        if (optEmployee.isEmpty()) {
            throw new EntityNotFoundException("Employee with code " + employeeUpdateRequestDTO.employeeCode() + " not found!");
        }

        Employee employee = optEmployee.get();
        employee.setFirstName(employeeUpdateRequestDTO.firstName());
        employee.setLastName(employeeUpdateRequestDTO.lastName());
        employee.setEmail(employeeUpdateRequestDTO.email());

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(
                updatedEmployee.getFirstName(),
                updatedEmployee.getLastName(),
                updatedEmployee.getEmail(),
                updatedEmployee.getEmployeeCode(
                ));
    }

}
