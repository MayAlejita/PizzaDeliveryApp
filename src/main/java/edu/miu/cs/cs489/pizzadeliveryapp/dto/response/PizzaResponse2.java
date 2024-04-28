package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import java.util.List;

public record PizzaResponse2 (
        Integer pizzaId,
        String name,
        String type,
        String size,
        Double price,
        String additionalDetails,
        List<OrderLineResponse2> orderLines
){
}
