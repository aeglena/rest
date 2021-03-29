package kim.REST313.controllers;

import kim.REST313.model.User;
import kim.REST313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String readAll(Model model, Principal principal) {
        User user = new User();
        user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/api/admin/users";
    }

}
