package ru.vicpas.CrudBootSpringSecurity_01.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "role", unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER) // mappedBy - ссылка на поле User.roles
    private Set<User> users /*= new HashSet<>()*/;

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }


//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    public Role() {
//    }
////
//
//    public Role(String role, Set<User> users) {
//        this.role = role;
//        this.users = users;
//    }
//
//    public Role(Long id, String role, Set<User> users) {
//        this.id = id;
//        this.role = role;
//        this.users = users;
//    }
}
