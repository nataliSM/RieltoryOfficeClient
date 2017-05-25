package ru.itis.inform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Application.launch;

/**
 * Created by Natalia on 13.05.17.
 */
public class RieltorOfficeClientApplication extends Application{
    private static RieltorOfficeClientApplication instance;
    private Stage stage;

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

    private void gotoLogin() {
        try {
            replaceSceneContent("views/login.fxml");
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
