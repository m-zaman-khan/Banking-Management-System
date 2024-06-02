package com.example.banking_management_system;

public class TableModel {
    String Name_Col,DOB_Col,Country_Col,Gender_Col,NIC_Col,AccNo_Col,Phone_Col,AccType_Col;

    public TableModel(String Name_Col, String DOB_Col, String Country_Col, String Gender_Col, String NIC_Col, String AccNo_Col, String Phone_Col, String AccType_Col) {
        this.Name_Col = Name_Col;
        this.DOB_Col = DOB_Col;
        this.Country_Col = Country_Col;
        this.Gender_Col = Gender_Col;
        this.NIC_Col = NIC_Col;
        this.AccNo_Col = AccNo_Col;
        this.Phone_Col = Phone_Col;
        this.AccType_Col = AccType_Col;
    }

    public String getAccNo_Col() {
        return AccNo_Col;
    }

    public void setAccNo_Col(String accNo_Col) {
        AccNo_Col = accNo_Col;
    }

    public String getAccType_Col() {
        return AccType_Col;
    }

    public void setAccType_Col(String accType_Col) {
        AccType_Col = accType_Col;
    }

    public String getCountry_Col() {
        return Country_Col;
    }

    public void setCountry_Col(String country_Col) {
        Country_Col = country_Col;
    }

    public String getDOB_Col() {
        return DOB_Col;
    }

    public void setDOB_Col(String DOB_Col) {
        this.DOB_Col = DOB_Col;
    }

    public String getGender_Col() {
        return Gender_Col;
    }

    public void setGender_Col(String gender_Col) {
        Gender_Col = gender_Col;
    }

    public String getNIC_Col() {
        return NIC_Col;
    }

    public void setNIC_Col(String NIC_Col) {
        this.NIC_Col = NIC_Col;
    }

    public String getName_Col() {
        return Name_Col;
    }

    public void setName_Col(String name_Col) {
        Name_Col = name_Col;
    }

    public String getPhone_Col() {
        return Phone_Col;
    }

    public void setPhone_Col(String phone_Col) {
        Phone_Col = phone_Col;
    }
}
