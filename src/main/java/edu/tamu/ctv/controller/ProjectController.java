package edu.tamu.ctv.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.entity.enums.Access;
import edu.tamu.ctv.entity.enums.Status;
import edu.tamu.ctv.repository.ProjectTypesRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.ProjectService;
import edu.tamu.ctv.service.validator.ProjectFormValidator;

@Controller
public class ProjectController
{
	private final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectFormValidator projectFormValidator;
	
	@Autowired
	private ProjectService projectService;
	
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
		
			projectService.save(project);

			return "redirect:/projects/" + project.getId();
		}
	}

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

	@RequestMapping(value = "/projects/select", method = RequestMethod.GET)
	public String selectProject(Model model, @RequestParam(value = "todoaction", required = false) String TODOAction, HttpServletRequest request)
	{
		model.addAttribute("todoaction", TODOAction);
		model.addAttribute("projects", projectRepository.findAll());
		return "projects/select";
	}
	
	@RequestMapping(value = "/projects/select/{id}", method = RequestMethod.GET)
	public String selectProject(@PathVariable("id") Long id, @RequestParam(value = "todoaction", required = false) String TODOAction, Model model, HttpServletRequest request)
	{
		Projects project = projectRepository.findOne(id);

		HttpSession session = request.getSession();
		session.setAttribute("projectId", id);
		session.setAttribute("currentProjectCode", project.getCode());

		model.addAttribute("projectForm", project);
		if (TODOAction != null)
		{
			if (TODOAction.equals("import"))
			{
				return "redirect:/upload?projectId=" + id;
			}
			else if (TODOAction.equals("export"))
			{
				return "redirect:/export/" + id;
			}
		}
		return "projects/show";
	}

	private void populateDefaultModel(Model model, Projects project)
	{
		model.addAttribute("projectForm", project);
		model.addAttribute("usersCache", (List<Users>) userRepository.findAll());
		model.addAttribute("projectTypesCache", (List<Projecttypes>) projectTypesRepository.findAll());
		model.addAttribute("accessList", Access.values());
		model.addAttribute("statusList", Status.values());
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception
	{
		binder.setValidator(projectFormValidator);
		
		binder.registerCustomEditor(Projecttypes.class, "projecttypes", new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				Projecttypes ch = projectTypesRepository.findOne(Long.parseLong(text));
				setValue(ch);
			}
		});
		
		binder.registerCustomEditor(Access.class, new PropertyEditorSupport()
		{
	        @Override
	        public void setAsText(String value) throws IllegalArgumentException
	        {
	            if (StringUtils.isBlank(value)) return;
	            setValue(Access.parse(Integer.valueOf(value)));
	        }

	    });
		
		binder.registerCustomEditor(Status.class, new PropertyEditorSupport()
		{
	        @Override
	        public void setAsText(String value) throws IllegalArgumentException
	        {
	            if (StringUtils.isBlank(value)) return;
	            setValue(Status.parse(Integer.valueOf(value)));
	        }

	    });
	}
}
