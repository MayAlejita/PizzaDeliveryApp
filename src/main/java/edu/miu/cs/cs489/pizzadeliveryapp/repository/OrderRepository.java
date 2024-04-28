package edu.miu.cs.cs489.pizzadeliveryapp.repository;

import edu.miu.cs.cs489.pizzadeliveryapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer_CustomerId(Integer customerId);
    List<Order> findAllByOrderDateIsBetween(LocalDate startDate, LocalDate endDate);
}
