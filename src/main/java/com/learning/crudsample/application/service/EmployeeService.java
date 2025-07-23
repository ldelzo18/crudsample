package com.learning.crudsample.application.service;

import com.learning.crudsample.presentation.dto.EmployeeDTO;
import com.learning.crudsample.presentation.dto.EmployeeRequestDTO;
import com.learning.crudsample.presentation.dto.EmployeeUpdateRequestDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    EmployeeDTO findByEmployeeCode(String employeeCode);

    EmployeeDTO create(EmployeeRequestDTO employeeRequestDTO);

    void deleteByEmployeeCode(String employeeCode);

    EmployeeDTO update(EmployeeUpdateRequestDTO employeeUpdateRequestDTO);
}
