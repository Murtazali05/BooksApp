package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "author", schema = "book")
public class AuthorEntity {
    private int id;
    private String fio;
    private String about;
    private String pic;

    private Collection<BookEntity> books;

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
    @Column(name = "fio", nullable = false, length = 255)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "about", nullable = false, length = 65535, columnDefinition = "text")
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Basic
    @Column(name = "pic", nullable = false, length = 45)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return id == that.id &&
                Objects.equals(fio, that.fio) &&
                Objects.equals(about, that.about) &&
                Objects.equals(pic, that.pic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fio, about, pic);
    }

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    public Collection<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookEntity> books) {
        this.books = books;
    }
}
