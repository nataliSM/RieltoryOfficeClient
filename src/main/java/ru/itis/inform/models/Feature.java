package ru.itis.inform.models;

import ru.itis.inform.controllers.entities.FeatureEntity;

/**
 * Created by Natalia on 09.04.17.
 */
public class Feature {
    private Integer id;
    private Integer countOfRoom;
    private String condition;
    private String repair;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(Integer countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public Feature() {

    }

    public Feature(FeatureEntity featureEntity) {
        this.id = featureEntity.id.getValue();
        this.countOfRoom = featureEntity.countOfRoom.getValue();
        this.condition = featureEntity.condition.getValue();
        this.repair = featureEntity.repair.getValue();
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
