package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import java.time.LocalDate;
import java.util.List;

public record CustomerResponse(
        Integer customerId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        LocalDate birthDate,
        AddressResponse address,
        List<OrderResponse> orders
) {
}
