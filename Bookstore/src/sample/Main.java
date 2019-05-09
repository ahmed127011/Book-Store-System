package sample;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Book;
import models.User;

import java.io.File;


public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        File file = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/login.fxml");
        Parent root = FXMLLoader.load(file.toURI().toURL());
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/sample.fxml"));
        primaryStage.setTitle("Library Managment System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        DatabaseHandler databaseHandler = new MysqlDatabaseHandler();
        DatabaseHandler d=new MysqlDatabaseHandler();
        User user=new User("ahmed127011","ahmed127011@gmail.com","09876","098765");
        User manager=new User("ahmed127011","ahmed127011@gmail.com","09876","098765");
        manager.setIsManger("1");

        user.setIsManger("true");
        Book b=new Book("12","the book");
        // List<Book> l=d.ShowShoppingCardInfo(user);
        d.promoteUser(user);
        launch(args);
    }
}
