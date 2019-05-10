package database;

import controllers.ShoppingCart;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MysqlDatabaseHandler implements DatabaseHandler {
    private SessionFactory factory;
    private static MysqlDatabaseHandler instance;

    private MysqlDatabaseHandler() {
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

    public static DatabaseHandler getInstance() {
        if (instance == null)
            instance = new MysqlDatabaseHandler();
        return instance;
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
    public boolean login(String username, String password) {
        Session session = factory.getCurrentSession();
        String query = "From User u where u.userName= '" + username + "'";
        session.beginTransaction();
        try {
            User user = (User) session.createQuery(query).getResultList().get(0);
            session.getTransaction().commit();
            if (user.passwordEqualityCheck(password)) {
                LoggedUser loggedUser = LoggedUser.getInstance();
                loggedUser.setUser(user);
                loggedUser.setCart(new ShoppingCart());
                loggedUser.setLoggedIn(true);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void logout() {
        LoggedUser.getInstance().logOut();
    }

    @Override
    public boolean addNewBook(Book book) {
        return add(book);
    }

    @Override
    public boolean updateBookData(Book newBook) {
        User user = LoggedUser.getInstance().getUser();
        if (!user.getIsManger())
            return false;
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        session.update(newBook);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean UpdateUserData() {
        User user = LoggedUser.getInstance().getUser();
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
    public boolean orderFromSupplier(String isbn, int quantity) {
        if (!LoggedUser.getInstance().getUser().getIsManger())
            return false;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        LibraryOrders order = new LibraryOrders(isbn, quantity);
        session.save(order);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean confirmOrder(LibraryOrders order) {
        User user = LoggedUser.getInstance().getUser();
        if (!user.getIsManger())
            return false;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        order.setConfirmed(true);

        session.update(order);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean promoteUser(String username) {
        User manager = LoggedUser.getInstance().getUser();
        Session session = factory.getCurrentSession();
        if (manager.getIsManger()) {
            String query = "From User u where u.userName= '" + username + "'";
            System.out.println(query);
            session.beginTransaction();
            User user = (User) session.createQuery(query).getResultList().get(0);
            try {
                session.getTransaction().commit();
                user.setIsManger(true);
                session = factory.getCurrentSession();
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

    @Override
    public int getprevMonthSales() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        StoredProcedureQuery query = session.createStoredProcedureQuery("get_total_sales");
        query.registerStoredProcedureParameter("sales", Integer.class, ParameterMode.OUT);
        query.execute();
        if (query.getOutputParameterValue("sales") == null)
            return 0;
        int sales = (int) query.getOutputParameterValue("sales");
        session.getTransaction().commit();
        return sales;


    }

    @Override
    public List<String> getTop5Customers() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        StoredProcedureQuery query = session.createStoredProcedureQuery("get_top_users");
        query.execute();
        if (query.getResultList() == null)
            return new ArrayList<>();
        List<String> users = (List<String>) query.getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public List<String> viewTopSellingBooks() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        StoredProcedureQuery query = session.createStoredProcedureQuery("get_top_books");
        query.execute();
        if (query.getResultList() == null)
            return new ArrayList<>();
        List<String> books = (List<String>) query.getResultList();
        session.getTransaction().commit();
        return books;


    }

    @Override
    public List<Book> findBook(BookDAO bookData) {
        Session session = factory.getCurrentSession();
        StringBuilder query = new StringBuilder("From Book b where");
        boolean flag = false;
        if (bookData.getIsbn() != null) {
            query.append(" b.isbn like '");
            query.append(bookData.getIsbn());
            query.append("%'");
            flag = true;
        }
        if (bookData.getTitle() != null) {
            if (flag)
                query.append(" and ");
            query.append(" b.title like '");
            query.append(bookData.getTitle());
            query.append("%'");
            flag = true;
        }
        if (bookData.getLowerPrice() != 0) {
            if (flag)
                query.append(" and ");
            query.append(" b.price >= ");
            query.append(bookData.getLowerPrice());
            flag = true;
        }
        if (bookData.getUpperPrice() != 0) {
            if (flag)
                query.append(" and ");
            query.append(" b.price <= ");
            query.append(bookData.getUpperPrice());
            flag = true;
        }
        if (bookData.getPublisher() != null) {
            if (flag)
                query.append(" and ");
            query.append(" b.publisherName = '");
            query.append(bookData.getPublisher());
            query.append("'");
            flag = true;

        }
        if (bookData.getCategories() != null) {
            if (flag)
                query.append(" and ");
            query.append(" b.categoryName in (");
            int i = 0;
            for (String category : bookData.getCategories()) {
                query.append(" '");
                query.append(category);
                query.append("' ");
                if (i != bookData.getCategories().size() - 1) {
                    query.append(",");
                }
                i++;
            }
            query.append(")");
        }

        System.out.println(query.toString());
        session.beginTransaction();
        try {
            List<?> books = session.createQuery(query.toString()).getResultList();
            session.getTransaction().commit();
            if(books==null) return new LinkedList<>();
            return (List<Book>) books;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void addToShoppingCard(String isbn, int quantity) {
        User user = LoggedUser.getInstance().getUser();
        UserOrders order = new UserOrders(isbn, user.getUserName(), quantity);
        ShoppingCart cart = LoggedUser.getInstance().getCart();
        cart.addOrder(order);
    }

    @Override
    public List<UserOrders> ShowShoppingCardInfo() {
        ShoppingCart cart = LoggedUser.getInstance().getCart();
        return cart.getOrders();

    }

    @Override
    public boolean removeFromShoppingCard(String isbn) {
        ShoppingCart cart = LoggedUser.getInstance().getCart();
        for (UserOrders order : cart.getOrders()) {
            if (order.getIsbn().equals(isbn)) {
                cart.getOrders().remove(order);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean Checkout( String creditCardNum, java.sql.Date expirationDate) {
        ShoppingCart cart = LoggedUser.getInstance().getCart();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        for (UserOrders order : cart.getOrders()) {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            order.setCheckOutDate(date);
            order.setCreditCardNum(creditCardNum);
            order.setExpirationDate(expirationDate);
            if (order.isValidCreditCardNum() && order.isValidExpirationDate()) {
                session.save(order);
            } else {
                System.out.println("Invalid Credit Card info.");
            }
        }
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<LibraryOrders> getOrders() {
        Session session = factory.getCurrentSession();
        String query = "From LibraryOrders l";
        session.beginTransaction();
        try {
            List<?> orders = session.createQuery(query).getResultList();
            session.getTransaction().commit();
            return (List<LibraryOrders>) orders;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<String> getCategories() {
        Session session = factory.getCurrentSession();
        String query = "From Category c";
        session.beginTransaction();
        try {
            List<?> categories = session.createQuery(query).getResultList();
            List<String> result=new LinkedList<>();
            session.getTransaction().commit();
            for(Category c:(List<Category>)categories)
            {
                result.add(c.getCategoryName());
            }
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Publisher> getPublishers() {
        Session session = factory.getCurrentSession();
        String query = "From Publisher p";
        session.beginTransaction();
        try {
            List<?> publishers = session.createQuery(query).getResultList();
            session.getTransaction().commit();
            return (List<Publisher>) publishers;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addNewPublisher(Publisher publisher) {
    return add(publisher);
    }



    @Override
    public boolean addNewAuthor(BookAuthors author) {
        return add(author);
    }

    @Override
    public boolean addPublisherAddresses(Publisher publisher, List<String> addresses) {
        boolean res=true;
        for(String address:addresses)
        {
            res&=add(new PublisherAddresses(publisher.getPublisherName(),address));
            if(!res) return false;
        }
        return true;
    }

    @Override
    public boolean addPublisherPhones(Publisher publisher, List<String> phones) {
        boolean res=true;
        for(String phone:phones)
        {
            res&=add(new PublisherPhones(publisher.getPublisherName(),phone));
            if(!res) return false;
        }
        return true;
    }


    private boolean add(Object o ){
        User user = LoggedUser.getInstance().getUser();
        if (!user.getIsManger())
            return false;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(o);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
