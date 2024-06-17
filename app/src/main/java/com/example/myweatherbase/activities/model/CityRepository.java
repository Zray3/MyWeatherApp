package com.example.myweatherbase.activities.model;

import java.util.ArrayList;

public class CityRepository {
    ArrayList<City> ciudades;

    public CityRepository() {
        ciudades = new ArrayList<>();
        ciudades.add(new City("Manises", new Coord(-0.5411163,39.5862518)));
        ciudades.add(new City("Londres", new Coord(-0.4312316,51.5281798)));
        ciudades.add(new City("Alcalá del Jucar",  new Coord(-1.4305688,39.1924273)));
        ciudades.add(new City("El Puig", new Coord(-0.3072054,39.590303)));
    }

    public String[] getAllNames(){
        String[] aux = new String[ciudades.size()];
        for (int i = 0; i < ciudades.size(); i++) {
            aux[i]=ciudades.get(i).getName();
        }
        return  aux;
    }

    public ArrayList<City> getCiudades() {
        return ciudades;
    }
}



