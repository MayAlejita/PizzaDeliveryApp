package edu.miu.cs.cs489.pizzadeliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;
    @Column(nullable = false)
    @NotNull(message = "Order orderDate is required and cannot be null")
    private LocalDateTime orderDate;
    @Column(nullable = false)
    @NotBlank(message = "Order status is required and cannot be null or empty string or blank spaces")
    private String status;
    @Column(nullable = false)
    @NotNull(message = "Order totalPrice is required and cannot be null")
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;
}
