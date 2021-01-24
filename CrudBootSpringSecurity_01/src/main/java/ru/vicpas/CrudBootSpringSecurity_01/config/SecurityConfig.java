package ru.vicpas.CrudBootSpringSecurity_01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.vicpas.CrudBootSpringSecurity_01.service.UserDetailServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserDetailServiceImpl userDetailService;
    @Autowired
    private  LoginSuccessHandler loginSuccessHandler;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("aa")
//                .password("aa")
//                .roles("ADMIN")
//                .and()
//                .withUser("b")
//                .password("2")
//                .roles("USER");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureDBAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user").hasAnyRole("USER") // только по одной роли
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login").anonymous()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(loginSuccessHandler)
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();
    }
}
