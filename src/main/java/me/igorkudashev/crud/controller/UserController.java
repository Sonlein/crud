package me.igorkudashev.crud.controller;

import me.igorkudashev.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author RulleR
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getHomePage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserByAuth(authentication));
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserByAuth(authentication));
        return "profile";
    }

}
