package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import edu.miu.cs.cs489.pizzadeliveryapp.dto.request.UserAuthRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizzamgm")
public class UserAdminController {

    @GetMapping("/useradmin")
    public String displaySysAdminPage(Model model) {
        var userAuthRequest = new UserAuthRequest(null,null);
        model.addAttribute("userAuthRequest", userAuthRequest);
        return "secured/useradmin/useradmin";
    }
}
