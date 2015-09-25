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
		createDefault(projectService, projectsRepository, usersRepository, projectTypesRepository);
	}

	private void createDefault(ProjectService projectService, ProjectsRepository projectsRepository, UsersRepository usersRepository, ProjectTypesRepository projectTypesRepository)
	{
		if (null == projectsRepository.findTopByCode("ProjectCode"))
		{
			Projects project = new Projects();
			project.setCode("ProjectCode");
			project.setName("ProjectName");
			project.setStatus(Status.NEW);
			project.setAccess(Access.PRIVATE);
			project.setProjecttypes(projectTypesRepository.findOne(1l));
			project.setCreatedby(usersRepository.findOne(1l));
			project.setLastmodifiedby(usersRepository.findOne(1l));
			project.setRegistereddt(ProjectAuthentication.getCurrentDate());
			project.setLastupdatedt(ProjectAuthentication.getCurrentDate());
			project.setVersion(1l);

			projectService.createDefaultColumnsAndTypes(project);

			projectsRepository.save(project);
		}
	}
}
