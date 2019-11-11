package ru.hetoiiblpb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.hetoiiblpb.exception.DBException;
import ru.hetoiiblpb.model.User;
import ru.hetoiiblpb.service.UserService;

/**
 * Validator for {@link ru.hetoiiblpb.model.User} class,
 * implements {@link org.springframework.validation.Validator}.
 *
 * @author hetoiiblpb
 * @version 1.0.SNAPSHOT
 */

@Component
public class UserValidator implements Validator {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","Required");
        if (user.getName().length() < 8 || user.getName().length() > 32) {
            errors.rejectValue("username","Size.userForm.username");
        }

        try {
            if (userService.getUserByName(user.getName()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword","Different.userForm.password");
        }

    }

}
