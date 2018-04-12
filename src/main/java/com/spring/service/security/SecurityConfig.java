package com.spring.service.security;

import com.spring.service.security.MyPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    /**
     * 在内存中设置三个用户
     * @param auth
     * @throws Exception
     */


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())//在此处应用自定义PasswordEncoder
                .withUser("user").password("password").roles("USER").and()
                .withUser("lyc").password("123456").roles("USER").and()
                .withUser("admin").password("123456").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            //http.csrf().disable();
            http
                    .authorizeRequests()
                        .antMatchers(
                            "/"
                        ).permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/").failureUrl("/login?error")
                        //.usernameParameter("user-name").passwordParameter("pwd")
                        .and()
                    .logout()
                        .permitAll()
                        .logoutSuccessUrl("/login?logout")
                        //.and()
                    //.httpBasic().csrf()
            ;
    }
}



