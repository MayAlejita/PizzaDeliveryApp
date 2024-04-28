package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderLineRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderLineResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderLineNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Order;
import edu.miu.cs.cs489.pizzadeliveryapp.model.OrderLine;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Pizza;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.OrderLineRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.OrderRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.service.OrderLineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private OrderLineRepository orderLineRepository;
    private OrderRepository orderRepository;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository, OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderLineResponse> getAllOrderLinesByOrderId(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new OrderNotFoundException(String.format("Error: Order with Id, %d, is not found", orderId));
        }
        return orderLineRepository.findAllByOrder_OrderNumber(orderId)
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
                .toList();
    }

    @Override
    public OrderLineResponse addOrderLineByOrderId(Long orderId, OrderLineRequest orderLineRequest) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new OrderNotFoundException(String.format("Error: Order with Id, %d, is not found", orderId));
        }
        OrderLine orderLine = new OrderLine(null,
                orderLineRequest.quantity(), orderLineRequest.price(),
                orderLineRequest.deliveryDate(), order, new Pizza(orderLineRequest.pizza().pizzaId(),
                orderLineRequest.pizza().name(), orderLineRequest.pizza().type(), orderLineRequest.pizza().size(),
                orderLineRequest.pizza().price(), orderLineRequest.pizza().additionalDetails()));
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return new OrderLineResponse(
                savedOrderLine.getOrderLineId(), savedOrderLine.getQuantity(),
                savedOrderLine.getPrice(), savedOrderLine.getDeliveryDate(),
                savedOrderLine.getPizza() != null ? new PizzaResponse(
                        savedOrderLine.getPizza().getPizzaId(),
                        savedOrderLine.getPizza().getName(),
                        savedOrderLine.getPizza().getType(),
                        savedOrderLine.getPizza().getSize(),
                        savedOrderLine.getPizza().getPrice(),
                        savedOrderLine.getPizza().getAdditionalDetails()
                ) : null
        );
    }

    @Override
    public OrderLineResponse updateOrderLineById(Long orderLineId, OrderLineRequest orderLineRequest) throws OrderLineNotFoundException {
        OrderLine orderLine = orderLineRepository.findById(orderLineId).orElse(null);
        if(orderLine == null) {
            throw new OrderLineNotFoundException(String.format("Error: OrderLine with Id, %d, is not found", orderLineId));
        }
        orderLine.setQuantity(orderLineRequest.quantity());
        orderLine.setPrice(orderLineRequest.price());
        orderLine.setDeliveryDate(orderLineRequest.deliveryDate());

        return new OrderLineResponse(
                orderLine.getOrderLineId(), orderLine.getQuantity(),
                orderLine.getPrice(), orderLine.getDeliveryDate(),
                orderLine.getPizza() != null ? new PizzaResponse(
                        orderLine.getPizza().getPizzaId(),
                        orderLine.getPizza().getName(),
                        orderLine.getPizza().getType(),
                        orderLine.getPizza().getSize(),
                        orderLine.getPizza().getPrice(),
                        orderLine.getPizza().getAdditionalDetails()
                ) : null
        );
    }

    @Override
    public void deleteOrderLineById(Long orderLineId) {
        orderLineRepository.deleteById(orderLineId);
    }
}
