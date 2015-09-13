package edu.tamu.ctv.controller;


import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.tamu.ctv.entity.customdefined.Analysis;
import edu.tamu.ctv.service.AnalysisService;

@Controller
public class AnalysisController
{
	private final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
	
	@Autowired
	private AnalysisService analysisService;

	@Autowired
	public void setAnalysisService(AnalysisService analysisService)
	{
		this.analysisService = analysisService;
	}
	
	@RequestMapping(value = "/analysis/{id}", method = RequestMethod.GET)
	public String showAnalysis(@PathVariable("id") Long id, Model model) throws IOException
	{

		logger.debug("showAnalysis() project id: {}", id);

		Analysis analysis = analysisService.findResultsByProject(id);
		if (analysis == null)
		{
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Results not found");
		} 
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String json = ow.writeValueAsString(analysis.results);
		String json = ow.writeValueAsString(analysis.results);
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String json = ow.writeValueAsString(analysis);
//		 ObjectWriter w = objectMapper.writerWithView(Analysis.class);
		ObjectMapper mapper = new ObjectMapper();
//		Object jsono = mapper.readValue(json, Object.class);
		model.addAttribute("results",  json);
		
		json = ow.writeValueAsString(analysis.columns); 
		model.addAttribute("columns",  json);
		model.addAttribute("columnheaders",  analysis.columnheaders);
		model.addAttribute("components",  analysis.components);
		return "analysis/show";

	}
}
