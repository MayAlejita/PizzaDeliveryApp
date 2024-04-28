package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderLineRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.*;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Customer;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Order;
import edu.miu.cs.cs489.pizzadeliveryapp.model.OrderLine;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Pizza;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.CustomerRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.OrderLineRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.OrderRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderLineRepository orderLineRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public List<OrderResponse2> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(o -> new OrderResponse2(
                        o.getOrderNumber(), o.getOrderDate(),
                        o.getStatus(), o.getTotalPrice(),
                        o.getOrderLines() != null ? o.getOrderLines()
                                .stream()
                                .map(or -> new OrderLineResponse(
                                        or.getOrderLineId(), or.getQuantity(),
                                        or.getPrice(), or.getDeliveryDate(),
                                        or.getPizza() != null ? new PizzaResponse(
                                                or.getPizza().getPizzaId(),
                                                or.getPizza().getName(),
                                                or.getPizza().getType(),
                                                or.getPizza().getSize(),
                                                or.getPizza().getPrice(),
                                                or.getPizza().getAdditionalDetails()
                                        ) : null
                                ))
                                .toList() : null
                ))
                .toList();
    }

    @Override
    public List<OrderLineRequest> setAllOrdersByOrderLine(OrderLineRequest orderLineRequest) {
        return List.of(orderLineRequest);
    }

    @Override
    public OrderResponse2 getOrderById(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new OrderNotFoundException(String.format("Error: Order with Id, %d, is not found", orderId));
        }
        return new OrderResponse2(
                order.getOrderNumber(), order.getOrderDate(),
                order.getStatus(), order.getTotalPrice(),
                order.getOrderLines() != null ? order.getOrderLines()
                        .stream()
                        .map(or -> new OrderLineResponse(
                                or.getOrderLineId(), or.getQuantity(),
                                or.getPrice(), or.getDeliveryDate(),
                                or.getPizza() != null ? new PizzaResponse(
                                        or.getPizza().getPizzaId(),
                                        or.getPizza().getName(),
                                        or.getPizza().getType(),
                                        or.getPizza().getSize(),
                                        or.getPizza().getPrice(),
                                        or.getPizza().getAdditionalDetails()
                                ) : null
                        ))
                        .toList() : null
        );
    }

    @Override
    public List<OrderResponse2> getOrderByCustomerId(Integer customerId) throws CustomerNotFoundException{
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            throw new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found", customerId));
        }
        return orderRepository.findAllByCustomer_CustomerId(customerId)
                .stream()
                .map(o -> new OrderResponse2(
                        o.getOrderNumber(), o.getOrderDate(),
                        o.getStatus(), o.getTotalPrice(),
                        o.getOrderLines() != null ? o.getOrderLines()
                                .stream()
                                .map(or -> new OrderLineResponse(
                                        or.getOrderLineId(), or.getQuantity(),
                                        or.getPrice(), or.getDeliveryDate(),
                                        or.getPizza() != null ? new PizzaResponse(
                                                or.getPizza().getPizzaId(),
                                                or.getPizza().getName(),
                                                or.getPizza().getType(),
                                                or.getPizza().getSize(),
                                                or.getPizza().getPrice(),
                                                or.getPizza().getAdditionalDetails()
                                        ) : null
                                ))
                                .toList() : null
                ))
                .toList();
    }

    @Override
    public OrderResponse2 addOrderByCustomerId(Integer customerId, OrderRequest orderRequest) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            throw new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found", customerId));
        }

        Order order = new Order(null, orderRequest.orderDate(),
                orderRequest.status(), orderRequest.totalPrice(),
                customer, null);

        Order newOrder = orderRepository.save(order);

        List<OrderLine> orderLines = orderRequest.orderLines().stream()
                .map(ol -> new OrderLine(null, ol.quantity(),
                        ol.price(), ol.deliveryDate(), newOrder, new Pizza(ol.pizza().pizzaId(),
                        ol.pizza().name(), ol.pizza().type(), ol.pizza().size(),
                        ol.pizza().price(), ol.pizza().additionalDetails()))
                ).toList();

        List<OrderLine> orderL = orderLineRepository.saveAll(orderLines);

        return new OrderResponse2(
                newOrder.getOrderNumber(), newOrder.getOrderDate(),
                newOrder.getStatus(), newOrder.getTotalPrice(),
                orderL.stream()
                        .map(or -> new OrderLineResponse(
                                or.getOrderLineId(), or.getQuantity(),
                                or.getPrice(), or.getDeliveryDate(),
                                or.getPizza() != null ? new PizzaResponse(
                                        or.getPizza().getPizzaId(),
                                        or.getPizza().getName(),
                                        or.getPizza().getType(),
                                        or.getPizza().getSize(),
                                        or.getPizza().getPrice(),
                                        or.getPizza().getAdditionalDetails()
                                ) : null
                        ))
                        .toList()
        );
    }

    @Override
    public OrderResponse2 updateOrderById(Long orderId, OrderRequest orderRequest) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new OrderNotFoundException(String.format("Error: Order with Id, %d, is not found", orderId));
        }
        order.setOrderDate(orderRequest.orderDate());
        order.setStatus(orderRequest.status());
        order.setTotalPrice(orderRequest.totalPrice());
        Order updatedOrder = orderRepository.save(order);

        return new OrderResponse2(
                updatedOrder.getOrderNumber(), updatedOrder.getOrderDate(),
                updatedOrder.getStatus(), updatedOrder.getTotalPrice(),
                updatedOrder.getOrderLines() != null ? updatedOrder.getOrderLines()
                        .stream()
                        .map(or -> new OrderLineResponse(
                                or.getOrderLineId(), or.getQuantity(),
                                or.getPrice(), or.getDeliveryDate(),
                                or.getPizza() != null ? new PizzaResponse(
                                        or.getPizza().getPizzaId(),
                                        or.getPizza().getName(),
                                        or.getPizza().getType(),
                                        or.getPizza().getSize(),
                                        or.getPizza().getPrice(),
                                        or.getPizza().getAdditionalDetails()
                                ) : null
                        ))
                        .toList() : null
        );
    }

    @Override
    public OrderResponse2 updateOrderStatusById(Long orderId, OrderRequest orderRequest) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new OrderNotFoundException(String.format("Error: Order with Id, %d, is not found", orderId));
        }
        if(!order.getStatus().equals("REQUESTED")) {
            throw new OrderNotFoundException(String.format("Error: Order status with Id, %d, is not availability to update", orderId));
        }

        order.setStatus(orderRequest.status());
        Order updatedOrder = orderRepository.save(order);

        return new OrderResponse2(
                updatedOrder.getOrderNumber(), updatedOrder.getOrderDate(),
                updatedOrder.getStatus(), updatedOrder.getTotalPrice(),
                updatedOrder.getOrderLines() != null ? updatedOrder.getOrderLines()
                        .stream()
                        .map(or -> new OrderLineResponse(
                                or.getOrderLineId(), or.getQuantity(),
                                or.getPrice(), or.getDeliveryDate(),
                                or.getPizza() != null ? new PizzaResponse(
                                        or.getPizza().getPizzaId(),
                                        or.getPizza().getName(),
                                        or.getPizza().getType(),
                                        or.getPizza().getSize(),
                                        or.getPizza().getPrice(),
                                        or.getPizza().getAdditionalDetails()
                                ) : null
                        ))
                        .toList() : null
        );
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<PizzaOrderResponse> getOrderByDate(LocalDate startDate, LocalDate endDate) {
        List<Order> orders;
        if(startDate == null && endDate == null) {
            orders = orderRepository.findAllByOrderDateIsBetween(startDate, endDate);
        } else{
            orders = orderRepository.findAll();
        }
        return orders.stream()
                .flatMap(o ->
                    o.getOrderLines().stream()
                            .map(p-> {
                                return new PizzaOrderResponse(
                                        p.getPizza().getPizzaId(),
                                        p.getPizza().getName(),
                                        p.getQuantity(),
                                        p.getOrder().getStatus(),
                                        p.getOrder().getOrderNumber()
                                );
                            })
                )
                .toList();
    }
}
