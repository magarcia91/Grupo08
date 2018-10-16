    package com.dievall.tarea_03_g08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

    public class ActivityB extends AppCompatActivity {
        Button clk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        clk = (Button) findViewById(R.id.buttonSalir);
    }

        public void regresar (View v) {

                Intent salir = null;
                salir = new Intent(ActivityB.this,ActivityA.class);
                startActivity(salir);
        }
}
