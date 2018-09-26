package ru.murtazali.service.dto;

import lombok.Getter;
import lombok.Setter;
import ru.murtazali.persistense.entity.RoleEntity;

@Getter
@Setter
public class RoleDTO {
    private int id;
    private String name;

    public RoleDTO(RoleEntity role) {
        id = role.getId();
        name = role.getName();
    }
}
