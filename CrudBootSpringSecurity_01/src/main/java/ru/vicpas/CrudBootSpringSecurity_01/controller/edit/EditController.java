package ru.vicpas.CrudBootSpringSecurity_01.controller.edit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vicpas.CrudBootSpringSecurity_01.model.Role;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.service.roleService.RoleService;
import ru.vicpas.CrudBootSpringSecurity_01.service.userService.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class EditController {

    private final UserService userService;
    private final RoleService roleService;

    public EditController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model ) {
        User user = userService.getByID(id); // получаем User-а

        // маркер состояния чекбокса
        boolean roleAdmin = user.getRoles().stream().anyMatch(c->c.getRole().equals("ROLE_ADMIN"));
        boolean roleUser  = user.getRoles().stream().anyMatch(c->c.getRole().equals("ROLE_USER"));

        model.addAttribute("roleAdmin", roleAdmin);
        model.addAttribute("roleUser",  roleUser);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(value = "roleInput" ) String requiredRole
                       ) {
        Set<Role> set = new HashSet<>();// Set - на случай если будет много ролей у одного пользователя
        set.add(roleService.findRoleByRoleName(requiredRole));

        user.setRoles(set);
        userService.editExistedUser(user);
        return "redirect:/admin/show";
    }
}
