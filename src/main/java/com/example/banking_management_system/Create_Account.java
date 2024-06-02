package com.example.banking_management_system;

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
import java.util.ResourceBundle;
import java.sql.*;
import java.time.LocalDate;


public class Create_Account implements Initializable {
    @FXML
    public RadioButton Male;
    @FXML
    public RadioButton Female;
    @FXML
    private TextField AccountNo;

    @FXML
    private TextField AccountType;

    @FXML
    private TextField Address;

    @FXML
    private TextField Country;

    @FXML
    private DatePicker DOB;

    @FXML
    private TextField Ini_Deposit;

    @FXML
    private TextField NIC;

    @FXML
    private TextField Name;

    @FXML
    private TextField Phone_No;

    @FXML
    private Button back;

    @FXML
    private Button register;

    @FXML
    private TextField pin;

    @FXML
    private ToggleGroup tggender;

    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void oneRegisterClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (Name.getText().isEmpty() || DOB.getValue() == null || Country.getText().isEmpty()
                || tggender.getSelectedToggle() == null || NIC.getText().isEmpty()
                || pin.getText().isEmpty() || AccountNo.getText().isEmpty()
                || AccountType.getText().isEmpty() || Ini_Deposit.getText().isEmpty()
                || Phone_No.getText().isEmpty() || Address.getText().isEmpty()) {
            // Show an error message if any text field is empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill in all the text fields.");
            alert.showAndWait();
            return; // Exit the method to prevent further processing
        }
        String databaseUser="root";
        String databasePassword="zamanlew6732";
        String url="jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,databaseUser,databasePassword);
        String sq="INSERT Into clients  (name,DOB,country,gender,nic,pin,accNo,accType,iniDeposit,phone,address) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(sq);


        stat.setString(1,Name.getText());
        LocalDate localDate = DOB.getValue();
        Date date = Date.valueOf(localDate);
        stat.setDate(2, date);
        stat.setString(3,Country.getText());
        String gender = null;
        if (Male.isSelected()) {
            gender = "Male";
            stat.setString(4, gender);
        }else if (Female.isSelected()) {
            gender = "Female";
            stat.setString(4, gender);
        }
        else {
            gender = "NULL";
            stat.setString(4, gender);
        }
        stat.setString(5,NIC.getText());
        stat.setString(6,pin.getText());
        stat.setString(7,AccountNo.getText());
        stat.setString(8,AccountType.getText());
        stat.setString(9,Ini_Deposit.getText());
        stat.setString(10,Phone_No.getText());
        stat.setString(11,Address.getText());
       // prepareStatement

        stat.executeUpdate();
        stat.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create Account");
        alert.setHeaderText(null);
        alert.setContentText("Account is created Successfully");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
