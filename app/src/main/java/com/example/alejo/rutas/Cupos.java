package com.example.alejo.rutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Cupos extends AppCompatActivity {

    private String[] datos;
    private int cambiado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupos);

        final NumberPicker num = findViewById(R.id.numberPicker);
        final Button boton = findViewById(R.id.siguienteC);

        num.setMinValue(1);
        num.setMaxValue(10);

        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        num.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                cambiado = 1;
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(cambiado == 0){
                    Toast.makeText(Cupos.this, "Asegúrate de seleccionar la cantidad de cupos que ofrecerás en el servicio",
                            Toast.LENGTH_SHORT).show();
                }else {
                    //Obtener dato(s) seleccionado/ingresado(s)
                    String cupos = Integer.toString(num.getValue());
                    datos[1] = cupos;

                    //Cambiar de actividad y enviar ArrayList
                    Intent intent = new Intent(Cupos.this,
                            FechaHora.class);
                    intent.putExtra("datos", datos);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Cupos.this, TipoRuta.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("datos", datos);
        startActivity(intent);
    }
}
