package com.example.banking_management_system;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManagerMainMenu implements Initializable {

    @FXML
    private TextField Date;

    @FXML
    private Button LoanApproval;

    @FXML
    private Button back;

    @FXML
    private Button create_Account;

    @FXML
    private Button customer_List;

    @FXML
    private Label date;

    @FXML
    private Button exit;

    @FXML
    private Button transaction;
    @FXML
    private TextField timeField;
    private Timeline timeline;

    @FXML
    void currentDate(ActionEvent event) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String formattedTime = currentDateTime.format(timeFormatter);
        Date.setText(formattedDateTime);
        timeField.setText(formattedTime);
    }

    @FXML
    void onCreateaccountClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AccountantCreateAccount.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onCustomerlistClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onLoanapprovalClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerLoanApproval.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onTrasactionClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TransactionHistory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onexitClicked(ActionEvent event) {
     showExitConfirmation();
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
        currentDate(new ActionEvent());

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::currentDate));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
