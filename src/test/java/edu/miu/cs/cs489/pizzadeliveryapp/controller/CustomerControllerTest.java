package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.AddressResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.CustomerResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Before
    public void setUp() {
        List<CustomerResponse> customerList = List.of(new CustomerResponse(1, "John", "Doe",
                "546-098-0000", "john@email.com", LocalDate.of(1990,9,9),
                new AddressResponse(1,"1000 Nth","Fairfield","IA","52557"),
                List.of(new OrderResponse(1L,
                        LocalDateTime.of(2024,4,25,10,0),"REQUESTED",13.50))));
        Mockito.when(customerService.getAllCustomer()).thenReturn(customerList);
    }

    @Test
    public void testListCustomer() throws Exception {
        mockMvc.perform(get("/pizzamgm/api/v1/customers"))
                .andExpect(status().isOk());
    }
}
