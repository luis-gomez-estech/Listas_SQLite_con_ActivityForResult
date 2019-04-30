package com.luisgomez.listas_con_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SitiosAdapter extends ArrayAdapter<Sitio> {

    ArrayList<Sitio> arrayList;
    LayoutInflater inflater;
    ViewHolder holder = null;


    public SitiosAdapter(Context context, ArrayList<Sitio> arrayList) {
        super(context, 0);
        this.arrayList = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Sitio getItem(int pos) {
        return arrayList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup root) {

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_sitio, root,
                    false);
            holder = new ViewHolder();

            holder.textnombreSitio = view.findViewById(R.id.nombreSitio);
            holder.textnombreCiudad = view.findViewById(R.id.nombreCiudad);
            holder.textnombrePais = view.findViewById(R.id.nombrePais);

            view.setTag(holder);

            //Button btnDelete = view.findViewById(R.id.btnEliminarItem);
/*
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SitiosSQLiteHelper db = new SitiosSQLiteHelper(v.getContext());

                    remove(getItem(pos));
                    notifyDataSetChanged();

                }
            });
*/

        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Guardo los campos recibiendolos del arraylist
        String nombreSitio = arrayList.get(pos).getNombreSitio();
        String nombreCiudad = arrayList.get(pos).getNombreCiudad();
        String nombrePais = arrayList.get(pos).getNombrePais();

        // Los datos que se muestran en la lista a traves del ViewHolder
        holder.textnombreSitio.setText("Sitio : " + nombreSitio);

        holder.textnombreCiudad.setText("Ciudad : " + nombreCiudad);

        holder.textnombrePais.setText("Pais : " + nombrePais);

        return view;
    }

    public class ViewHolder {

        TextView textnombreSitio;
        TextView textnombreCiudad;
        TextView textnombrePais;

    }




}
