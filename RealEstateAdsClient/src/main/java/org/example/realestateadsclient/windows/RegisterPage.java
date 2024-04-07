package org.example.realestateadsclient.windows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.realestateadsclient.model.User;

import java.util.ArrayList;

public class RegisterPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels, text fields, and buttons
        Label titleLabel = new Label("Registration Page");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter your phone number");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Button registerButton = new Button("Register");

        // Set action for register button
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            Long phoneNum = Long.parseLong(phoneField.getText());
            String email = emailField.getText();
            // Create a User object with the entered information

            User newUser = new User(username, password, phoneNum,email, new ArrayList<>());
            // Call method to send registration request to server
            sendRegistrationRequest(newUser, (Stage) registerButton.getScene().getWindow());
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
        grid.add(phoneLabel, 0, 3);
        grid.add(phoneField, 1, 3);
        grid.add(emailLabel, 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(registerButton, 0, 5, 2, 1);

        // Create scene and set to stage
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration Page");
        primaryStage.show();
    }

    // Method to send registration request to server
    // Method to send registration request to server
    private void sendRegistrationRequest(User user, Stage primaryStage) {
        // Implement logic to send registration request to server
        System.out.println("Sending registration request for user: " + user.getUserName());

        // Close the current registration window
        primaryStage.close();

        // Open a new login window
        LoginPage loginPage = new LoginPage();
        loginPage.start(new Stage());
    }


    public static void main(String[] args) {
        launch(args);
    }
}

