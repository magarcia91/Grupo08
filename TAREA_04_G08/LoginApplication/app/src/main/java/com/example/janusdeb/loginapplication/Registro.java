package com.example.janusdeb.loginapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Registro extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    private static String APP_DIRECTORY="MyPictureApp/";
    private static String MEDIA_DIRECTORY=APP_DIRECTORY + "PictureApp";

    private static final String TAG = Activity_2.class.getName();
    private static final String FILENAME = "myFile.txt";

    private final int MY_PERMISSIONS=100;
    private final int PHOTO_CODE=200;
    private final int SELECT_PICTURE=300;

    private ListView lista;
    private AdapterUsuarios adaptador;
    ArrayList<Usuarios> listItems= new ArrayList<>();

    private EditText inputUser;
    private EditText inputPass;
    private EditText inputNombre;
    private EditText inputApellido;
    private EditText inputEmail;
    private EditText inputCelular;
    private RadioButton inputGeneroM;
    private RadioButton inputGeneroF;
    private CheckBox m1;
    private CheckBox m2;
    private CheckBox m3;
    private CheckBox m4;
    private CheckBox m5;
    private Spinner inputDia;
    private Spinner inputMes;
    private Spinner inputAmo;
    private Switch inputBeca;
    private Button BRegistrado;

    private ImageView mSetImage;
    private Button mOptionButton;
    private LinearLayout mRlView;

    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inputUser = (EditText) findViewById(R.id.txt_user);
        inputPass = (EditText) findViewById(R.id.txt_pass);
        inputNombre = (EditText) findViewById(R.id.txt_nombre);
        inputApellido = (EditText) findViewById(R.id.txt_apellido);
        inputEmail = (EditText) findViewById(R.id.txt_email);
        inputCelular = (EditText) findViewById(R.id.txt_cel);
        inputGeneroM = (RadioButton) findViewById(R.id.radio_masculino);
        inputGeneroF = (RadioButton) findViewById(R.id.radio_femenino);
        m1= (CheckBox) findViewById(R.id.check_m1);
        m2= (CheckBox) findViewById(R.id.checkm2);
        m3= (CheckBox) findViewById(R.id.checkm3);
        m4= (CheckBox) findViewById(R.id.checkm4);
        m5= (CheckBox) findViewById(R.id.checkm5);
        inputDia = (Spinner) findViewById(R.id.SpinnDia);
        inputMes =(Spinner) findViewById(R.id.SpinnMes);
        inputAmo =(Spinner) findViewById(R.id.SpinnAmo);
        inputBeca =(Switch) findViewById(R.id.SwBeca);
        BRegistrado=(Button) findViewById(R.id.btn_Registrado);

        mSetImage = (ImageView) findViewById(R.id.imgPhoto);
        mOptionButton = (Button) findViewById(R.id.btnPhoto);
        mRlView = (LinearLayout) findViewById(R.id.lbl_foto);

        if(mayRequestStoragePermission())
            mOptionButton.setEnabled(true);
        else
            mOptionButton.setEnabled(false);


        mOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
    }

    private boolean mayRequestStoragePermission() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(mRlView, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            }).show();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }

        return false;
    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }else if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";

            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath = savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });


                    Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                    mSetImage.setImageBitmap(bitmap);
                    break;
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    mSetImage.setImageURI(path);
                    break;

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Registro.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                mOptionButton.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.show();
    }

    public void Guardar(View v){

        String str="";
        String user = inputUser.getText().toString();
        String password = inputPass.getText().toString();
        String nombre = inputNombre.getText().toString();
        String apellido= inputApellido.getText().toString();
        String celular=inputCelular.getText().toString();
        String email = inputEmail.getText().toString();
        String genero ="";
        String Sm1="";
        String Sm2="";
        String Sm3="";
        String Sm4="";
        String dia=inputDia.getSelectedItem().toString();
        String mes=inputMes.getSelectedItem().toString();
        String amo=inputAmo.getSelectedItem().toString();
        String fecha=dia+"/"+mes+"/"+amo;
        String becado="";
        String imagen =mSetImage.toString();

        if(!inputGeneroM.isSelected()){
            genero=inputGeneroM.getText().toString();
        }else{
            genero=inputGeneroF.getText().toString();
        }
        if (m1.isChecked()) {
            Sm1 = m1.getText().toString();
        }
        if (m2.isChecked()) {
            Sm2 = m2.getText().toString();
        }
        if (m3.isChecked()) {
            Sm3 = m3.getText().toString();
        }
        if (m4.isChecked()) {
            Sm4 = m4.getText().toString();
        }
        if(!inputBeca.isChecked()){
            becado="Si";
        }else{
            becado="No";
        }

        str="Foto: "+imagen+" Usuario: "+user+" Nombre: "+nombre+" Apellido: "+apellido+" Email: "+email+" Celular: "+celular+" Genero: "+genero+" Materias: "+Sm1+" "+Sm2+" "+Sm3+" "+Sm4
                +" Fecha de Nacimiento: "+dia+"/"+mes+"/"+amo+" Becado: "+becado+" \n";
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_APPEND));
            outputStreamWriter.write(str);
            Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(TAG, "Escritura de archivo fallo: " + e.toString());
        }

        }


}


