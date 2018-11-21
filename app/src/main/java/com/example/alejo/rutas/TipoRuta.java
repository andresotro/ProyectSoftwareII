package com.example.alejo.rutas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class TipoRuta extends AppCompatActivity {

    private String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ruta);

        final Button boton = findViewById(R.id.siguienteTR);
        final RadioGroup opciones = findViewById(R.id.tipoRutaRG);

        //Obtener arreglo de actividad anterior
        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(((RadioButton) findViewById(opciones.getCheckedRadioButtonId())) == null){
                    Toast.makeText(TipoRuta.this, "Debes seleccionar un tipo de ruta",
                            Toast.LENGTH_SHORT).show();
                }else {
                    //Obtener dato(s) seleccionado/ingresado(s)
                    String tipoRuta = ((RadioButton) findViewById(opciones.getCheckedRadioButtonId())).getText().toString();
                    datos[5] = tipoRuta;

                    //Cambiar de actividad y enviar arreglo
                    Intent intent = new Intent(TipoRuta.this,
                            Cupos.class);
                    intent.putExtra("datos", datos);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(TipoRuta.this)
                .setMessage("¿Deseas cancelar la creación de esta ruta?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(TipoRuta.this, MenuPrincipal.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("datos", datos);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
