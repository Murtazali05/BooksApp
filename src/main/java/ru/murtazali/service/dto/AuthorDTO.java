package ru.murtazali.service.dto;

import lombok.Getter;
import lombok.Setter;
import ru.murtazali.persistense.entity.AuthorEntity;

@Getter
@Setter
public class AuthorDTO {
    private int id;
    private String fio;
    private String about;
    private String pic;
    private int countBook;

    public AuthorDTO(AuthorEntity author) {
        id = author.getId();
        fio = author.getFio();
        about = author.getAbout();
        pic = author.getPic();
        countBook = author.getBooks().size();
    }
}
