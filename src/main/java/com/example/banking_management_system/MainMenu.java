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

public class MainMenu implements Initializable {

    @FXML
    private TextField Date;

    @FXML
    private TextField Username;

    @FXML
    private Button back;

    @FXML
    private Button change_Pin;

    @FXML
    private Button create_Account;

    @FXML
    private Button customer_List;

    @FXML
    private Label date;

    @FXML
    private Button deposit;

    @FXML
    private Button exit;

    @FXML
    private Button profile;

    @FXML
    private Button transaction;

    @FXML
    private Button transfer;

    @FXML
    private Label username;

    @FXML
    private Button veiw_Balance;

    @FXML
    private Button withdraw;

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
    void onBalanceClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("ViewBalance.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onChangepinClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Change-Pin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onCreateaccountClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Create Account.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onCustomerlistClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onDepositClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Deposit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onTransferClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Transfer.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void onTrasactionClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("TransactionHistory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void onWithdrawClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("withdraw.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("AccountantLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

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

    @FXML
    void onexitClicked(ActionEvent event) {
        showExitConfirmation();
    }

    @FXML
    void seeProfile(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
    @FXML
    void onLoanRequestClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("LoanRequest.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::currentDate));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
