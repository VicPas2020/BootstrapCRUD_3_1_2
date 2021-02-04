package ru.vicpas.CrudBootSpringSecurity_01.controller.adminController.edit;

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
        System.out.println("GET EDIT");
        User user = userService.getByID(id); // получаем User-а
//
//        // маркер состояния чекбокса ДЛЯ ЧЕКБОКСА
//        boolean roleAdmin = user.getRoles().stream().anyMatch(c->c.getRole().equals("ROLE_ADMIN"));
//        boolean roleUser  = user.getRoles().stream().anyMatch(c->c.getRole().equals("ROLE_USER"));
//        System.out.println(roleAdmin);
//        System.out.println(roleUser);
//
//        model.addAttribute("roleAdmin", roleAdmin);
//        model.addAttribute("roleUser",  roleUser);
        model.addAttribute("user", user);
        return "editUser";
    }


    //TODO:  select список на экране на EDIT дублируется! Это проблема фронт-энда

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user, Model model,
                       @RequestParam(value = "roleInput" ) String roleRoles
                       ) {

        Set<Role> setRoles = new HashSet<>();// Set - на случай если будет много ролей у одного пользователя

        if(roleRoles.contains("ROLE_USER")) {
            setRoles.add(roleService.findRoleByRoleName("ROLE_USER"));
        }

        if(roleRoles.contains("ROLE_ADMIN")) {
            setRoles.add(roleService.findRoleByRoleName("ROLE_ADMIN"));
        }



        user.setRoles(setRoles);
        userService.editExistedUser(user);
        return "redirect:/admin/show";
    }
}
