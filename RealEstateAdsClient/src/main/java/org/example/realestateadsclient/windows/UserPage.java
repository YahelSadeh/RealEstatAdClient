package org.example.realestateadsclient.windows;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.realestateadsclient.model.Ad;

import java.io.IOException;

public class UserPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Dummy data for the ads table
        ObservableList<Ad> adsData = FXCollections.observableArrayList(
        );

        // Create table columns
        TableColumn<Ad, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Ad, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Ad, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Create ads table
        TableView<Ad> adsTable = new TableView<>();
        adsTable.setItems(adsData);
        adsTable.getColumns().addAll(idColumn, titleColumn, descriptionColumn);

        // Create buttons for adding and deleting ads
        Button addButton = new Button("Add Ad");
        Button deleteButton = new Button("Delete Ad");

        // Create search button
        Button searchButton = new Button("Search Ads");
        searchButton.setOnAction(event -> {
            // Redirect to AdvertisementTable page
            AdvertisementTable advertisementTable = new AdvertisementTable();
            try {
                advertisementTable.start(primaryStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Create VBox for buttons
        VBox buttonsBox = new VBox(10, addButton, deleteButton, searchButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // Create VBox for ads table and buttons
        VBox root = new VBox(10, adsTable, buttonsBox);
        root.setPadding(new Insets(10));

        // Create scene and set to stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
