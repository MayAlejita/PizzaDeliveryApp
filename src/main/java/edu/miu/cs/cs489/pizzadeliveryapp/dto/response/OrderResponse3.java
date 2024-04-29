package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse3 {
    private Long orderNumber;
    private LocalDateTime orderDate;
    private String status;
    private Double totalPrice;
    private List<OrderLineResponse3> orderLines;
}
