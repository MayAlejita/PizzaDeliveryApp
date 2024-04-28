package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.PizzaDeliveryAppApplicationTests;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.CustomerResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.CustomerNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.CustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImplTest extends PizzaDeliveryAppApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() {
        logger.info("CustomerServiceImplTest started");
    }

    @After
    public void tearDown() {
        logger.info("CustomerServiceImplTest completed");
    }

    @Test
    public void testGetCustomerById() throws CustomerNotFoundException {
        Integer customerId = 2;
        CustomerResponse customer = customerService.getCustomerById(customerId);

        Assert.assertNotNull("Failure: expected customer to be not null", customer);
        Assert.assertEquals("Failure: expected customerId to match", customerId, customer.customerId());
        logger.info("Customer data: " + customer);
    }

    @Test
    public void testGetCustomerByIdForInvalidId(){
        Integer customerId = Integer.MAX_VALUE;
        CustomerNotFoundException exception = Assert.assertThrows(CustomerNotFoundException.class, () -> {
            customerService.getCustomerById(customerId);
        });
        Assert.assertEquals(String.format("Error: Customer with Id, %d, is not found", customerId), exception.getMessage());
    }
}
