package ru.murtazali.service.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.murtazali.persistense.entity.UserEntity;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private Date birthday;
    private String linkAvatarka;
    private int idRole;

    public UserDTO(UserEntity user) {
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();
        confirmPassword = user.getPassword();
        name = user.getName();
        birthday = user.getBirthday();
        linkAvatarka = user.getLinkAvatarka();
        idRole = user.getRole().getId();

    }
}
