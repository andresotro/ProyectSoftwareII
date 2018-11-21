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
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.echo.facade.Facade;
import com.example.echo.facade.model.Ruta;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.util.ArrayList;
import java.util.List;

public class ListarRutas extends AppCompatActivity {

    private AdapterLista adapterLista;
    private ListView listView;
    private String[] datos;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_rutas);

        listView = findViewById(R.id.lista);
        listView.setVisibility(View.INVISIBLE);
        imagen = findViewById(R.id.ups);
        imagen.setVisibility(View.INVISIBLE);

        //Obtener datos
        Intent intent = getIntent();
        datos = intent.getStringArrayExtra("datos");
        new listarRutas(ListarRutas.this).execute(datos);



    }

    private class listarRutas extends AsyncTask<String, Void, List<Ruta>> {

        Context context;
        private ProgressDialog pd;
        public listarRutas(Context context) {
            this.context = context;
        }

        protected void onPreExecute(){

            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Obteniendo rutas...");
            pd.show();

        }

        @Override
        protected List<Ruta> doInBackground(String... params) {
            List<Ruta> response = null;
            Facade.Builder builder = new Facade.Builder(
                    AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            Facade service = builder.build();

            try {
                response = service.listarRutas(datos[0]).execute().getItems();
            } catch (Exception e) {
                Log.e("Error", "Aiuda", e);
            }

            return response;

        }

        protected void onPostExecute(List<Ruta> rutas) {
            pd.dismiss();
            if(rutas.size()==0){
                imagen.setVisibility(View.VISIBLE);
            }else{
                ArrayList<Ruta> listaRutas = new ArrayList<>();
                for (Ruta r : rutas) {
                    listaRutas.add(r);
                }
                adapterLista = new AdapterLista(ListarRutas.this, listaRutas);
                listView.setAdapter(adapterLista);
                listView.setVisibility(View.VISIBLE);
                imagen.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ListarRutas.this,
                MenuPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("datos", datos);
        startActivity(intent);
        finish();
    }

}
