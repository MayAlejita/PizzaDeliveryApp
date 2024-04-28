package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.UserAuthRequest;
import edu.miu.cs.cs489.pizzadeliveryapp.dto.response.UserAuthResponse;
import edu.miu.cs.cs489.pizzadeliveryapp.service.UserService;
import edu.miu.cs.cs489.pizzadeliveryapp.service.util.JWTMgmtUtilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/pizzamgm/api/v1/public/auth")
public class UserAuthController {

    private JWTMgmtUtilityService jwtMgmtUtilityService;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    public UserAuthController(JWTMgmtUtilityService jwtMgmtUtilityService, AuthenticationManager authenticationManager, UserService userService, AuthenticationProvider authenticationProvider) {
        this.jwtMgmtUtilityService = jwtMgmtUtilityService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/rest/login")
    public ResponseEntity<UserAuthResponse> authenticateUser(@RequestBody @Valid UserAuthRequest userAuthRequest) {
        UserAuthResponse userAuthResponse = null;
        try{
            var username = userAuthRequest.username();
            var password = userAuthRequest.password();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var jwtToken = jwtMgmtUtilityService.generateToken(username);
            var user = userService.getUserByUsername(username);
            if(user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken, user.getFirstName(), user.getLastName());
            }
        }
        catch (Exception ex){
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }

    @PostMapping("/login")
    public ModelAndView authenticateUser(@Valid @ModelAttribute("userAuthRequest") UserAuthRequest userAuthRequest, BindingResult bindingResult, Model model) {
        UserAuthResponse userAuthResponse = null;
        var modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()) {
            model.addAttribute("userAuthRequest", userAuthRequest);
            model.addAttribute("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("secured/useradmin/useradmin");
            return modelAndView;
        }

        try{
            var username = userAuthRequest.username();
            var password = userAuthRequest.password();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var jwtToken = jwtMgmtUtilityService.generateToken(username);
            var user = userService.getUserByUsername(username);
            if(user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken, user.getFirstName(), user.getLastName());
            }
            modelAndView.addObject("userAuthResponse", userAuthResponse);
            modelAndView.setViewName("index");
        }
        catch (Exception ex){
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return modelAndView;
    }
}
