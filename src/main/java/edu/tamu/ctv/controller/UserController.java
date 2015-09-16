package edu.tamu.ctv.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.UsersRepository;
import edu.tamu.ctv.service.UsersService;
import edu.tamu.ctv.service.validator.UserFormValidator;

@Controller
public class UserController
{
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	UserFormValidator userFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		binder.setValidator(userFormValidator);
	    
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));*/
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model)
	{
		logger.debug("showAllUsers()");
		model.addAttribute("users", userRepository.findAll());
		return "users/list";
	}
	
	@RequestMapping(value = "/userslist", method = RequestMethod.GET)
	public @ResponseBody List<Users> getUsersList() {
		return (List<Users>)userRepository.findAll();
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated Users user, BindingResult result, Model model, final RedirectAttributes redirectAttributes)
	{
		logger.debug("saveOrUpdateUser() : {}", user);

		if (result.hasErrors())
		{
			populateDefaultModel(model);
			return "users/userform";
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "success");
			if (user.isNew())
			{
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			}
			else
			{
				redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
			}

			usersService.save(user);

			return "redirect:/users/" + user.getId();
		}
	}

	// delete user
	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes)
	{

		logger.debug("deleteUser() : {}", id);

		userRepository.delete(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");

		return "redirect:/users";

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") Long id, Model model)
	{

		logger.debug("showUser() id: {}", id);

		Users user = userRepository.findOne(id);
		if (user == null)
		{
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "users/show";

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex)
	{
		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("user/show");
		model.addObject("msg", "user not found");

		return model;
	}

	// show add user form
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model)
	{
		logger.debug("showAddUserForm()");
		Users user = new Users();
		// set default value
		model.addAttribute("userForm", user);
		populateDefaultModel(model);
		
		return "users/userform";
	}

	// show update form
	@RequestMapping(value = "/users/update/{id}", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") Long id, Model model)
	{
		logger.debug("showUpdateUserForm() : {}", id);
		Users user = userRepository.findOne(id);
		model.addAttribute("userForm", user);
		populateDefaultModel(model);

		return "users/userform";

	}

	private void populateDefaultModel(Model model)
	{
		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("US", "United Stated");
		country.put("CN", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		model.addAttribute("countryList", country);

	}
}
