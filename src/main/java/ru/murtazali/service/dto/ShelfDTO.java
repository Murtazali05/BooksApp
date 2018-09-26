package ru.murtazali.service.dto;

import lombok.Getter;
import lombok.Setter;
import ru.murtazali.persistense.entity.ShelfEntity;

@Getter
@Setter
public class ShelfDTO {
    private int id;
    private String name;

    public ShelfDTO(ShelfEntity shelfEntity) {
        this.id = shelfEntity.getId();
        this.name = shelfEntity.getName();
    }
}
