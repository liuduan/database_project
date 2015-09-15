package edu.tamu.ctv.utils.session;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Units;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.service.UnitService;
import edu.tamu.ctv.service.UsersService;

@Service("projectAuthentication")
public class ProjectAuthentication
{
	private final Logger logger = LoggerFactory.getLogger(ProjectAuthentication.class);
	
	private final String anonymousUser = "anonymousUser";
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private UnitService unitService;
	
	public Users getCurrentUser()
	{
		Users user = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//TODO: !!! REMOVE !!!
		if (null == auth || anonymousUser.equals(auth.getName()))
		{
			user = usersService.getUsersRepository().findOne(1l);		
		}
		else
		{
			user = usersService.findByLogin(auth.getName());
		}
		return user;
	}
	
	public Units getDefaultUnit()
	{
		return unitService.getDefaultUnit();
	}
	
	public static Date getCurrentDate()
	{
		return Calendar.getInstance().getTime();
	}
}
