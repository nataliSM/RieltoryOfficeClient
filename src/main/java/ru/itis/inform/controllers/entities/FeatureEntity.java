package ru.itis.inform.controllers.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ru.itis.inform.models.Feature;

/**
 * Created by Natalia on 09.04.17.
 */
public class FeatureEntity extends RecursiveTreeObject<FeatureEntity> {
    public SimpleIntegerProperty id;
    public SimpleIntegerProperty countOfRoom;
    public SimpleStringProperty condition;
    public SimpleStringProperty repair;

    public FeatureEntity(Feature feature) {
        this.id = new SimpleIntegerProperty(feature.getId());
        this.countOfRoom = new SimpleIntegerProperty(feature.getCountOfRoom());
        this.condition = new SimpleStringProperty(feature.getCondition());
        this.repair = new SimpleStringProperty(feature.getRepair());
    }

    @Override
    public String toString() {
        return "FeatureEntity{" +
                "id=" + id +
                ", countOfRoom=" + countOfRoom +
                ", condition='" + condition + '\'' +
                ", repair='" + repair + '\'' +
                '}';
    }
}
