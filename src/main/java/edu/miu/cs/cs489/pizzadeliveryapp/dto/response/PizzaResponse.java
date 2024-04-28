package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

public record PizzaResponse(
        Integer pizzaId,
        String name,
        String type,
        String size,
        Double price,
        String additionalDetails
) {
}
