package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        File file = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/loginClk.fxml");
        Parent root = FXMLLoader.load(file.toURI().toURL());
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
