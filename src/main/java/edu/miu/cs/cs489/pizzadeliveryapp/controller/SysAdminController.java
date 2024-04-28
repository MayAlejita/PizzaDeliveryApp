package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizzamgm")
public class SysAdminController {

    @GetMapping("/sysadmin")
    public String displaySysAdminPage() {
        return "secured/sysadmin/sysadmin";
    }
}
