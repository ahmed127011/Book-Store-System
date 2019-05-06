package models;


public class LibraryOrders {

    private long orderId;
    private String isbn;
    private long quantity;
    private java.sql.Date orderedDate;
    private String confirmed;

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


    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

}
