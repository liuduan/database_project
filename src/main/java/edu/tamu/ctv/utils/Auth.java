package edu.tamu.ctv.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.ctv.entity.Units;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.service.UsersService;


public class Auth
{
	@Autowired
	private UsersService usersService;
	
	public static Users getCurrentUser()
	{
		return null;
	}
	
	public static Units getDefaultUnit()
	{
		return null;
	}
	
	public static Date getCurrentDate()
	{
		return Calendar.getInstance().getTime();
	}
}
