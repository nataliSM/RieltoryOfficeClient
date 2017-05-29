package ru.itis.inform.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import ru.itis.inform.RieltorOfficeClientApplication;
import ru.itis.inform.models.User;
import ru.itis.inform.services.LoginService;
import ru.itis.inform.services.LoginServiceImpl;
import ru.itis.inform.services.Result;
import ru.itis.inform.services.ServerException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by acacuce on 28.05.17.
 */
public class SignUpController implements Initializable {
    private LoginService service = new LoginServiceImpl();
    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    void signUpButtonDidPressed(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();

        service.register(username, password, email, new Result<User>() {
            @Override
            public void successful(User result) {
                RieltorOfficeClientApplication.getInstance().gotoLogin();
            }

            @Override
            public void failure(ServerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
