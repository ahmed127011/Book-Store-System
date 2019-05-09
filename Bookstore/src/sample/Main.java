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
import viewsControllers.ViewsController;

import java.io.File;
import java.io.IOException;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");
        ViewsController.getInstance();
        ViewsController.getInstance().setPrimaryStage(primaryStage);
        ViewsController.getInstance().openLoginScreen();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
