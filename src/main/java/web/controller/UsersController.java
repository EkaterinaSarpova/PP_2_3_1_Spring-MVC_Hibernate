package web.controller;
import web.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserService;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String usersList(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user-create")
    public String createUserForm (User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser (User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete")
    public String deleteUser (@RequestParam(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update")
    public String updateUserForm (@RequestParam(value = "id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser (User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
