package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.Auth;

public class InitProjectCreateService
{
	public InitProjectCreateService(ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository)
	{
		createDefault(projectsRepository, usersRepository, projectTypesRepository);
	}

	private void createDefault(ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository)
	{
		Projects projectTypes = new Projects();
		projectTypes.setCode("ProjectCode");
		projectTypes.setName("ProjectName");
		projectTypes.setAccess(2);
		projectTypes.setProjecttypes(projectTypesRepository.findOne(1l));
		projectTypes.setUsers(usersRepository.findOne(1l));
		projectTypes.setRegistereddt(Auth.getCurrentDate());
		projectTypes.setLastvisitdt(Auth.getCurrentDate());
		
		projectsRepository.save(projectTypes);

	}
}
