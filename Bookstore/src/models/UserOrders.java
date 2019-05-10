package models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "user_orders")
public class UserOrders {
    @EmbeddedId
    private OrderPK pk;
    private long quantity;
    @Column(name = "check_out_date")
    private java.sql.Date checkOutDate;
    @Column(name = "credit_card_num")
    private String creditCardNum;
    @Column(name = "expiration_date")
    private java.sql.Date expirationDate;

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

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }


    public java.sql.Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(java.sql.Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @param creditCardNum
     * @return true it the creditCarNum is valid number, false otherwise.
     */
    public boolean isValidCreditCardNum() {
        long number = Integer.parseInt(creditCardNum);
        return (getSize(number) >= 13 &&
                getSize(number) <= 16) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }

    public boolean isValidExpirationDate() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        return expirationDate.after(currentDate);
    }

    private int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    private int getDigit(int number) {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    private int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    private boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    private int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    private long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
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
