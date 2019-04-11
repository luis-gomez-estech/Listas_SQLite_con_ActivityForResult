package com.luisgomez.listas_con_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;




import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Una vez creadas las clases Sitio y Sitios replicando el ejmplo Gson

    // Por un lado, en este proyecto teniamos creado aqui en el MainActivity un arrayList
    // llamado sitios, pero como ahora he creado la class llamada sitios, pues para no confindirla,
    // el array se va a llamar sitiosArray

    //Ahora hay que hacer lo siguiente, ahora al crear esa clase llamada Sitios, ya no tenemos que crear aqui
    // el arrayList llamado sitiosArray, por tanto desactivamos private ArrayList<String> sitiosArray;
    // y creamos Sitios sitiosArray; como hemos hecho en el ejmlo Gson que tiene puesto Houses housesList;
    // donde housesList es el arrayList de ese proyecto


    // Codigo añadido para Gson y Preferencias

    Sitios sitiosArray; // Declaro el arrayLst que va a contener los sitios
    String json; //Creamos String para guardar el json de la class Sitios
    Button toJsonBtn; // Boton para guardar en el json

    // private ArrayList<String> sitiosArray; // Ahora lo desactivamos, ya noe s necesario

    private ListView milista; // declaro la lista

    ArrayAdapter<Sitio> adaptador1; //  //Declaro el adaptador necesario cuando tenemos listas para actualizarla y
    // sustituimos lo que hay entre corchetes, por la class Sitio que guarda cada sitio añadido

    Button boton; // Declaramos el boton, para ir al Main2Activity



    Button btnInsertar;
    Button btnConsultar;
    Button btnActualizar;
    Button btnEliminar;

    private TextView txtResultado;

    private SQLiteDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Creo el array referido a la class Sitios

        sitiosArray= new Sitios();

        txtResultado = (TextView)findViewById(R.id.txtResultado);


        // SQLite

        btnInsertar = findViewById(R.id.insertar);
        btnConsultar = (Button)findViewById(R.id.consultar);
       // btnEliminar = (Button)findViewById(R.id.eliminar);
       // btnActualizar = (Button)findViewById(R.id.actualizar);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        db = usdbh.getWritableDatabase();


        // Acaba SQLite



        // A la lista declarada le digo su id
        milista= findViewById(R.id.lista_final);

        // Ahora al adaptador le decimos que obtenga los valores del arrayList que esta en la class Sitios

        adaptador1 = new SitiosAdaptador(this,sitiosArray.getSitiosArray());
        //adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sitios);

        // y actualizamos la lista mediante los valores del adaptador

        milista.setAdapter(adaptador1);


        // Boton guardar en el Json
        toJsonBtn = findViewById(R.id.toJSON);

        // Al pulsar boton toJson, se crea el json con los resultados del arrayList y ademas va a guardar el valor en preferencias

        toJsonBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String innerJson = sitiosArray.toJSON(); // String que recoge el arraylist para convertirlo a json
                Log.i("gsonExample", innerJson); // Con esto creamos el json usando el String innerJson que acabamos de crear

            }
        });

        // Boton para insertar en la base de datos

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String sitioSQ = sitiosArray.toString();
                adaptador1.notifyDataSetChanged();
                //String nom = txtNombre.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES ('" + cod + "','" + nom + "') ";
                //db.execSQL(sql);

                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("codigo", sitioSQ);
                //nuevoRegistro.put("nombre", nom);
                db.insert("Usuarios", null, nuevoRegistro);
            }
        });

        /*
        // Boton para actualizar la base de datos

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String sitioSQ = sitiosArray.toString();
               // String nom = txtNombre.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "UPDATE Usuarios SET nombre='" + nom + "' WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método update()
                ContentValues valores = new ContentValues();
               // valores.put("nombre", nom);
                db.update("Usuarios", valores, "codigo=" + sitioSQ, null);
            }
        });

*/
/*
        // Boton para eliminar el ultimo dato
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String sitioSQ = sitiosArray.toString();

                //Alternativa 1: método sqlExec()
                //String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método delete()
                db.delete("Usuarios", "codigo=" + sitioSQ, null);
            }
        });

*/

        // Boton para consultar la base de datos

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Alternativa 1: método rawQuery()
                Cursor c = db.rawQuery("SELECT codigo, nombre FROM Usuarios", null);



                //Alternativa 2: método delete()
                //String[] campos = new String[] {"codigo", "nombre"};
                //Cursor c = db.query("Usuarios", campos, null, null, null, null, null);

                //Recorremos los resultados para mostrarlos en pantalla
                txtResultado.setText("");

                if (c.moveToFirst()) {

                    //Recorremos el cursor hasta que no haya más registros
                    do {

                        String sitioSQ = c.getString(0);
                        //String nom = c.getString(1);

                        txtResultado.append(" " + sitioSQ + " - "  + "\n");

                    } while(c.moveToNext());
                }





            }
        });






        // Le decimos a que id se refiere el boton que se usa para ir al MAin2Activity

        boton = findViewById(R.id.boton_ir_añadir);

        //Ahora le decimos a este boton que vaya al Main2Activity, pero con startActivityForResult para que nos devuelva un resultado de ese Main2Activity
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent, 2);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2) {
            String message = data.getStringExtra("MESSAGE"); // recibimos el valor del EditText del Main2Activity

            // Ahora aqui teniamos, sitiosArray.add(message); , osea, para añadir al arrayList el sitio introducido en el Main2Activity
            //por tanto ahora lo vamos añadir a la class Sitio, que es la que guarda cada objeto y sus atributos,
            // para pasarlo despues a la class Sitios, donde guardamos el json de objetos, es asi???

            //sitiosArray.add(message); // Esto ya no nos ghace falta, ahora tenemos que añadir string recibido a la clase Sitio y se hace de esta forma,
            // muy similar pero diferente a la anterior (san google !!!)

            sitiosArray.addSitio(new Sitio(message));


            adaptador1.notifyDataSetChanged(); // Actualizamos la lista

        }

    }






}


