package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

public record UserAuthResponse(
        String jwtToken,
        String firstName,
        String lastName
) {
}
