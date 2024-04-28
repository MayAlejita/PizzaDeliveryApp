package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.PizzaRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.PizzaResponse2;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.PizzaNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/pizzamgm/api/v1/pizzas")
public class PizzaController {

    private PizzaService pizzaService;
    public PizzaController(PizzaService pizzaService) {this.pizzaService = pizzaService;}

    @GetMapping
    public ModelAndView listPizzas(){
        var modelAndView = new ModelAndView();
        var pizzas = pizzaService.getAllPizzas();
        modelAndView.addObject("pizzas", pizzas);
        modelAndView.setViewName("secured/sysadmin/pizzas");
        return modelAndView;
    }

    @GetMapping("/{pizzaId}")
    public ResponseEntity<PizzaResponse2> getPizzaById(@PathVariable Integer pizzaId) throws PizzaNotFoundException {
        return ResponseEntity.ok(pizzaService.getPizzaById(pizzaId));
    }

    @GetMapping(value = {"/new"})
    public ModelAndView displayNewPizzaForm(Model model) {
        var modelAndView = new ModelAndView();
        var newPizza = new PizzaRequest(null, null, null, null, null, null);
        model.addAttribute("pizza", newPizza);
        modelAndView.setViewName("secured/sysadmin/pizza/new");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addNewPizza(@Valid @ModelAttribute PizzaRequest pizza){
        var modelAndView = new ModelAndView();
        pizzaService.addPizza(pizza);
        var pizzas = pizzaService.getAllPizzas();
        modelAndView.addObject("pizzas", pizzas);
        modelAndView.setViewName("secured/sysadmin/pizzas");
        return modelAndView;
    }

    @GetMapping(value = {"/edit/{pizzaId}"})
    public ModelAndView displayEditPizzaForm(@PathVariable Integer pizzaId) throws PizzaNotFoundException {
        var modelAndView = new ModelAndView();
        var pizza = pizzaService.getPizzaById(pizzaId);
        modelAndView.addObject("pizza", pizza);
        modelAndView.setViewName("secured/sysadmin/pizza/edit");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updatePizzaById(@Valid @ModelAttribute PizzaRequest pizza) throws PizzaNotFoundException {
        var modelAndView = new ModelAndView();
        pizzaService.updatePizzaById(pizza.pizzaId(), pizza);
        var pizzas = pizzaService.getAllPizzas();
        modelAndView.addObject("pizzas", pizzas);
        modelAndView.setViewName("secured/sysadmin/pizzas");
        return modelAndView;
    }

    @GetMapping("/delete/{pizzaId}")
    public ModelAndView deletePizzaById(@PathVariable Integer pizzaId){
        var modelAndView = new ModelAndView();
        pizzaService.deletePizzaById(pizzaId);
        var pizzas = pizzaService.getAllPizzas();
        modelAndView.addObject("pizzas", pizzas);
        modelAndView.setViewName("secured/sysadmin/pizzas");
        return modelAndView;
    }
}
