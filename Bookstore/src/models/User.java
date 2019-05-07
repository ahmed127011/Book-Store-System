package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import controllers.ShoppingCart;

@Entity
@Table(name = "user")
public class User {

    @Column(name = "user_name")
    private String userName;
    private String email;
    private String password;
    private String phone;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last name")
    private String lastName;
    private String address;
    @Column(name = "is_manager")
    private String isManger;

    private ShoppingCart shoppingCart;

    public User() {
    }

    public User(String userName, String email, String password, String phone) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.shoppingCart = new ShoppingCart();
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getIsManger() {
        return isManger;
    }

    public void setIsManger(String isManger) {
        this.isManger = isManger;
    }

    public void addOrder(UserOrders order) {
        this.shoppingCart.addOrder(order);
    }

    public void removeOrder(UserOrders order) {
        this.shoppingCart.removeOrder(order);
    }

    public void checkout() {
        // TODO Send Session from SessionFactory to checkout function
//        this.shoppingCart.checkout(session);
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return user.userName.equals(this.userName) &&
                user.email.equals(this.email) &&
                user.password.equals(this.password);
    }

}
