package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.UserRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.UserResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.exception.UserNotFoundException;
import edu.miu.cs.cs489.pizzadeliveryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzamgm/api/v1/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> listUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/new")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody @Valid UserRequest userRequest){
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Integer userId, @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserById(userId, userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
