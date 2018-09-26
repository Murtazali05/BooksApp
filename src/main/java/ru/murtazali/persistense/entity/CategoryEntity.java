package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "book")
public class CategoryEntity {
    private int id;
    private String name;
    private String urlCategory;

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
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url_category", nullable = false, length = 255)
    public String getUrlCategory() {
        return urlCategory;
    }

    public void setUrlCategory(String urlCategory) {
        this.urlCategory = urlCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(urlCategory, that.urlCategory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, urlCategory);
    }

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    public Collection<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookEntity> books) {
        this.books = books;
    }
}
