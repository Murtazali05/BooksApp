package ru.murtazali.service.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewBookDTO {
    private int id;
    private String title;
    private String description;
    private Integer pubyear;
    private String pic;
    private String link;
    private String linkShop;
    private List<Integer> authorsId;
    private List<Integer> categoriesId;

    public NewBookDTO(BookDTO bookDTO) {
        this.id = bookDTO.getId();
        this.title = bookDTO.getTitle();
        this.description = bookDTO.getDescription();
        this.pubyear = bookDTO.getPubyear();
        this.pic = bookDTO.getPic();
        this.link = bookDTO.getLink();
        this.linkShop = bookDTO.getLinkShop();
    }
}
