package models;


public class BookAuthors {

    private String isbn;
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
