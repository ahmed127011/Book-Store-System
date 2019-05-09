package viewsControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewsController {
    private static ViewsController ourInstance;

    static {
        try {
            ourInstance = new ViewsController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ViewsController getInstance() {
        return ourInstance;
    }

    private Stage primaryStage;
    private Scene loginScreenScene;
    private Scene signUpScreenScene;

    private ViewsController() throws IOException {
        File loginScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/login.fxml");
        URL loginScreenUrl = loginScreenFile.toURI().toURL();
        Parent loginScreenParent = FXMLLoader.load(loginScreenUrl);
        loginScreenScene = new Scene(loginScreenParent);

        File SignUpScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/signUp.fxml");
        URL SignUpScreenUrl = SignUpScreenFile.toURI().toURL();
        Parent signUpScreenParent = FXMLLoader.load(SignUpScreenUrl);
        signUpScreenScene = new Scene(signUpScreenParent);
    }

    public void openLoginScreen() {
        primaryStage.setScene(loginScreenScene);
    }

    public void openSignUpScreen() {
        primaryStage.setScene(signUpScreenScene);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
