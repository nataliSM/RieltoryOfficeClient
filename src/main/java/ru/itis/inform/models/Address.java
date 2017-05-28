package ru.itis.inform.models;

import ru.itis.inform.controllers.entities.AddressEntity;

/**
 * Created by Natalia on 09.04.17.
 */
public class Address {
    private Integer id;
    private Integer house;
    private Integer flat;
    private  String cityName;
    private  String streetName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Address() {
    }

    public Address(AddressEntity addressEntity) {
        id = addressEntity.id.getValue();
        house = addressEntity.house.getValue();
        flat = addressEntity.flat.getValue();
        cityName = addressEntity.cityName.getValue();
        streetName = addressEntity.streetName.getValue();
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
