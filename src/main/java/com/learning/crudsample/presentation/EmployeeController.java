package com.learning.crudsample.presentation;

import com.learning.crudsample.application.service.EmployeeService;
import com.learning.crudsample.presentation.dto.EmployeeDTO;
import com.learning.crudsample.presentation.dto.EmployeeRequestDTO;
import com.learning.crudsample.presentation.dto.EmployeeUpdateRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{employeeCode}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeCode") String employeeCode) {
        employeeService.deleteByEmployeeCode(employeeCode);
        return ResponseEntity.ok().body("Employee deleted successfully");
    }

    @PostMapping("/register")
    public EmployeeDTO registerEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.create(employeeRequestDTO);
    }

    @PutMapping("/update")
    public EmployeeDTO updateEmployee(@Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        return employeeService.update(employeeUpdateRequestDTO);
    }
}
