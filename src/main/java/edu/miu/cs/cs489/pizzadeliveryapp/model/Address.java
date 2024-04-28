package edu.miu.cs.cs489.pizzadeliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    @Column(nullable = false)
    @NotBlank(message = "Address street is required and cannot be null or empty string or blank spaces")
    private String street;
    private String city;
    @Column(nullable = false)
    @NotBlank(message = "Address state is required and cannot be null or empty string or blank spaces")
    private String state;
    private String zipCode;
    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Address(Integer addressId, String street, String city, String state, String zipCode) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
