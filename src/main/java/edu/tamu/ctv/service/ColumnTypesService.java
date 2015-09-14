package edu.tamu.ctv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Columntypes;
import edu.tamu.ctv.entity.Columntypestempl;
import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.ColumnTypesTemplRepository;
import edu.tamu.ctv.utils.Auth;

@Service("columnTypesService")
public class ColumnTypesService
{
	@Autowired
	private ColumnTypesTemplRepository columnTypesTemplRepository;
	
	private Columntypes convert(Columntypestempl columnTypesTempl, Projects project, Columntypes parent)
	{
		Columntypes columnType = new Columntypes(null, parent, project, columnTypesTempl.getCode(), columnTypesTempl.getName(), Auth.getCurrentDate());
		return columnType;
	}
	
	public void createDefaultColumns(Projects project)
	{
		int counter = 0;
		Columntypes parent = null;
		List<Columntypes> list = new ArrayList<Columntypes>();

		for (Columntypestempl columnTypesTempl : columnTypesTemplRepository.findByProjecttypeId(project.getProjecttypes().getId()))
		{
			list.add(convert(columnTypesTempl, project, parent));
			parent = list.get(counter++);
		}
		project.getColumntypeses().addAll(list);
	}
}
