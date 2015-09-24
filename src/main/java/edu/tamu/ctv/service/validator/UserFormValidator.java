package edu.tamu.ctv.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.tamu.ctv.entity.Users;
import edu.tamu.ctv.service.UsersService;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	UsersService usersService;
	
	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		Users user = (Users) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty.userForm.login");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty.userForm.firstname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty.userForm.lastname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.userForm.phone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "NotEmpty.userForm.address1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.userForm.city");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userForm.country");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "NotEmpty.userForm.state");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "NotEmpty.userForm.zip");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "NotEmpty.userForm.birthday");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organization", "NotEmpty.userForm.organization");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organaddress", "NotEmpty.userForm.organaddress");

		if(!emailValidator.valid(user.getEmail())){
			errors.rejectValue("email", "Pattern.userForm.email");
		}
		if(user.getCountry().equalsIgnoreCase("none")){
			errors.rejectValue("country", "NotEmpty.userForm.country");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}
		
		if (usersService.findByLogin(user.getLogin()) != null)
		{
			errors.rejectValue("login", "Diff.userform.exist");
		}
	}

}
