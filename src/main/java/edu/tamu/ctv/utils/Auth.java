package edu.tamu.ctv.utils;

import java.util.Calendar;
import java.util.Date;

import edu.tamu.ctv.entity.Units;
import edu.tamu.ctv.entity.Users;


public class Auth
{
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
