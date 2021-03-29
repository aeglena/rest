package kim.REST313.dao;




import kim.REST313.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String name);

    User readUser(Long id);

    List<User> readAll();

    void save(User user);

    User update(User user, Long id);

    void delete(Long id);
}
