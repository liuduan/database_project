package edu.tamu.ctv.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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

import edu.tamu.ctv.utils.importdata.ImportManager;

@Controller
public class FileUploadController
{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private ImportManager importManager;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model)
	{
		logger.debug("upload()");
		return "fileupload/upload";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
	{
		if (!file.isEmpty())
		{
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

				parseFile(serverFile);
				
				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			}
			catch (Exception e)
			{
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		else
		{
			return "You failed to upload " + name + " because the file was empty.";
		}
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
	
	private void parseFile(File serverFile)
	{
		try
		{
			importManager.setFile(serverFile.getPath());
			//Runnable func = new ImportManager();
			Thread thread = new Thread(importManager);
			thread.start();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}

	}
}
