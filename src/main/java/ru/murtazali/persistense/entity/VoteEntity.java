package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vote", schema = "book")
public class VoteEntity {
    private int id;
    private UserEntity user;
    private CommentEntity comment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteEntity that = (VoteEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }
}
