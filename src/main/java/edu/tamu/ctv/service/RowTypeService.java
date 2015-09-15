package edu.tamu.ctv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Rowtypes;
import edu.tamu.ctv.entity.Rowtypestempl;
import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.RowTypesTemplRepository;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Service("rowTypeService")
public class RowTypeService
{
	@Autowired
	private RowTypesTemplRepository rowTypesTemplRepository;
	
	private Rowtypes convert(Rowtypestempl rowTypesTempl, Projects project)
	{
		Rowtypes rowType = new Rowtypes(null, project, rowTypesTempl.getCode(), rowTypesTempl.getName(), rowTypesTempl.getShoworder(), ProjectAuthentication.getCurrentDate());
		return rowType;
	}
	
	public void createDefaultTypes(Projects project)
	{
		List<Rowtypes> list = new ArrayList<Rowtypes>();
		for (Rowtypestempl rowTypesTempl : rowTypesTemplRepository.findByProjecttypeId(project.getProjecttypes().getId()))
		{
			list.add(convert(rowTypesTempl, project));
		}
		project.getRowtypeses().addAll(list);
	}
}
