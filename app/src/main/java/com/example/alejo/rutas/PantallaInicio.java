package com.example.alejo.rutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PantallaInicio extends AppCompatActivity {

    private Button boton;
    private String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        boton = findViewById(R.id.comenzar);

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Obtener ArrayList y asignarlo
                Intent antes = getIntent();
                datos = antes.getStringArrayExtra("datos");
                //Cambiar de actividad y enviar arreglo
                Intent intent = new Intent(PantallaInicio.this,
                        TipoRuta.class);
                intent.putExtra("datos", datos);
                startActivity(intent);

            }
        });
    }
}
