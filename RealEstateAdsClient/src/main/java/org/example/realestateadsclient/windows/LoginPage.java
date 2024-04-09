package org.example.realestateadsclient.windows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.realestateadsclient.server.GetFromServer;
import org.example.realestateadsclient.server.SendToServer;
import org.example.realestateadsclient.model.Request;
import org.example.realestateadsclient.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels, text fields, and buttons
        Label titleLabel = new Label("Login Page");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        // Set action for login button
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Call method to send login request to server
            boolean bool = false;
            try {
                bool = sendLoginRequest(username, password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (bool){
                primaryStage.close();
                UserPage userPage = new UserPage();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username or password");

                // Show the alert dialog
                alert.showAndWait();
            }
        });

        // Set action for register button
        registerButton.setOnAction(event -> {
            primaryStage.close();

            // Open a new login window
            RegisterPage registerPage = new RegisterPage();
            registerPage.start(new Stage());
        });

        // Create grid pane and add controls
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(loginButton, 0, 3);
        grid.add(registerButton, 1, 3);

        // Create scene and set to stage
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    // Method to send login request to server
    private boolean sendLoginRequest(String username, String password) throws IOException {
        User user = new User(username, password);
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("verifyUser", user);
        Request request = new Request("verifyUser", reqMap);
        SendToServer.saveToServer(request);
        GetFromServer getFromServer = new GetFromServer();
        reqMap = getFromServer.process();
        if (reqMap.containsKey(false)){
            return false;
        }
        else if (reqMap.containsKey(true)){
            return true;
        }
        return false;
    }

    // Method to send register request to server
    private void sendRegisterRequest(User newUser) throws IOException {
        // Implement logic to send register request to server

        Map<String, Object> reqMap = new HashMap<>();
        //reqMap.put("verifyUser", user);
        Request request = new Request("verifyUser", reqMap);
        SendToServer.saveToServer(request);

        GetFromServer getFromServer = new GetFromServer();
        reqMap = getFromServer.process();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


