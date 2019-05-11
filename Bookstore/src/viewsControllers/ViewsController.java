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
    private Scene addBookScene;
    private Scene addPublisherScene;
    private Scene searchForBooksScene;
    private Scene checOutScene;

    private URL loginScreenUrl;
    private URL signUpScreenUrl;
    private URL controlPanelScreenUrl;
    private URL addBookPanelScreenUrl;
    private URL addPublisherScreenUrl;
    private URL searchForBooksScreenUrl;
    private URL checOutScreenUrl;

    private Parent controlPanelScreenParent;
    private Parent signUpScreenParent;
    private Parent loginScreenParent;
    private Parent addBookScreenParent;
    private Parent addPublisherScreenParent;
    private Parent searchForBooksScreenParent;
    private Parent checOutScreenParent;

    private SignUp signUpController;
    private AddBook addBookController;
    private SearchForBooks searchForBooksController;

    public void setAddBookController(AddBook addBookController) {
        this.addBookController = addBookController;
    }

    public void setSignUpController(SignUp signUpController) {
        this.signUpController = signUpController;
    }

    public void setSearchForBooksController(SearchForBooks searchForBooksController) {
        this.searchForBooksController = searchForBooksController;
    }

    private ViewsController() throws IOException {
        File loginScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/login.fxml");
        loginScreenUrl = loginScreenFile.toURI().toURL();

        File signUpScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/signUp.fxml");
        signUpScreenUrl = signUpScreenFile.toURI().toURL();

        File controlPanelScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/controlPanel.fxml");
        controlPanelScreenUrl = controlPanelScreenFile.toURI().toURL();

        File addBookScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/addBook.fxml");
        addBookPanelScreenUrl = addBookScreenFile.toURI().toURL();

        File addPublisherScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/addPublisher.fxml");
        addPublisherScreenUrl = addPublisherScreenFile.toURI().toURL();

        File searchForBooksScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/searchForBooks.fxml");
        searchForBooksScreenUrl = searchForBooksScreenFile.toURI().toURL();

        File checkOutScreenFile = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/checkOut.fxml");
        checOutScreenUrl = checkOutScreenFile.toURI().toURL();
    }

    public void openLoginScreen() throws IOException {
        if(loginScreenParent == null) {
            loginScreenParent = FXMLLoader.load(loginScreenUrl);
            loginScreenScene = new Scene(loginScreenParent);
        }
        primaryStage.setScene(loginScreenScene);
    }

    public void openCheckOutScreen() throws IOException {
        if(checOutScreenParent == null) {
            checOutScreenParent = FXMLLoader.load(checOutScreenUrl);
            checOutScene = new Scene(checOutScreenParent);
        }
        primaryStage.setScene(checOutScene);
    }

    public void openSignUpScreen() throws IOException {
        if(signUpScreenScene == null) {
            signUpScreenParent = FXMLLoader.load(signUpScreenUrl);
            signUpScreenScene = new Scene(signUpScreenParent);
        }
        signUpController.onSceneShow();
        primaryStage.setScene(signUpScreenScene);
    }

    public void openControlPanelScreen() throws IOException {
        if(controlPanelScreenParent == null) {
            controlPanelScreenParent = FXMLLoader.load(controlPanelScreenUrl);
            controlPanelScene = new Scene(controlPanelScreenParent);
        }
        primaryStage.setScene(controlPanelScene);
    }

    public void openAddBookScreen() throws IOException {
        if(addBookScreenParent == null) {
            addBookScreenParent = FXMLLoader.load(addBookPanelScreenUrl);
            addBookScene = new Scene(addBookScreenParent);
        }
        addBookController.onSceneShow(null);
        primaryStage.setScene(addBookScene);
    }

    public void openSearchForBooksScreen() throws IOException {
        if(searchForBooksScreenParent == null) {
            searchForBooksScreenParent = FXMLLoader.load(searchForBooksScreenUrl);
            searchForBooksScene = new Scene(searchForBooksScreenParent);
        }
        searchForBooksController.onSceneShow();
        primaryStage.setScene(searchForBooksScene);
    }

    public void openAddPublisherScreen() throws IOException {
        if(addPublisherScreenParent == null) {
            addPublisherScreenParent = FXMLLoader.load(addPublisherScreenUrl);
            addPublisherScene = new Scene(addPublisherScreenParent);
        }
        primaryStage.setScene(addPublisherScene);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
