package com.learning.crudsample.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record EmployeeRequestDTO(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Email is required")
        String lastName,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        String email
) {
}
