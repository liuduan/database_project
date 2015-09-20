package edu.tamu.ctv.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.tamu.ctv.utils.importdata.ImportManager;

@Controller
public class FileUploadController
{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private ImportManager importManager;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(@RequestParam("projectId") String projectId, Model model)
	{
		logger.debug("upload()");
		model.addAttribute("importProjectId", projectId);
		return "fileupload/upload";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId, final RedirectAttributes redirectAttributes)
	{
		String returnString = "";
		if (!file.isEmpty() && StringUtils.isNotBlank(projectId))
		{
			Long prjId = Long.valueOf(projectId);
			try
			{
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists()) dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				parseFile(serverFile, prjId);
				
				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				redirectAttributes.addFlashAttribute("css", "success");
				returnString = "You successfully uploaded file=" + name + ". File is processing.";
			}
			catch (Exception e)
			{
				redirectAttributes.addFlashAttribute("css", "error");
				returnString = "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "warning");
			returnString = "You failed to upload " + name + " because the file was empty.";
		}
		
		redirectAttributes.addFlashAttribute("msg", returnString);
		return "redirect:/projects/" + projectId;
	}

	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files)
	{

		if (files.length != names.length) return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++)
		{
			MultipartFile file = files[i];
			String name = names[i];
			try
			{
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists()) dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "<br />";
			}
			catch (Exception e)
			{
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	private void parseFile(File serverFile, Long projectId)
	{
		try
		{
			importManager.setFile(serverFile.getPath());
			importManager.setProject(projectId);
			Thread thread = new Thread(importManager);
			thread.start();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}

	}
}
