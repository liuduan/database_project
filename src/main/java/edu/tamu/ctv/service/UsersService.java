package edu.tamu.ctv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.UsersRepository;

@Service
public class UsersService
{
	private UsersRepository userRepository;

	@Autowired
	public void setAnalysisService(UsersRepository userService)
	{
		this.userRepository = userService;
	}
	
	public Users findOne(Long id)
	{
		return userRepository.findOne(id);
	}
}
