package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerAddressRequest (
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
        Integer addressId,
        @NotBlank(message = "street shouldn't be blank")
        String street,
        String city,
        @NotBlank(message = "state shouldn't be blank")
        String state,
        String zipCode
){
}
