package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.Auth;

import java.util.Date;

public class InitProjectTypeCreateService
{
	public InitProjectTypeCreateService(ProjectTypesRepository projectTypesRepository)
	{
		createDefault(projectTypesRepository);
	}

	private void createDefault(ProjectTypesRepository projectTypesRepository)
	{
		Projecttypes projectTypes = new Projecttypes();
		projectTypes.setCode("code1");
		projectTypes.setName("name1");
		projectTypes.setRegistereddt(Auth.getCurrentDate());
		projectTypes.setLastvisitdt(Auth.getCurrentDate());
		projectTypesRepository.save(projectTypes);
		
		projectTypes = new Projecttypes();
		projectTypes.setCode("code2");
		projectTypes.setName("name2");
		projectTypes.setRegistereddt(Auth.getCurrentDate());
		projectTypes.setLastvisitdt(Auth.getCurrentDate());
		projectTypesRepository.save(projectTypes);
	}
}
