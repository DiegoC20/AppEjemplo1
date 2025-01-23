package com.example.app3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

import Models.Estudiante;

public class ActividadAgregar extends AppCompatActivity {

    EditText txtNombre, txtEdad;
    RatingBar rbPunt;
    RadioGroup rgSexo;
    RadioButton masculino, femenino;
    ImageView imgSeleccionar;
    byte[] imgSeleccionada = null;
    private boolean esImagenDefecto = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_agregar);

        txtNombre = findViewById(R.id.txtNombreAgregar);
        txtEdad = findViewById(R.id.txtEdadAgregar);
        rgSexo = findViewById(R.id.rgSexo);
        masculino = findViewById(R.id.rbMasc);
        femenino = findViewById(R.id.rbfem);
        imgSeleccionar = findViewById(R.id.imgSeleccionar);

        Button btnAgregar = findViewById(R.id.btnRegistrar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarEstudiante();
            }
        });

        imgSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarImagen();
            }
        });
    }

    private void seleccionarImagen() {
        Intent oIntento = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        oIntento.setType("image/*");
        startActivityIfNeeded(Intent.createChooser(oIntento,"Seleccionar Imagen"),100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent oIntento) {
        super.onActivityResult(requestCode, resultCode, oIntento);
        if(requestCode==100){
            if(resultCode==RESULT_OK){
                Uri ruta = oIntento.getData();
                imgSeleccionar.setImageURI(ruta);
                imgSeleccionar.buildDrawingCache();
                Bitmap oBitmap = imgSeleccionar.getDrawingCache();
                ByteArrayOutputStream flujoSalida = new ByteArrayOutputStream();
                oBitmap.compress(Bitmap.CompressFormat.JPEG,0,flujoSalida);
                imgSeleccionada = flujoSalida.toByteArray();
                esImagenDefecto=false;
            }
        }
    }

    private void agregarEstudiante() {
        AlertDialog.Builder oDialogo = new AlertDialog.Builder(this);
        oDialogo.setTitle("Aviso");
        oDialogo.setMessage("¿Desea registrar plato?");
        oDialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActividadAgregar.this.finish();
            }
        });
        oDialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean registroExitoso = agregarObjeto();
                if (registroExitoso) {
                    Toast.makeText(ActividadAgregar.this, "Plato registrado con éxito", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(ActividadAgregar.this, "Fallo en el registro. Verifique los datos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        oDialogo.create();
        oDialogo.show();
    }

    private void limpiar() {
    }

    private boolean agregarObjeto() {
        String titulo = txtNombre.getText().toString();
        String edad = txtEdad.getText().toString();
        int preferencia = rbPunt.getProgress();

        if(titulo.isEmpty() || edad.isEmpty() || esImagenDefecto){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        int e = Integer.valueOf(txtEdad.getText().toString());

        String preparacion;
        if (rgSexo.getCheckedRadioButtonId() == R.id.rbMasc) {
            preparacion = "Masculino";
        } else {
            preparacion = "Femenino";
        }

        Estudiante oEstudiandte = new Estudiante(titulo,e,preparacion,preferencia,imgSeleccionada);
        MainActivity.listaEstudiantes.add(oEstudiandte);
        return true;
    }
}