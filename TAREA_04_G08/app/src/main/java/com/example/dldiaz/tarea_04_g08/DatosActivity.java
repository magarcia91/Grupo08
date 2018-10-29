package com.example.dldiaz.tarea_04_g08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DatosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);

        SharedPreferences preferences =  getApplicationContext().getSharedPreferences("DATOS",MODE_PRIVATE);
        // String informacion = preferences.getString("informacion","");
/*
        String informacion = preferences.getString("informacion","");
        String[] itemsInformacion = informacion.split(",");
        List<String>  items = new ArrayList<String>();
        for (int i = 0; i< itemsInformacion.length; i++){
            items.add(itemsInformacion[i]);
        }
        for (int i=0; i<items.size(); i++){
            Log.d("listitem",items.get(i));
        }
        //   TextView info = (TextView) findViewById(R.id.infoDatos);
       // info.setText(informacion);
        ListView milista = (ListView) findViewById(R.id.lista);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemsInformacion);
        milista.setAdapter(adapter);

*/

        Map<String,?> allEntries = preferences.getAll();
        List<String>  itemsM = new ArrayList<String>();

        //for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
        //  Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        //}

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String clave = entry.getKey();
            String valor = entry.getValue().toString();
            if (clave.equals("clave")) {
                itemsM.add("");
            } else {
                itemsM.add(clave + "  ->  " + valor);
            }
        }
        ListView milistaM = (ListView) findViewById(R.id.lista);
        ArrayAdapter<String> adapterM = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemsM);
        milistaM.setAdapter(adapterM);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.itemSalir) {
            Intent registroPantalla = new Intent(DatosActivity.this,MainActivity.class);
            startActivity(registroPantalla);
            return true;
            // lo ideal aquí sería hacer un intent para abrir una nueva clase como lo siguiente
            //Log.i("ActionBar", "Settings!");
            //Intent about = new Intent(getApplicationContext(), MainActivity.class);
            // startActivity(about);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
