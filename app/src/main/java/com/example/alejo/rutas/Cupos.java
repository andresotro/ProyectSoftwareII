package com.example.alejo.rutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class Cupos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupos);

        Button boton = findViewById(R.id.siguienteC);

        NumberPicker num = findViewById(R.id.numberPicker);
        num.setMinValue(1);
        num.setMaxValue(10);

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /*
                 * Intent es el objeto que permite navegar entre actividades
                 */
                Intent intent = new Intent(Cupos.this,
                        FechaHora.class);
                startActivity(intent);
                /*
                 * startActivity da comienzo a la actividad especificada en el segundo parametro
                 * de la constructora del Intent
                 */

            }
        });



    }
}
