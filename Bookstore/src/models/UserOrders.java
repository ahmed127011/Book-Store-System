package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_orders")
public class UserOrders {

    @EmbeddedId
    private OrderPK pk;
    private long quantity;
    @Column(name = "check_out_date")
    private java.sql.Date checkOutDate;

    public UserOrders() {
    }

    public UserOrders(String isbn, String userName, String email, long quantity) {
        this.pk=new OrderPK();
        this.pk.isbn = isbn;
        this.pk.userName = userName;
        this.pk.email = email;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return pk.isbn;
    }

    public void setIsbn(String isbn) {
        this.pk.isbn = isbn;
    }


    public String getUserName() {
        return pk.userName;
    }

    public void setUserName(String userName) {
        this.pk.userName = userName;
    }


    public String getEmail() {
        return pk.email;
    }

    public void setEmail(String email) {
        this.pk.email = email;
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
        return order.pk.isbn.equals(this.pk.isbn) &&
                order.pk.userName.equals(this.pk.userName) &&
                order.pk.email.equals(this.pk.email);
    }
}

@Embeddable
 class OrderPK implements Serializable {
    @Column(name = "ISBN")
    String isbn;

    @Column(name = "user_name")
    String userName;
    String email;

}