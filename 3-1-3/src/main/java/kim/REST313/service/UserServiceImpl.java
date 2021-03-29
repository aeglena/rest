package kim.REST313.service;

import kim.REST313.dao.RoleDao;
import kim.REST313.dao.UserDao;
import kim.REST313.model.Role;
import kim.REST313.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    @Override
    public Role getRoleByName(String name){
        return roleDao.getRoleByName(name);
    }

    @Override
    public User readUser(Long id) {
        return userDao.readUser(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        return userDao.update(user, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
