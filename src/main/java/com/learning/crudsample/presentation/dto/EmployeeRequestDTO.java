package com.learning.crudsample.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record EmployeeRequestDTO(
        @NotBlank(message = "firstName is required")
        String firstName,

        @NotBlank(message = "lastName is required")
        String lastName,

        @Email(message = "email should be valid")
        @NotBlank(message = "email is required")
        String email
) {
}
