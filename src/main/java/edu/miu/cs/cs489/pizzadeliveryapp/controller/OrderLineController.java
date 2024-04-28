package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderLineRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderLineResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderLineNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.OrderLineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzamgm/api/v1/orderlines")
public class OrderLineController {
    private OrderLineService orderLineService;
    public OrderLineController(OrderLineService orderLineService) {this.orderLineService = orderLineService;}

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLineByOrderId(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderLineService.getAllOrderLinesByOrderId(orderId));
    }

    @PostMapping("/new/{orderId}")
    public ResponseEntity<OrderLineResponse> addNewOrderLine(@PathVariable Long orderId, @RequestBody @Valid OrderLineRequest orderlineRequest) throws OrderNotFoundException {
        return new ResponseEntity<>(orderLineService.addOrderLineByOrderId(orderId, orderlineRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{orderLineId}")
    public ResponseEntity<OrderLineResponse> updateOrderLineById(@PathVariable Long orderLineId, @RequestBody @Valid OrderLineRequest orderlineRequest) throws OrderLineNotFoundException {
        return new ResponseEntity<>(orderLineService.updateOrderLineById(orderLineId, orderlineRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderLineId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderLineId){
        orderLineService.deleteOrderLineById(orderLineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
