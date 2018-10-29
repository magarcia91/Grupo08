package com.example.dldiaz.tarea_04_g08;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText usuario,clave;
    Button ingresar,registrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        usuario = (EditText) findViewById(R.id.usuarioIngreso);
        clave = (EditText) findViewById(R.id.claveIngreso);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        registrar = (Button) findViewById(R.id.btnRegistrar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioI = usuario.getText().toString();
                String claveI = clave.getText().toString();

                SharedPreferences preferences = getApplicationContext().getSharedPreferences("DATOS", MODE_PRIVATE);
                String usuarioM = preferences.getString(usuarioI,"No existe informacion del usuario ingresado");
                String claveM = preferences.getString("clave","No existe informacion del usuario ingresado");
                if(usuarioM.equals(usuarioI) && claveM.equals(claveI)){
                    Intent datosPantalla = new Intent(MainActivity.this, DatosActivity.class );
                    startActivity(datosPantalla);
                }else{
                    Toast.makeText(getApplicationContext(), "Credenciales no validas.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registroPantalla = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(registroPantalla);
            }
        });


    }
}
