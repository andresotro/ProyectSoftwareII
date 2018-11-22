package com.example.alejo.rutas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.facade.Facade;
import com.example.echo.facade.model.Ruta;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class TarifaPlaca extends AppCompatActivity {

    String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_placa);

        final EditText tarifa = findViewById(R.id.tarifa);
        final EditText letP = findViewById(R.id.letras);
        final EditText numP = findViewById(R.id.nums);
        Button terminar = findViewById(R.id.terminarR);

        //Cambiar al edittext del numero cuando ya hayan 3 letras en de letras en la placa
        letP.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(letP.getText().toString().length()==3)     //size as per your requirement
                {
                    numP.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        //Recuperar arraylist enviado desde actividad anterior
        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        //Añadir listener al boton
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("".equals(tarifa.getText().toString()) || "".equals(letP.getText().toString()) || "".equals(numP.getText().toString())){
                    Toast.makeText(TarifaPlaca.this, "Debes completar ambos campos",
                            Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(TarifaPlaca.this);
                    alert.setTitle("Crear Ruta");
                    alert.setMessage("¿Estás seguro de crear esta ruta?");
                    alert.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Agregar ultimos datos al array
                            datos[2] = letP.getText().toString()+numP.getText().toString();
                            datos[9] = tarifa.getText().toString();
                            datos[6] = datos[0]+Integer.toString(1*(int)Math.random()+100);

                            //Crear ruta
                            new registrarRuta(TarifaPlaca.this).execute(datos);
                            dialog.dismiss();
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }
            }
        });

    }

    private class registrarRuta extends AsyncTask<String, Void, Ruta> {

        Context context;
        private ProgressDialog pd;
        public registrarRuta(Context context) {
            this.context = context;
        }

        protected void onPreExecute(){

            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Añadiendo ruta...");
            pd.show();

        }

        @Override
        protected Ruta doInBackground(String... params) {
            Ruta response = null;

            Facade.Builder builder = new Facade.Builder(
                    AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            Facade service = builder.build();


            try {
                service.registrarRuta(params[0], new Integer(params[1]), params[2], params[3], params[4],params[5], params[6],params[7],params[8],
                                                new Float(params[9])).execute();

            } catch (Exception e) {
                Log.e("Error", "Aiuda", e);
            }

            return response;

        }

        @Override
        protected void onPostExecute(Ruta ruta) {
            super.onPostExecute(ruta);
            String correo = datos[0];
            datos = new String[10];
            datos[0] = correo;
            Intent intent = new Intent(TarifaPlaca.this, MenuPrincipal.class);

            //Resetear todos los campos
            EditText ed = findViewById(R.id.tarifa);
            ed.setText("");
            ed = findViewById(R.id.nums);
            ed.setText("");
            ed = findViewById(R.id.letras);
            ed.setText("");

            intent.putExtra("datos", datos);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(TarifaPlaca.this, "¡Ruta añadida!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}


/*
    datos[0] = correoConductor;
    datos[1] = cupos;
    datos[2] = placa;
    datos[3] = origen;
    datos[4] = destino;
    datos[5] = tipoRuta;
    datos[6] = id;
    datos[7] = hora;
    datos[8] = fecha;
    datos[9] = precio;
 */