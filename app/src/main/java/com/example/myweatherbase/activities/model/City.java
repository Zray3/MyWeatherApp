package com.example.myweatherbase.activities.model;

import java.io.Serializable;

public class City implements Serializable {
    public int id;
    public String name;
    public Coord coord;
    public String country;
    public int population;
    public int timezone;
    public int sunrise;
    public int sunset;

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public City(String name, Coord coord) {
        this.name = name;
        this.coord = coord;
    }
}
