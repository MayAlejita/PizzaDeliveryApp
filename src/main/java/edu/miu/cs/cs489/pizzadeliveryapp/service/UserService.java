package edu.miu.cs.cs489.pizzadeliveryapp.service;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.UserRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.UserResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.UserNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Integer userId)  throws UserNotFoundException;
    User getUserByUsername(String username);
    UserResponse addUser(UserRequest userRequest);
    UserResponse updateUserById(Integer userId, UserRequest userRequest) throws UserNotFoundException;
    void deleteUserById(Integer userId);
}
