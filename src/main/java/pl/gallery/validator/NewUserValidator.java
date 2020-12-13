package pl.gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.gallery.entities.*;
import pl.gallery.repositories.UserRepository;

@Component
public class NewUserValidator implements Validator {

	@Autowired
	UserRepository userRepository;
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
	        if (user.getName().length() < 6 || user.getName().length() > 32) {
	            errors.rejectValue("name", "Size.userForm.name");
	        }
	        if (userRepository.findByName(user.getName()) != null) {
	            errors.rejectValue("name", "Duplicate.userForm.name");
	        }

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
	        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
	            errors.rejectValue("password", "Size.userForm.password");
	        }
		
	}

}
