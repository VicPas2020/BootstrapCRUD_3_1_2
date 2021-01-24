package ru.vicpas.CrudBootSpringSecurity_01.service.userService;

import ru.vicpas.CrudBootSpringSecurity_01.model.User;

import java.util.List;

public interface UserService {

    // UNformatted method names

    List<User> showAllUsers();
    void addNewUser(User user);
    void editExistedUser(User user);
    void deleteExistedUser(User user);

    User getByID(Long id);

    User getUserByUserName(String name);


}
