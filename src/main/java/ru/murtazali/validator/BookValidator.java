package ru.murtazali.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.murtazali.service.BookService;
import ru.murtazali.service.dto.book.NewBookDTO;

import java.util.Date;

@Component
public class BookValidator implements Validator {
    @Autowired
    private BookService bookService;

    @Override
    public boolean supports(Class<?> aClass) {
        return NewBookDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewBookDTO newBookDTO = (NewBookDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Поле Заголовок обязательно для заполнения");
        if (newBookDTO.getTitle().length() > 5)
            errors.rejectValue("title", "Длина заголовка должна быть больше 5 символов");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Поле Описание обязательно для заполнения");
        if (newBookDTO.getDescription().length() < 150)
            errors.rejectValue("description","Описание должно быть больше 100 символов");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pubyear", "Поле Год издания обязательно для заполнения");
        if (newBookDTO.getPubyear() < 0)
            errors.rejectValue("pubyear", "Год издания не может быть меньше нуля");
        if (newBookDTO.getPubyear() > new Date().getYear())
            errors.rejectValue("pubyear", "Год издания не может быть больше " + new Date().getYear() + " года");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "link", "Поле Ссылка на загрузку обязательно для заполнения");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "linkShop", "Поле Ссылка на покупку обязательно для заполнения");
    }
}
