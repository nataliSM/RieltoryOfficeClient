package ru.itis.inform.controllers.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import ru.itis.inform.models.Address;
import ru.itis.inform.models.Feature;
import ru.itis.inform.models.Offer;
import ru.itis.inform.models.Trader;

/**
 * Created by Natalia on 26.05.17.
 */
public class OfferEntity extends RecursiveTreeObject<OfferEntity> {
    public SimpleIntegerProperty id;
    public SimpleStringProperty cost;
    public SimpleObjectProperty<FeatureEntity> feature;
    public SimpleObjectProperty<TraderEntity> trader;
    public SimpleObjectProperty<AddressEntity> address;

    public OfferEntity(Offer offer) {
        this.cost = new SimpleStringProperty(offer.getCost());
        this.id = new SimpleIntegerProperty(offer.getId());
        this.feature = new SimpleObjectProperty<FeatureEntity>(new FeatureEntity(offer.getFeature()));
        this.trader = new SimpleObjectProperty<>(new TraderEntity(offer.getTrader()));
        this.address = new SimpleObjectProperty<>(new AddressEntity(offer.getAddress()));

    }
}
