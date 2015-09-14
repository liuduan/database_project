package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.ProjectService;
import edu.tamu.ctv.utils.Auth;

public class InitProjectCreateService
{
	public InitProjectCreateService(ProjectService projectService, ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository)
	{
		if (0 == projectsRepository.findByCode("ProjectCode").size())
		{
			createDefault(projectService, projectsRepository, usersRepository, projectTypesRepository);			
		}
	}

	private void createDefault(ProjectService projectService, ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository)
	{
		Projects project = new Projects();
		project.setCode("ProjectCode");
		project.setName("ProjectName");
		project.setAccess(2);
		project.setProjecttypes(projectTypesRepository.findOne(1l));
		project.setUsers(usersRepository.findOne(1l));
		project.setRegistereddt(Auth.getCurrentDate());
		project.setLastupdatedt(Auth.getCurrentDate());
		project.setStatus(0l);
		
		projectService.createDefaultColumnsAndTypes(project);
		
		projectsRepository.save(project);

	}
}
