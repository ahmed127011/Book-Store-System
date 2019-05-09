package models;

import javax.persistence.*;

@Entity
@Table(name = "library_orders")
public class LibraryOrders {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "ISBN")
    private String isbn;
    private long quantity;
    @Column(name = "ordered_date")
    private java.sql.Date orderedDate;
    private boolean confirmed;

    public LibraryOrders() {
    }

    public LibraryOrders(String isbn, long quantity) {
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public java.sql.Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(java.sql.Date orderedDate) {
        this.orderedDate = orderedDate;
    }


    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

}
