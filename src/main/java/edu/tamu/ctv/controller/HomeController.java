package edu.tamu.ctv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.customdefined.ContactForm;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Controller
public class HomeController
{
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model)
	{
		logger.debug("index()");
		return "/home";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String showContact(Model model)
	{
		logger.debug("index()");
		return "/contact";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute("contactForm") @Validated ContactForm contactForm, BindingResult result, Model model, final RedirectAttributes redirectAttributes)
	{
		logger.debug("sendMessage() : {}", contactForm);

		if (result.hasErrors())
		{
			return "contact";
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "success");
		}
		
		return "redirect:/home";
	}
}
