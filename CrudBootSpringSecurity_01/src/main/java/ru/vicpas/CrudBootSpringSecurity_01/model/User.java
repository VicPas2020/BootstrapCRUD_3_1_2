package ru.vicpas.CrudBootSpringSecurity_01.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter    //TODO: проверить геттеры
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns        = {@JoinColumn(name = "id_from_users")},
            inverseJoinColumns = {@JoinColumn(name = "id_from_roles")})
    private Set<Role> roles;

    ///////////////////   Overrided ////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /////////////// constructors ///////////////////////////////////////

    public User() {
    }

    public User(String country, String city, String street, String username, String password, Set<Role> roles) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
