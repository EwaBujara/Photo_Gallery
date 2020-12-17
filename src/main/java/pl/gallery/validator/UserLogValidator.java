package pl.gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.gallery.entities.User;
import pl.gallery.repositories.UserRepository;
import pl.gallery.services.UserService;

@Component
public class UserLogValidator implements Validator {

	 @Autowired
	 UserRepository userRepository;
	 @Autowired
	 UserService userService;
	 
	@Override
	public boolean supports(Class<?> clazz) {
		 return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		 User user = (User) o;

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
	        if(userRepository.findByName(user.getName()) == null){
	            errors.rejectValue("name", "nonExisting.userLog.name");
	        } 

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
	        if(!userService.verify(user)){
	            errors.rejectValue("password", "nonMatching.userLog.password");
	        }
		
	}

}
