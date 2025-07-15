package com.learning.crudsample.domain;

import com.learning.crudsample.infrastructure.entity.Employee;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import com.learning.crudsample.presentation.dto.EmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    EmployeeDTO findByEmployeeCode(String employeeCode);

    EmployeeDTO create(EmployeeRequestDTO employeeRequestDTO);
}
