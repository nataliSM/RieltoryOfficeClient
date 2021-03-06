package ru.itis.inform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.inform.models.User;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Natalia on 13.05.17.
 */
public class RieltorOfficeClientApplication extends Application{
    private static RieltorOfficeClientApplication instance;
    private Stage stage;
    private User user;

    public RieltorOfficeClientApplication () {
        instance = this;
    }

    public static RieltorOfficeClientApplication getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(RieltorOfficeClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void gotoNewOffer() {
        try {
            replaceSceneContent("views/createFeature.fxml");
        } catch (Exception ex) {
            Logger.getLogger(RieltorOfficeClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLogin() {
        try {
            replaceSceneContent("views/login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(RieltorOfficeClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToFeatures() {
        try {
            replaceSceneContent("views/features.fxml");
        } catch (Exception ex) {
            Logger.getLogger(RieltorOfficeClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToSignUp() {
        try {
            replaceSceneContent("views/registration.fxml");
        } catch (Exception ex) {
            Logger.getLogger(RieltorOfficeClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 550);

//            scene.getStylesheets().add(RieltorOfficeClientApplication.class.getResource("demo.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }


}
