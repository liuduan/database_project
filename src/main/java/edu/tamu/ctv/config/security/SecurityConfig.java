package edu.tamu.ctv.config.security;

import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UnitsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.defaultdata.InitProjectCreateService;
import edu.tamu.ctv.service.defaultdata.InitProjectTypeCreateService;
import edu.tamu.ctv.service.defaultdata.InitUnitCreateService;
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
        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");;
    }
    


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: Upload file temporary

	    http.authorizeRequests()
	    //.csrf().disable()
	    .antMatchers("/**").access("permitAll");
	    //.antMatchers("/**").access("isAuthenticated()")
		//.and().formLogin()
		//.loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")		
		//.and().logout().logoutSuccessUrl("/login?logout")
		//.and().csrf(); 

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
    
    @Autowired
    @Bean
    public InitProjectCreateService initProjectsCreateService(ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository) {
        return new InitProjectCreateService(projectsRepository, usersRepository, projectTypesRepository);
    }
    
    @Autowired
    @Bean
    public InitUnitCreateService initUnitsCreateService(UnitsRepository unitRepository, UsersRepository usersRepository) {
        return new InitUnitCreateService(unitRepository, usersRepository);
    }
}
