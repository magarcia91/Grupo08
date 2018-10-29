package com.example.dldiaz.tarea_04_g08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {
    EditText usuarioR,claveR,nombreR,apellidoR,emailR,celularR;
    Button guardarB;
    RadioButton mujerR,hombreR;
    String materia;
    Boolean aux,b;
    Spinner diaR,mesR,anioR;
    CheckBox materia1R,materia2R,materia3R,materia4R,materia5R;
    Switch becadoR;
   static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        usuarioR = (EditText) findViewById(R.id.usuario);
        claveR = (EditText) findViewById(R.id.clave);
        nombreR = (EditText) findViewById(R.id.nombre);
        apellidoR = (EditText) findViewById(R.id.apellido);
        emailR = (EditText) findViewById(R.id.email);
        celularR = (EditText) findViewById(R.id.celular);
        mujerR = (RadioButton) findViewById(R.id.mujer);
        hombreR = (RadioButton) findViewById(R.id.hombre);
        diaR = (Spinner) findViewById(R.id.dia);
        mesR = (Spinner) findViewById(R.id.mes);
        anioR = (Spinner) findViewById(R.id.anio);
        materia1R = (CheckBox) findViewById(R.id.materia);
        materia2R = (CheckBox) findViewById(R.id.materia2);
        materia3R = (CheckBox) findViewById(R.id.materia3);
        materia4R = (CheckBox) findViewById(R.id.materia4);
        materia5R = (CheckBox) findViewById(R.id.materia5);
        becadoR = (Switch) findViewById(R.id.becado);
        guardarB = (Button) findViewById(R.id.btnGuardar);
        //Registro Fecha de Nacimiento
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dia_array,android.R.layout.simple_spinner_item);
        diaR.setAdapter(adapter);
        final ArrayAdapter<CharSequence> adapterM = ArrayAdapter.createFromResource(this, R.array.mes_array,android.R.layout.simple_spinner_item);
        mesR.setAdapter(adapterM);
        final ArrayAdapter<CharSequence> adapterA = ArrayAdapter.createFromResource(this, R.array.anio_array,android.R.layout.simple_spinner_item);
        anioR.setAdapter(adapterA);


        guardarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("DATOS",MODE_PRIVATE);
                String newUsuario = usuarioR.getText().toString();
                String newClave = claveR.getText().toString();
                String newNombre = nombreR.getText().toString();
                String newApellido = apellidoR.getText().toString();
                String newEmail = emailR.getText().toString();
                String newCelular = celularR.getText().toString();
                String newDia = diaR.getSelectedItem().toString();
                String newMes = mesR.getSelectedItem().toString();
                String newAnio = anioR.getSelectedItem().toString();


                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(newUsuario + newClave ,
                        "Usuario:"  + "\t" +newUsuario + "\n"+
                                "Nombre:" + "\t" +newNombre + "\n"+
                                "Apellido:"+ "\t" +newApellido + "\n"+
                                "Email:"+"\t" + newEmail + "\n"+
                                "Telefono:"+"\t" +newCelular + "\n"+
                                "Genero:"+"\t" +registroGenero() + "\n"+
                                "Fecha de nacimiento" + "\t"+ newDia + "\t"+ newMes + "\t"+ newAnio+ "\n"+
                                "Materias a cursar: "+ "\t"+ registroMaterias() + "\n"+
                                "Becado:" + "\t" + registroBeca()
                );

                if(isEmailValid(newEmail)){
                    if (isPhoneValid(newCelular)){
                        editor.commit();
                        editor.putString(newUsuario,newUsuario);
                        editor.commit();
                        editor.putString("clave",newClave);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Registrado Correctamente",
                                Toast.LENGTH_SHORT).show();
                        Intent regresoPantalla = new Intent(RegistroActivity.this,MainActivity.class);
                        startActivity(regresoPantalla);
                    }else{
                        celularR.setError("Celular Incorrecto");
                        celularR.requestFocus();
                    }
                }else{
                    emailR.setError("Correo Incorrecto");
                    emailR.requestFocus();
                }
            }
            //Registro Genero
            public Boolean registroGenero(){
                if(mujerR.isChecked()){
                    aux = Boolean.TRUE;
                }else{
                    aux = Boolean.FALSE;
                }
                return aux;
            }
            //Registro Materias
            public String registroMaterias(){
                if(materia1R.isChecked()){
                    materia += materia1R.getText().toString();
                }
                if(materia2R.isChecked()){
                    materia += materia2R.getText().toString();
                }
                if(materia3R.isChecked()){
                    materia += materia3R.getText().toString();
                }
                if(materia4R.isChecked()){
                    materia += materia4R.getText().toString();
                }
                if(materia5R.isChecked()){
                    materia += materia5R.getText().toString();
                }
                return materia;
            }
            //Registro beca
            public boolean registroBeca(){
                if (becadoR.isChecked()){
                    b = Boolean.TRUE;
                }else{
                    b= Boolean.FALSE;
                }
                return b;
            }
            public  boolean isEmailValid(String newEmail) {
                String expression = "^[a-zA-Z0-9\\._-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,4}$" ;
                /*
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
                 */
                Pattern pattern = Pattern.compile(expression);
                Matcher matcher = pattern.matcher(newEmail);
                return matcher.matches();
            }
            public  boolean isPhoneValid(String newCelular) {
                Pattern pattern = Pattern.compile("^[0][9][0-9]{8}$");
                return pattern.matcher(newCelular).matches();
            }
        });

    }

}
