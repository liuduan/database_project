package edu.tamu.ctv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.Auth;

@Service("projectService")
public class ProjectService
{
	private final Logger logger = LoggerFactory.getLogger(ProjectService.class);
	
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private ColumnTypesService columnTypesService;
	@Autowired
	private RowTypeService rowTypeService;
	
	public void save(Projects project)
	{
		if (project.isNew())
		{
			InitDefaultValues(project);
		}
		else
		{
			updateProject(project);
		}
		
		try
		{
			projectRepository.save(project);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
	
	private void updateProject(Projects project)
	{
		project.setLastupdatedt(Auth.getCurrentDate());
	}
	
	private void InitDefaultValues(Projects project)
	{
		project.setUsers(userRepository.findOne(1l));
		project.setRegistereddt(Auth.getCurrentDate());
		project.setLastupdatedt(Auth.getCurrentDate());
		
		createDefaultColumnsAndTypes(project);
	}
	
	public void createDefaultColumnsAndTypes(Projects project)
	{
		columnTypesService.createDefaultColumns(project);
		rowTypeService.createDefaultTypes(project);
	}
}