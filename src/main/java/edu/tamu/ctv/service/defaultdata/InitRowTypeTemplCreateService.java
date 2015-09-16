package edu.tamu.ctv.service.defaultdata;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.entity.Rowtypestempl;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.RowTypesTemplRepository;

public class InitRowTypeTemplCreateService
{
	public InitRowTypeTemplCreateService(RowTypesTemplRepository rowTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		createDefault(rowTypesTemplRepository, projectTypesRepository);			
	}

	private void createDefault(RowTypesTemplRepository rowTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		List<Rowtypestempl> list = new ArrayList<Rowtypestempl>();
		
		Projecttypes projectType = projectTypesRepository.findByCode("INVIVO").get(0);
		if (0 == rowTypesTemplRepository.findByProjecttypeId(projectType.getId()).size())
		{
			int counter = 1;
			
			list.add(new Rowtypestempl(null, projectType, "chemical_source_sid", "Source", counter++));
			list.add(new Rowtypestempl(null, projectType, "casrn", "CASR Number", counter++));
			list.add(new Rowtypestempl(null, projectType, "chemical_name", "Chemical Name", counter++));
		}
		
		projectType = projectTypesRepository.findByCode("INVITRO").get(0);
		if (0 == rowTypesTemplRepository.findByProjecttypeId(projectType.getId()).size())
		{
			int counter = 1;
			list.add(new Rowtypestempl(null, projectType, "SOURCE", "Source", counter++));
			list.add(new Rowtypestempl(null, projectType, "CASRN", "CASR Number", counter++));
			list.add(new Rowtypestempl(null, projectType, "CHEMICAL", "Chemical", counter++));
		}
		
		rowTypesTemplRepository.save(list);
	}
}
