package edu.tamu.ctv.controller;

import java.beans.PropertyEditorSupport;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.utils.Auth;

@Controller
public class ProjectController
{
	private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectsRepository projectRepository;

	@Autowired
	private ProjectTypesRepository projectTypesRepository;

	@Autowired
	private UsersRepository userRepository;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String showAllProjects(Model model)
	{
		logger.debug("showAllProjects()");
		model.addAttribute("projects", projectRepository.findAll());
		return "projects/list";
	}

	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public String saveOrUpdateProject(@ModelAttribute("projectForm") @Validated Projects project, BindingResult result, Model model, final RedirectAttributes redirectAttributes)
	{
		logger.debug("saveOrUpdateProject() : {}", project);

		if (result.hasErrors())
		{
			populateDefaultModel(model, project);
			return "projects/projectform";
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "success");
			if (project.isNew())
			{
				redirectAttributes.addFlashAttribute("msg", "Project added successfully!");
			}
			else
			{
				redirectAttributes.addFlashAttribute("msg", "Project updated successfully!");
			}

			project.setUsers(userRepository.findOne(1l));
			project.setRegistereddt(Auth.getCurrentDate());
			project.setLastvisitdt(Auth.getCurrentDate());
			projectRepository.save(project);

			return "redirect:/projects/" + project.getId();
		}
	}

	// delete project
	@RequestMapping(value = "/projects/delete/{id}", method = RequestMethod.POST)
	public String deleteProject(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes)
	{
		logger.debug("deleteProject() : {}", id);

		projectRepository.delete(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Project is deleted!");

		return "redirect:/projects";

	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public String showProject(@PathVariable("id") Long id, Model model)
	{

		logger.debug("showProject() id: {}", id);

		Projects project = projectRepository.findOne(id);
		if (project == null)
		{
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Project not found");
		}
		model.addAttribute("project", project);

		return "projects/show";

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex)
	{

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("project/show");
		model.addObject("msg", "project not found");

		return model;
	}

	@RequestMapping(value = "/projects/add", method = RequestMethod.GET)
	public String showAddProjectForm(Model model)
	{
		logger.debug("showAddProjectForm()");
		Projects project = new Projects();
		// set default value
		model.addAttribute("projectForm", project);
		populateDefaultModel(model, project);
		return "projects/projectform";

	}

	@RequestMapping(value = "/projects/update/{id}", method = RequestMethod.GET)
	public String showUpdateProjectForm(@PathVariable("id") Long id, Model model)
	{

		logger.debug("showUpdateProjectForm() : {}", id);

		Projects project = projectRepository.findOne(id);
		model.addAttribute("projectForm", project);

		populateDefaultModel(model, project);

		return "projects/projectform";

	}

	@RequestMapping(value = "/projects/select/{id}", method = RequestMethod.GET)
	public String selectProject(@PathVariable("id") Long id, Model model, HttpServletRequest request)
	{
		Projects project = projectRepository.findOne(id);
		model.addAttribute("projectForm", project);
		HttpSession session = request.getSession();
		session.setAttribute("projectId", id);
		
		populateDefaultModel(model, project);
		return "projects/projectform";
	}

	private void populateDefaultModel(Model model, Projects project)
	{
		Map<Integer, String> access = new LinkedHashMap<Integer, String>();
		access.put(0, "Private");
		access.put(1, "Selected");
		access.put(2, "For registered users (readonly)");
		access.put(3, "For registered users (allow edit)");
		access.put(4, "Public (readonly)");
		access.put(5, "Public (allow edit)");
		model.addAttribute("accessList", access);

		model.addAttribute("projTypes", getProjectTypes());
		model.addAttribute("userList", getUsers());

	}

	@ModelAttribute("usersCache")
	public List<Users> getUsers()
	{
		List<Users> users = (List<Users>) userRepository.findAll();
	    //Hibernate.initialize(users);
		return users;
	}

	@ModelAttribute("projectTypesCache")
	public List<Projecttypes> getProjectTypes()
	{
		List<Projecttypes> projecttypes = (List<Projecttypes>) projectTypesRepository.findAll();
		//Hibernate.initialize(projecttypes);
		return projecttypes;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception
	{
		binder.registerCustomEditor(Set.class, "projectmanagerses", new CustomCollectionEditor(Set.class)
		{
			protected Object convertElement(Object element)
			{
				if (element instanceof String)
				{
					Users user = userRepository.findOne(Long.parseLong(element.toString()));
					return user;
				}
				return null;
			}
		});

		binder.registerCustomEditor(Set.class, "projectreviewerses", new CustomCollectionEditor(Set.class)
		{
			protected Object convertElement(Object element)
			{
				if (element instanceof String)
				{
					Users user = userRepository.findOne(Long.parseLong(element.toString()));
					return user;
				}
				return null;
			}
		});

		binder.registerCustomEditor(Set.class, "projectmemberses", new CustomCollectionEditor(Set.class)
		{
			protected Object convertElement(Object element)
			{
				if (element instanceof String)
				{
					Users user = userRepository.findOne(Long.parseLong(element.toString()));
					return user;
				}
				return null;
			}
		});

		binder.registerCustomEditor(Projecttypes.class, "projecttypes", new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				Projecttypes ch = projectTypesRepository.findOne(Long.parseLong(text));
				setValue(ch);
			}
		});
	}
}
