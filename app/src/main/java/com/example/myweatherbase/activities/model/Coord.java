package com.example.myweatherbase.activities.model;

import java.io.Serializable;

public class Coord implements Serializable {
    public double lat;
    public double lon;

    public Coord(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getCoord() {
        return "&lat="+lat+"&lon="+lon;
    }
}
