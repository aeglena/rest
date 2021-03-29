package kim.REST313.dao;

import kim.REST313.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;
//    @Autowired
//    @Lazy
//    PasswordEncoder bCryptPassword;
    @Autowired
    RoleDao roleDao;

    @Override
    public User findByUsername(String name) {
        Query query = em.createQuery("SELECT u from User u where u.username = :username")
                .setParameter("username", name);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        em.merge(user);
    }

    @Override
    public User readUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> readAll() {
        TypedQuery<User> query = (TypedQuery) em.createQuery("SELECT DISTINCT p FROM User p JOIN FETCH p.roles ORDER BY p.id");
        return query.getResultList();
    }


    @Override
    public User update(User userNew, Long idOld) {
        User user = new User();
        user.setId(idOld);
        user.setName(userNew.getName());
        user.setAge(userNew.getAge());
        user.setEmail(userNew.getEmail());
        user.setPassword(userNew.getPassword());
        user.setRoles(userNew.getRoles());
        return em.merge(user);
    }

    @Override
    public void delete(Long id) {
        em.remove(em.find(User.class, id));
    }
}
