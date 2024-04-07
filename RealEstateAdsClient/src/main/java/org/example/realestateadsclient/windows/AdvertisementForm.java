package org.example.realestateadsclient.windows;

import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.realestateadsclient.model.Ad;
import org.example.realestateadsclient.model.Request;
import org.example.realestateadsclient.server.GetFromServer;
import org.example.realestateadsclient.server.SendToServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdvertisementForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create form controls
        TextField cityField = new TextField();
        TextField streetField = new TextField();
        Spinner<Integer> homeSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
        Spinner<Integer> roomsSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
        Spinner<Integer> floorSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
        Spinner<Integer> squareMeterSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
        TextField priceField = new TextField();
        DatePicker datePicker = new DatePicker();
        TextArea descriptionArea = new TextArea();

        // Create labels
        Label cityLabel = new Label("City:");
        Label streetLabel = new Label("Street:");
        Label homeLabel = new Label("Num of House:");
        Label roomsLabel = new Label("Num of Rooms:");
        Label floorLabel = new Label("Num of Floor:");
        Label squareMeterLabel = new Label("Square Meter:");
        Label priceLabel = new Label("Price:");
        Label dateLabel = new Label("Date:");
        Label descriptionLabel = new Label("Description:");

        // Create submit button
        Button submitButton = new Button("Submit");

        // Create grid pane and add controls
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, cityLabel, cityField);
        grid.addRow(1, streetLabel, streetField);
        grid.addRow(2, homeLabel, homeSpinner);
        grid.addRow(3, roomsLabel, roomsSpinner);
        grid.addRow(4, floorLabel, floorSpinner);
        grid.addRow(5, squareMeterLabel, squareMeterSpinner);
        grid.addRow(6, priceLabel, priceField);
        grid.addRow(7, dateLabel, datePicker);
        grid.addRow(8, descriptionLabel, descriptionArea);
        grid.add(submitButton, 1, 9);

        // Set action for submit button
        submitButton.setOnAction(event -> {
            // Create Ad object with entered details
            long id = 0; // You need to generate a unique id for each ad
            String user = ""; // You need to set the user
            String city = cityField.getText();
            String street = streetField.getText();
            int numOfHome = homeSpinner.getValue();
            int numOfRooms = roomsSpinner.getValue();
            int numOfFloor = floorSpinner.getValue();
            int squareOfMeter = squareMeterSpinner.getValue();
            int price = Integer.parseInt(priceField.getText());
            String date = datePicker.getValue().toString();
            String description = descriptionArea.getText();

            // Create Ad object
            Ad ad = new Ad(id, user, city, street, numOfHome, numOfRooms, numOfFloor, squareOfMeter, price, date, description);

            // Send Ad object to server (you need to implement this part)
            try {
                publishNewAd(ad);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Create scene and set to stage
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Advertisement Form");
        primaryStage.show();
    }

    private void publishNewAd(Ad ad) throws IOException {
        Map map = new HashMap();
        map.put("addAd", ad);
        Request allAd = new Request("addAd",map);
        SendToServer.saveToServer(allAd);
    }
    private void sendToServer(Ad ad) {
        // Send ad object to server (you need to implement this part)
        // For demonstration, simply print ad details
        System.out.println("Sending advertisement to server:");
        System.out.println(ad);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
