package rax.springpassaccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rax.springpassaccess.forms.UserForm;
import rax.springpassaccess.models.User;
import rax.springpassaccess.services.UsersService;

import java.util.List;

@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{user-id}")
    public String getUserPage(Model model, @PathVariable("user-id") Long userId) {
        User user = usersService.getUser(userId);
        model.addAttribute("user", user);
        return "userId";
    }

    @PostMapping("/users")
    public String addUser(UserForm userForm) {
        usersService.addUser(userForm);
        return "redirect:/users";
    }

    @PostMapping("/users/{user-id}/delete")
    public String deleteUser(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return "redirect:/users";
    }

    @PostMapping("/users/{user-id}/update")
    public String update(@PathVariable("user-id") Long userId, UserForm userForm) {
        usersService.updateUser(userId, userForm);
        return "redirect:/users";
    }
}
