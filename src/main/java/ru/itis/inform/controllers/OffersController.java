package ru.itis.inform.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import ru.itis.inform.RieltorOfficeClientApplication;
import ru.itis.inform.controllers.entities.OfferEntity;
import ru.itis.inform.models.Offer;
import ru.itis.inform.services.OffersServicesImpl;
import ru.itis.inform.services.Result;
import ru.itis.inform.services.ServerException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Natalia on 26.05.17.
 */
public class OffersController implements Initializable {
    @FXML
    private JFXTreeTableView<OfferEntity> tableView;
    @FXML
    private JFXButton deleteButton;

    private OfferEntity selectedEntity;

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

        idColumn.setCellFactory((param) ->
                new GenericEditableTreeTableCell<OfferEntity, Integer>(new TextFieldEditorBuilder()));
        idColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<OfferEntity, Integer> t)->{
            (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).id.
                    set(t.getNewValue());

        });

        JFXTreeTableColumn<OfferEntity, String> addressColumn = new JFXTreeTableColumn<>("Address");
        addressColumn.setPrefWidth(300);
        addressColumn.setEditable(false);
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
            Offer offer = new Offer(t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue());

            new OffersServicesImpl().update(offer, RieltorOfficeClientApplication.getInstance().getUser(), new Result<Offer>() {
                @Override
                public void successful(Offer result) {

                }

                @Override
                public void failure(ServerException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("Failure update offer: " + e.getMessage());
                    alert.showAndWait();
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

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedEntity = newSelection.getValue();
                deleteButton.setDisable(false);

            }else  {
                deleteButton.setDisable(true);
            }
        });
        deleteButton.setDisable(true);
        

        JFXTextField filterField = new JFXTextField();
        filterField.textProperty().addListener((o,oldVal,newVal)->{
            tableView.setPredicate(user -> user.getValue().cost.get().contains(newVal));
        });

        Label size = new Label();
        size.textProperty().bind(Bindings.createStringBinding(()->tableView.getCurrentItemsCount()+"",
                tableView.currentItemsCountProperty()));

    }

    @FXML
    public void addButtonDidPressed(ActionEvent event) {
        RieltorOfficeClientApplication.getInstance().gotoNewOffer();

    }

    @FXML
    public void deleteButtonDidPressed(ActionEvent event) {
        new OffersServicesImpl().delete(new Offer(selectedEntity), RieltorOfficeClientApplication.getInstance().getUser(), new Result<Void>() {
            @Override
            public void successful(Void result) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Deleted");
                alert.setHeaderText(null);
                alert.setContentText("Offer deleted at: " + selectedEntity.id);
                alert.showAndWait();

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
            }

            @Override
            public void failure(ServerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Updated");
                alert.setHeaderText(null);
                alert.setContentText("Failure delete offer: " + e.getMessage());
                alert.showAndWait();
            }
        });

    }

}
