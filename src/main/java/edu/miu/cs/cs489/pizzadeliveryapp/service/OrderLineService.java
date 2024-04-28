package edu.miu.cs.cs489.pizzadeliveryapp.service;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderLineRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderLineResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderLineNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderNotFoundException;

import java.util.List;

public interface OrderLineService {
    List<OrderLineResponse> getAllOrderLinesByOrderId(Long orderId) throws OrderNotFoundException;
    OrderLineResponse addOrderLineByOrderId(Long orderId, OrderLineRequest orderLineRequest) throws OrderNotFoundException;
    OrderLineResponse updateOrderLineById(Long orderLineId, OrderLineRequest orderLineRequest) throws OrderLineNotFoundException;
    void deleteOrderLineById(Long orderLineId);
}
