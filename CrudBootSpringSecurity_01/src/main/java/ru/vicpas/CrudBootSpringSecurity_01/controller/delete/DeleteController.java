package ru.vicpas.CrudBootSpringSecurity_01.controller.delete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vicpas.CrudBootSpringSecurity_01.service.userService.UserService;

@Controller
@RequestMapping("/admin")
public class DeleteController {

    private final UserService userService;

    public DeleteController(UserService userService) {
        this.userService = userService;
    }

    //TODO:  последнего Админа удалять нельзя
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteExistedUser(userService.getByID(id));

        return "redirect:/admin/show";
    }
}
