package com.example.java_learn.bookstore.view;

import bookstore.model.Author;
import bookstore.model.Book;
import bookstore.model.exceptions.InvalidPriceException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookEditDialogController {

    @FXML
    private TextField bookNameTextField;
    @FXML
    private TextField publicationYearTextField;
    @FXML
    private ComboBox<Author> authorComboBox;
    @FXML
    private TextField pageNumberTextField;
    @FXML
    private TextField priceTextField;

    private Stage dialogStage;
    private Book book;
    private boolean okClicked;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBook(Book book) {
        this.book = book;

        bookNameTextField.setText(book.getName());
        publicationYearTextField.setText(Integer.toString(book.getCreationYear()));
        authorComboBox.getSelectionModel().select(book.getAuthor());
        pageNumberTextField.setText(Integer.toString(book.getNumberPages()));
        priceTextField.setText(Integer.toString(book.getPrice()));
    }

    public void setAuthors(ObservableList<Author> authors) {
        authorComboBox.getItems().addAll(authors);
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            book.setName(bookNameTextField.getText());
            book.setCreationYear(Integer.valueOf(publicationYearTextField.getText()));
            try {
                book.setPrice(Integer.valueOf(priceTextField.getText()));
            } catch (InvalidPriceException e) {
                generateErrorAlert("Стоимость должна быть положительной");
                return;
            }
            book.setNumberPages(Integer.valueOf(pageNumberTextField.getText()));
            book.setAuthor(authorComboBox.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (bookNameTextField.getText() == null || bookNameTextField.getText().length() == 0) {
            errorMessage += "Не правильное имя книги!\n";
        }

        if (publicationYearTextField.getText() == null || publicationYearTextField.getText().length() == 0) {
            errorMessage += "Не правильный год публикации книги!\n";
        } else {
            try {
                Integer.parseInt(publicationYearTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Не правильный год публикации книги (должен быть целым числом)!\n";
            }
        }

        if (authorComboBox.getValue() == null) {
            errorMessage += "Не выбран автор!\n";
        }

        if (pageNumberTextField.getText() == null || pageNumberTextField.getText().length() == 0) {
            errorMessage += "Не правильное количество страниц!\n";
        } else {
            try {
                Integer.parseInt(pageNumberTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Не правильное количество страниц (должно быть целым числом)!\n";
            }
        }

        if (priceTextField.getText() == null || priceTextField.getText().length() == 0) {
            errorMessage += "Не правильная стоимость книги!\n";
        } else {
            try {
                Integer.parseInt(priceTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Не правильная стоимость книги (должна быть целым числом)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            generateErrorAlert(errorMessage);
            return false;
        }
    }

    private void generateErrorAlert(String errorMessage) {
        // Показываем сообщение об ошибке.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Не правильный ввод");
        alert.setHeaderText("Пожалуйста исправте неправильно введенные поля");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }
}
