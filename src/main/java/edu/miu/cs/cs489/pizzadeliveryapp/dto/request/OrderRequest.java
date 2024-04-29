package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest{
        private Long orderNumber;
        @NotNull(message = "orderDate shouldn't be null")
        private LocalDateTime orderDate;
        @NotBlank(message = "status shouldn't be blank")
        private String status;
        @NotNull(message = "totalPrice shouldn't be null")
        private Double totalPrice;
        @NotNull(message = "orderLine shouldn't be null")
        private List<OrderLineRequest> orderLines;

}
