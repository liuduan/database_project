package edu.tamu.ctv.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Service("projectService")
public class ProjectService
{
	private final Logger logger = LoggerFactory.getLogger(ProjectService.class);
	
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private ColumnTypesService columnTypesService;
	@Autowired
	private RowTypeService rowTypeService;
	@Autowired
	private ProjectAuthentication projectAuthentication;
	
	public Projects findByCode(String code)
	{
		return projectRepository.findTopByCode(code);
	}
	
	public void save(Projects entity)
	{
		if (entity.isNew())
		{
			InitDefaultValues(entity);
		}
		else
		{
			entity = updateEntry(entity);
		}
		try
		{
			projectRepository.save(entity);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
	
	private void InitDefaultValues(Projects entity)
	{
		entity.setVersion(1l);
		entity.setCreatedby(projectAuthentication.getCurrentUser());
		entity.setLastmodifiedby(projectAuthentication.getCurrentUser());
		entity.setRegistereddt(ProjectAuthentication.getCurrentDate());
		entity.setLastupdatedt(ProjectAuthentication.getCurrentDate());
		
		createDefaultColumnsAndTypes(entity);
	}
	
	private Projects updateEntry(Projects entity)
	{
		Projects oldEntity = projectRepository.findOne(entity.getId());
		
		oldEntity.setName(entity.getName());
		oldEntity.setAccess(entity.getAccess());
		oldEntity.setStatus(entity.getStatus());
		oldEntity.setNotes(entity.getNotes());
		oldEntity.setStarts(entity.getStarts());
		oldEntity.setEnds(entity.getEnds());
		oldEntity.setProjectmanagerses(entity.getProjectmanagerses());
		oldEntity.setProjectreviewerses(entity.getProjectreviewerses());
		oldEntity.setProjectmemberses(entity.getProjectmemberses());
		
		oldEntity.setLastupdatedt(ProjectAuthentication.getCurrentDate());

		return oldEntity;
	}
	
	public void createDefaultColumnsAndTypes(Projects entity)
	{
		columnTypesService.createDefaultColumns(entity);
		rowTypeService.createDefaultTypes(entity);
	}
}