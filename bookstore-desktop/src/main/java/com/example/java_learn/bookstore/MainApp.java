package com.example.java_learn.bookstore;

import com.example.java_learn.bookstore.model.Artwork;
import com.example.java_learn.bookstore.model.Author;
import com.example.java_learn.bookstore.model.Book;
import com.example.java_learn.bookstore.model.Painter;
import com.example.java_learn.bookstore.view.ItemsOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Book> booksForSale;
    private ObservableList<Artwork> picturesForSale;
    private ObservableList<Author> authors;
    private ObservableList<Painter> painters;


    private static final String PATH_TO_FILE_RESOURCES = System.getProperty("user.dir") + "\\koltsov\\9\\resources\\";

    public MainApp() {
        ArrayList<Author> authors = Author.loadFromFile(PATH_TO_FILE_RESOURCES + "AUTHORS.txt");
        ArrayList<Painter> painters = Painter.loadFromFile(PATH_TO_FILE_RESOURCES + "PAINTERS.txt");

        ArrayList<Book> books = Book.loadFromFile(PATH_TO_FILE_RESOURCES + "BOOKS.txt", authors);
        ArrayList<Artwork> pictures = Artwork.loadFromFile(PATH_TO_FILE_RESOURCES + "PICTURES.txt", painters);

        this.booksForSale = FXCollections.observableArrayList(books);
        this.picturesForSale = FXCollections.observableArrayList(pictures);
        this.authors = FXCollections.observableArrayList(authors);
        this.painters = FXCollections.observableArrayList(painters);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Список книг и картин");

        initRootLayout();
        showItemsOverview();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showItemsOverview() {
        try {
            // Load main layout.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ItemsOverview.fxml"));
            AnchorPane mainLayout = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainLayout);

            // Give the controller access to the main app.
            ItemsOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Book> getBooksForSale() {
        return booksForSale;
    }

    public ObservableList<Artwork> getPicturesForSale() {
        return picturesForSale;
    }

    public ObservableList<Author> getAuthors() {
        return authors;
    }

    public ObservableList<Painter> getPainters() {
        return painters;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
