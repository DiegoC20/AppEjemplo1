package com.example.app3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadDetalle extends AppCompatActivity {
    TextView txtNombre, txtSexo;
    ImageView imgEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_detalle);

        txtNombre = findViewById(R.id.txtNombreDetalle);
        txtSexo = findViewById(R.id.txtSexoDetalle);
        imgEst = findViewById(R.id.imgEstDetalle);

        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString("nombre");
        String sexo = parametros.getString("sexo");
        byte[] imagen = parametros.getByteArray("imagen");

        txtNombre.setText("Nombre: "+nombre);
        txtSexo.setText("Sexo: "+sexo);
        imgEst.setImageBitmap(convertirBitmap(imagen));
    }

    private Bitmap convertirBitmap(byte[] imagen) {
        return BitmapFactory.decodeByteArray(imagen,0,imagen.length);
    }
}