package com.example.banking_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerList implements Initializable{


    @FXML
    private TableView<TableModel> table;

    @FXML
    private TableColumn<TableModel, String> AccNo_Col;

    @FXML
    private TableColumn<TableModel, String> AccType_Col;

    @FXML
    private TableColumn<TableModel, String> Country_Col;

    @FXML
    private TableColumn<TableModel, String> DOB_Col;

    @FXML
    private TableColumn<TableModel, String> Gender_Col;

    @FXML
    private TableColumn<TableModel, String> NIC_Col;

    @FXML
    private TableColumn<TableModel, String> Name_Col;

    @FXML
    private TableColumn<TableModel, String> Phone_Col;

    @FXML
    private Button back;

    ObservableList<TableModel> listview= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccNo_Col.setCellValueFactory(new PropertyValueFactory<>("AccNo_Col"));
        AccType_Col.setCellValueFactory(new PropertyValueFactory<>("AccType_Col"));
        Country_Col.setCellValueFactory(new PropertyValueFactory<>("Country_Col"));
        DOB_Col.setCellValueFactory(new PropertyValueFactory<>("DOB_Col"));
        Gender_Col.setCellValueFactory(new PropertyValueFactory<>("Gender_Col"));
        NIC_Col.setCellValueFactory(new PropertyValueFactory<>("NIC_Col"));
        Name_Col.setCellValueFactory(new PropertyValueFactory<>("Name_Col"));
        Phone_Col.setCellValueFactory(new PropertyValueFactory<>("Phone_Col"));

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_mangement_system","root","zamanlew6732");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select name,DOB,country,gender,nic,accNo,phone,accType from clients");

            while(resultSet.next()){
                listview.add(new TableModel(
                        resultSet.getString("name"),
                        resultSet.getString("DOB"),
                        resultSet.getString("country"),
                        resultSet.getString("gender"),
                        resultSet.getString("nic"),
                        resultSet.getString("accNo"),
                        resultSet.getString("phone"),
                        resultSet.getString("accType")

                ));
                table.setItems(listview);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
