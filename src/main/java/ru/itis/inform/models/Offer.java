package ru.itis.inform.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ru.itis.inform.controllers.entities.OfferEntity;

/**
 * Created by Natalia on 09.04.17.
 */
public class Offer {
    private Integer id;
    private String cost;
    private Feature feature;
    private Trader trader;
    private Address address;

    public Offer() {
    }

    public Offer(OfferEntity offerEntity) {
        this.id = offerEntity.id.getValue();
        this.cost = offerEntity.cost.getValue();
        this.feature = new Feature(offerEntity.feature.getValue());
        this.trader = new Trader(offerEntity.trader.getValue());
        this.address = new Address(offerEntity.address.get());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", cost='" + cost + '\'' +
                ", feature=" + feature +
                ", trader=" + trader +
                ", address=" + address +
                '}';
    }
}
