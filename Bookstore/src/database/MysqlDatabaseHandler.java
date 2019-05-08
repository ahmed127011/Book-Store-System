package database;

import models.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MysqlDatabaseHandler implements DatabaseHandler {
    private SessionFactory factory;

    public MysqlDatabaseHandler() {
        // create session factory
         factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookAuthors.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(LibraryOrders.class)
                .addAnnotatedClass(Publisher.class)
                .addAnnotatedClass(PublisherAddresses.class)
                .addAnnotatedClass(PublisherPhones.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserOrders.class)
                .buildSessionFactory();

        // create session
    }

    @Override
    public boolean signUp(User user) {
       Session session = factory.getCurrentSession();

        session.beginTransaction();
        session.save(user);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean login(User user, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean addNewBook(Book book, User user) {
        if (user.getIsManger().equals("false"))
            return false;
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        session.save(book);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBookData(Book oldBook, Book newBook) {
        return false;
    }

    @Override
    public boolean UpdateUserData(User user) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        session.update(user);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public int orderFromSupplier(Book book, int quantity) {
        return 0;
    }

    @Override
    public boolean confirmOrder(UserOrders order) {
        return false;
    }

    @Override
    public boolean promoteUser(User user) {
        return false;
    }

    @Override
    public List<User> getTop5Customes() {
        return null;
    }

    @Override
    public List<Book> viewTopSellingBooks() {
        return null;
    }

    @Override
    public List<Book> findBook(Book book) {
        return null;
    }

    @Override
    public boolean addToShoppingCard(User user, Book book, int quantity) {
        Session session = factory.getCurrentSession();

        UserOrders order = new UserOrders(book.getIsbn(), user.getUserName(), user.getEmail(), quantity);
        session.beginTransaction();
        session.save(order);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Book> ShowShoppingCardInfo(User user) {
        Session session = factory.getCurrentSession();

        String query = "From UserOrders u where u.pk.userName= '" + user.getUserName() +"' and u.pk.email='"+user.getEmail()+"'" ;
        session.beginTransaction();
        List<?> orders = session.createQuery(query).getResultList();
        List<Book> books = new ArrayList<>();
        session.getTransaction().commit();
        for (Object o : orders) {
             session = factory.getCurrentSession();

            session.beginTransaction();
            Book b=session.get(Book.class, ((UserOrders) o).getIsbn());
            b.setQuantity(((UserOrders)o).getQuantity());
            books.add(b);
            session.getTransaction().commit();
        }

        return books;
    }

    @Override
    public boolean removeShoppingCard(User user) {
        return false;
    }

    @Override
    public boolean Checkout(User user, String creditCard, Date expireDate) {
        return false;
    }
}
