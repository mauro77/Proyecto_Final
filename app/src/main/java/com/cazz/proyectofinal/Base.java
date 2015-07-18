package com.cazz.proyectofinal;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static com.cazz.proyectofinal.R.layout.*;
import static com.cazz.proyectofinal.R.layout.base;

/*R
 * Created by Home on 01/06/2015.
 */
public class Base extends ActionBarActivity implements View.OnClickListener {
    static private DataBaseManager Manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(base);

        Manager = new DataBaseManager(this);
        lista = (ListView) findViewById(android.R.id.list);
        int opcion;

        Manager.eliminar("Ruta 302-Circular Sur Izquierda");
        Manager.eliminar("Ruta 303-Circular Sur Derecha");
        Manager.eliminar("Ruta 300-Circular Coonatra Derecha");
        Manager.eliminar("Ruta 301-Circular Coonatra Izquierda");
        Manager.eliminar("Ruta 308-Ruta de la Salud Derecha");
        Manager.eliminar("Ruta 308-Ruta de la Salud Izquierda");
        Manager.eliminar("Ruta 304-Comercial Hotelera Derecha");
        Manager.eliminar("Ruta 305-Comercial Hotelera Izquierda");

        String[] from = new String[]{Manager.CN_NAME,Manager.CN_URL};
        int[] to = new int[]{R.id.texto1};

        Bundle extras = getIntent().getExtras();
        opcion=extras.getInt("TEXT");

        switch (opcion) {
            case 1:
                Toast.makeText(getApplicationContext(), "Zona suroccidental",
                        Toast.LENGTH_LONG).show();
                Manager.insertar("Ruta 302-Circular Sur Izquierda", "https://www.google.com/maps/d/u/0/viewer?mid=zkqaR0eIsgBA.k1xRl-1iGFZI&hl=en_US");
                Manager.insertar("Ruta 303-Circular Sur Derecha", "https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kTKcYlxL4ELY");
                cursor = Manager.cargarCursorRutas();
                adapter = new SimpleCursorAdapter(this,R.layout.list_item,cursor,from,to,0);
                lista.setAdapter(adapter);
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "Zona suroriental",
                        Toast.LENGTH_LONG).show();
                Manager.insertar("Ruta 300-Circular Coonatra Derecha","https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kOEr-fWgDFps");
                Manager.insertar("Ruta 301-Circular Coonatra Izquierda", "https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kzQ7PNo1pUQ4");
                cursor = Manager.cargarCursorRutas();
                adapter = new SimpleCursorAdapter(this,R.layout.list_item,cursor,from,to,0);
                lista.setAdapter(adapter);
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "Zona noroccidental",
                        Toast.LENGTH_LONG).show();
                Manager.insertar("Ruta 308-Ruta de la Salud Derecha", "https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kTALSim0DXUw");
                Manager.insertar("Ruta 308-Ruta de la Salud Izquierda", "https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kDxwda1-MUrs");
                cursor = Manager.cargarCursorRutas();
                adapter = new SimpleCursorAdapter(this,R.layout.list_item,cursor,from,to,0);
                lista.setAdapter(adapter);
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "Zona nororiental",
                        Toast.LENGTH_LONG).show();
                Manager.insertar("Ruta 304-Comercial Hotelera Derecha", "https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kAgfINk6WiwU");
                Manager.insertar("Ruta 305-Comercial Hotelera Izquierda", "https://www.google.com/maps/d/u/0/edit?mid=zoibO83HAGaM.kspLGBX4Y7ko");
                cursor = Manager.cargarCursorRutas();
                adapter = new SimpleCursorAdapter(this,R.layout.list_item,cursor,from,to,0);
                lista.setAdapter(adapter);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Opcion equivocada",
                        Toast.LENGTH_LONG).show();
                break;

        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String url = cursor.getString(cursor.getColumnIndex(Manager.CN_URL)).toString();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });


    }


    public static DataBaseManager getManager() {
        return Manager;
    }

    @Override
    public void onClick(View v) {

    }


}

