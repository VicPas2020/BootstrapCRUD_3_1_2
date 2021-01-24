package ru.vicpas.CrudBootSpringSecurity_01.service.roleService;

import ru.vicpas.CrudBootSpringSecurity_01.model.Role;

public interface RoleService {

    Role findRoleByRoleName(String role);
}
