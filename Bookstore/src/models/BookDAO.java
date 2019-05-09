package models;

import java.util.List;

public class BookDAO {
    private String isbn;
    private String title;
    private long lowerPrice;
    private long upperPrice;
    private String author;
    private String publisher;
    private List<String> categories;

    public BookDAO() {
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLowerPrice(long lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public long getLowerPrice() {
        return lowerPrice;
    }

    public void setUpperPrice(long upperPrice) {
        this.upperPrice = upperPrice;
    }

    public long getUpperPrice() {
        return upperPrice;
    }
}
