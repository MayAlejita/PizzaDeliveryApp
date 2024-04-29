package edu.miu.cs.cs489.pizzadeliveryapp.service;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderLineRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderResponse3;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaOrderResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<OrderResponse2> getAllOrders();
    List<OrderLineRequest> setAllOrdersByOrderLine(OrderLineRequest orderLineRequest);
    OrderResponse3 getOrderById(Long orderId) throws OrderNotFoundException;
    List<OrderResponse2> getOrderByCustomerId(Integer customerId)  throws CustomerNotFoundException;
    OrderResponse3 addOrderByCustomerId(Integer customerId, OrderRequest orderRequest) throws CustomerNotFoundException;
    OrderResponse2 updateOrderById(Long orderId, OrderRequest orderRequest) throws OrderNotFoundException;
    OrderResponse2 updateOrderStatusById(Long orderId, OrderRequest orderRequest) throws OrderNotFoundException;
    void deleteOrderById(Long orderId);
    List<PizzaOrderResponse> getOrderByDate(LocalDate startDate, LocalDate endDate);
    void addOrderLine(OrderRequest order);
}
