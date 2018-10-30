package com.example.janusdeb.loginapplication;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
      //  lista=(ListView) findViewById(R.id.ListView);
        ListView lista = (ListView) findViewById(R.id.listView);
        ArrayList<Usuarios> arraydir = new ArrayList<Usuarios>();
        Usuarios usuarios = null;

        TextView datos = (TextView) findViewById(R.id.datostxt);
        try {
            FileInputStream fin= openFileInput("myFile.txt");

            InputStreamReader fileReader=new InputStreamReader(fin);
            char[] buffer=new char[9000];
            String s="";
            int charRead;

            while((charRead=fileReader.read(buffer))>0){
                s+= String.copyValueOf(buffer,0,charRead);
                //usuarios = new Usuarios(s);
                //arraydir.add(usuarios);
                //usuarios=null;
            }
            fileReader.close();
            datos.setText(s);

        } catch (FileNotFoundException e) {
            Toast.makeText(this,"No se pudo localizar el archivo",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this,"No se pudo leer el archivo",Toast.LENGTH_SHORT).show();
        }

        AdapterUsuarios adapter = new AdapterUsuarios(this, arraydir);
        lista.setAdapter(adapter);

    }

   /* public void CargaDatos(View view) throws IOException {

        List<String> listado= new ArrayList<String>();
        String linea;
        InputStream is= this.getResources().openRawResource(R.raw.data);
        BufferedReader reader= new BufferedReader(new InputStreamReader(is));
        if(is!=null){
            while((linea=reader.readLine())!=null){
             listado.add(linea.split(";")[0]);
            }
        }
        is.close();
        Toast.makeText(this,"Carga"+listado.size(),Toast.LENGTH_LONG).show();
        String datos[]=listado.toArray(new String[listado.size()]);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
    }*/

    /*public String LeerDats() throws IOException {
        String ret = "";

        try {
            InputStream inputStream = openFileInput(FILENAME);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }

        return ret;
    }*/
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.salir:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}