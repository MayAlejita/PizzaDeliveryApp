package edu.miu.cs.cs489.pizzadeliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;
    @Column(nullable = false)
    @NotNull(message = "OrderLine quantity is required and cannot be null")
    private Integer quantity;
    @Column(nullable = false)
    @NotNull(message = "OrderLine price is required and cannot be null")
    private Double price;
    @Column(nullable = false)
    @NotNull(message = "OrderLine deliveryDate is required and cannot be null")
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;
}
