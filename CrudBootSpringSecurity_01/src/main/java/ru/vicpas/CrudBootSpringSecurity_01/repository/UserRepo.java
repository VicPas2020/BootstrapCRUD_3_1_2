package ru.vicpas.CrudBootSpringSecurity_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    // formatted method names - только по именам элементов ТАБЛИЦЫ USER
//
//    List<User> findAllUsers();
//    void addUser(User user);
//    void editUser(User user);
//    void deleteUser(User user);

    User findUserByUsername(String name);
}
