package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.*;

import java.io.IOException;
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
    public TextField minPriceTxtField;
    public TextField maxPriceTxtField;

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
        if(!minPriceTxtField.isDisabled())
            bookDAO.setLowerPrice(Long.valueOf(minPriceTxtField.getText()));
        if(!maxPriceTxtField.isDisabled())
            bookDAO.setLowerPrice(Long.valueOf(maxPriceTxtField.getText()));
        bookDAO.setCategories(getChosenCategories());
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        List<Book> books = databaseHandler.findBook(bookDAO);
        addBooksToResult(books);
    }

    private void addBooksToResult(List<Book> books) {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        for (Book book:
             books) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.BASELINE_CENTER);
            Label titleLabel = new Label(book.getTitle());
            titleLabel.setPrefWidth(100);
            Label isbnLabel = new Label(book.getIsbn());
            isbnLabel.setPrefWidth(100);
            isbnLabel.setAlignment(Pos.CENTER);
            Label categoryLabel = new Label(book.getCategoryName());
            categoryLabel.setPrefWidth(100);
            categoryLabel.setAlignment(Pos.CENTER);
            Label priceLabel = new Label(String.valueOf(book.getPrice()));
            priceLabel.setPrefWidth(100);
            priceLabel.setAlignment(Pos.CENTER);
            Label publiserLabel = new Label(book.getPublisherName());
            publiserLabel.setPrefWidth(100);
            publiserLabel.setAlignment(Pos.CENTER);
            TextField buyingQuantity = new TextField();
            buyingQuantity.setPrefWidth(50);
            buyingQuantity.setAlignment(Pos.CENTER);
            Button buyBtn = new Button("Buy");
            buyBtn.setPrefWidth(50);
            buyBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    databaseHandler.addToShoppingCard(book.getIsbn(), Integer.parseInt(buyingQuantity.getText()));
                    buyingQuantity.setText("");
                }
            });
            TextField orderQuantity = new TextField();
            orderQuantity.setPrefWidth(50);
            orderQuantity.setAlignment(Pos.CENTER);
            Button orderBtn = new Button("Order");
            orderBtn.setPrefWidth(100);
            orderBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(databaseHandler.orderFromSupplier(book.getIsbn(), Integer.parseInt(orderQuantity.getText()))) {
                        orderQuantity.setText("");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Ordered ");
                        alert.setHeaderText(null);
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error While Ordering ");
                        alert.setHeaderText(null);
                        alert.show();
                    }
                }
            });
            Button editBtn = new Button("Edit");
            editBtn.setPrefWidth(50);
            editBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // Todo
                }
            });
            Button deleteBtn = new Button("Delete");
            deleteBtn.setPrefWidth(70);
            deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    databaseHandler.removeBook(book);
                    resultsVBox.getChildren().remove(hBox);
                }
            });

            hBox.getChildren().add(isbnLabel);
            hBox.getChildren().add(titleLabel);
            hBox.getChildren().add(categoryLabel);
            hBox.getChildren().add(priceLabel);
            hBox.getChildren().add(publiserLabel);
            hBox.getChildren().add(buyingQuantity);
            hBox.getChildren().add(buyBtn);
            if(LoggedUser.getInstance().getUser().getIsManger()) {
                hBox.getChildren().add(orderQuantity);
                hBox.getChildren().add(orderBtn);
                hBox.getChildren().add(editBtn);
                hBox.getChildren().add(deleteBtn);
            }

            resultsVBox.getChildren().add(hBox);
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
        resultsVBox.getChildren().clear();
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        List<Publisher> publishers = databaseHandler.getPublishers();
        ArrayList<String> publishersNames = getPublishersNames(publishers);
        publisherChioceBox.setItems(FXCollections.observableArrayList(publishersNames));
        HBox hBox = new HBox();
        hBox.setPrefHeight(25);
        Label isbnLabel = new Label("ISBN");
        isbnLabel.setAlignment(Pos.CENTER);
        isbnLabel.setPrefWidth(100);
        Label titleLabel = new Label("Title");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPrefWidth(100);
        Label categoryLabel = new Label("Category");
        categoryLabel.setAlignment(Pos.CENTER);
        categoryLabel.setPrefWidth(100);
        Label priceLabel = new Label("Price");
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setPrefWidth(100);
        Label publiserLabel = new Label("Publisher");
        publiserLabel.setAlignment(Pos.CENTER);
        publiserLabel.setPrefWidth(100);
        Label buyLabel = new Label("Add to Cart");
        buyLabel.setAlignment(Pos.CENTER);
        buyLabel.setPrefWidth(100);
        Label orderLabel = new Label("Order From Supplier");
        orderLabel.setAlignment(Pos.CENTER);
        orderLabel.setPrefWidth(150);
        Label editLabel = new Label("Edit");
        editLabel.setAlignment(Pos.CENTER);
        editLabel.setPrefWidth(50);
        Label deleteLabel = new Label("Delete");
        deleteLabel.setAlignment(Pos.CENTER);
        deleteLabel.setPrefWidth(70);

        hBox.getChildren().add(isbnLabel);
        hBox.getChildren().add(titleLabel);
        hBox.getChildren().add(categoryLabel);
        hBox.getChildren().add(priceLabel);
        hBox.getChildren().add(publiserLabel);
        hBox.getChildren().add(buyLabel);
        if(LoggedUser.getInstance().getUser().getIsManger()) {
            hBox.getChildren().add(orderLabel);
            hBox.getChildren().add(editLabel);
            hBox.getChildren().add(deleteLabel);
        }
        resultsVBox.getChildren().add(hBox);
    }

    private ArrayList<String> getPublishersNames(List<Publisher> publishers) {
        ArrayList<String> publishersNames = new ArrayList<>();
        for (Publisher publisher :
                publishers) {
            publishersNames.add(publisher.getPublisherName());
        }
        return publishersNames;
    }

    public void minPriceChkChanged(ActionEvent actionEvent) {
        checkBoxChanged(minPriceTxtField);
    }

    public void maxPriceChkChanged(ActionEvent actionEvent) {
        checkBoxChanged(maxPriceTxtField);
    }

    public void shppingCartClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openShoppingCartScreen();
    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        User curUser = LoggedUser.getInstance().getUser();
        if(curUser.getIsManger()) {
            ViewsController.getInstance().openControlPanelScreen();
        } else {
            ViewsController.getInstance().openUserHomeScreen();
        }
    }
}
