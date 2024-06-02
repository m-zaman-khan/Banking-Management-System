package com.example.banking_management_system;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountantLogIn implements Initializable {

    @FXML
    private RadioButton Accountant_rb;

    @FXML
    private Button Exit;

    @FXML
    private RadioButton Manager_rb;

    @FXML
    private PasswordField Password_field;

    @FXML
    private TextField Username_field;

    @FXML
    private ToggleGroup accType;

    @FXML
    private Label loginCredentials;

    @FXML
    private Label loginCredentials1;

    @FXML
    private Label passWord;

    @FXML
    private Button signIn;

    @FXML
    private Label userName;

    @FXML
    private Label ErrorLabel;

    @FXML
    private Label showPasswordLabel;
    @FXML
    private TextField passwordTextField;

    private boolean passwordVisible = false;


    @FXML
    void Onaccountantclicked(ActionEvent event) throws IOException {
        accountanttype(event);

    }

    @FXML
    void onExitClicked(ActionEvent event) {
        showExitConfirmation();
    }

    @FXML
    void onManagerClicked(ActionEvent event) throws IOException {
       managertype(event);
    }

    @FXML
    void onSignInClicked(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Connection dataBaselink;
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dataBaselink = DriverManager.getConnection(url, databaseUser, databasePassword);
        ResultSet rs;
        PreparedStatement stat = dataBaselink.prepareStatement(("SELECT * FROM accountant"));
        rs = stat.executeQuery();

        boolean isValidUser = false;

        while (rs.next()) {
            if (Objects.equals(rs.getString("Accountant_Name"), Username_field.getText())) {
                if (Objects.equals(rs.getString("accountant_Pas"), Password_field.getText())) {
                    isValidUser = true;
                    break;
                }
            }
        }

        if (isValidUser) {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } else {
            // Display an error message
            ErrorLabel.setText("Invalid password or username");

            passwordVisible = false;
            showPassword();

            // Clear the text and password fields
            Username_field.setText("");
            Password_field.setText("");


        }
    }
    private String actualPassword = "";

    private void showPassword() {
        if (passwordVisible) {
            passwordTextField.setText(Password_field.getText());
            passwordTextField.setVisible(true);
            Password_field.setVisible(false);
            showPasswordLabel.setText("Hide Password");
        } else {
            Password_field.setText(passwordTextField.getText());
            Password_field.setVisible(true);
            passwordTextField.setVisible(false);
            showPasswordLabel.setText("Show Password");
        }
    }
    private void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit this app?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, so exit the application
                Platform.exit();
            }
        });
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPasswordLabel.setOnMouseClicked(event -> {
            passwordVisible = !passwordVisible;
            showPassword();
        });

    }

    public void accountanttype(ActionEvent event) throws IOException {
        if (Accountant_rb.isSelected()) {
            Parent root = FXMLLoader.load(getClass().getResource("AccountantLogIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }
    public void managertype(ActionEvent event) throws IOException {
        if (Manager_rb.isSelected()) {
            Parent root = FXMLLoader.load(getClass().getResource("ManagerLogIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }
}
