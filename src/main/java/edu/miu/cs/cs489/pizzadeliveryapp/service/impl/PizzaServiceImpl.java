package edu.miu.cs.cs489.pizzadeliveryapp.service.impl;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.PizzaRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.OrderLineResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.model.Pizza;
import edu.miu.cs.cs489.pizzadeliveryapp.repository.PizzaRepository;
import edu.miu.cs.cs489.pizzadeliveryapp.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    private PizzaRepository pizzaRepo;
    public PizzaServiceImpl(PizzaRepository pizzaRepo) {this.pizzaRepo = pizzaRepo;}

    @Override
    public List<PizzaResponse2> getAllPizzas() {
        return pizzaRepo.findAll()
                .stream()
                .map(p -> new PizzaResponse2(
                        p.getPizzaId(),
                        p.getName(),
                        p.getType(),
                        p.getSize(),
                        p.getPrice(),
                        p.getAdditionalDetails(),
                        p.getOrderLines() != null ? p.getOrderLines().stream()
                                .map(or -> new OrderLineResponse2(
                                        or.getOrderLineId(),
                                        or.getQuantity(),
                                        or.getPrice(),
                                        or.getDeliveryDate()
                                ))
                                .toList() : null
                ))
                .toList();
    }

    @Override
    public PizzaResponse2 getPizzaById(Integer pizzaId) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepo.findById(pizzaId).orElse(null);
        if (pizza == null) {
            throw new PizzaNotFoundException("Pizza with id " + pizzaId + " not found");
        }
        return new PizzaResponse2(
                pizza.getPizzaId(),
                pizza.getName(),
                pizza.getType(),
                pizza.getSize(),
                pizza.getPrice(),
                pizza.getAdditionalDetails(),
                pizza.getOrderLines() != null ? pizza.getOrderLines().stream()
                        .map(or -> new OrderLineResponse2(
                                or.getOrderLineId(),
                                or.getQuantity(),
                                or.getPrice(),
                                or.getDeliveryDate()
                        ))
                        .toList() : null
        );
    }

    @Override
    public PizzaResponse2 addPizza(PizzaRequest pizzaRequest) {
        Pizza pizza = new Pizza(null, pizzaRequest.name(),
                pizzaRequest.type(), pizzaRequest.size(),
                pizzaRequest.price(), pizzaRequest.additionalDetails());
        Pizza savedPizza = pizzaRepo.save(pizza);
        return new PizzaResponse2(
                savedPizza.getPizzaId(),
                savedPizza.getName(),
                savedPizza.getType(),
                savedPizza.getSize(),
                savedPizza.getPrice(),
                savedPizza.getAdditionalDetails(),
                pizza.getOrderLines() != null ? pizza.getOrderLines().stream()
                        .map(or -> new OrderLineResponse2(
                                or.getOrderLineId(),
                                or.getQuantity(),
                                or.getPrice(),
                                or.getDeliveryDate()
                        ))
                        .toList() : null
        );
    }

    @Override
    public PizzaResponse2 updatePizzaById(Integer pizzaId, PizzaRequest pizzaRequest) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepo.findById(pizzaId).orElse(null);
        if (pizza == null) {
            throw new PizzaNotFoundException("Pizza with id " + pizzaId + " not found");
        }
        pizza.setName(pizzaRequest.name());
        pizza.setType(pizzaRequest.type());
        pizza.setSize(pizzaRequest.size());
        pizza.setPrice(pizzaRequest.price());
        pizza.setAdditionalDetails(pizzaRequest.additionalDetails());
        Pizza updatedPizza = pizzaRepo.save(pizza);

        return new PizzaResponse2(
                updatedPizza.getPizzaId(),
                updatedPizza.getName(),
                updatedPizza.getType(),
                updatedPizza.getSize(),
                updatedPizza.getPrice(),
                updatedPizza.getAdditionalDetails(),
                pizza.getOrderLines() != null ? pizza.getOrderLines().stream()
                        .map(or -> new OrderLineResponse2(
                                or.getOrderLineId(),
                                or.getQuantity(),
                                or.getPrice(),
                                or.getDeliveryDate()
                        ))
                        .toList() : null
        );
    }

    @Override
    public void deletePizzaById(Integer pizzaId) {
        pizzaRepo.deleteById(pizzaId);
    }
}
