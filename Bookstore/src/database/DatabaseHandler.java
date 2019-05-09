package database;

import models.*;

import java.sql.Date;
import java.util.List;

public interface DatabaseHandler {
    boolean signUp(User user);

    boolean login(String username,String password);

    void logout();

    boolean addNewBook(Book book);

    boolean updateBookData(Book book);

    boolean UpdateUserData();

    /**
     * order a book from suppliers
     *
     * @param book     book ISBN and data
     * @param quantity
     * @return orderID
     */
    int orderFromSupplier(Book book, int quantity);

    boolean confirmOrder(UserOrders order);

    boolean promoteUser(String username);

    List<User> getTop5Customers();

    List<Book> viewTopSellingBooks();

    //Todo get proper data about books ie. price ranges
    List<Book> findBook(BookDAO book);

    boolean addToShoppingCard(User user, Book book, int quantity);

    List<Book> ShowShoppingCardInfo(User user);

    boolean removeShoppingCard(User user);

    boolean Checkout(User user, String creditCard, Date expireDate);


}
