package edu.tamu.ctv.controller;

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
	public String export(Model model)
	{
		logger.debug("export()");
		return "import/import";
	}
}
