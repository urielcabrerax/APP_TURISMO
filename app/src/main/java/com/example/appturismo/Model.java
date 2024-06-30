package com.example.appturismo;

import java.util.ArrayList;

public class Model {

    ArrayList<data> data;

    public ArrayList<Model.data> getData() {
        return data;
    }

    public class  data{
        String categoria;
        String nombre_lugar;
        String telefono;

        public String getCategoria() {
            return categoria;
        }

        public String getNombre_lugar() {
            return nombre_lugar;
        }

        public String getTelefono() {
            return telefono;
        }
    }

}
