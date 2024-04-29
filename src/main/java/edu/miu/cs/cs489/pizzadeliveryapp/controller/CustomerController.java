package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.*;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.CustomerResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pizzamgm/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {this.customerService = customerService;}

    @GetMapping("list")
    public ResponseEntity<List<CustomerResponse>> listCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping
    public ModelAndView listCustomersView(){
        var modelAndView = new ModelAndView();
        var customers = customerService.getAllCustomer();
        modelAndView.addObject("customers", customers);
        modelAndView.setViewName("secured/sysadmin/customers");
        return modelAndView;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping("/new")
    public ResponseEntity<CustomerResponse> addNewCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = {"/new"})
    public ModelAndView displayNewCustomerForm(Model model) {
        var modelAndView = new ModelAndView();
        var newCustomer = new CustomerRequest(null,null,null,null,null,null,
                new AddressRequest(null,null,null,null,null));
//        var newAddress = new AddressRequest(null,null,null,null,null);
//        model.addAttribute("address", newAddress);
        model.addAttribute("customer", newCustomer);
        modelAndView.setViewName("secured/sysadmin/customer/new");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addNewOCustomer(@Valid @ModelAttribute CustomerRequest customerRequest){
        var modelAndView = new ModelAndView();
        System.out.println(customerRequest);
        customerService.addCustomer(customerRequest);
        var customers = customerService.getAllCustomer();
        modelAndView.addObject("customers", customers);
        modelAndView.setViewName("secured/sysadmin/customers");
        return modelAndView;
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomerById(@PathVariable Integer customerId, @RequestBody @Valid CustomerRequest customerRequest) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.updateCustomerById(customerId, customerRequest), HttpStatus.OK);
    }

    @GetMapping(value = {"/edit/{customerId}"})
    public ModelAndView displayEditCustomerForm(@PathVariable Integer customerId) throws CustomerNotFoundException {
        var modelAndView = new ModelAndView();
        var customer = customerService.getCustomerById(customerId);
        var newAddress = customer.address();
        modelAndView.addObject("address", newAddress);
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("secured/sysadmin/customer/edit");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCustomerById(@Valid @ModelAttribute CustomerRequest customerRequest) throws CustomerNotFoundException {
        var modelAndView = new ModelAndView();
        customerService.updateCustomerById(customerRequest.getCustomerId(), customerRequest);
        var customers = customerService.getAllCustomer();
        modelAndView.addObject("customers", customers);
        modelAndView.setViewName("secured/sysadmin/customers");
        return modelAndView;
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer customerId){
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/delete/{customerId}")
    public ModelAndView deleteCustomerViewById(@PathVariable Integer customerId){
        var modelAndView = new ModelAndView();
        customerService.deleteCustomerById(customerId);
        var customers = customerService.getAllCustomer();
        modelAndView.addObject("customers", customers);
        modelAndView.setViewName("secured/sysadmin/customers");
        return modelAndView;
    }
}
