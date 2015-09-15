package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.enums.Access;
import edu.tamu.ctv.entity.enums.Status;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.ProjectService;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

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
		project.setStatus(Status.NEW);
		project.setAccess(Access.PRIVATE);
		project.setProjecttypes(projectTypesRepository.findOne(1l));
		project.setUsers(usersRepository.findOne(1l));
		project.setRegistereddt(ProjectAuthentication.getCurrentDate());
		project.setLastupdatedt(ProjectAuthentication.getCurrentDate());
		
		
		projectService.createDefaultColumnsAndTypes(project);
		
		projectsRepository.save(project);

	}
}
