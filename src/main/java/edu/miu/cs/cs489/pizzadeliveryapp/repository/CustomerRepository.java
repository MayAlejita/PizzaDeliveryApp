package edu.miu.cs.cs489.pizzadeliveryapp.repository;

import edu.miu.cs.cs489.pizzadeliveryapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
