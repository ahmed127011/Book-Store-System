package database;

import models.*;

import java.sql.Date;
import java.util.List;

public interface DatabaseHandler {
    boolean signUp(User user);

    boolean login(String username,String password);

    void logout();

    boolean addNewBook(Book book, User user);

    boolean updateBookData(Book oldBook, Book newBook);

    boolean UpdateUserData(User user);

    /**
     * order a book from suppliers
     *
     * @param book     book ISBN and data
     * @param quantity
     * @return orderID
     */
    int orderFromSupplier(Book book, int quantity);

    boolean confirmOrder(UserOrders order);

    boolean promoteUser(User user,User manager);

    List<User> getTop5Customes();

    List<Book> viewTopSellingBooks();

    //Todo get proper data about books ie. price ranges
    List<Book> findBook(Book book);

    boolean addToShoppingCard(User user, Book book, int quantity);

    List<Book> ShowShoppingCardInfo(User user);

    boolean removeShoppingCard(User user);

    boolean Checkout(User user, String creditCard, Date expireDate);


}
