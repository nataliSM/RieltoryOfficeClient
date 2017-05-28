package ru.itis.inform.controllers.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ru.itis.inform.models.Address;

/**
 * Created by Natalia on 09.04.17.
 */
public class
AddressEntity extends RecursiveTreeObject<AddressEntity> {
    public SimpleIntegerProperty id;
    public SimpleIntegerProperty house;
    public SimpleIntegerProperty flat;
    public SimpleStringProperty cityName;
    public SimpleStringProperty streetName;

    public AddressEntity(Address address) {
        this.id = new SimpleIntegerProperty(address.getId());
        this.house = new SimpleIntegerProperty(address.getHouse());
        this.flat = new SimpleIntegerProperty(address.getFlat());
        this.cityName = new SimpleStringProperty(address.getCityName());
        this.streetName = new SimpleStringProperty(address.getStreetName());
    }



    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", house=" + house +
                ", flat=" + flat +
                ", cityName='" + cityName + '\'' +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}
