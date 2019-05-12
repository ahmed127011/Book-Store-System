package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Book;
import models.BookAuthors;
import models.Publisher;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBook implements Initializable {

    public Label titleLabel;
    public Button submitBtn;
    @FXML
    private DatePicker publicationDatePicker;
    @FXML
    private TextField quantityTxtField;
    @FXML
    private TextField defOrderQuantityTxtField;
    @FXML
    private TextField minQuantityTxtField;
    @FXML
    private TextField isbnTxtField;
    @FXML
    private TextField titleTxtField;
    @FXML
    private ChoiceBox publisherChoiceBox;
    @FXML
    private ChoiceBox categoryChoiceBox;
    @FXML
    private TextField priceTxtField;
    @FXML
    private VBox authorsVBox;

    private Book chosenBook;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewsController.getInstance().setAddBookController(this);
    }

    public void onSceneShow(Book chosenBook) {
        this.chosenBook = chosenBook;
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        categoryChoiceBox.setItems(FXCollections.observableArrayList(databaseHandler.getCategories()));
        List<Publisher> publishers = databaseHandler.getPublishers();
        ArrayList<String> publishersNames = getPublishersNames(publishers);
        publisherChoiceBox.setItems(FXCollections.observableArrayList(publishersNames));
        if(chosenBook != null) { // Edit book
            titleLabel.setText("Edit Book");
            isbnTxtField.setText(chosenBook.getIsbn());
            titleTxtField.setText(chosenBook.getTitle());
            authorsVBox.setDisable(true);
            publisherChoiceBox.setValue(chosenBook.getPublisherName());
            publicationDatePicker.setValue(chosenBook.getPublicationDate().toLocalDate());
            priceTxtField.setText(String.valueOf(chosenBook.getPrice()));
            categoryChoiceBox.setValue(chosenBook.getCategoryName());
            quantityTxtField.setText(String.valueOf(chosenBook.getQuantity()));
            minQuantityTxtField.setText(String.valueOf(chosenBook.getRequiredQuantity()));
            defOrderQuantityTxtField.setText(String.valueOf(chosenBook.getThreshold()));
            submitBtn.setText("Save");
        } else { // Add book
            titleLabel.setText("Add Book");
            isbnTxtField.setText("");
            titleTxtField.setText("");
            publisherChoiceBox.setValue("");
            priceTxtField.setText("");
            categoryChoiceBox.setValue("");
            quantityTxtField.setText("");
            minQuantityTxtField.setText("");
            defOrderQuantityTxtField.setText("");
            submitBtn.setText("Submit");
        }
    }

    private ArrayList<String> getPublishersNames(List<Publisher> publishers) {
        ArrayList<String> publishersNames = new ArrayList<>();
        for (Publisher publisher :
                publishers) {
            publishersNames.add(publisher.getPublisherName());
        }
        return publishersNames;
    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openControlPanelScreen();
    }

    public void submitClk(ActionEvent actionEvent) {
        String isbn = isbnTxtField.getText();
        String title = titleTxtField.getText();
        String price = priceTxtField.getText();
        Date publicationDate = Date.valueOf(publicationDatePicker.getValue());
        String publisherName = publisherChoiceBox.getValue().toString();
        String categoryName = categoryChoiceBox.getValue().toString();
        Long minQuantity = Long.valueOf(minQuantityTxtField.getText());
        Long quantity = Long.valueOf(quantityTxtField.getText());
        Long orderQuantity = Long.valueOf(defOrderQuantityTxtField.getText());
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        Book newBook = new Book(isbn, title);
        newBook.setPrice(Long.valueOf(price));
        newBook.setPublisherName(publisherName);
        newBook.setCategoryName(categoryName);
        newBook.setPublicationDate(publicationDate);
        newBook.setQuantity(quantity);
        newBook.setThreshold(orderQuantity);
        newBook.setRequiredQuantity(minQuantity);
        Boolean added = databaseHandler.addNewBook(newBook);
        for (Node children: authorsVBox.getChildren()) {
            String authorName = ((TextField)(((HBox)children).getChildren().get(0))).getText();
            added = added && databaseHandler.addNewAuthor(new BookAuthors(isbn,authorName));
        }
        if(added) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Added The Book");
            alert.setHeaderText(null);
            alert.setOnCloseRequest(dialogEvent -> {
                try {
                    ViewsController.getInstance().openControlPanelScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error While adding the book");
            alert.show();
        }
    }

    public void addAuthorClk(ActionEvent actionEvent) {
        HBox newAuthor = new HBox();
        TextField authorNameTxtField = new TextField();
        authorNameTxtField.setId("N"+String.valueOf(authorsVBox.getChildren().size()));
        newAuthor.getChildren().add(authorNameTxtField);
        Button removeAuthor = new Button();
        removeAuthor.setText("-");
        removeAuthor.setOnAction(actionEvent1 -> authorsVBox.getChildren().remove(newAuthor));
        newAuthor.getChildren().add(removeAuthor);
        authorsVBox.getChildren().add(newAuthor);
    }
}
