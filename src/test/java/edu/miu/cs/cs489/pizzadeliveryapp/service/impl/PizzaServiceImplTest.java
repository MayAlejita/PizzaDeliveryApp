package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.PizzaDeliveryAppApplicationTests;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.PizzaService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PizzaServiceImplTest extends PizzaDeliveryAppApplicationTests {

    @Autowired
    private PizzaService pizzaService;

    @Before
    public void setUp() {
        logger.info("PizzaServiceImplTest started");
    }

    @After
    public void tearDown() {
        logger.info("PizzaServiceImplTest completed");
    }

    @Test
    public void testGetPizzaById() throws PizzaNotFoundException {
        Integer pizzaId = 1;
        PizzaResponse2 actual = pizzaService.getPizzaById(pizzaId);

        Assert.assertNotNull("Failure: expected pizza to be not null", actual);
        Assert.assertEquals("Failure: expected pizzaId to match", pizzaId, actual.pizzaId());
        logger.info("Pizza data: " + actual);
    }

    @Test
    public void testGetPizzaByIdWithInvalidId() {
        Integer pizzaId = Integer.MAX_VALUE;
        PizzaNotFoundException exception = Assert.assertThrows(PizzaNotFoundException.class, () -> {
            pizzaService.getPizzaById(pizzaId);
        });

        Assert.assertEquals(String.format("Pizza with id %d not found", pizzaId), exception.getMessage());
    }
}
