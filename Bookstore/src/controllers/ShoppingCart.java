package controllers;

import models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaledabdelfattah on 5/7/19.
 */
public class ShoppingCart {
    private List<Book> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addBook(Book book) {
        cart.add(book);
    }

    public void removeBook(Book book) {
        cart.remove(book);
    }

    public List<Book> getBooks() {
        return this.cart;
    }

    public void checkout() {
        // TODO Insert books into UserOrders Table
    }
}
