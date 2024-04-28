package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.PizzaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @Before
    public void setUp() throws PizzaNotFoundException {
        PizzaResponse2 pizzaResponse = new PizzaResponse2(2, "supreme","SUP", "small", 15.0,
                "", null);
        Mockito.when(pizzaService.getPizzaById(2)).thenReturn(pizzaResponse);
    }

    @Test
    public void testListCustomer() throws Exception {
        mockMvc.perform(get("/pizzamgm/api/v1/pizzas/{pizzaId}"))
                .andExpect(status().isOk());

//                        .andExpect(view().name("book/list"))
//                .andExpect(model().attributeExists("books"))
//                .andExpect(model().attribute("books", iterableWithSize(2)));
//                        .andExpect(view().name("home/index"))
//                .andExpect(content().string(
//                        containsString("<h3>Welcome to the eLibrary Books Management System (eLibBMS)</h3>")));
    }
}
