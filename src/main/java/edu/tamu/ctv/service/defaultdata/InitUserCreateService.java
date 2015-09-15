package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.entity.enums.Roles;
import edu.tamu.ctv.repository.UsersRepository;

import java.util.Date;

public class InitUserCreateService {
    public InitUserCreateService(UsersRepository usersRepository) {
    	if (0 == usersRepository.findByLogin("admin").size())
    	{
    		createAdmin(usersRepository);    		
    	}
    }

    private void createAdmin(UsersRepository usersRepository) {
        Users admin = new Users();
        admin.setLogin("admin");
        admin.setRole(Roles.ADMIN);
        admin.setFirstname("admin");
        admin.setLastname("admin");
        admin.setCountry("Ukraine");
        admin.setPassword("admin");
        admin.setSex("M");
        admin.setEmail("admin@mail.box");
        admin.setPhone("+380000000000");
        admin.setAddress1("Lviv");
        admin.setState("Lviv");
        admin.setCity("Lviv");
        admin.setZip("79000");
        admin.setRegistereddt(new Date());
        admin.setLastvisitdt(new Date());
        admin.setBirthday(new Date());
        usersRepository.save(admin);
    }
}
