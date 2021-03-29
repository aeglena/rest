package kim.REST313.service;


import kim.REST313.model.Role;

public interface RoleService {
    void add(Role role);
    void update(Role role, Long id);
    void delete(Long id);
    Role read(Long id);
}
