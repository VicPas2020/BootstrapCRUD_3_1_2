package ru.vicpas.CrudBootSpringSecurity_01.controller.adminController.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vicpas.CrudBootSpringSecurity_01.model.Role;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.service.roleService.RoleService;
import ru.vicpas.CrudBootSpringSecurity_01.service.userService.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AddController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AddController(UserService userService, RoleService roleSevrice) {
        this.userService = userService;
        this.roleService = roleSevrice;
    }



    // TODO: нужен только при переходе на НОВУЮ страницу Add
    @GetMapping("/addUser")
    public String addUser(Model model) {
        System.out.println("ADD GET");

        User user = new User();
        model.addAttribute(user);
        return "/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, Model model,
                          @RequestParam(value = "roleInput" ) String roleRoles
//                          @RequestParam(value = "roleInput2"  , required = false ) String roleUser
                          ) {
        //System.out.println("ADD POST");
        // TODO костыль: роли приходят перечислением через запятую в   виде  String
        //System.out.println( roleRoles ); // ROLE_ADMIN,ROLE_USER
        //System.out.println("user: " + roleRoles.contains("ROLE_USER") );
        //System.out.println("admin: "+ roleRoles.contains("ROLE_ADMIN") );
        //System.out.println(roleUser);
        Set<Role> setRoles = new HashSet<>();

        if(roleRoles.contains("ROLE_USER")) {
            setRoles.add(roleService.findRoleByRoleName("ROLE_USER"));
        }

        if(roleRoles.contains("ROLE_ADMIN")) {
            setRoles.add(roleService.findRoleByRoleName("ROLE_ADMIN"));
        }

        //System.out.println(setRoles.size());
        //System.out.println("ROLE "+roleService.findRoleByRoleName(roleRoles));

//        boolean roleAdmin = user.getRoles().stream().anyMatch(c->c.getRole().equals("ROLE_ADMIN"));
//        boolean roleUser  = user.getRoles().stream().anyMatch(c->c.getRole().equals("ROLE_USER"));
//        System.out.println(roleAdmin);
//        System.out.println(roleUser);
//
//        model.addAttribute("roleAdmin", roleAdmin);
//        model.addAttribute("roleUser",  roleUser);


        user.setRoles(setRoles);
        userService.addNewUser(user);
        System.out.println(user);

        return "redirect:/admin/show";
    }

    // TODO: нужна проверка на существующее имя пользователя - сейчас пока ошибка  Duplicate entry *** for key 'users.
}
