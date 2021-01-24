package ru.vicpas.CrudBootSpringSecurity_01.controller.showOne;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;
import ru.vicpas.CrudBootSpringSecurity_01.service.userService.UserService;

@Controller
public class OneUserController {

    //private final UserService userService;
    private final UserRepo userRepo;

    public OneUserController(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    public String oneUser(Model model){

//        User user = userService.getUserByUserName(getNameFromSecurityContext());
        User user = userRepo.findUserByUsername(getNameFromSecurityContext());

        model.addAttribute("user", user);
        return "/oneUser";
    }

    public String getNameFromSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
