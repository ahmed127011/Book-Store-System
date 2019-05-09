package models;

public class BookDAO {
    private String isbn;
    private String title;
    private long lowerPrice;
    private long upperPrice;

    public BookDAO() {
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
