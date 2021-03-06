package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "shelf", schema = "book")
public class ShelfEntity {
    private int id;
    private String name;
    private Collection<BookshelfEntity> bookshelves;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelfEntity that = (ShelfEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "shelf")
    public Collection<BookshelfEntity> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Collection<BookshelfEntity> bookshelves) {
        this.bookshelves = bookshelves;
    }
}
