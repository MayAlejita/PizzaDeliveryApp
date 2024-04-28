package edu.miu.cs.cs489.pizzadeliveryapp.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = {"", "/", "/pizzamgm"})
public class HomePageController {

    @GetMapping(value = {"", "/home"})
    public String displayHomepage(HttpServletRequest request, HttpServletResponse response) {
        var session = request.getSession();
        var currentDateTime = LocalDateTime.now().toString();
        session.setAttribute("HTTP_REQUEST_DATETIME", currentDateTime);
        response.addCookie(new Cookie("HTTP_REQUEST_DATETIME", currentDateTime));
        return "index";
    }

    @GetMapping(value = {"/about"})
    public String displayAboutUsPage() {
        return "about";
    }
}
