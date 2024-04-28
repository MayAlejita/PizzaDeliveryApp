package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        Integer addressId,
        @NotBlank(message = "street shouldn't be blank")
        String street,
        String city,
        @NotBlank(message = "state shouldn't be blank")
        String state,
        String zipCode
) {
}
