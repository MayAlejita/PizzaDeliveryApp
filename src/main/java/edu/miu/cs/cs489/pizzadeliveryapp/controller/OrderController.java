package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderLineRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.OrderRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.PizzaRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.PizzaRequest2;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaOrderResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.OrderNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.OrderService;
import edu.miu.cs.cs489.pizzadeliveryapp.service.PizzaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pizzamgm/api/v1/orders")
public class OrderController {

    private OrderService orderService;
    private PizzaService pizzaService;
    public OrderController(OrderService orderService, PizzaService pizzaService) {
        this.orderService = orderService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ModelAndView listOrders(){
        var modelAndView = new ModelAndView();
        var orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("secured/sysadmin/orders");
        return modelAndView;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse2> getOrderById(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse2>> getOrderByCustomerId(@PathVariable Integer customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
    }

    @GetMapping(value = {"/new"})
    public ModelAndView displayNewOrderForm(Model model) {
        var modelAndView = new ModelAndView();
        var newOrder = new OrderRequest(null, LocalDateTime.now(), null, null,
                List.of(new OrderLineRequest(null,null,null, null,
                        new PizzaRequest2(null,null,null,null,null,null))));
        model.addAttribute("order", newOrder);
        modelAndView.setViewName("secured/sysadmin/order/new");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addNewOrder(@Valid @ModelAttribute OrderRequest order) throws CustomerNotFoundException {
        var modelAndView = new ModelAndView();
        orderService.addOrderByCustomerId(1, order);
        var orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("secured/sysadmin/orders");
        return modelAndView;
    }

    @RequestMapping(value="/add", params={"addRow"})
    public String addRow(final OrderLineRequest orderLine, final BindingResult bindingResult) {
        System.out.println(orderLine);
//        order.orderLines().add(new OrderLineRequest(null,null, null, null));
        return "orderline";
    }

    @RequestMapping(value="/orderline", params={"removeRow"})
    public String removeRow(
            final OrderRequest seedStarter, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.orderLines().remove(rowId.intValue());
        return "orderline";
    }


    @PostMapping("/new/{customerId}")
    public ResponseEntity<OrderResponse2> addNewOrder(@PathVariable Integer customerId, @RequestBody @Valid OrderRequest orderRequest) throws CustomerNotFoundException {
        return new ResponseEntity<>(orderService.addOrderByCustomerId(customerId, orderRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = {"/edit/{orderId}"})
    public ModelAndView displayEditOrderForm(@PathVariable Long orderId) throws OrderNotFoundException {
        var modelAndView = new ModelAndView();
        var order = orderService.getOrderById(orderId);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("secured/sysadmin/order/edit");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateOrderById(@Valid @ModelAttribute  OrderRequest order) throws OrderNotFoundException {
        var modelAndView = new ModelAndView();
//        OrderRequest orderRequest = new OrderRequest(order.orderNumber(), order.orderDate(), order.status(),
//                order.totalPrice(), order.orderLines().stream().map(
//                        ol-> new OrderLineRequest(ol.orderLineId(), ol.quantity(),
//                                ol.price(), ol.deliveryDate(), new PizzaRequest2(ol.pizza().pizzaId(),
//                                ol.pizza().name(), ol.pizza().type(), ol.pizza().size(), ol.pizza().price(),
//                                ol.pizza().additionalDetails())
//                        )
//        ).toList());
        orderService.updateOrderById(order.orderNumber(), order);
        var orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("secured/sysadmin/orders");
        return modelAndView;
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderResponse2> updateOrderById(@PathVariable Long orderId, @RequestBody @Valid OrderRequest orderRequest) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.updateOrderById(orderId, orderRequest), HttpStatus.OK);
    }

    @PutMapping("/update/status/{orderId}")
    public ResponseEntity<OrderResponse2> updateOrderStatusById(@PathVariable Long orderId, @RequestBody @Valid OrderRequest orderRequest) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.updateOrderStatusById(orderId, orderRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/delete/{pizzaId}")
    public ModelAndView deleteOrderByIdView(@PathVariable Long orderId){
        var modelAndView = new ModelAndView();
        orderService.deleteOrderById(orderId);
        var orders = orderService.getAllOrders();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("secured/sysadmin/orders");
        return modelAndView;
    }

    @GetMapping("/pizzas")
    public ResponseEntity<List<PizzaOrderResponse>> getPizzasByDate(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate) {
        return ResponseEntity.ok(orderService.getOrderByDate(startDate, endDate));
    }

    @ModelAttribute("allPizzas")
    public List<PizzaResponse2> getPizzas() {
        return pizzaService.getAllPizzas();
    }
}
