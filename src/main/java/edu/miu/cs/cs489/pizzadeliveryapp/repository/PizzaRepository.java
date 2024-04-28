package edu.miu.cs.cs489.pizzadeliveryapp.repository;

import edu.miu.cs.cs489.pizzadeliveryapp.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
}
