package database;

import models.*;

import java.sql.Date;
import java.util.List;

public interface DatabaseHandler {
    boolean signUp(User user);

    boolean login(String username, String password);

    void logout();

    boolean addNewBook(Book book);

    boolean updateBookData(Book book);

    boolean UpdateUserData();

    boolean orderFromSupplier(String isbn, int quantity);

    boolean confirmOrder(LibraryOrders order);

    boolean promoteUser(String username);

    int getprevMonthSales();

    List<String> getTop5Customers();

    List<String> viewTopSellingBooks();

    List<Book> findBook(BookDAO book);

    void addToShoppingCard(String isbn, int quantity);

    List<UserOrders> ShowShoppingCardInfo();

    boolean removeFromShoppingCard(String isbn);

    boolean Checkout(String creditCardNum, java.sql.Date expirationDate);

    List<LibraryOrders> getOrders();

    List<String> getCategories();

    List<Publisher> getPublishers();

    boolean addNewPublisher(Publisher publisher);
    boolean addNewAuthor(BookAuthors author);

    boolean addPublisherAddresses(Publisher publisher,List<String> addresses);

    boolean addPublisherPhones(Publisher publisher,List<String> phones);



}
