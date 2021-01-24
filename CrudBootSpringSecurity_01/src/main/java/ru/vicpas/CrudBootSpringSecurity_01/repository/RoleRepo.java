package ru.vicpas.CrudBootSpringSecurity_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vicpas.CrudBootSpringSecurity_01.model.Role;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleByRole(String role);

}

