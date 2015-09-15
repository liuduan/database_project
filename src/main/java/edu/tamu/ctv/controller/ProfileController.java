package edu.tamu.ctv.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Controller
public class ProfileController
{
	private final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ProjectAuthentication projectAuthentication;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Model model)
	{
		logger.debug("showProfile()");

		Users profile = projectAuthentication.getCurrentUser();
		if (profile == null)
		{
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Profile not found");
		}
		model.addAttribute("profile", profile);

		populateDefaultModel(model, profile);
		
		return "profile/mypage";

	}
	
	private void populateDefaultModel(Model model, Users user)
	{
		model.addAttribute("projectOwnCache", user.getProjectses());
		model.addAttribute("projectManagerCache", user.getProjectmanagerses());
		model.addAttribute("projectReviewerCache", user.getProjectreviewerses());
		model.addAttribute("projectMemberCache", user.getProjectmemberses());
	}
	
/*	@ModelAttribute("projectOwnCache")
	public Set<Projects> getProjects()
	{
		Users user = projectAuthentication.getCurrentUser();
		return userRepository.findOne(user.getId()).getProjectses();
	}
	
	@ModelAttribute("projectManagerCache")
	public Set<Projects> getProjectsManager()
	{
		Users user = projectAuthentication.getCurrentUser();
		return userRepository.findOne(user.getId()).getProjectmanagerses();
	}
	
	@ModelAttribute("projectReviewerCache")
	public Set<Projects> getProjectsReviewer()
	{
		Users user = projectAuthentication.getCurrentUser();
		return userRepository.findOne(user.getId()).getProjectreviewerses();
	}
	
	@ModelAttribute("projectMemberCache")
	public Set<Projects> getProjectsMember()
	{
		Users user = projectAuthentication.getCurrentUser();
		return userRepository.findOne(user.getId()).getProjectmemberses();
	}*/
}
