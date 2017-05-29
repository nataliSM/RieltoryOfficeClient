package ru.itis.inform.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import ru.itis.inform.RieltorOfficeClientApplication;
import ru.itis.inform.models.*;
import ru.itis.inform.services.OffersServices;
import ru.itis.inform.services.OffersServicesImpl;
import ru.itis.inform.services.Result;
import ru.itis.inform.services.ServerException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by acacuce on 28.05.17.
 */
public class NewOfferController implements Initializable {

    OffersServices offersServices = new OffersServicesImpl();

    @FXML
    private StackPane root;

    @FXML
    private JFXScrollPane scroll;

    @FXML
    private JFXListView<?> form;

    @FXML
    private JFXListView<?> addressSubList;

    @FXML
    private JFXTextField cityTextField;

    @FXML
    private JFXTextField streetTextField;

    @FXML
    private JFXTextField houseTextField;

    @FXML
    private JFXTextField flatTextField;

    @FXML
    private JFXListView<?> featuresSubList;

    @FXML
    private JFXTextField conditionTextField;

    @FXML
    private JFXTextField countOfRoomTextField;

    @FXML
    private JFXTextField repairTextField;

    @FXML
    private JFXListView<?> traderInfoSubList;

    @FXML
    private JFXTextField traderNameTextField;

    @FXML
    private JFXTextField traderPhoneTextField;

    @FXML
    private JFXTextField costPhoneTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void saveButtonDidPressed(ActionEvent event) {
        Offer offer = new Offer();
        Address address = new Address();
        address.setCityName(cityTextField.getText());
        address.setStreetName(streetTextField.getText());
        address.setFlat(Integer.valueOf(flatTextField.getText()));
        address.setHouse(Integer.valueOf(houseTextField.getText()));
        offer.setAddress(address);

        Feature feature = new Feature();
        feature.setCondition(conditionTextField.getText());
        feature.setCountOfRoom(Integer.valueOf(countOfRoomTextField.getText()));
        feature.setRepair(repairTextField.getText());
        offer.setFeature(feature);

        Trader trader = new Trader();
        trader.setName(traderNameTextField.getText());
        trader.setPhoneNumber(traderPhoneTextField.getText());
        offer.setTrader(trader);
        offer.setCost(costPhoneTextField.getText());

        User user = RieltorOfficeClientApplication.getInstance().getUser();
        offersServices.save(offer, user, new Result<Offer>() {
            @Override
            public void successful(Offer result) {
                RieltorOfficeClientApplication.getInstance().goToFeatures();
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

    @FXML
    public void backButtonDidPressed(ActionEvent event) {
        RieltorOfficeClientApplication.getInstance().goToFeatures();

    }


}
