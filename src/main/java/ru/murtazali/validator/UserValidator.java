package ru.murtazali.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.murtazali.service.dto.user.UserDTO;
import ru.murtazali.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        if (user.getName().length() < 3 || user.getName().length() > 45) {
            errors.rejectValue("name", "User.name.length");
        }

        if (userService.getUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Такой Email уже существует");
        }

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.matches()) {
            errors.rejectValue("email", "Email.incorrectly");
        }

        if (user.getPassword().length() < 3 || user.getPassword().length() > 15) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Пароли не совпадают");
        }
    }
}
