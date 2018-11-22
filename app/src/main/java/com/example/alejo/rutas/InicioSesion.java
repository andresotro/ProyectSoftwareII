package com.example.alejo.rutas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.echo.facade.Facade;
import com.example.echo.facade.model.Ruta;
import com.example.echo.proxy.Proxy;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.util.ArrayList;

public class InicioSesion extends AppCompatActivity {

    private String[] datos = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        final EditText correo = findViewById(R.id.correo);
        final EditText password = findViewById(R.id.password);
        ImageView acceder = findViewById(R.id.acceder);

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correo.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(InicioSesion.this, "Debes completar ambos campos",
                            Toast.LENGTH_SHORT).show();
                } else {
                    /*
                     * Intent es un objeto que permite navegar entre actividades
                     */
                    String[] params = {correo.getText().toString(), password.getText().toString()};
                    datos[0] = correo.getText().toString();
                    new iniciarSesion(InicioSesion.this).execute(params);

                }
            }
        });

    }

    private class iniciarSesion extends AsyncTask<String, Void, Integer> {

        Context context;
        private ProgressDialog pd;
        public iniciarSesion(Context context) {
            this.context = context;
        }

        protected void onPreExecute(){

            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Iniciando Sesión...");
            pd.show();

        }

        @Override
        protected Integer doInBackground(String... params) {
            int response = 0;

            Proxy.Builder builder = new Proxy.Builder(
                    AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            Proxy service = builder.build();


            try {
                response = service.iniciarsesion(params[0], params[1]).execute().getSesion();
            } catch (Exception e) {
                Log.e("Error", "Aiuda", e);
            }

            return response;

        }

        @Override
        protected void onPostExecute(Integer sesion) {
            super.onPostExecute(sesion);
            pd.dismiss();
            if(sesion != 0) {
                Log.i("SESION", ""+sesion);
                String correo = datos[0];
                datos = new String[11];
                datos[0] = correo;
                datos[10] = "" + sesion;
                Intent intent = new Intent(InicioSesion.this, MenuPrincipal.class);
                intent.putExtra("datos", datos);
                startActivity(intent);
                Toast.makeText(InicioSesion.this, "Sesión Iniciada Correctamente",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(InicioSesion.this, "Credenciales incorrectas",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


}
