package com.flight.reservation.controllers;

import com.flight.reservation.entities.User;
import com.flight.reservation.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//    @RequestMapping(value = {"", "/"})
//    public String showHomePage() {
//        return "index";
//    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        LOGGER.info("Inside showLoginPage()");
        return "login/login";
    }

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        LOGGER.info("Inside showRegistrationPage()");
        return "login/registerUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        LOGGER.info("Inside register() " + user);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        ModelMap modelMap) {
        LOGGER.info("Inside login() and the email is: " + email);
        User user = userRepo.findByEmail(email);
        if (user.getPassword().equals(password)) {
            return "findFlights";
        } else {
            modelMap.addAttribute("msg", "Invalid username or password. Please try again!");
        }
        return "login/login";
    }
}
