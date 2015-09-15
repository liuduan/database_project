package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.utils.session.ProjectAuthentication;


public class InitProjectTypeCreateService
{
	public InitProjectTypeCreateService(ProjectTypesRepository projectTypesRepository)
	{
		createDefault(projectTypesRepository);			
	}

	private void createDefault(ProjectTypesRepository projectTypesRepository)
	{
		Projecttypes projectTypes = null;
		
		if (0 == projectTypesRepository.findByCode("INVIVO").size())
		{
			projectTypes = new Projecttypes();
			projectTypes.setCode("INVIVO");
			projectTypes.setName("In Vivo");
			projectTypes.setRegistereddt(ProjectAuthentication.getCurrentDate());
			projectTypes.setLastvisitdt(ProjectAuthentication.getCurrentDate());
			projectTypesRepository.save(projectTypes);
		}

		if (0 == projectTypesRepository.findByCode("INVITRO").size())
		{
			projectTypes = new Projecttypes();
			projectTypes.setCode("INVITRO");
			projectTypes.setName("In Vitro");
			projectTypes.setRegistereddt(ProjectAuthentication.getCurrentDate());
			projectTypes.setLastvisitdt(ProjectAuthentication.getCurrentDate());
			projectTypesRepository.save(projectTypes);
		}

	}
}
