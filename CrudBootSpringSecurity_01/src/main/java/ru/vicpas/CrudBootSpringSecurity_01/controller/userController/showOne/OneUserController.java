package ru.vicpas.CrudBootSpringSecurity_01.controller.userController.showOne;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

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


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("uk", authentication.getName());
            model.addAttribute("ur", authentication.getAuthorities());
            model.addAttribute("us", userRepo.findUserByUsername(authentication.getName()) );
        }
        model.addAttribute("user", user);
        return "/navtabForOneUser";
    }

    public String getNameFromSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
