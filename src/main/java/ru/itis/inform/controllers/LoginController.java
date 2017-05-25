package ru.itis.inform.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.itis.inform.RieltorOfficeClientApplication;
import ru.itis.inform.services.LoginService;
import ru.itis.inform.services.LoginServiceImpl;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Natalia on 13.05.17.
 */
public class LoginController implements Initializable {
    private LoginService loginService = new LoginServiceImpl();
    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXTextField passwordTextField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton signUpButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loginButtonDidPressed(ActionEvent event) {
        loginService.login(usernameTextField.getText(), passwordTextField.getText());

    }

    @FXML
    public void signUpButtonDidPressed(ActionEvent event) {

    }

}
