package com.example.java_learn.bookstore.view;

import bookstore.model.Artwork;
import bookstore.model.Painter;
import bookstore.model.exceptions.InvalidPriceException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArtworkEditDialogController {

    @FXML
    private TextField pictureNameTextField;
    @FXML
    private TextField creationYearTextField;
    @FXML
    private ComboBox<Painter> paintersComboBox;
    @FXML
    private TextField drawingStyleTextField;
    @FXML
    private TextField priceTextField;

    private Stage dialogStage;
    private Artwork picture;
    private boolean okClicked;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPicture(Artwork picture) {
        this.picture = picture;

        pictureNameTextField.setText(picture.getName());
        creationYearTextField.setText(Integer.toString(picture.getCreationYear()));
        paintersComboBox.getSelectionModel().select(picture.getAuthor());
        drawingStyleTextField.setText(picture.getDrawingStyle());
        priceTextField.setText(Integer.toString(picture.getPrice()));
    }

    public void setPainters(ObservableList<Painter> painters) {
        paintersComboBox.getItems().addAll(painters);
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
            picture.setName(pictureNameTextField.getText());
            picture.setCreationYear(Integer.valueOf(creationYearTextField.getText()));
            try {
                picture.setPrice(Integer.valueOf(priceTextField.getText()));
            } catch (InvalidPriceException e) {
                generateErrorAlert("Стоимость должна быть положительной");
                return;
            }
            picture.setDrawingStyle(drawingStyleTextField.getText());
            picture.setAuthor(paintersComboBox.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (pictureNameTextField.getText() == null || pictureNameTextField.getText().length() == 0) {
            errorMessage += "Не правильное имя книги!\n";
        }

        if (creationYearTextField.getText() == null || creationYearTextField.getText().length() == 0) {
            errorMessage += "Не правильный год публикации книги!\n";
        } else {
            try {
                Integer.parseInt(creationYearTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Не правильный год публикации книги (должен быть целым числом)!\n";
            }
        }

        if (paintersComboBox.getValue() == null) {
            errorMessage += "Не выбран автор!\n";
        }

        if (drawingStyleTextField.getText() == null || drawingStyleTextField.getText().length() == 0) {
            errorMessage += "Не правильный стиль!\n";
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
