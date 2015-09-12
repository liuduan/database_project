package edu.tamu.ctv.service.defaultdata;

import edu.tamu.ctv.entity.Units;
import edu.tamu.ctv.repository.UnitsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.Auth;

public class InitUnitCreateService
{
	public InitUnitCreateService(UnitsRepository unitsRepository, UsersRepository usersRepository)
	{
		createDefault(unitsRepository, usersRepository);
	}

	private void createDefault(UnitsRepository unitsRepository, UsersRepository usersRepository)
	{
		Units unit = new Units(null, usersRepository.findOne(1l), "Default", Auth.getCurrentDate());
		unit.setName("Default");
		unit.setUnits(null);
		unit.setVolumeof(null);
		unitsRepository.save(unit);
	}
}
