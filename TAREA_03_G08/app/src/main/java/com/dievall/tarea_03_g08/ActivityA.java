package com.dievall.tarea_03_g08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityA extends AppCompatActivity {
EditText usuario_nombre, usuario_clave;
Button clk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        usuario_nombre = (EditText) findViewById(R.id.usuario);
        usuario_clave = (EditText) findViewById(R.id.clave);
        clk = (Button) findViewById(R.id.buttonIngresar);

    }

    public void acceder (View v) {
        String stusuario = usuario_nombre.getText().toString();
        String stclave = usuario_clave.getText().toString();

        if (
                (stusuario.equals("Diego") && stclave.equals("Vallejo")) ||
                        (stusuario.equals("Dayana") && stclave.equals("Diaz")) ||
                        (stusuario.equals("Jimmy") && stclave.equals("Roman")) ||
                        (stusuario.equals("Miguel") && stclave.equals("Garcia"))){
            Intent ingresar = null;
            ingresar = new Intent(ActivityA.this,ActivityB.class);
            startActivity(ingresar);

        }
        else if (stusuario.equals("") || stclave.equals("")){
                Toast.makeText(getBaseContext(),"Ingrese usuario y clave",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(),"Datos de usuario y clave incorrectos",Toast.LENGTH_SHORT).show();
        }
    }

}
