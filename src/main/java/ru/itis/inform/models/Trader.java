package ru.itis.inform.models;

import ru.itis.inform.controllers.entities.TraderEntity;

/**
 * Created by Natalia on 09.04.17.
 */
public class Trader {
    private Integer id;
    private String name;
    private String phoneNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Trader() {

    }

    public Trader(TraderEntity traderEntity) {
        this.id = traderEntity.id.getValue();
        this.name = traderEntity.name.getValue();
        this.phoneNumber = traderEntity.phoneNumber.getValue();
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
