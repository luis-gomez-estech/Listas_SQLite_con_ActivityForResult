package com.luisgomez.listas_con_sqlite;

public class Sitio { //Aqui le he borrado la palabra public

    //Creo la variable del atributo del sitio que guardamos
    private String sitio;

    //private int zip;
    //private int rooms;

    // Creamos constructor
    public Sitio(String sitio) {
        this.sitio = sitio;
    }

    // Ahora los getter and setter
    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    // El toString
    @Override
    public String toString() {
        return "Sitio{" +
                "sitio='" + sitio + '\'' +
                '}';
    }
}
