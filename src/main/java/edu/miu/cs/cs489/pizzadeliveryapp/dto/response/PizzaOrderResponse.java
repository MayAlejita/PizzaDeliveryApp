package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

public record PizzaOrderResponse(
        Integer pizzaId,
        String pizzaName,
        Integer quantity,
        String status,
        Long orderNumber
) {
}
