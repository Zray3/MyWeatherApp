package com.example.myweatherbase.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.City;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements CallInterface {

    private TextView ciudad;
    private RecyclerView recycler;
    AdaptadorRecycleView adaptador;
    private Root root;

    private City ciudadsel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ciudad = findViewById(R.id.tvNombreCiudad);
        Bundle extras = getIntent().getExtras();
        ciudadsel = (City) extras.get("ciudad");


        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {

        ciudad.setText(ciudadsel.getName());
        root = Connector.getConector().get(Root.class,ciudadsel.getCoord().getCoord().toString());
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();
        recycler=findViewById(R.id.rcTiempo);
        adaptador = new AdaptadorRecycleView(this, root);
        recycler.setAdapter(adaptador);
        adaptador.setLayout_displayed(R.layout.simple_element);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}