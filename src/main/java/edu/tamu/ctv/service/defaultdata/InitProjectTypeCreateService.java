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
		projectTypes.setCode("INVIVO");
		projectTypes.setName("In Vivo");
		projectTypes.setRegistereddt(Auth.getCurrentDate());
		projectTypes.setLastvisitdt(Auth.getCurrentDate());
		projectTypesRepository.save(projectTypes);
		
		projectTypes = new Projecttypes();
		projectTypes.setCode("INVITRO");
		projectTypes.setName("In Vitro");
		projectTypes.setRegistereddt(Auth.getCurrentDate());
		projectTypes.setLastvisitdt(Auth.getCurrentDate());
		projectTypesRepository.save(projectTypes);
	}
}
