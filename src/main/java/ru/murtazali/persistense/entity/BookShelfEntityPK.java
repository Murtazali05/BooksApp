package ru.murtazali.persistense.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookShelfEntityPK implements Serializable {
    private int shelfId;
    private int bookId;
    private int userId;

    @Id
    @Column(name="shelf_id", nullable = false)
    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    @Id
    @Column(name="book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Id
    @Column(name="user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookShelfEntityPK that = (BookShelfEntityPK) o;
        return shelfId == that.shelfId &&
                bookId == that.bookId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(shelfId, bookId, userId);
    }
}
