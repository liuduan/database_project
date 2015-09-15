package edu.tamu.ctv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Controller
public class LoginController
{
	@Autowired
	private ProjectAuthentication projectAuthentication;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout)
	{
		ModelAndView model = new ModelAndView();
		if (error != null)
		{
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null)
		{
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout()
	{
		return "login";
	}
	
	@RequestMapping(value = "/currentuser", method = RequestMethod.GET)
	public @ResponseBody String currentUser(ModelMap model)
	{
		return projectAuthentication.getCurrentUser().getLogin();

	}
}
