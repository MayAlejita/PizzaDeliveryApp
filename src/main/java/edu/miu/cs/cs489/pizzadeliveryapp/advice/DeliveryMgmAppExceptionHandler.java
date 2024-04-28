package edu.miu.cs.cs489.pizzadeliveryapp.advice;

import edu.miu.cs.cs489.pizzadeliveryapp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DeliveryMgmAppExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", customerNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", orderNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(PizzaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePizzaNotFoundException(PizzaNotFoundException pizzaNotFoundException) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", pizzaNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", userNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", usernameNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(OrderLineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderLineNotFoundException(OrderLineNotFoundException orderLineNotFoundException) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", orderLineNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleDataValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errorMap = new HashMap<String, String>();
        methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errorMap.put(error.getField(), error.getDefaultMessage())
                );
        return errorMap;
    }
}
