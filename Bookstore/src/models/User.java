package models;

import javax.persistence.*;

import controllers.ShoppingCart;

import java.io.Serializable;

@Entity
@Table(name = "user")
public class User {
    @EmbeddedId
    private UserPK pk;
    private String password;
    private String phone;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last name")
    private String lastName;
    private String address;
    @Column(name = "is_manager")
    private String isManger;


    public User() {
    }

    public User(String userName, String email, String password, String phone) {
        this.pk=new UserPK();
        this.pk.userName = userName;
        this.pk.email = email;
        this.password = password;
        this.phone = phone;
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


    public void checkout() {
        // TODO Send Session from SessionFactory to checkout function
//        this.shoppingCart.checkout(session);
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return user.pk.userName.equals(this.pk.userName) &&
                user.pk.email.equals(this.pk.email) &&
                user.password.equals(this.password);
    }

}

@Embeddable
class UserPK implements Serializable {
    @Column(name = "user_name")
    String userName;
    String email;
}