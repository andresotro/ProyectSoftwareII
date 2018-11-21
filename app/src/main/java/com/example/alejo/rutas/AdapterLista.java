package com.example.alejo.rutas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.facade.Facade;
import com.example.echo.facade.model.Ruta;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.util.ArrayList;
import java.util.List;

public class AdapterLista extends ArrayAdapter<Ruta> {

    private Context mContext;
    private List<Ruta> listaRutas = new ArrayList<>();
    private String[] datos = new String[10];

    public AdapterLista(Context context, ArrayList<Ruta> lista){
        super(context, 0, lista);
        mContext = context;
        listaRutas = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.formatolista,parent,false);

        final Ruta ruta = listaRutas.get(position);
        TextView tipoR = listItem.findViewById(R.id.tipoRutaL);
        tipoR.setText(""+ruta.getTipoRuta());

        TextView hora = listItem.findViewById(R.id.HoraL);
        hora.setText(""+ruta.getHora());

        TextView fecha = listItem.findViewById(R.id.FechaL);
        fecha.setText(""+ruta.getFecha());

        TextView cupos = listItem.findViewById(R.id.CuposL);
        cupos.setText(""+ruta.getNumeroPuestos());

        TextView tarifa = listItem.findViewById(R.id.TarifaL);
        tarifa.setText("$"+ruta.getPrecio());

        TextView placa = listItem.findViewById(R.id.PlacaL);
        placa.setText(""+ruta.getPlacaCarro());

        Button eliminar = listItem.findViewById(R.id.eliminarR);
        Button actualizar = listItem.findViewById(R.id.actualizarR);

        // Listener del boton eliminar de cada elemento
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("Crear Ruta");
                alert.setMessage("¿Estás seguro de eliminar esta ruta?");
                alert.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Eliminar ruta
                        String[] params = {ruta.getCorreoConductor(), ruta.getHora(), ruta.getFecha()};
                        datos[0] = ruta.getCorreoConductor();

                        new eliminarRuta(mContext).execute(params);
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
        });

        // Listener del boton actualizar de cada elemento
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActualizarRuta.class);
                datos[0] = ruta.getCorreoConductor();
                intent.putExtra("datos", datos);
                String[] paramsB = {ruta.getCorreoConductor(), ruta.getHora(), ruta.getFecha(), ""+ruta.getNumeroPuestos(),
                ruta.getPlacaCarro(), ruta.getHora(), ""+ruta.getPrecio()};
                intent.putExtra("param", paramsB);
                mContext.startActivity(intent);
            }
        });

        return listItem;
    }

    private class eliminarRuta extends AsyncTask<String, Void, Ruta> {

        Context context;
        private ProgressDialog pd;
        public eliminarRuta(Context context) {
            this.context = context;
        }

        protected void onPreExecute(){

            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Eliminando Ruta...");
            pd.show();

        }

        @Override
        protected Ruta doInBackground(String... params) {
            Ruta response = null;
            Facade.Builder builder = new Facade.Builder(
                    AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            Facade service = builder.build();

            try {
                service.eliminarRuta(params[0],params[1],params[2]).execute();
            } catch (Exception e) {
                Log.e("Error", "Aiuda", e);
            }

            return response;

        }

        @Override
        protected void onPostExecute(Ruta ruta) {
            pd.dismiss();
            Toast.makeText(context, "¡Ruta eliminada!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ListarRutas.class);
            intent.putExtra("datos", datos);
            mContext.startActivity(intent);
        }
    }

}
