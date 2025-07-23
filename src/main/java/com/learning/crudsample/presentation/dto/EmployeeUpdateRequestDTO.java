package com.learning.crudsample.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeUpdateRequestDTO(
        @NotBlank(message = "employeeCode is required")
        String employeeCode,

        @NotBlank(message = "firstName is required")
        String firstName,

        @NotBlank(message = "lastName is required")
        String lastName,

        @Email(message = "email should be valid")
        @NotBlank(message = "email is required")
        String email
) {
}
