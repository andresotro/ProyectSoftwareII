package com.example.alejo.rutas;

import android.Manifest;
import android.app.ProgressDialog;
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
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapaHaciaU extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private LatLng ubicacionAct;
    private Button buscarRuta;
    private Button siguiente;
    private ImageView cambiar;
    private LatLng origen;
    private LatLng destino;
    private SupportPlaceAutocompleteFragment placeAutocompleteFragment;
    private TextView textoOrigen;
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
        setContentView(R.layout.activity_mapahaciau);

        textoOrigen = findViewById(R.id.descOrigen);
        botonUbicacion = findViewById(R.id.miUbicacion);
        cambiar = findViewById(R.id.cambiar);
        buscarRuta = findViewById(R.id.buscarRuta);
        siguiente = findViewById(R.id.siguienteSR);
        destino = new LatLng(4.8615787, -74.0325368);
        coordDestino = destino.latitude+","+destino.longitude;
        coordOrigen = "";

        pd = new ProgressDialog(MapaHaciaU.this);
        pd.setMessage("Cargando mapa...");
        pd.show();

        //Obtener arreglo de actividad anterior
        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapaHaciaU.this, MapaDesdeU.class);
                datos[5] = "Desde la Universidad";
                intent.putExtra("datos", datos);
                startActivity(intent);
            }
        });

        botonUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoOrigen.setText("Mi Ubicación");
                map.clear();
                origen = coordenada;
                coordOrigen = origen.latitude+","+origen.longitude;
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionAct,17.0f));
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(coordOrigen)) {
                        //Crear ruta
                        datos[3] = coordOrigen;
                        datos[4] = coordDestino;
                        Intent intent = new Intent(MapaHaciaU.this, TarifaPlaca.class);
                        //Mandar arreglo hacia la siguiente actividad y empezarla
                        intent.putExtra("datos",datos);
                        startActivity(intent);
                }else{
                    Toast.makeText(MapaHaciaU.this, "No has seleccionado una ruta",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        buscarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(origen != null) {
                    try {
                        String desCord = destino.latitude + "," + destino.longitude;
                        String desOri = origen.latitude + "," + origen.longitude;
                        DirectionsResult result = DirectionsApi.newRequest(getGeoContext())
                                .origin(desOri)
                                .destination(desCord)
                                .await();
                        addPolyline(result, map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {

                    }
                }else{
                    Toast.makeText(MapaHaciaU.this, "No has seleccionado el origen",
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
                origen = coord;
                coordOrigen = origen.latitude+","+origen.longitude;
                map.addMarker(new MarkerOptions().position(coord).title(place.getName().toString()));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 15.0f));
                textoOrigen.setText(place.getName().toString());
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
                    origen = new LatLng(latitud, longitud);
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
        Intent intent = new Intent(MapaHaciaU.this, FechaHora.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        datos[1] = "Hacia la Universidad";
        intent.putExtra("datos", datos);
        startActivity(intent);
    }
}
