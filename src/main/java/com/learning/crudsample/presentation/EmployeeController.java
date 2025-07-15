package com.learning.crudsample.presentation;

import com.learning.crudsample.domain.EmployeeService;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import com.learning.crudsample.presentation.dto.EmployeeRequestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeCode}")
    public EmployeeDTO getEmployeeByCode(@PathVariable("employeeCode") String employeeCode) {
        return employeeService.findByEmployeeCode(employeeCode);
    }

    @PostMapping("/register")
    public EmployeeDTO registerEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.create(employeeRequestDTO);
    }
}
