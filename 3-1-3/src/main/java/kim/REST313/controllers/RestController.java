package kim.REST313.controllers;

import kim.REST313.model.Role;
import kim.REST313.model.User;
import kim.REST313.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final UserService userService;

    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public List<User> alluser() {
        List<User> userList = userService.readAll();
        return userList;
    }

    @GetMapping("/user")
    public User readUser(Model model, Principal principal) {
        String username = principal.getName();
        return userService.findByUsername(username);
    }

    @GetMapping("/findOne/{id}")
    public User findOne(@PathVariable("id") Long id) {
        return userService.readUser(id);
    }


    @PostMapping(value = "/admin/users", consumes = "application/json")
    public User saveUser(@RequestBody Map<String, String> map) {
        User userNew = new User();
        Set<Role> listRole = new HashSet<>();
        String[] r = map.get("roles").split(",");
        for (int i = 0; i < r.length; i++) {
            listRole.add(userService.getRoleByName(r[i]));
        }
        userNew.setRoles(listRole);
        userNew.setName(map.get("name"));
        userNew.setAge(Integer.parseInt(map.get("age")));
        userNew.setEmail(map.get("email"));
        userNew.setPassword(map.get("password"));
        userService.save(userNew);
        return userNew;
    }

    @PutMapping("/admin/users/{id}")
    public void updateUser(@RequestBody Map<String, String> map) {
        Long id = Long.parseLong(map.get("id"));
        User editUser = new User();
        editUser.setName(map.get("name"));
        editUser.setAge(Integer.parseInt(map.get("age")));
        editUser.setEmail(map.get("email"));
        editUser.setPassword(map.get("password"));
        Set<Role> listRole = new HashSet<>();
        String[] r = map.get("roles").split(",");
        for (int i = 0; i < r.length; i++) {
            listRole.add(userService.getRoleByName(r[i]));
        }
        editUser.setRoles(listRole);
        userService.update(editUser, id);
    }

    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}

