package edu.tamu.ctv.service.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.tamu.ctv.entity.Projects;

@Component
public class ProjectFormValidator implements Validator
{
	public boolean supports(Class<?> clazz)
	{
		return Projects.class.equals(clazz);
	}

	public void validate(Object target, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.projectForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.projectForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "access", "NotEmpty.projectForm.access");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projecttype", "NotEmpty.projectForm.projecttype");
	}

}
