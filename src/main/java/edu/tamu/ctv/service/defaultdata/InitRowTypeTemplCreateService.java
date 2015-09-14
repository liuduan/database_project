package edu.tamu.ctv.service.defaultdata;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.entity.Rowtypestempl;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.RowTypesTemplRepository;
import edu.tamu.ctv.utils.Auth;


public class InitRowTypeTemplCreateService
{
	public InitRowTypeTemplCreateService(RowTypesTemplRepository rowTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		createDefault(rowTypesTemplRepository, projectTypesRepository);			
	}

	private void createDefault(RowTypesTemplRepository rowTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		List<Rowtypestempl> list = new ArrayList<Rowtypestempl>();
		
		Projecttypes projectType = projectTypesRepository.findOne(1l);
		if (0 == rowTypesTemplRepository.findByProjecttypeId(projectType.getId()).size())
		{
			list.add(new Rowtypestempl(null, projectType, "SOURCE", "Source", 1));
			list.add(new Rowtypestempl(null, projectType, "CASRN", "CASR Number", 2));
			list.add(new Rowtypestempl(null, projectType, "CHEMICAL", "Chemical", 3));
		}


	}
}
