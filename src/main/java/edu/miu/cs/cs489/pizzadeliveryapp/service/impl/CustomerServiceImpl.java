package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.CustomerRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.AddressResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.CustomerResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Address;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Customer;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.CustomerRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(c -> new CustomerResponse(
                        c.getCustomerId(), c.getFirstName(),
                        c.getLastName(), c.getPhoneNumber(),
                        c.getEmail(), c.getBirthDate(),
                        c.getAddress() != null ? new AddressResponse(
                                c.getAddress().getAddressId(),
                                c.getAddress().getStreet(),
                                c.getAddress().getCity(),
                                c.getAddress().getState(),
                                c.getAddress().getZipCode()
                        ) : null,
                        c.getOrders() != null ? c.getOrders().stream()
                                .map(o -> new OrderResponse(
                                        o.getOrderNumber(),
                                        o.getOrderDate(),
                                        o.getStatus(),
                                        o.getTotalPrice()
                                )).toList() : null
                ))
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Integer customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            throw new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found", customerId));
        }
        return new CustomerResponse(
                customer.getCustomerId(), customer.getFirstName(),
                customer.getLastName(), customer.getPhoneNumber(),
                customer.getEmail(), customer.getBirthDate(),
                customer.getAddress() != null ? new AddressResponse(
                        customer.getAddress().getAddressId(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getCity(),
                        customer.getAddress().getState(),
                        customer.getAddress().getZipCode()
                ) : null,
                customer.getOrders() != null ? customer.getOrders().stream()
                        .map(o -> new OrderResponse(
                                o.getOrderNumber(),
                                o.getOrderDate(),
                                o.getStatus(),
                                o.getTotalPrice()
                        )).toList() : null
        );
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer newCustomer = new Customer(null,
                customerRequest.firstName(), customerRequest.lastName(),
                customerRequest.phoneNumber(), customerRequest.email(),
                customerRequest.birthDate(),
                customerRequest.address() != null ? new Address(null,
                        customerRequest.address().street(),
                        customerRequest.address().city(),
                        customerRequest.address().street(),
                        customerRequest.address().zipCode()
                ) : null);
        Customer customer = customerRepository.save(newCustomer);
        return new CustomerResponse(
                customer.getCustomerId(), customer.getFirstName(),
                customer.getLastName(), customer.getPhoneNumber(),
                customer.getEmail(), customer.getBirthDate(),
                customer.getAddress() != null ? new AddressResponse(
                        customer.getAddress().getAddressId(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getCity(),
                        customer.getAddress().getState(),
                        customer.getAddress().getZipCode()
                ) : null,
                customer.getOrders() != null ? customer.getOrders().stream()
                        .map(o -> new OrderResponse(
                                o.getOrderNumber(),
                                o.getOrderDate(),
                                o.getStatus(),
                                o.getTotalPrice()
                        )).toList() : null
        );
    }

    @Override
    public CustomerResponse updateCustomerById(Integer customerId, CustomerRequest customerRequest) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            throw new CustomerNotFoundException(String.format("Error: Customer with Id, %d, is not found", customerId));
        }
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setPhoneNumber(customerRequest.phoneNumber());
        customer.setEmail(customerRequest.email());
        customer.setBirthDate(customerRequest.birthDate());
        if (customer.getAddress() != null && customerRequest.address() != null) {
            var address = customer.getAddress();
            address.setStreet(customerRequest.address().street());
            address.setCity(customerRequest.address().city());
            address.setState(customerRequest.address().state());
            address.setZipCode(customerRequest.address().zipCode());
        } else if (customer.getAddress() == null && customerRequest.address() != null) {
            var newAddress = new Address();
            newAddress.setStreet(customerRequest.address().street());
            newAddress.setCity(customerRequest.address().city());
            newAddress.setState(customerRequest.address().state());
            newAddress.setZipCode(customerRequest.address().zipCode());
            customer.setAddress(newAddress);
        } else {
            customer.setAddress(null);
        }
        Customer updateCustomer = customerRepository.save(customer);
        return new CustomerResponse(
                updateCustomer.getCustomerId(), updateCustomer.getFirstName(),
                updateCustomer.getLastName(), updateCustomer.getPhoneNumber(),
                updateCustomer.getEmail(), updateCustomer.getBirthDate(),
                updateCustomer.getAddress() != null ? new AddressResponse(
                        updateCustomer.getAddress().getAddressId(),
                        updateCustomer.getAddress().getStreet(),
                        updateCustomer.getAddress().getCity(),
                        updateCustomer.getAddress().getState(),
                        updateCustomer.getAddress().getZipCode()
                ) : null,
                updateCustomer.getOrders() != null ? updateCustomer.getOrders().stream()
                        .map(o -> new OrderResponse(
                                o.getOrderNumber(),
                                o.getOrderDate(),
                                o.getStatus(),
                                o.getTotalPrice()
                        )).toList() : null
        );
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
}
