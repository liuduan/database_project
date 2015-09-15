package edu.tamu.ctv.config.security;

import edu.tamu.ctv.entity.enums.Roles;
import edu.tamu.ctv.repository.ColumnTypesTemplRepository;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.RowTypesTemplRepository;
import edu.tamu.ctv.repository.UnitsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.ProjectService;
import edu.tamu.ctv.service.defaultdata.InitColumnTypeTemplCreateService;
import edu.tamu.ctv.service.defaultdata.InitProjectCreateService;
import edu.tamu.ctv.service.defaultdata.InitProjectTypeCreateService;
import edu.tamu.ctv.service.defaultdata.InitRowTypeTemplCreateService;
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
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        //.antMatchers("/users/**").hasRole(Roles.ADMIN.getCode())
        .and()
            .formLogin().loginPage("/login")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/users")
        .and()
            .logout().logoutSuccessUrl("/login?logout")
        .and().csrf();
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
    public InitUnitCreateService initUnitsCreateService(UnitsRepository unitRepository, UsersRepository usersRepository) {
        return new InitUnitCreateService(unitRepository, usersRepository);
    }
    
    @Autowired
    @Bean
    public InitRowTypeTemplCreateService initRowTypeTemplCreateService(RowTypesTemplRepository rowTypesTemplRepository, ProjectTypesRepository projectTypesRepository) {
        return new InitRowTypeTemplCreateService(rowTypesTemplRepository, projectTypesRepository);
    }
    
    @Autowired
    @Bean
    public InitColumnTypeTemplCreateService initColumnTypeTemplCreateService(ColumnTypesTemplRepository columnTypesTemplRepository, ProjectTypesRepository projectTypesRepository) {
        return new InitColumnTypeTemplCreateService(columnTypesTemplRepository, projectTypesRepository);
    }
    
    @Autowired
    @Bean
    public InitProjectCreateService initProjectsCreateService(ProjectService projectService, ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository) {
        return new InitProjectCreateService(projectService, projectsRepository, usersRepository, projectTypesRepository);
    }
}
