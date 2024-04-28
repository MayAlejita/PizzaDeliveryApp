package edu.miu.cs.cs489.pizzadeliveryapp.service;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.CustomerRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.CustomerResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomer();
    CustomerResponse getCustomerById(Integer customerId)  throws CustomerNotFoundException;
    CustomerResponse addCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomerById(Integer customerId, CustomerRequest customerRequest) throws CustomerNotFoundException;
    void deleteCustomerById(Integer customerId);
}
