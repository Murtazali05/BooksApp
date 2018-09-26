package ru.murtazali.service.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ru.murtazali.persistense.entity.UserEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
public class EditUserDTO {
    @NotNull
    private int id;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 45)
    private String name;
    @NotNull
    @Email
    private String email;
    private Date birthday;
    private String linkAvatarka;


}
