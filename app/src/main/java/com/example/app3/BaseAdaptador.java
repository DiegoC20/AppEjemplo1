package com.example.app3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import Models.Estudiante;

public class BaseAdaptador extends BaseAdapter {
    Context contexto;
    private List<Estudiante> datos;
    private static LayoutInflater inflater = null;

    public BaseAdaptador(Context contexto, List<Estudiante> datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = inflater.inflate(R.layout.item_estudiante,null);
        TextView lbTitulo = vista.findViewById(R.id.txtNombreItem);
        TextView lbSexo = vista.findViewById(R.id.txtSexoItem);
        TextView lbEdad = vista.findViewById(R.id.txtEdadItem);
        RatingBar rbPuntuacion = vista.findViewById(R.id.rbPuntuacionItem);
        ImageView imagenE = vista.findViewById(R.id.imgEstudianteItem);

        lbTitulo.setText(datos.get(i).getNombre());
        lbSexo.setText(datos.get(i).getSexo());
        lbEdad.setText(datos.get(i).getEdad()+" años.");
        rbPuntuacion.setRating(datos.get(i).getPuntuacion());
        imagenE.setImageBitmap(convertirBitmap(datos.get(i).getFoto()));
        imagenE.setTag(i);
        return vista;
    }

    private Bitmap convertirBitmap(byte[] foto) {
        return BitmapFactory.decodeByteArray(foto,0,foto.length);
    }
}
