package com.example.alejo.rutas;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echo.facade.Facade;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapaDesdeU extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private LatLng ubicacionAct;
    private Button buscarRuta;
    private Button siguiente;
    private ImageView cambiar;
    private LatLng origen;
    private LatLng destino;
    private SupportPlaceAutocompleteFragment placeAutocompleteFragment;
    private TextView textoDestino;
    private ImageView botonUbicacion;
    private String coordOrigen;
    private String coordDestino;
    private LatLng coordenada;
    private String[] datos;
    private ProgressDialog pd;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapadesdeu);

        textoDestino = findViewById(R.id.descOrigen);
        botonUbicacion = findViewById(R.id.miUbicacion);
        cambiar = findViewById(R.id.cambiar);
        buscarRuta = findViewById(R.id.buscarRuta);
        siguiente = findViewById(R.id.siguienteSR);
        origen = new LatLng(4.8615787, -74.0325368);
        coordOrigen = origen.latitude+", "+origen.longitude;
        coordDestino = "";

        pd = new ProgressDialog(MapaDesdeU.this);
        pd.setMessage("Cargando mapa...");
        pd.show();

        //Obtener arreglo de actividad anterior
        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapaDesdeU.this, MapaHaciaU.class);
                datos[5] = "Hacia la Universidad";
                intent.putExtra("datos", datos);
                startActivity(intent);
            }
        });

        botonUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoDestino.setText("Mi Ubicación");
                map.clear();
                destino = coordenada;
                coordDestino = destino.latitude+","+destino.longitude;
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionAct,17.0f));
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(coordDestino)) {
                    coordDestino = destino.latitude+","+destino.longitude;
                    datos[3] = coordOrigen;
                    datos[4] = coordDestino;
                    Intent intent = new Intent(MapaDesdeU.this, TarifaPlaca.class);
                    //Mandar arreglo hacia la siguiente actividad y empezarla
                    intent.putExtra("datos",datos);
                    startActivity(intent);
                }else{
                    Toast.makeText(MapaDesdeU.this, "No has seleccionado una ruta",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Enviar solicitud al API de direcciones para obtener al ruta
        buscarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destino != null) {
                    try {
                        String desCord = destino.latitude + "," + destino.longitude;
                        String desOri = origen.latitude + "," + origen.longitude;
                        DirectionsResult result = DirectionsApi.newRequest(getGeoContext())
                                .origin(desOri)
                                .destination(desCord)
                                .await();
                        addPolyline(result, map);
                    } catch (Exception e) {
                        Toast.makeText(MapaDesdeU.this, "No se pudo encontrar la ruta",
                                Toast.LENGTH_SHORT).show();
                    }finally {

                    }
                }else{
                    Toast.makeText(MapaDesdeU.this, "No has seleccionado un destino",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Llamar fragmento de autocompletado de Places
        placeAutocompleteFragment = (SupportPlaceAutocompleteFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                LatLng coord = place.getLatLng();
                destino = coord;
                coordDestino = destino.latitude+","+destino.longitude;
                map.addMarker(new MarkerOptions().position(coord).title(place.getName().toString()));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 15.0f));
                textoDestino.setText(place.getName().toString());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d("msg", "An error occurred: " + status);
            }
        });

        //Llamar fragmento que contiene el mapa del layout e inicializarlo
        SupportMapFragment mapa = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapa.getMapAsync(this);

        //Obtener ubicacion del usuario
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //Obtener latitud
                    double latitud = location.getLatitude();
                    //Obtener longitud
                    double longitud = location.getLongitude();
                    //Crear coordenada
                    LatLng coord = new LatLng(latitud, longitud);
                    coordenada = coord;
                    //Crear clase Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitud, longitud, 1);
                        ubicacionAct = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionAct,17.0f));
                        pd.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    locationManager.removeUpdates(this);
                }
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }
                @Override
                public void onProviderEnabled(String provider) {

                }
                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //Obtener latitud
                    double latitud = location.getLatitude();
                    //Obtener longitud
                    double longitud = location.getLongitude();
                    //Crear coordenada
                    LatLng coord = new LatLng(latitud, longitud);
                    destino = new LatLng(latitud, longitud);
                    //Crear clase Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitud, longitud, 1);
                        ubicacionAct = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionAct,17.0f));
                        pd.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    locationManager.removeUpdates(this);
                }
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
                @Override
                public void onProviderEnabled(String provider) {
                }
                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(false);
        //Verificar si se dieron los permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
    }

    //Agregar polilinea al mapa
    private void addPolyline(DirectionsResult results, GoogleMap mMap) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(1)
                .setApiKey(getString(R.string.google_maps_key))
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MapaDesdeU.this, FechaHora.class);
        datos[1] = "Hacia la Universidad";
        intent.putExtra("datos", datos);
        startActivity(intent);
    }
}
