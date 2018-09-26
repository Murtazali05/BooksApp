package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "book")
public class UserEntity {
    private int id;
    private String name;
    private String password;
    private String email;
    private Date birthday;
    private String linkAvatarka = "ava.jpg";
    private Collection<BookshelfEntity> bookshelves;
    private Collection<CommentEntity> comments;
    private RoleEntity role;
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "link_avatarka", nullable = true, length = 45)
    public String getLinkAvatarka() {
        return linkAvatarka;
    }

    public void setLinkAvatarka(String linkAvatarka) {
        this.linkAvatarka = linkAvatarka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(linkAvatarka, that.linkAvatarka);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, email, birthday, linkAvatarka);
    }

    @OneToMany(mappedBy = "user")
    public Collection<BookshelfEntity> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Collection<BookshelfEntity> bookshelves) {
        this.bookshelves = bookshelves;
    }

    @OneToMany(mappedBy = "user")
    public Collection<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentEntity> comments) {
        this.comments = comments;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    public Collection<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(Collection<VoteEntity> votes) {
        this.votes = votes;
    }
}
