package com.luisgomez.listas_con_sqlite;

public class Sitio {

        String nombreSitio, nombreCiudad, nombrePais;

        // Constructor
        public Sitio(String nombre, String ciudad, String pais) {
            this.nombreSitio = nombre;
            this.nombreCiudad = ciudad;
            this.nombrePais = pais;
        }

        // obtengo los Getter de los campos
        public String getNombreSitio() {
            return nombreSitio;
        }

        public String getNombreCiudad() {
            return nombreCiudad;
        }

        public String getNombrePais() {
            return nombrePais;
        }

    }

