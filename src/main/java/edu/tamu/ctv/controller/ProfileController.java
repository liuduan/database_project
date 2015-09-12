package edu.tamu.ctv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;

@Controller
public class ProfileController
{
	private final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ProjectsRepository projectRepository;
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public String showProfile(@PathVariable("id") Long id, Model model)
	{
		logger.debug("showProfile() id: {}", id);

		Users profile = userRepository.findOne(id);
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
		model.addAttribute("projectsList", getProjects(user));
	}
	
	@ModelAttribute("projectsCache")
	public List<Projects> getProjects(Users user)
	{
		return projectRepository.findByUsersId(user.getId());
	}
}
