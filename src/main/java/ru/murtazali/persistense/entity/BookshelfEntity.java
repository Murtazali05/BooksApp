package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookshelf", schema = "book")
public class BookshelfEntity {
    @EmbeddedId
    private BookShelfEntityPK pk;

    @ManyToOne
    @MapsId("shelfId")
    private ShelfEntity shelf;
    @ManyToOne
    @MapsId("bookId")
    private BookEntity book;
    @ManyToOne
    @MapsId("userId")
    private UserEntity user;

    public BookshelfEntity() {
        pk = new BookShelfEntityPK();
    }

    public BookShelfEntityPK getPk() {
        return pk;
    }

    public void setPk(BookShelfEntityPK pk) {
        this.pk = pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookshelfEntity that = (BookshelfEntity) o;
        return Objects.equals(pk, that.pk) &&
                Objects.equals(shelf, that.shelf) &&
                Objects.equals(book, that.book) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pk, shelf, book, user);
    }

    //    @ManyToOne
//    @JoinColumn(name = "shelf_id", referencedColumnName = "id", nullable = false)
    public ShelfEntity getShelf() {
        return shelf;
    }

    public void setShelf(ShelfEntity shelf) {
        this.shelf = shelf;
    }

//    @ManyToOne
//    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
