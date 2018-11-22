package com.example.alejo.rutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuPrincipal extends AppCompatActivity {

    private String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        final ImageView ver = findViewById(R.id.ver);
        final ImageView crear = findViewById(R.id.crear);

        //Obtener arreglo de actividad anterior
        Intent antes = getIntent();
        datos = antes.getStringArrayExtra("datos");

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambiar de actividad
                //Cambiar de actividad
                Intent intent = new Intent(MenuPrincipal.this,
                        ListarRutas.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("datos", datos);
                startActivity(intent);
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambiar de actividad
                Intent intent = new Intent(MenuPrincipal.this,
                        PantallaInicio.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("datos", datos);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(MenuPrincipal.this,
                InicioSesion.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("datos", datos);
        startActivity(intent);
        finish();

    }
}
