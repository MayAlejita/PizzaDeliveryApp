package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse3 {
    private Long orderLineId;
    private Integer quantity;
    private Double price;
    private LocalDateTime deliveryDate;
    private PizzaResponse pizza;
}
