package com.example.app3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import Models.Estudiante;

public class MainActivity extends AppCompatActivity {

    ListView lvListaEstudiantes;
    public static List<Estudiante> listaEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEstudiantes = new ArrayList<>();

        Toolbar tb = findViewById(R.id.tbMain);
        setSupportActionBar(tb);
        lvListaEstudiantes = findViewById(R.id.lvListaEstudiantes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent oIntento;
        if(item.getItemId()==R.id.itemAgregar){
            oIntento = new Intent(this,ActividadAgregar.class);
            startActivity(oIntento);
        }
        if(item.getItemId()==R.id.itemSalir){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        lvListaEstudiantes.setAdapter(new BaseAdaptador(this,listaEstudiantes));
        lvListaEstudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent oIntento = new Intent(parent.getContext(),ActividadDetalle.class);
                oIntento.putExtra("sexo",listaEstudiantes.get(i).getSexo());
                oIntento.putExtra("nombre",listaEstudiantes.get(i).getNombre());
                oIntento.putExtra("foto",listaEstudiantes.get(i).getFoto());
                startActivity(oIntento);
            }
        });
        super.onResume();
    }
}