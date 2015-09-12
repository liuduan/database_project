package edu.tamu.ctv.config.security;

import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.defaultdata.InitProjectTypeCreateService;
import edu.tamu.ctv.service.defaultdata.InitUserCreateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/**").access("isAuthenticated()")
/*        .antMatchers("/results/**").access("isAuthenticated()")
        .antMatchers("/analysis/**").access("isAuthenticated()")*/
        .and().formLogin();
    }

    @Autowired
    @Bean
    public InitUserCreateService initUserCreateService(UsersRepository userRepository) {
        return new InitUserCreateService(userRepository);
    }
    
    @Autowired
    @Bean
    public InitProjectTypeCreateService initProjectTypesCreateService(ProjectTypesRepository projectTypesRepository) {
        return new InitProjectTypeCreateService(projectTypesRepository);
    }
}
