package viewsControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
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
    private Scene controlPanelScene;



    private ViewsController() throws IOException {
        File loginScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/login.fxml");
        URL loginScreenUrl = loginScreenFile.toURI().toURL();
        Parent loginScreenParent = FXMLLoader.load(loginScreenUrl);
        loginScreenScene = new Scene(loginScreenParent);

        File signUpScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/signUp.fxml");
        URL signUpScreenUrl = signUpScreenFile.toURI().toURL();
        Parent signUpScreenParent = FXMLLoader.load(signUpScreenUrl);
        signUpScreenScene = new Scene(signUpScreenParent);

        File controlPanelScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/controlPanel.fxml");
        URL controlPanelScreenUrl = controlPanelScreenFile.toURI().toURL();
        Parent controlPanelScreenParent = FXMLLoader.load(controlPanelScreenUrl);
        controlPanelScene = new Scene(controlPanelScreenParent);
    }

    public void openLoginScreen() {
        primaryStage.setScene(loginScreenScene);
    }

    public void openSignUpScreen() {
        primaryStage.setScene(signUpScreenScene);
    }

    public void openControlPanelScreen() {
        primaryStage.setScene(controlPanelScene);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
