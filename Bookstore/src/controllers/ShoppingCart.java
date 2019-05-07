package controllers;

import models.UserOrders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaledabdelfattah on 5/7/19.
 */
public class ShoppingCart {
    private List<UserOrders> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addBook(UserOrders order) {
        cart.add(order);
    }

    public void removeBook(UserOrders order) {
        cart.remove(order);
    }

    public List<UserOrders> getBooks() {
        return this.cart;
    }

    /**
     * Takes session and insert user orders into user_orders table.
     */
    public void checkout() {
        // TODO Insert books into UserOrders Table
    }
}
