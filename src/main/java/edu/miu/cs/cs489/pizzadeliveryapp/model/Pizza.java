package edu.miu.cs.cs489.pizzadeliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pizzaId;
    @Column(nullable = false)
    @NotBlank(message = "Pizza name is required and cannot be null or empty string or blank spaces")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Pizza type is required and cannot be null or empty string or blank spaces")
    private String type;
    @Column(nullable = false)
    @NotBlank(message = "Pizza size is required and cannot be null or empty string or blank spaces")
    private String size;
    @Column(nullable = false)
    @NotNull(message = "Pizza price is required and cannot be null")
    private Double price;
    private String additionalDetails;
    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    private List<OrderLine> orderLines;

    public Pizza(Integer pizzaId, String name, String type, String size, Double price, String additionalDetails) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.additionalDetails = additionalDetails;
    }
}
