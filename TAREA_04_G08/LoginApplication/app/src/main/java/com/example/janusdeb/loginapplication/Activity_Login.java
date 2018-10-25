package com.example.janusdeb.loginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Login extends AppCompatActivity {
EditText unombre,upass;
    Button clk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unombre=(EditText) findViewById(R.id.user);
        upass=(EditText) findViewById(R.id.pass);
        clk =(Button) findViewById(R.id.btn_login);

    }
    public void avanza(View v){
        String strname=unombre.getText().toString();
        String strpass=upass.getText().toString();

        if(strname.equals("Jimmy") && strpass.equals("Roman") ||
                (strname.equals("Miguel") && strpass.equals("Garcia"))||
                (strname.equals("Dayana") && strpass.equals("Diaz"))||
                (strname.equals("Diego") && strpass.equals("Vallejo")))
        {
            Intent in=new Intent(Activity_Login.this,Activity_2.class);
            startActivity(in);
        }
        else if((strname.equals("") || strpass.equals("")))
        {
            Toast.makeText(getBaseContext(),"Ingrese usuario y contraseña",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getBaseContext(),"Usuario y contraseña incorrecto..",Toast.LENGTH_SHORT).show();
        }
    }
    public void registro(View v){
        Intent in1=new Intent(Activity_Login.this,Registro.class);
        startActivity(in1);
    }
}



