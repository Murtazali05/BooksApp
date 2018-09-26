package ru.murtazali.service.dto.comment;

import lombok.Getter;
import lombok.Setter;
import ru.murtazali.persistense.entity.CommentEntity;
import ru.murtazali.service.dto.book.BookDTO;
import ru.murtazali.service.dto.user.UserDTO;
import ru.murtazali.util.DateFormatUtil;

@Getter
@Setter
public class CommentDTO {
    private int id;
    private String title;
    private String content;
    private String createDate;
    private int countLike;
    private int countDislike;
    private BookDTO book;
    private UserDTO user;
    private String attitude;

    public CommentDTO(CommentEntity commentEntity) {
        id = commentEntity.getId();
        title = commentEntity.getTitle();
        content = commentEntity.getContent();
        createDate = new DateFormatUtil().toString(commentEntity.getDate());
        countLike = commentEntity.getCountLike();
        countDislike = commentEntity.getCountDislike();
        book = new BookDTO(commentEntity.getBook());
        user = new UserDTO(commentEntity.getUser());

        if (countLike == countDislike)
            attitude = "neutral";
        else if (countLike > countDislike)
            attitude = "positive";
        else
            attitude = "unpositive";
    }
}
