package edu.tamu.ctv.controller;


import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.entity.customdefined.Analysis;
import edu.tamu.ctv.entity.customdefined.AnalysisResults;
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
		String json = ow.writeValueAsString(analysis.getResults());
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String json = ow.writeValueAsString(analysis);
//		 ObjectWriter w = objectMapper.writerWithView(Analysis.class);
//		ObjectMapper mapper = new ObjectMapper();
//		Object jsono = mapper.readValue(json, Object.class);
		model.addAttribute("results",  json);
		
//		json = ow.writeValueAsString(analysis.columns); 
//		model.addAttribute("columns",  analysis.columns);
		model.addAttribute("columnheaders",  analysis.getColumnHeaders());
		model.addAttribute("components",  analysis.getComponents());
		model.addAttribute("rowTypes",  analysis.getRowTypes());
		model.addAttribute("columnTypes",  analysis.getColumnTypes());
		
		json = ow.writeValueAsString(analysis.getColumnHeaderResults());
		model.addAttribute("columnHeaderResults",  json);
		
		return "analysis/show";

	}
	
	@RequestMapping(value = "/results/get", method = RequestMethod.GET)
	public @ResponseBody AnalysisResults getResults(@RequestParam(value = "orderid[]", required = false) List<Long> orderid, @RequestParam(value = "componentid[]", required = false) List<Long> componentid, Model model)
	{
		logger.debug("getResults() : {}");

		return analysisService.getResultsForAnalysis(orderid, componentid);
	}
}
