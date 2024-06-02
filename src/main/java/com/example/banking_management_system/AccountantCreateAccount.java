package com.example.banking_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class AccountantCreateAccount {

    @FXML
    private TextField Address;

    @FXML
    private DatePicker DOB;

    @FXML
    private RadioButton Female;

    @FXML
    private TextField ID;

    @FXML
    private RadioButton Male;

    @FXML
    private TextField NIC;

    @FXML
    private TextField Name;

    @FXML
    private TextField Password;

    @FXML
    private TextField Phone_No;

    @FXML
    private Button back;

    @FXML
    private Button register;

    @FXML
    private ToggleGroup tggender;

    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void oneRegisterClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser="root";
        String databasePassword="zamanlew6732";
        String url="jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,databaseUser,databasePassword);
        String sq="INSERT Into accountant  (accountantID, Accountant_Name, accountant_Pas, DOB, Gender, NIC, Phone_no, Address) values (?,?,?,?,?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(sq);


        stat.setString(1,ID.getText());
        stat.setString(2,Name.getText());
        stat.setString(3,Password.getText());
        LocalDate localDate = DOB.getValue();
        Date date = Date.valueOf(localDate);
        stat.setDate(4, date);
        String gender = null;
        if (Male.isSelected()) {
            gender = "Male";
            stat.setString(5, gender);
        }else if (Female.isSelected()) {
            gender = "Female";
            stat.setString(5, gender);
        }
        else {
            gender = "NULL";
            stat.setString(5, gender);
        }
        stat.setString(6,NIC.getText());
        stat.setString(7,Phone_No.getText());
        stat.setString(8,Address.getText());
        stat.executeUpdate();
        stat.close();
        con.close();
    }

    }


