package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.Book;
import models.BookDAO;
import models.Publisher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchForBooks implements Initializable {
    public TextField titleTxtField;
    public TextField isbnTxtField;
    public TextField authorTxtField;
    public ChoiceBox publisherChioceBox;
    public CheckBox artChkBox;
    public CheckBox geographyChkBox;
    public CheckBox historyChkBox;
    public CheckBox religionChkBox;
    public CheckBox scienceChkBox;
    public VBox resultsVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewsController.getInstance().setSearchForBooksController(this);
    }

    public void titleChkChanged(ActionEvent actionEvent) {
        checkBoxChanged(titleTxtField);
    }

    public void isbnChkChanged(ActionEvent actionEvent) {
        checkBoxChanged(isbnTxtField);
    }

    public void authorChkChanged(ActionEvent actionEvent) {
        checkBoxChanged(authorTxtField);
    }

    private void checkBoxChanged(TextField authorTxtField) {
        if (authorTxtField.isDisabled())
            authorTxtField.setDisable(false);
        else
            authorTxtField.setDisable(true);
    }

    public void publisherChkChanged(ActionEvent actionEvent) {
        if(publisherChioceBox.isDisabled())
            publisherChioceBox.setDisable(false);
        else
            publisherChioceBox.setDisable(true);
    }

    public void searchClk(ActionEvent actionEvent) {
        BookDAO bookDAO = new BookDAO();
        if(!titleTxtField.isDisabled())
            bookDAO.setTitle(titleTxtField.getText());
        if(!isbnTxtField.isDisabled())
            bookDAO.setIsbn(isbnTxtField.getText());
        if(!authorTxtField.isDisabled())
            bookDAO.setAuthor(authorTxtField.getText());
        if(!publisherChioceBox.isDisabled())
            bookDAO.setPublisher(publisherChioceBox.getValue().toString());
        bookDAO.setCategories(getChosenCategories());
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        List<Book> books = databaseHandler.findBook(bookDAO);
        addBooksToResult(books);
    }

    private void addBooksToResult(List<Book> books) {
        System.out.println(books);
        for (Book book:
             books) {
            resultsVBox.getChildren().add(new Label(book.getIsbn()));
            resultsVBox.getChildren().add(new Label(book.getTitle()));
        }
    }

    private List<String> getChosenCategories() {
        List<String> chosenCategories = new ArrayList<>();
        if(artChkBox.isSelected())
            chosenCategories.add("Art");
        if(geographyChkBox.isSelected())
            chosenCategories.add("Geography");
        if(historyChkBox.isSelected())
            chosenCategories.add("History");
        if(religionChkBox.isSelected())
            chosenCategories.add("Religion");
        if(scienceChkBox.isSelected())
            chosenCategories.add("Science");
        return chosenCategories;
    }

    public void onSceneShow() {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        List<Publisher> publishers = databaseHandler.getPublishers();
        ArrayList<String> publishersNames = getPublishersNames(publishers);
        publisherChioceBox.setItems(FXCollections.observableArrayList(publishersNames));
    }

    private ArrayList<String> getPublishersNames(List<Publisher> publishers) {
        ArrayList<String> publishersNames = new ArrayList<>();
        for (Publisher publisher :
                publishers) {
            publishersNames.add(publisher.getPublisherName());
        }
        return publishersNames;
    }
}
