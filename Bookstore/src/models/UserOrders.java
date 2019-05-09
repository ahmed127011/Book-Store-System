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
        this.pk = new OrderPK();
    }

    public UserOrders(String isbn, String userName, long quantity) {
        this.pk = new OrderPK();
        this.pk.isbn = isbn;
        this.pk.userName = userName;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return this.pk.orderId;
    }

    public void setOrderId(long orderId) {
        this.pk.orderId = orderId;
    }


    public String getIsbn() {
        return this.pk.isbn;
    }

    public void setIsbn(String isbn) {
        this.pk.isbn = isbn;
    }


    public String getUserName() {
        return this.pk.userName;
    }

    public void setUserName(String userName) {
        this.pk.userName = userName;
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
        return order.pk.orderId == this.pk.orderId &&
                order.pk.isbn.equals(this.pk.isbn) &&
                order.pk.userName.equals(this.pk.userName);
    }

}

@Embeddable
class OrderPK implements Serializable {
  //  @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id")
    long orderId;
    @Column(name = "ISBN")
    String isbn;
    @Column(name = "user_name")
    String userName;
}
