package edu.tamu.ctv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.repository.UsersRepository;

@Controller
public class InfoProviderController
{
	private final Logger logger = LoggerFactory.getLogger(InfoProviderController.class);
	
	@Autowired
	private UsersRepository userRepository;
	
	@RequestMapping(value = "/userslistbyfilter", method = RequestMethod.GET)
	public @ResponseBody List<Users> getUsersMatches(@ModelAttribute("q") String q, BindingResult result)
	{
		return (List<Users>) userRepository.findUserByFirstnameLikeOrLastnameLikeOrLoginLike(q, q, q);
	}
}
