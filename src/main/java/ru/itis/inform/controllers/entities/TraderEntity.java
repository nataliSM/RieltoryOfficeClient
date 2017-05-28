package ru.itis.inform.controllers.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ru.itis.inform.models.Trader;

/**
 * Created by Natalia on 09.04.17.
 */
public class TraderEntity extends RecursiveTreeObject<TraderEntity> {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleStringProperty phoneNumber;

    public TraderEntity(Trader trader) {
        this.id = new SimpleIntegerProperty(trader.getId());
        this.name = new SimpleStringProperty(trader.getName());
        this.phoneNumber = new SimpleStringProperty(trader.getName());
    }

    @Override
    public String toString() {
        return "TraderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
