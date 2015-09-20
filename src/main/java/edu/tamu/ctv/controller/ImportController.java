package edu.tamu.ctv.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImportController
{
	private final Logger logger = LoggerFactory.getLogger(ImportController.class);

	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String export(Model model, HttpServletRequest request)
	{
		logger.debug("import()");
		Object projectIdObj = request.getSession().getAttribute("projectId");
		
		if (projectIdObj != null)
		{
			return "redirect:/upload?projectId="+projectIdObj;
		}
		else
		{
			model.addAttribute("TODOAction", "/upload?projectId=");
			return "redirect:/projects";
		}
		
	}
}
