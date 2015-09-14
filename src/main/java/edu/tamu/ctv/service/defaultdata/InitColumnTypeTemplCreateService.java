package edu.tamu.ctv.service.defaultdata;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.ctv.entity.Columntypestempl;
import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.repository.ColumnTypesTemplRepository;
import edu.tamu.ctv.repository.ProjectTypesRepository;

public class InitColumnTypeTemplCreateService
{
	public InitColumnTypeTemplCreateService(ColumnTypesTemplRepository columnTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		createDefault(columnTypesTemplRepository, projectTypesRepository);			
	}

	private void createDefault(ColumnTypesTemplRepository columnTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		List<Columntypestempl> list = new ArrayList<Columntypestempl>();
		
		Projecttypes projectType = projectTypesRepository.findByCode("INVIVO").get(0);
		if (0 == columnTypesTemplRepository.findByProjecttypeId(projectType.getId()).size())
		{
			int counter = 0;
			list.add(new Columntypestempl(null, projectType, null, "WEIGHT", "Weight"));
			list.add(new Columntypestempl(null, projectType, list.get(counter++), "GROUP", "Group"));
			list.add(new Columntypestempl(null, projectType, list.get(counter++), "TYPE", "Type"));
			list.add(new Columntypestempl(null, projectType, list.get(counter++), "SOURCE", "Source"));
		}
		
		projectType = projectTypesRepository.findByCode("INVITRO").get(0);
		if (0 == columnTypesTemplRepository.findByProjecttypeId(projectType.getId()).size())
		{
			int counter = 0;
			list.add(new Columntypestempl(null, projectType, null, "WEIGHT", "Weight"));
			list.add(new Columntypestempl(null, projectType, list.get(counter++), "GROUP", "Group"));
			list.add(new Columntypestempl(null, projectType, list.get(counter++), "TYPE", "Type"));
			list.add(new Columntypestempl(null, projectType, list.get(counter++), "SOURCE", "Source"));
		}
		
		columnTypesTemplRepository.save(list);
	}
}
