package com.example.alejo.rutas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class FechaHora extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_hora);

        final EditText fecha = findViewById(R.id.fecha);
        final EditText hora = findViewById(R.id.hora);
        Button boton = findViewById(R.id.siguienteF);

        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar fechaActual = Calendar.getInstance();
                int mYear = fechaActual.get(Calendar.YEAR);
                int mMes = fechaActual.get(Calendar.MONTH);
                int mDia = fechaActual.get(Calendar.DAY_OF_MONTH);

                //Crear dialogo para seleccionar fecha
                DatePickerDialog selecFecha;
                selecFecha = new DatePickerDialog(FechaHora.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int yearSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                        // TODO Auto-generated method stub
                        /* Generar fecha */
                        datepicker.setMinDate(System.currentTimeMillis() - 1000);
                        mesSeleccionado = mesSeleccionado + 1;
                        fecha.setText("" + diaSeleccionado + "/" + mesSeleccionado + "/" + yearSeleccionado);
                    }
                }, mYear, mMes, mDia);
                selecFecha.show();

            }
        });

        hora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                //Obtener hora actual
                Calendar fechaActual = Calendar.getInstance();
                int horaa = fechaActual.get(Calendar.HOUR_OF_DAY);
                int minutos = fechaActual.get(Calendar.MINUTE);

                //Crear dialogo para seleccionar hora
                TimePickerDialog selecHora;
                selecHora = new TimePickerDialog(FechaHora.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int horaSeleccionada, int minutoSeleccionado) {
                        hora.setText(horaSeleccionada+":"+minutoSeleccionado);
                    }
                }, horaa, minutos, true);
                selecHora.show();

            }

        });

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /*
                 * Intent es el objeto que permite navegar entre actividades
                 */
                Intent intent = new Intent(FechaHora.this,
                        SeleccionRuta.class);
                startActivity(intent);
                /*
                 * startActivity da comienzo a la actividad especificada en el segundo parametro
                 * de la constructora del Intent
                 */

            }
        });



    }
}
