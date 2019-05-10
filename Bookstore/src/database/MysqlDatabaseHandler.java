package database;

import controllers.ShoppingCart;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


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
    public static DatabaseHandler getInstance(){
        if(instance==null)
            instance=new MysqlDatabaseHandler();
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
            if(user.getPassword().equals(password)){
                LoggedUser loggedUser=LoggedUser.getInstance();
                loggedUser.setUser(user);
                loggedUser.setCart(new ShoppingCart());
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
        User user=LoggedUser.getInstance().getUser();
        if (!user.getIsManger())
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
    public boolean updateBookData(Book newBook) {
        User user=LoggedUser.getInstance().getUser();
        if(!user.getIsManger())
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
        User user=LoggedUser.getInstance().getUser();
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
        if(!LoggedUser.getInstance().getUser().getIsManger())
            return false;
        Session session=factory.getCurrentSession();
        session.beginTransaction();
        LibraryOrders order=new LibraryOrders(isbn,quantity);
        session.save(order);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean confirmOrder(LibraryOrders order) {
        User user=LoggedUser.getInstance().getUser();
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
        User manager=LoggedUser.getInstance().getUser();
        Session session = factory.getCurrentSession();
        if (manager.getIsManger()) {
            String query = "From User u where u.userName= '" +username + "'";
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
           }catch (Exception e)
           {
               session.getTransaction().rollback();
               e.printStackTrace();
               return false;
           }

        }
        return false;
    }

    @Override
    public List<User> getTop5Customers() {
        return null;
    }

    @Override
    public List<Book> viewTopSellingBooks() {
        return null;
    }

    @Override
    public List<Book> findBook(BookDAO bookData) {
        Session session = factory.getCurrentSession();
        StringBuilder query =new StringBuilder( "From Book b where");
        boolean flag=false;
        if(bookData.getIsbn()!=null)
        {
            query.append(" b.isbn like '");
            query.append(bookData.getIsbn());
            query.append("%'");
            flag=true;
        }
        if(bookData.getTitle()!=null)
        {
            if(flag)
                query.append(" and ");
            query.append("b.title like '");
            query.append(bookData.getTitle());
            query.append("%'");
            flag=true;
        }
        if(bookData.getLowerPrice()!=0)
        {
            if(flag)
                query.append(" and ");
            query.append(" b.price >= ");
            query.append(bookData.getLowerPrice());
            flag=true;
        }
        if(bookData.getUpperPrice()!=0)
        {
            if(flag)
                query.append(" and ");
            query.append(" b.price <= ");
            query.append(bookData.getUpperPrice());
            flag=true;
        }
        if(bookData.getPublisher()!=null) {
            if(flag)
                query.append(" and ");
            query.append("b.publisherName = '");
            query.append(bookData.getPublisher());
            query.append("'");
            flag=true;

        }
        if(bookData.getCategories()!=null)
        {
            if(flag)
                query.append(" and ");
            query.append("b.categoryName in (");
            int i=0;
            for(String category:bookData.getCategories())
            {
                query.append(" '");
                query.append(category);
                query.append("' ");
                if(i!=bookData.getCategories().size()-1){
                    query.append(",");
                }
                i++;
            }
            query.append(")");
        }

        System.out.println(query.toString());
        session.beginTransaction();
        try {
            List<?> books =  session.createQuery(query.toString()).getResultList();
            session.getTransaction().commit();

            return (List<Book>)books;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void addToShoppingCard(String isbn, int quantity) {
        User user=LoggedUser.getInstance().getUser();
        UserOrders order=new UserOrders(isbn,user.getUserName(),quantity);
        ShoppingCart cart=LoggedUser.getInstance().getCart();
        cart.addOrder(order);
    }

    @Override
    public List<UserOrders> ShowShoppingCardInfo() {
        ShoppingCart cart=LoggedUser.getInstance().getCart();
        return cart.getOrders();

    }

    @Override
    public boolean removeFromShoppingCard(String isbn) {
        ShoppingCart cart=LoggedUser.getInstance().getCart();
        for(UserOrders order:cart.getOrders())
        {
            if(order.getIsbn().equals(isbn)){
                cart.getOrders().remove(order);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean Checkout() {
        ShoppingCart cart=LoggedUser.getInstance().getCart();
        Session session =factory.getCurrentSession();
        session.beginTransaction();
        for(UserOrders order:cart.getOrders())
        {
            session.save(order);
        }
        try {
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public List<LibraryOrders> getOrders(){
        Session session=factory.getCurrentSession();
        String query = "From LibraryOrders l";
        session.beginTransaction();
        try {
            List<?> orders = session.createQuery(query).getResultList();
            session.getTransaction().commit();
            return (List<LibraryOrders>) orders;
        }catch (Exception e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }

}
