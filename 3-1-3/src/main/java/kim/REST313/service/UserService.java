package kim.REST313.service;

import kim.REST313.model.Role;
import kim.REST313.model.User;

import java.util.List;

public interface UserService {
    User readUser(Long id);

    List<User> readAll();

    void save(User user);

    User update(User user, Long id);

    void delete(Long id);

    Role getRoleByName(String name);

    User findByUsername(String username);
}
