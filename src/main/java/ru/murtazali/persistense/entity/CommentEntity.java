package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "book")
public class CommentEntity {
    private int id;
    private String title;
    private String content;
    private Timestamp date;
    private int countLike;
    private int countDislike;
    private BookEntity book;
    private UserEntity user;
    private Collection<VoteEntity> votes;

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
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 65535, columnDefinition = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "count_like", nullable = false)
    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    @Basic
    @Column(name = "count_dislike", nullable = false)
    public int getCountDislike() {
        return countDislike;
    }

    public void setCountDislike(int countDislike) {
        this.countDislike = countDislike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id == that.id &&
                countLike == that.countLike &&
                countDislike == that.countDislike &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, date, countLike, countDislike);
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "comment")
    public Collection<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(Collection<VoteEntity> votes) {
        this.votes = votes;
    }
}
