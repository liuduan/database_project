package edu.tamu.ctv.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.entity.enums.Roles;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Service
public class UsersService
{
	private final Logger logger = LoggerFactory.getLogger(UsersService.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Users findByLogin(String login)
	{
		Users user = null;
		List<Users> list = usersRepository.findByLogin(login);
		if (list.size() > 0)
		{
			user = list.get(0);
		}
		return user;
	}

	public UsersRepository getUsersRepository()
	{
		return usersRepository;
	}
	
	public void save(Users user)
	{
		if (user.isNew())
		{
			InitDefaultValues(user);
		}
		else
		{
			user = updateEntry(user);
		}
		try
		{
			usersRepository.save(user);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
	
	private void InitDefaultValues(Users entity)
	{
		entity.setRole(Roles.USER);
		entity.setRegistereddt(ProjectAuthentication.getCurrentDate());
		entity.setLastvisitdt(ProjectAuthentication.getCurrentDate());
	}
	
	private Users updateEntry(Users entity)
	{
		Users oldEntity = usersRepository.findOne(entity.getId());
		
		oldEntity.setFirstname(entity.getFirstname());
		oldEntity.setLastname(entity.getLastname());
		oldEntity.setEmail(entity.getEmail());
		oldEntity.setPhone(entity.getPhone());
		oldEntity.setAddress1(entity.getAddress1());
		oldEntity.setAddress2(entity.getAddress2());

		oldEntity.setCountry(entity.getCountry());
		oldEntity.setState(entity.getState());
		oldEntity.setZip(entity.getZip());
		oldEntity.setCity(entity.getCity());
		oldEntity.setSex(entity.getSex());
		oldEntity.setWebsite(entity.getWebsite());
		oldEntity.setInterests(entity.getInterests());
		oldEntity.setBirthday(entity.getBirthday());
		oldEntity.setOrganization(entity.getOrganization());
		oldEntity.setOrganaddress(entity.getOrganaddress());
		oldEntity.setPhoto(entity.getPhoto());
		//oldEntity.setNotes(entity.getNotes());
		oldEntity.setRegistereddt(entity.getRegistereddt());
		oldEntity.setLastvisitdt(ProjectAuthentication.getCurrentDate());
		
		entity = null;
		
		return oldEntity;
	}
}
