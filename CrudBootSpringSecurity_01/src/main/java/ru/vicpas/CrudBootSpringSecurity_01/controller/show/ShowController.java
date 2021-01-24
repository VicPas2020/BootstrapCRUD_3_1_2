package ru.vicpas.CrudBootSpringSecurity_01.controller.show;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//        List<User> allUsers = new ArrayList<>();
//        allUsers.add(new User("ww","www", "eee", "eee", "eee", null));
        model.addAttribute("allUsers", allUsers);
        return "show";
    }

}

