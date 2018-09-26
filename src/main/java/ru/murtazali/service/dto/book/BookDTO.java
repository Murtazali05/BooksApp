package ru.murtazali.service.dto.book;

import lombok.Getter;
import lombok.Setter;
import ru.murtazali.persistense.entity.BookEntity;

@Getter
@Setter
public class BookDTO {
    private int id;
    private String title;
    private String description;
    private int pubyear;
    private String pic;
    private String link;
    private String linkShop;

    public BookDTO(BookEntity book) {
        id = book.getId();
        title = book.getTitle();
        description = book.getDescription();
        pubyear = book.getPubyear();
        pic = book.getPic();
        link = book.getLink();
        linkShop = book.getLinkShop();
    }
}
