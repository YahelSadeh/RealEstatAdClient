package org.example.realestateadsclient.windows;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.realestateadsclient.server.GetFromServer;
import org.example.realestateadsclient.server.SendToServer;
import org.example.realestateadsclient.model.Ad;
import org.example.realestateadsclient.model.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdvertisementTable extends Application {

    private TableView<Ad> table = new TableView<>();
    private ObservableList<Ad> adList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws IOException {
        populateAds();
        // Create form controls
        TextField citySearchField = new TextField();
        TextField descriptionSearchField = new TextField();


        // Create labels for search bars
        Label citySearchLabel = new Label("Search by City:");
        Label descriptionSearchLabel = new Label("Search by Description:");

        // Create search button
        Button searchButton = new Button("Search");

        // Create My Account button
        Button myAccountButton = new Button("My Account");
        myAccountButton.setOnAction(event -> {
            // Redirect to UserPage
            UserPage userPage = new UserPage();
            userPage.start(primaryStage);
        });

        // Create grid pane and add search controls
        GridPane searchGrid = new GridPane();
        searchGrid.setPadding(new Insets(20));
        searchGrid.setHgap(10);
        searchGrid.setVgap(10);
        searchGrid.addRow(0, citySearchLabel, citySearchField);
        searchGrid.addRow(1, descriptionSearchLabel, descriptionSearchField);
        searchGrid.add(searchButton, 1, 2);

        // Set padding and alignment for My Account button
        BorderPane.setAlignment(myAccountButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(myAccountButton, new Insets(10));

        // Create border pane and add search controls and table
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(searchGrid);
        BorderPane.setAlignment(searchGrid, Pos.TOP_CENTER);
        borderPane.setCenter(table);
        borderPane.setRight(myAccountButton);

        getAllAds();//Fill the table with all the ads.

        // Set action for search button
        searchButton.setOnAction(event -> {
            String citySearchText = citySearchField.getText().toLowerCase();
            String descriptionSearchText = descriptionSearchField.getText().toLowerCase();
            filterAds(citySearchText, descriptionSearchText);
        });

        // Create scene and set to stage
        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Advertisement Form");
        primaryStage.show();
    }

    private void filterAds(String citySearchText, String descriptionSearchText) {
        // Filter the ads based on search criteria
        FilteredList<Ad> filteredAds = new FilteredList<>(adList);
        filteredAds.setPredicate(ad -> {
            String city = ad.getCity().toLowerCase();
            String description = ad.getDescription().toLowerCase();
            return city.contains(citySearchText) || description.contains(descriptionSearchText);
        });
        // Update the table with filtered data
        table.setItems(filteredAds);
    }

    // Assume this method adds sample ads to the adList
    private void populateAds() {
        adList.add(new Ad(1, "User1", "New York", "King David", 3, 2, 5, 150, 200000, "2022-03-15", "Spacious apartment with a great view"));
        adList.add(new Ad(2, "User2", "London", "The God Father", 4, 3, 2, 200, 300000, "2022-03-18", "Perfect for a family"));
        adList.add(new Ad(3, "User3", "Paris", "Moon", 1, 1, 3, 50, 100000, "2022-03-20", "Ideal for solo travelers"));
        adList.add(new Ad(4, "User4", "Tokyo", "Linkoln", 2, 1, 10, 80, 150000, "2022-03-25", "Close to public transport"));
    }
    private void getAllAds() throws IOException {
        Request allAd = new Request("getAllAds");
        SendToServer sendToServer = new SendToServer("getAllAds");
        SendToServer.saveToServer(allAd);
        GetFromServer getFromServer = new GetFromServer();
        Map map = getFromServer.process();
        FilteredList filteredAds = (FilteredList)map.get("allAds");
        table.setItems(filteredAds);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


