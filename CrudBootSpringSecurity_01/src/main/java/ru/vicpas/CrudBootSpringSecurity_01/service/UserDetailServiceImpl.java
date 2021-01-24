package ru.vicpas.CrudBootSpringSecurity_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vicpas.CrudBootSpringSecurity_01.model.Role;
import ru.vicpas.CrudBootSpringSecurity_01.model.User;
import ru.vicpas.CrudBootSpringSecurity_01.repository.UserRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    @Autowired
    public UserDetailServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user =  userRepo.findUserByUsername(name); // берем обычного Юзера
        Set<GrantedAuthority> authSet = new HashSet<>();

        for(Role role : user.getRoles()){
            authSet.add(new SimpleGrantedAuthority(role.getRole()));
        }


        //... и создаем специального Юзера - Принципала
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authSet);
    }
}
