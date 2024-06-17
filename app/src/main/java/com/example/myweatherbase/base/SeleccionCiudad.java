package com.example.myweatherbase.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.MainActivity;
import com.example.myweatherbase.activities.model.City;
import com.example.myweatherbase.activities.model.CityRepository;
import com.example.myweatherbase.activities.model.DetailedActivity;

import java.util.ArrayList;

public class SeleccionCiudad extends AppCompatActivity {

    private CityRepository cr = new CityRepository();
    private Spinner spinner;
    private Button bselecciona;

    private City ciudadSel;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_ciudad);

         spinner = findViewById(R.id.spinnerCiudades);
         bselecciona = findViewById(R.id.b_selecciona);
         foto = findViewById(R.id.fotoCiudad);


        String[] auxString = cr.getAllNames();

        City[] aux = cr.getCiudades().toArray(new City[0]);




        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,auxString);
        spinner.setAdapter(adaptadorSpinner);


        bselecciona.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("ciudad", ciudadSel);
            view.getContext().startActivity(intent);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < cr.getCiudades().size(); i++) {
                    if (cr.getCiudades().get(i).getName() == spinner.getSelectedItem()){
                        ciudadSel = cr.getCiudades().get(i);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }


}
