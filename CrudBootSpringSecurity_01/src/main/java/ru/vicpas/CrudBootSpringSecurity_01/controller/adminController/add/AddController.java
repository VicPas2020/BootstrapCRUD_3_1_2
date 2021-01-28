package ru.vicpas.CrudBootSpringSecurity_01.controller.adminController.add;

import org.springframework.beans.factory.annotation.Autowired;
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
        User user = new User();
        model.addAttribute(user);
        System.out.println("==ADD GET==");
        return "/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "roleInput" ) String requiredRole
                          ) {
        Set<Role> setRoles = new HashSet<>();
        setRoles.add(roleService.findRoleByRoleName(requiredRole));
        user.setRoles(setRoles);
        userService.addNewUser(user);
        System.out.println("==ADD POST==");

        return "redirect:/admin/show";
    }

    // TODO: нужна проверка на существующее имя пользователя - сейчас пока ошибка  Duplicate entry *** for key 'users.
}
