package edu.miu.cs.cs489.pizzadeliveryapp.repository;

import edu.miu.cs.cs489.pizzadeliveryapp.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrder_OrderNumber(Long orderId);
}
