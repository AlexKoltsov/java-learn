package com.example.java_learn.bookstore.view;

import bookstore.MainApp;
import bookstore.model.Artwork;
import bookstore.model.Book;
import bookstore.model.ItemForSale;
import bookstore.model.exceptions.InvalidPriceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemsOverviewController {
    @FXML
    private TableView<Book> booksForSaleTableView;
    @FXML
    private TableColumn<Book, String> nameBookForSaleColumn;

    @FXML
    private TableView<Artwork> picturesForSaleTableView;
    @FXML
    private TableColumn<Artwork, String> namePictureForSaleColumn;

    @FXML
    private Label nameItemLabel;
    @FXML
    private Label creationYearLabelVal;
    @FXML
    private Label priceLabel;
    @FXML
    private Label creatorLabel;
    @FXML
    private Label numberPagesOrDrawingStyleLabelVal;

    @FXML
    private Label creationYearLabelKey;
    @FXML
    private Label numberPagesOrDrawingStyleLabelKey;

    @FXML
    private Tab booksTab;
    @FXML
    private Tab picturesTab;


    private MainApp mainApp;

    @FXML
    private void initialize() {
        nameBookForSaleColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        namePictureForSaleColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showBookDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию.
        booksForSaleTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
        picturesForSaleTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPictureDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        booksForSaleTableView.setItems(mainApp.getBooksForSale());
        picturesForSaleTableView.setItems(mainApp.getPicturesForSale());
    }

    private void showItemDetails(ItemForSale item) {
        if (item != null) {
            // Заполняем метки информацией из объекта item.
            nameItemLabel.setText(item.getName());
            creationYearLabelVal.setText(Integer.toString(item.getCreationYear()));
            priceLabel.setText(Integer.toString(item.getPrice()));
        } else {
            // Если item = null, то убираем весь текст.
            nameItemLabel.setText("");
            creationYearLabelVal.setText("");
            priceLabel.setText("");
        }
    }

    private void showBookDetails(Book book) {
        showItemDetails(book);

        creationYearLabelKey.setText("Год публикации");
        numberPagesOrDrawingStyleLabelKey.setText("Кол-во страниц");

        if (book != null) {
            creatorLabel.setText(book.getAuthor().getFirstName() + " " + book.getAuthor().getSecondName());
            numberPagesOrDrawingStyleLabelVal.setText(Integer.toString(book.getNumberPages()));
        } else {
            creatorLabel.setText("");
            numberPagesOrDrawingStyleLabelVal.setText("");
        }
    }

    private void showPictureDetails(Artwork picture) {
        showItemDetails(picture);

        creationYearLabelKey.setText("Год создания");
        numberPagesOrDrawingStyleLabelKey.setText("Стиль");

        if (picture != null) {
            creatorLabel.setText(picture.getAuthor().getFirstName() + " " + picture.getAuthor().getSecondName());
            numberPagesOrDrawingStyleLabelVal.setText(picture.getDrawingStyle());
        } else {
            creatorLabel.setText("");
            numberPagesOrDrawingStyleLabelVal.setText("");
        }
    }

    @FXML
    private void handleDeleteItem() {
        int selectedIndex;
        TableView<? extends ItemForSale> table;

        if (booksTab.isSelected()) {
            table = booksForSaleTableView;
        } else {
            table = picturesForSaleTableView;
        }

        selectedIndex = table.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            table.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Элемент не выбран");
            alert.setContentText("Пожалуйста выберите элемент для удаления в таблице.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewItem() throws InvalidPriceException {
        if (booksTab.isSelected())
            handleNewBook();
        else
            handleNewPicture();
    }

    @FXML
    private void handleEditItem() throws InvalidPriceException {
        if (booksTab.isSelected())
            handleEditBook();
        else
            handleEditPicture();
    }

    private void handleNewBook() throws InvalidPriceException {
        Book newBook = new Book();

        boolean okClicked = showBookEditDialog(newBook);

        if (okClicked) {
            mainApp.getBooksForSale().add(newBook);
        }
    }

    private void handleNewPicture() throws InvalidPriceException {
        Artwork newPicture = new Artwork();

        boolean okClicked = showArtworkEditDialog(newPicture);

        if (okClicked) {
            mainApp.getPicturesForSale().add(newPicture);
        }
    }

    private boolean showBookEditDialog(Book book) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Книга");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём книгу в контроллер.
            BookEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAuthors(mainApp.getAuthors());
            controller.setBook(book);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showArtworkEditDialog(Artwork picture) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ArtworkEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Картина");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём картину в контроллер.
            ArtworkEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPainters(mainApp.getPainters());
            controller.setPicture(picture);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void handleEditBook() {
        Book selectedBook = booksForSaleTableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = showBookEditDialog(selectedBook);
            if (okClicked) {
                showBookDetails(selectedBook);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Элемент не выбран");
            alert.setContentText("Пожалуйста выберите элемент для изменения в таблице.");

            alert.showAndWait();
        }
    }

    private void handleEditPicture() {
        Artwork selectedPicture = picturesForSaleTableView.getSelectionModel().getSelectedItem();
        if (selectedPicture != null) {
            boolean okClicked = showArtworkEditDialog(selectedPicture);
            if (okClicked) {
                showPictureDetails(selectedPicture);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Элемент не выбран");
            alert.setContentText("Пожалуйста выберите элемент для изменения в таблице.");

            alert.showAndWait();
        }
    }

}
