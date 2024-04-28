package edu.miu.cs.cs489.pizzadeliveryapp.service;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.PizzaRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;

import java.util.List;

public interface PizzaService {
    List<PizzaResponse2> getAllPizzas();
    PizzaResponse2 getPizzaById(Integer pizzaId)  throws PizzaNotFoundException;
    PizzaResponse2 addPizza(PizzaRequest pizzaRequest);
    PizzaResponse2 updatePizzaById(Integer pizzaId, PizzaRequest pizzaRequest) throws PizzaNotFoundException;
    void deletePizzaById(Integer pizzaId);
}
