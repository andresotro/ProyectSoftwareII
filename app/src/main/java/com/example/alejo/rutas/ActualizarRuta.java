package com.example.alejo.rutas;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.echo.facade.Facade;
import com.example.echo.facade.model.Ruta;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.util.Calendar;

public class ActualizarRuta extends AppCompatActivity {

    private EditText cupos;
    private EditText hora;
    private EditText tarifa;
    private EditText let;
    private EditText num;
    private String[] datos = new String[10];
    private String[] paramB;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_ruta);

        //Obtener listas de datos
        Intent intent = getIntent();
        paramB = intent.getStringArrayExtra("param");

        cupos = findViewById(R.id.cuposA);
        hora = findViewById(R.id.horaA);
        tarifa = findViewById(R.id.tarifaA);
        let = findViewById(R.id.letrasA);
        num = findViewById(R.id.numsA);
        boton = findViewById(R.id.actualizarA);

        hora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                //Obtener hora actual
                Calendar fechaActual = Calendar.getInstance();
                int horaa = fechaActual.get(Calendar.HOUR_OF_DAY);
                int minutos = fechaActual.get(Calendar.MINUTE);

                //Crear dialogo para seleccionar hora
                TimePickerDialog selecHora;
                selecHora = new TimePickerDialog(ActualizarRuta.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int horaSeleccionada, int minutoSeleccionado) {
                        hora.setText(horaSeleccionada+":"+minutoSeleccionado);
                    }
                }, horaa, minutos, true);
                selecHora.show();

            }

        });

        boton.setOnClickListener(new View.OnClickListener() {
            String[] params = new String[7];
            @Override
            public void onClick(View v) {
                params[0] = paramB[0];
                params[1] = paramB[1];
                params[2] = paramB[2];
                if(cupos.getText().toString().equals("") && tarifa.getText().toString().equals("") && hora.getText().toString().equals("") &&
                        let.getText().toString().equals("") && num.getText().toString().equals("")){
                    Toast.makeText(ActualizarRuta.this, "Rellena los campos de la información que desees actualizar", Toast.LENGTH_SHORT).show();
                }else {
                    if(cupos.getText().toString().equals("")){
                        params[3] = paramB[3];
                    }else{
                        params[3] = cupos.getText().toString();
                    }
                    if(let.getText().toString().equals("") || num.getText().toString().equals("")){
                        params[4] = paramB[4];
                    }else{
                        params[4] = let.getText().toString() + num.getText().toString();
                    }
                    if(hora.getText().toString().equals("")){
                        params[5] = paramB[5];
                    }else{
                        params[5] = hora.getText().toString();
                    }
                    if(tarifa.getText().toString().equals("")){
                        params[6] = paramB[6];
                    }else{
                        params[6] = tarifa.getText().toString();
                    }
                    new actualizarRuta(ActualizarRuta.this).execute(params);
                }
            }
        });

    }

    private class actualizarRuta extends AsyncTask<String, Void, Ruta> {

        Context context;
        private ProgressDialog pd;
        public actualizarRuta(Context context) {
            this.context = context;
        }

        protected void onPreExecute(){

            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Actualizando Ruta...");
            pd.show();

        }

        @Override
        protected Ruta doInBackground(String... params) {
            Ruta response = null;
            Facade.Builder builder = new Facade.Builder(
                    AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            Facade service = builder.build();

            try {
                service.actualizarRuta(params[0],params[1],params[2],Integer.parseInt(params[3]),params[4],params[5],
                        Float.parseFloat(params[6])).execute();
            } catch (Exception e) {
                Log.e("Error", "Aiuda", e);
            }

            return response;

        }

        @Override
        protected void onPostExecute(Ruta ruta) {
            pd.dismiss();
            Toast.makeText(context, "¡Ruta actualizada!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ListarRutas.class);
            datos[0] = paramB[0];
            intent.putExtra("datos", datos);
            startActivity(intent);
        }
    }
}
