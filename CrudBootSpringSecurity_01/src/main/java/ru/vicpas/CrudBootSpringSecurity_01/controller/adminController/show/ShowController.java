package ru.vicpas.CrudBootSpringSecurity_01.controller.adminController.show;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ShowController {

    private final UserRepo userRepo;

    public ShowController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/show")
    public String showAll(Model model){
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        List<User> allUsers = userRepo.findAll();
        model.addAttribute("allUsers", allUsers);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("uk", authentication.getName());
            model.addAttribute("ur", authentication.getAuthorities());

            model.addAttribute("us", userRepo.findUserByUsername(authentication.getName()) );
            //System.out.println(userRepo.findUserByUsername(authentication.getName()));

        }




        return "navtab";
    }
}

