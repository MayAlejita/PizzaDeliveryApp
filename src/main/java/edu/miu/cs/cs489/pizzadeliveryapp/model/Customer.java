package edu.miu.cs.cs489.pizzadeliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @Column(nullable = false)
    @NotBlank(message = "Customer firstName is required and cannot be null or empty string or blank spaces")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Customer lastName is required and cannot be null or empty string or blank spaces")
    private String lastName;
    @Column(nullable = false)
    @NotBlank(message = "Customer phoneNumber is required and cannot be null or empty string or blank spaces")
    private String phoneNumber;
    private String email;
    @Column(nullable = false)
    @NotNull(message = "Customer birthDate is required and cannot be null")
    private LocalDate birthDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Customer(Integer customerId, String firstName, String lastName, String phoneNumber, String email, LocalDate birthDate, Address address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }
}
