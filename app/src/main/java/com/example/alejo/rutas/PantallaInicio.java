package com.example.alejo.rutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaInicio extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        boton = findViewById(R.id.comenzar);

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /*
                 * Intent es el objeto que permite navegar entre actividades
                 */
                Intent intent = new Intent(PantallaInicio.this,
                        TipoRuta.class);
                startActivity(intent);
                /*
                 * startActivity da comienzo a la actividad especificada en el segundo parametro
                 * de la constructora del Intent
                 */

            }
        });
    }
}
