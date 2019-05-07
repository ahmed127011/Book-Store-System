package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_orders")
public class UserOrders {
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "user_name")
    private String userName;
    private String email;
    private long quantity;
    @Column(name = "check_out_date")
    private java.sql.Date checkOutDate;

    public UserOrders() {
    }

    public UserOrders(String isbn, String userName, String email, long quantity) {
        this.isbn = isbn;
        this.userName = userName;
        this.email = email;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public java.sql.Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(java.sql.Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object object) {
        UserOrders order = (UserOrders) object;
        return order.isbn.equals(this.isbn) &&
                order.userName.equals(this.userName) &&
                order.email.equals(this.email);
    }
}
