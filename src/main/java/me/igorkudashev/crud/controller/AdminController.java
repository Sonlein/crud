package me.igorkudashev.crud.controller;

import me.igorkudashev.crud.model.User;
import me.igorkudashev.crud.service.RoleService;
import me.igorkudashev.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author RulleR
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getUsersAlias() {
        return "redirect:/admin";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping("/users/new")
    public String createUser(@ModelAttribute("user") User user) {
        user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/admin";
    }

    @GetMapping("/users/{id}")
    public String getUser(Authentication authentication, Model model, @PathVariable("id") Long id) {
        User user = userService.getUserByAuth(authentication);
        model.addAttribute("user", userService.findById(id));
        if (user.getId().equals(id)) {
            return "profile";
        }
        return "admin/user";
    }
}
