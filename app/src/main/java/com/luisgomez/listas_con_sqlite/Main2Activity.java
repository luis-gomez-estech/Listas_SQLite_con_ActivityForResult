package com.luisgomez.listas_con_sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    EditText nombreSitio; // declaro EditText para poder escribir el sitio

    EditText nombreCiudad; // declaro EditText para poder escribir el sitio

    EditText nombrePais; // declaro EditText para poder escribir el pais

    Button boton_ver_sitios; // Declaro el boton para ir al MainActivity y ver sitios

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombreSitio = findViewById(R.id.nombreSitio);
        nombreCiudad = findViewById(R.id.nombreCiudad);
        nombrePais = findViewById(R.id.nombrePais);

        boton_ver_sitios = findViewById(R.id.boton_ver_sitios);    // Le decimos a que id se refiere el boton1, en este caso es el boton
        // con id boton_ir_añadir que se usa para ir al MAinActivity y ver la lista


        //Ahora le decimos a este boton_ver_sitios  con intent que vaya al MainActivity para ver la lista con los sitios,
        boton_ver_sitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String nombreSitioFinal = nombreSitio.getText().toString();
                String nombreCiudadFinal = nombreCiudad.getText().toString();
                String nombrePaisFinal = nombrePais.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("Tarea_nombreSitio",nombreSitioFinal);
                intent.putExtra("Tarea_nombreCiudad",nombreCiudadFinal);
                intent.putExtra("Tarea_nombrePais",nombrePaisFinal);
                setResult(3, intent);
                finish();
            }

        });

    }






}

