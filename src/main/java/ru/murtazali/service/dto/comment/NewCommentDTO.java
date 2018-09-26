package ru.murtazali.service.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class NewCommentDTO {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 100, max = 5000)
    private String content;

    @NotNull
    private Integer bookId;

}
