package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerRequest (
                Integer customerId,
                @NotBlank(message = "firstName shouldn't be blank")
                String firstName,
                @NotBlank(message = "lastName shouldn't be blank")
                String lastName,
                @NotBlank(message = "phoneNumber shouldn't be blank")
                String phoneNumber,
                String email,
                @NotNull(message = "birthDate shouldn't be null")
                LocalDate birthDate,
                @NotNull(message = "address shouldn't be null")
                AddressRequest address
) {
}
