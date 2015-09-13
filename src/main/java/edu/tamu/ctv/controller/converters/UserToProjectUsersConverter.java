package edu.tamu.ctv.controller.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.UsersRepository;

@Component
public class UserToProjectUsersConverter implements Converter<Object, Users>
{
	@Autowired
	private UsersRepository usersRepository;

	public Users convert(Object element)
	{
		Long id = Long.parseLong((String) element);
		Users user = usersRepository.findOne(id);
		System.out.println("Project user : " + user);
		return user;
	}
}
