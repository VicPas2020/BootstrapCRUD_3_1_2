package ru.vicpas.CrudBootSpringSecurity_01.service.userService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional // иначе вылетает по StackOverFlow
    public List<User> showAllUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public void addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // part of business logic
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void editExistedUser(User user) {
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteExistedUser(User user) {
        userRepo.delete(user);
    }

    @Override
    @Transactional
    public User getByID(Long id) {
        return userRepo.getOne(id);
    }

    @Override
    public User getUserByUserName(String name) {
        return userRepo.findUserByUsername(name);
    }
}
