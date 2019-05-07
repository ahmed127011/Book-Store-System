package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "ISBN")
    private String isbn;
    private String title;
    private long price;
    @Column(name = "publication_date")
    private java.sql.Date publicationDate;
    private long quantity;
    private long threshold;
    @Column(name = "required_quantity")
    private long requiredQuantity;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "publisher_name")
    private String publisherName;

    public Book() {
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    public java.sql.Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(java.sql.Date publicationDate) {
        this.publicationDate = publicationDate;
    }


    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }


    public long getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(long requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public boolean equals(Object object) {
        Book book = (Book) object;
        return book.isbn.equals(this.isbn);
    }

}
