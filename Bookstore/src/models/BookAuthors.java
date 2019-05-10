package models;

import javax.persistence.*;
import java.io.Serializable;

//TODO add key

@Entity
@Table(name = "book_authors")
public class BookAuthors {
    @EmbeddedId
   private AuthorsPK pk;

    public BookAuthors() {
    }

    public BookAuthors(String isbn, String authorName) {
        pk=new AuthorsPK();
        this.pk.isbn = isbn;
        this.pk.authorName = authorName;
    }

    public String getIsbn() {
        return pk.isbn;
    }

    public void setIsbn(String isbn) {
        this.pk.isbn = isbn;
    }


    public String getAuthorName() {
        return pk.authorName;
    }

    public void setAuthorName(String authorName) {
        this.pk.authorName = authorName;
    }

}

@Embeddable
class AuthorsPK implements Serializable {
    @Column(name = "ISBN")
    String isbn;
    @Column(name = "author_name")
    String authorName;
}