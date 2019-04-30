package com.luisgomez.listas_con_sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SitiosSQLiteHelper extends SQLiteOpenHelper {

    // nombreSitio de la base de datos
    private static final String Database_Name = "ListasLQLite";

    // Version de la base de datos
    private static final int Database_Version = 1;

    // nombreSitio de la tabla
    private static final String Nombre_tabla = "User_Data";

    // Campos de la tabla
    private static final String nombreSitio = "Nombre";
    private static final String nombreCiudad = "Ciudad";
    private static final String nombrePais = "Pais";

    // Creamos la tabla y sus campos
    private static final String Create_Table = "Create table " + Nombre_tabla
            + " ( " + nombreSitio + " text not null, "
            + nombreCiudad + " text not null, "
            + nombrePais + " text not null );";

    // Eliminar o borrar la tabla
    // private static final String Drop_Table = "Drop table if exists " + Nombre_tabla;

    public SitiosSQLiteHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Consulta la tabla creada
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS User_Data");

        // Consulta si se ha cambiado la version de la base de datos
        onCreate(db);

    }

    // Metodo que es llamado por los botones Guardar y Mostrar para añadir los datos de los campos a la base de datos

    // este metodo lee los datos de la clase Sitio

    public void insertData(Sitio data) {

        // Accedemos a la bd para escribir los datos
        SQLiteDatabase db = this.getWritableDatabase();

        // Valores de contenido utilizados para editar / escribir datos en la base de datos
        ContentValues values = new ContentValues();

        // Añadimos los valores a los campos
        values.put(nombreSitio, data.getNombreSitio());
        values.put(nombreCiudad, data.getNombreCiudad());
        values.put(nombrePais, data.getNombrePais());

        // Ahora insertamos los datoss en la tabla
        db.insert(Nombre_tabla, null, values);

        // Cerramos la base de datos despues de usarla
        db.close();

    }

    // Metodo que es llamado por los botones Guardar y Mostrar para añadir los datos d elos campos a la base de datos
    public List<Sitio> getAllData() {

        // ArrayList que va a guardar cada sitio
        List<Sitio> data = new ArrayList<Sitio>();

        // Accedemos a la base de datos para leer los datos
        SQLiteDatabase db = this.getReadableDatabase();

        // Aqui le decimos que lea los datos de toda la tabla con el Select *from como siempre
        String select_query = "Select * from " + Nombre_tabla;

        // Cursor para leer datos completos en base de datos
        Cursor cursor = db.rawQuery(select_query, null);
        try {
            // Para movernos entre filas individuales
            if (cursor.moveToFirst()) {

                // Recorriendo todos los datos y agregandolos al Arraylist
                do {

                    // El 0,1,y son los lugares del array, al ser tres campos, nombre, ciudad y pais, van a ser 3 posiciones de array empezando desde el cero como siempre
                    Sitio data_model = new Sitio(cursor.getString(0),
                            cursor.getString(1), cursor.getString(2));
                    data.add(data_model);

                } while (cursor.moveToNext());

            }
        } finally {

            // Cerramos cursor despues de usarlo
            cursor.close();

        }

        // Cerramos base de datos
        db.close();

        // Devolvemos la lista
        return data;
    }

    // Metodo para borrar todos los datos de la base de datos
    public void deleteTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        // Para borrar la tabla
        db.delete(Nombre_tabla, null, null);

        // cerramos base de datos
        db.close();

    }


}
