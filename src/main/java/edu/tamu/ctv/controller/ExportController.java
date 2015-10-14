package edu.tamu.ctv.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.service.ExportService;

@Controller
public class ExportController
{
	private final Logger logger = LoggerFactory.getLogger(ExportController.class);

	@Autowired
	private ExportService exportService;
	@Autowired
	private ProjectsRepository projectRepository;
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String export(Model model, HttpServletRequest request)
	{
		logger.debug("export()");
		
		Object projectIdObj = request.getSession().getAttribute("projectId");
		if (projectIdObj != null)
		{
			return "redirect:/export/byproject/" + projectIdObj;
		}
		else
		{
			model.addAttribute("todoaction", "export");
			return "redirect:/projects/select";
		}
	}

	@RequestMapping(value = "/export/byproject/{id}", method = RequestMethod.GET)
	public void export(@PathVariable("id") Long id, HttpServletResponse response) throws IOException
	{
		logger.debug("export() projectid: {}", id);
		
		Projects project = projectRepository.findOne(id);
		if (project != null)
		{
			exportService.ExportByProject(project, null, null, response);
		}
		else
		{
			String reportName = "error.csv";
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		}
		response.getOutputStream().flush();
	}
	
	@RequestMapping(value = "/export/byparams", method = RequestMethod.GET)
	public void selectProject(Model model,
			@RequestParam(value = "projectid") Long projectid,
			@RequestParam(value = "orderid[]", required = false) List<Long> orderid,
			@RequestParam(value = "componentid[]", required = false) List<Long> componentid,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		logger.debug("export() projectid: {}", projectid);
		
		Projects project = projectRepository.findOne(projectid);
		if (project != null)
		{
			exportService.ExportByProject(project, orderid, componentid, response);
		}
		else
		{
			String reportName = "error.csv";
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		}
		response.getOutputStream().flush();
	}

}
