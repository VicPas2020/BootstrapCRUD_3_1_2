package ru.vicpas.CrudBootSpringSecurity_01.controller.adminController.show;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

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
        List<User> allUsers = userRepo.findAll();
        model.addAttribute("allUsers", allUsers);
        return "show";
    }
}

