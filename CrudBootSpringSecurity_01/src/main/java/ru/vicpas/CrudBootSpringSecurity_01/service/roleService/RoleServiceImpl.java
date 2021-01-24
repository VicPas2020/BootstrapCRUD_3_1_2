package ru.vicpas.CrudBootSpringSecurity_01.service.roleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vicpas.CrudBootSpringSecurity_01.model.Role;
import ru.vicpas.CrudBootSpringSecurity_01.repository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    //@Transactional
    public Role findRoleByRoleName(String role) {
        return roleRepo.findRoleByRole(role);
    }
}
