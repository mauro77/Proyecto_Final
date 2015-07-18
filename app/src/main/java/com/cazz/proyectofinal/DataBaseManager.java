package com.cazz.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alejandro on 24/05/2015.
 */
public class DataBaseManager {
    public static final String TABLE_NAME = "rutas";
    public static final String CN_ID = "_id";  // Nombre columna
    public static final String CN_NAME = "nombre";
    public static final String CN_URL = "url"
    ;
    // create table contactos(
    //                          _id integer primary key autoincrement,
    //                          nombre text not null,
    //                          telefono text);
    public static final String CREATE_TABLE = "create table "+ TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_NAME + " TEXT NOT NULL, "
            + CN_URL + " TEXT NOT NULL "
            + ")";

    DBhelper helper;
    SQLiteDatabase db;
    public DataBaseManager(Context context) {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues (String Nombre, String Url){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,Nombre);
        valores.put(CN_URL,Url);
        return valores;
    }

    public Cursor cargarCursorRutas(){
        String [] columnas = new String[]{CN_ID,CN_NAME,CN_URL};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public void insertar(String nombre, String url){
        db.insert(TABLE_NAME, null, generarContentValues(nombre, url));
    }

    public void eliminar(String nombre){

        db.delete(TABLE_NAME,CN_NAME+"=?", new String[] {nombre});
    }



}
