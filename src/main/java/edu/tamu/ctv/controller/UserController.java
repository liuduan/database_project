package edu.tamu.ctv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.tamu.ctv.repository.UsersRepository;

@Controller
public class UserController {

	@Autowired
	private UsersRepository userService;

	/**
	 * Request mapping for user
	 */
	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	public ModelAndView getUsersView() {
		ModelAndView mv = new ModelAndView("usersView");
		mv.addObject("usersModel", userService.findAll());
		return mv;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ModelAndView getUser(@RequestParam("userId") Integer userId) {
		ModelAndView modelAndView = new ModelAndView("singleUserView");
		modelAndView.addObject("user", userService.findOne(userId));
		return modelAndView;
	}

}