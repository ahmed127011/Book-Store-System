package models;

import controllers.ShoppingCart;

public class LoggedUser {
    private User user;
    private ShoppingCart cart;

    private static LoggedUser userInstance;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    private boolean isLoggedIn = false;

    private LoggedUser() {
        this.user = new User();
        this.cart = new ShoppingCart();
        this.isLoggedIn = false;
    }

    public static LoggedUser getInstance() {
        if (userInstance == null) {
            userInstance = new LoggedUser();
        }
        return userInstance;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void logOut() {
        userInstance = null;
        isLoggedIn = false;
    }
}
