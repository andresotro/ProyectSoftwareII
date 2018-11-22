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
import android.widget.Toast;

import java.util.Calendar;

public class FechaHora extends AppCompatActivity {

    private String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_hora);

        final EditText fecha = findViewById(R.id.fecha);
        final EditText hora = findViewById(R.id.hora);
        final Button boton = findViewById(R.id.siguienteF);

        //Obtener arreglo de actividad anterior
        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar fechaActual = Calendar.getInstance();
                int mYear = fechaActual.get(Calendar.YEAR);
                int mMes = fechaActual.get(Calendar.MONTH);
                int mDia = fechaActual.get(Calendar.DAY_OF_MONTH);
                final long time = System.currentTimeMillis() - 1;

                //Crear dialogo para seleccionar fecha
                DatePickerDialog selecFecha;
                selecFecha = new DatePickerDialog(FechaHora.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int yearSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                        // TODO Auto-generated method stub
                        /* Generar fecha */
                        mesSeleccionado = mesSeleccionado + 1;
                        fecha.setText("" + diaSeleccionado + "/" + mesSeleccionado + "/" + yearSeleccionado);
                    }
                }, mYear, mMes, mDia);
                selecFecha.getDatePicker().setMinDate(time);
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

                if("".equals(hora.getText().toString()) || "".equals(fecha.getText().toString())){
                    Toast.makeText(FechaHora.this, "Debes completar ambos campos",
                            Toast.LENGTH_SHORT).show();
                }else {
                    String horaS = hora.getText().toString();
                    String fechaS = fecha.getText().toString();
                    datos[7] = horaS;
                    datos[8] = fechaS;
                    Intent intent = null;
                    if(datos[5].equals("Desde la Universidad")){
                        //Cambiar de actividad y enviar arreglo
                        intent = new Intent(FechaHora.this,
                                MapaDesdeU.class);
                    }else{
                        //Cambiar de actividad y enviar arreglo
                        intent = new Intent(FechaHora.this,
                                MapaHaciaU.class);
                    }
                    intent.putExtra("datos", datos);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FechaHora.this, Cupos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("datos", datos);
        startActivity(intent);
    }

}
