package com.example.kurs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {



    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;


    @FXML
    protected void loginButtonClick() throws SQLException, IOException {
        if (loginQuery(loginField.getText(), passwordField.getText())) {
            login();
        }
    }

    private void login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private boolean loginQuery(String login, String password) throws SQLException {

        String query = "Select login,password from client";
        ResultSet resultSet = DBConnector.executeQuery(query);
        while (resultSet.next()) {
            if ((resultSet.getString(1).equals(login)) && (resultSet.getString(2).equals(password))) {
                LoginController.login = login;
                return true;
            }
        }
        DBConnector.closeConnection();
        return false;
    }
}