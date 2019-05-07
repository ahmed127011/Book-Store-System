package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//TODO add key

@Entity
@Table (name = "book_authors")
public class BookAuthors {

    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "author_name")
    private String authorName;

    public BookAuthors() {
    }

    public BookAuthors(String isbn, String authorName) {
        this.isbn = isbn;
        this.authorName = authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
