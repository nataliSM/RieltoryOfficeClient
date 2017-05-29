package ru.itis.inform.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import ru.itis.inform.RieltorOfficeClientApplication;
import ru.itis.inform.controllers.entities.OfferEntity;
import ru.itis.inform.controllers.entities.OfferEntity;
import ru.itis.inform.models.Address;
import ru.itis.inform.models.Offer;
import ru.itis.inform.services.OffersServices;
import ru.itis.inform.services.OffersServicesImpl;
import ru.itis.inform.services.Result;
import ru.itis.inform.services.ServerException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Created by Natalia on 26.05.17.
 */
public class OffersController implements Initializable {
    @FXML
    private JFXTreeTableView<OfferEntity> tableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXTreeTableColumn<OfferEntity, Integer> idColumn = new JFXTreeTableColumn<>("Id");
        idColumn.setPrefWidth(20);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<OfferEntity, Integer> param) ->{
            if(idColumn.validateValue(param)) return param.getValue().getValue().id.asObject();
            else return idColumn.getComputedValue(param);
        });
        idColumn.setEditable(false);
        idColumn.setCellFactory((param) ->
                new GenericEditableTreeTableCell<OfferEntity, Integer>(new TextFieldEditorBuilder()));
        idColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<OfferEntity, Integer> t)->{
            (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).id.
                    set(t.getNewValue());

        });

        JFXTreeTableColumn<OfferEntity, String> addressColumn = new JFXTreeTableColumn<>("Address");
        addressColumn.setPrefWidth(300);
        addressColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<OfferEntity, String> param) ->{
            if(addressColumn.validateValue(param)) return param.getValue().getValue().address.getValue().cityName
                    .concat(", ")
                    .concat(param.getValue().getValue().address.getValue().streetName)
                    .concat(", ")
                    .concat(param.getValue().getValue().address.getValue().house)
                    .concat(", ")
                    .concat(param.getValue().getValue().address.getValue().flat);
            else return addressColumn.getComputedValue(param);
        });
        addressColumn.setCellFactory((param) ->
                new GenericEditableTreeTableCell<OfferEntity, String>(new TextFieldEditorBuilder()));
        
        

        JFXTreeTableColumn<OfferEntity, String> costColumn = new JFXTreeTableColumn<>("cost");
        costColumn.setPrefWidth(80);
        costColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<OfferEntity, String> param) ->{
            if(costColumn.validateValue(param)) return param.getValue().getValue().cost;
            else return costColumn.getComputedValue(param);
        });
        costColumn.setCellFactory((param) ->
                new GenericEditableTreeTableCell<OfferEntity, String>(new TextFieldEditorBuilder()));
        costColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<OfferEntity, String> t)->{
            (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).cost.
                    set(t.getNewValue());

            new OffersServicesImpl().update(new Offer(t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()), RieltorOfficeClientApplication.getInstance().getUser(), new Result<Offer>() {
                @Override
                public void successful(Offer result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("Offer updated at: " + result.getId());
                    alert.showAndWait();
                }

                @Override
                public void failure(ServerException e) {

                }
            });
        });

        ObservableList<OfferEntity> offers = FXCollections.observableArrayList();
        new OffersServicesImpl().allOffers(new Result<List<Offer>>() {
            @Override
            public void successful(List<Offer> result) {
                result.iterator().forEachRemaining(offer -> {
                    offers.add(new OfferEntity(offer));
                });

            }

            @Override
            public void failure(ServerException e) {

            }
        }, RieltorOfficeClientApplication.getInstance().getUser());


        final TreeItem<OfferEntity> root = new RecursiveTreeItem<OfferEntity>(offers, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.setEditable(true);
        tableView.getColumns().setAll(idColumn, costColumn, addressColumn);
        

        JFXTextField filterField = new JFXTextField();
        filterField.textProperty().addListener((o,oldVal,newVal)->{
            tableView.setPredicate(user -> user.getValue().cost.get().contains(newVal));
        });

        Label size = new Label();
        size.textProperty().bind(Bindings.createStringBinding(()->tableView.getCurrentItemsCount()+"",
                tableView.currentItemsCountProperty()));
//
//        JFXTreeTableColumn<User, String> empColumn = new JFXTreeTableColumn<>("Employee");
//        empColumn.setPrefWidth(150);
//        empColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
//            if(empColumn.validateValue(param)) return param.getValue().getValue().userName;
//            else return empColumn.getComputedValue(param);
//        });
//
//        JFXTreeTableColumn<User, String> ageColumn = new JFXTreeTableColumn<>("Age");
//        ageColumn.setPrefWidth(150);
//        ageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->{
//            if(ageColumn.validateValue(param)) return param.getValue().getValue().age;
//            else return ageColumn.getComputedValue(param);
//        });
    }

}
