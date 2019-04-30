package com.luisgomez.listas_con_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button guardar, mostrar, eliminar, btnEliminarItem, insertarSitio;
    ListView listaSitios;

    SitiosSQLiteHelper database;

    // Variable para el tamaño de los campos de la tabla de base de datos
    public int tamanoCamposBD = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // txtResultado = (TextView) findViewById(R.id.txtResultado);

       // resultado=nombreCiudad.toString();

       // txtResultado.setText(resultado);


        //guardar = (Button) findViewById(R.id.guardar);
        //mostrar = (Button) findViewById(R.id.mostrar);
        eliminar = (Button) findViewById(R.id.eliminar);

        listaSitios = (ListView) findViewById(R.id.lista_final);

        // Inicio la base de datos
        database = new SitiosSQLiteHelper(MainActivity.this);

        // Le digo a los botones que al hacer click van a hacer algo (esa funcion que van a hacer esta dentro del metodo onClick
        //guardar.setOnClickListener(this);
        //mostrar.setOnClickListener(this);
        eliminar.setOnClickListener(this);

        // Boton que aparece en cada item para poderlo eliminar por separado
        //btnEliminarItem.setOnClickListener(this);

        // Le decimos a que id se refiere el boton que se usa para ir al MAin2Activity

        insertarSitio = findViewById(R.id.boton_ir_añadir);

        //Ahora le decimos a este boton que vaya al Main2Activity, pero con startActivityForResult para que nos devuelva un resultado de ese Main2Activity
        insertarSitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, 3);
            }
        });

        //LLama al metodo lista para mostrar la lista actualizada al abrir la app y leyendo los datos de la base de datos SQLite
        Lista();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            /*
            case R.id.guardar:

                // Guardo el valor de los campos
                String nombreSitio = this.nombreSitio.getText().toString();
                String nombreCiudad = this.nombreCiudad.getText().toString();
                String nombrePais = this.nombrePais.getText().toString();

                // Validacion para comprobar que todos los campos estan rellenos (lo he metido como opcional)
                if (TextUtils.isEmpty(nombreSitio) || TextUtils.isEmpty(nombreCiudad)
                        || TextUtils.isEmpty(nombrePais)) {

                    // Muestra mensaje para que se rellenen todos los datos
                    Toast.makeText(MainActivity.this, "Rellene todos los campos.",
                            Toast.LENGTH_SHORT).show();
                } else {

                    // Insertamos datos añadidos a la base de datos
                    database.insertData(new Sitio(nombreSitio, nombreCiudad,
                            nombrePais));

                    // Muestro mensaje para verificar que se ha añadido el sitio correctamente
                    Toast.makeText(MainActivity.this, "Sitio guardado correctamente.",
                            Toast.LENGTH_SHORT).show();


                    Lista(); // llamamos al metodo Lista el cual va a mostrar la lista con los valores que resolvemos en ella
                    // Asi cuando pulsamos el boton Guardar, ademas de guardar los datos en la base de datos, nos va a mostrar la lista

                }

                break;

            case R.id.mostrar:

                // Para poner losdatos almacenados de la base de datos en la lista
                List<Sitio> list = database.getAllData();

                // Arraylist que va a guardar los sitios
                ArrayList<Sitio> SitiosArrayList = new ArrayList<Sitio>();

                // obteniendo el tamaño de la lista y lo guarda en tamanoCamposBD
                tamanoCamposBD = list.size();

                // Validacion para Comprobar si hay datos en la base de datos (Si pulsamos el boton Mostrar y no hay datos en la tabla, entonces
                // un Toasts nos va a alertar con el mensaje No hay datos en la tabla
                if (tamanoCamposBD == 0) {

                    // Si no hay datos , muestro mensaje
                    Toast.makeText(MainActivity.this, "No hay datos en la tabla.",
                            Toast.LENGTH_SHORT).show();


                } else {

                    // Bucle que recorre la base de datos
                    for (Sitio data : list) {

                        // El bucle va  a recoger los atributos de cada sitio (objeto) de la base de datos
                        String nombreGuardado = data.getNombreSitio();
                        String emailGuardado = data.getNombreCiudad();
                        String direccionGuardada = data.getNombrePais();

                        // Añado los datos al arraylist
                        SitiosArrayList.add(new Sitio(nombreGuardado, emailGuardado,
                                direccionGuardada));

                    }

                    // Adaptador actualiza el arraylist
                    SitiosAdapter adapter = new SitiosAdapter(MainActivity.this,
                            SitiosArrayList);

                    // Adaptador actualiza la lista
                    listaSitios.setAdapter(adapter);

                    // Notifying adapter
                    adapter.notifyDataSetChanged();

                    // por ultimo, mostramos la lista (listview)
                    listaSitios.setVisibility(View.VISIBLE);
                }

                break;

                */

            case R.id.eliminar:



                    // Pero si, si existen datos, entonces se borran los datos y se oculta la vista de lista
                    Toast.makeText(MainActivity.this, "Datos borrados correctamente.",
                            Toast.LENGTH_SHORT).show();
                    database.deleteTable();
                    listaSitios.setVisibility(View.GONE);

                break;


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {

            // recibimos los valores de texto de los EditText del Main2Activity
           final String nombreSitioFinal = data.getStringExtra("Tarea_nombreSitio");
           final String nombreCiudadFinal = data.getStringExtra("Tarea_nombreCiudad");
           final String nombrePaisFinal = data.getStringExtra("Tarea_nombrePais");

            // Guardo el valor de los campos
            String nombreSitio = nombreSitioFinal;
            String nombreCiudad = nombreCiudadFinal;
            String nombrePais = nombrePaisFinal;

            //Insertamos en la base de datos, los datos recibidos desde el MainActivity2
            database.insertData(new Sitio(nombreSitio, nombreCiudad, nombrePais));

            //LLama al metodo lista para mostrar la lista actualizada al abrir la app y leyendo los datos de la base de datos SQLite
            Lista();


        }

    }

    public void Lista() {

        // Lista que va a mostrar los datos almacenados en la base de datos
        List<Sitio> list = database.getAllData();

        // Arraylist que va a guardar los sitios
        ArrayList<Sitio> SitiosArrayList = new ArrayList<Sitio>();

        // obteniendo el tamaño de la lista y lo guarda en tamanoCamposBD
        tamanoCamposBD = list.size();

        // Bucle que recorre la base de datos. He puesto data2 pq ya tenia un data al principio del activityOfResult para recibir los datos del Main2Activity
        for (Sitio data2 : list) {

            // El bucle va  a recoger los atributos de cada sitio (objeto) de la base de datos
            String nombreSitioGuardado = data2.getNombreSitio();
            String nombreCiudadGuardada = data2.getNombreCiudad();
            String nombrePaisGuardado = data2.getNombrePais();

            // Añado los datos al arraylist
            SitiosArrayList.add(new Sitio(nombreSitioGuardado, nombreCiudadGuardada,
                    nombrePaisGuardado));

        }

        // Adaptador actualiza el arraylist
        SitiosAdapter adapter = new SitiosAdapter(MainActivity.this,
                SitiosArrayList);

        // Adaptador actualiza la lista
        listaSitios.setAdapter(adapter);

        // Notifying adapter
        adapter.notifyDataSetChanged();

        // por ultimo, mostramos la lista (listview)
        listaSitios.setVisibility(View.VISIBLE);
    }

}


