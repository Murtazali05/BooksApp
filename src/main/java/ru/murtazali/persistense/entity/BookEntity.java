package ru.murtazali.persistense.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "book")
public class BookEntity {
    private int id;
    private String title;
    private String description;
    private int pubyear;
    private String pic;
    private String link;
    private String linkShop;
    private Collection<BookshelfEntity> bookshelves;
    private Collection<CommentEntity> comments;

    private Collection<AuthorEntity> authors;
    private Collection<CategoryEntity> categories;

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
    @Column(name = "description", nullable = false, length = 65535, columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "pubyear", nullable = false)
    public int getPubyear() {
        return pubyear;
    }

    public void setPubyear(int pubyear) {
        this.pubyear = pubyear;
    }

    @Basic
    @Column(name = "pic", nullable = false, length = 45)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "link", nullable = false, length = 45)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "link_shop", nullable = false, length = 45)
    public String getLinkShop() {
        return linkShop;
    }

    public void setLinkShop(String linkShop) {
        this.linkShop = linkShop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                pubyear == that.pubyear &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(pic, that.pic) &&
                Objects.equals(link, that.link) &&
                Objects.equals(linkShop, that.linkShop);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, pubyear, pic, link, linkShop);
    }

    @OneToMany(mappedBy = "book")
    public Collection<BookshelfEntity> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Collection<BookshelfEntity> bookshelves) {
        this.bookshelves = bookshelves;
    }

    @OneToMany(mappedBy = "book")
    public Collection<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentEntity> comments) {
        this.comments = comments;
    }

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    public Collection<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<AuthorEntity> authors) {
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public Collection<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Collection<CategoryEntity> categories) {
        this.categories = categories;
    }
}
