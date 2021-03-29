package kim.REST313.dao;


import kim.REST313.model.Role;

public interface RoleDao {
    Role getById(Long id);
    Role getRoleByName(String name);
}
