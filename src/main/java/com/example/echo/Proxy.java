package com.example.echo;

import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiIssuerAudience;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.ArrayList;
import java.util.Random;

@Api(
    name = "proxy",
    version = "v1",
    namespace =
    @ApiNamespace(
        ownerDomain = "echo.example.com",
        ownerName = "echo.example.com",
        packagePath = ""
    ),
    issuers = {
        @ApiIssuer(
            name = "firebase",
            issuer = "https://securetoken.google.com/proyectsoftwareii",
            jwksUri =
                "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system"
                    + ".gserviceaccount.com"
        )
    }
// [END_EXCLUDE]
)
// [END echo_api_annotation]
public class Proxy{
    private static Proxy unicaInstancia;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    Facade f = Facade.getInstance();
    
    public static Proxy reemplazarConstructora(){
        if(unicaInstancia == null){
            unicaInstancia = new Proxy();
        }
        return unicaInstancia;
    }
    
    @ApiMethod(name = "iniciarsesion")
    public Sesion iniciarSesion(@Named("correo") String correo, @Named("password") String password){
        Sesion s = new Sesion();
        s.setSesion(0);
        rellenarUsuarios();
        for(Usuario u : listaUsuarios){
            if(u.getCorreo().equals(correo) && u.getPassword().equals(password)){
                Devolver d = f.verificarSesion(correo);
                if(d.getInfo().equals(Integer.toString(0))){
                    Random rand = new Random();
                    int z = (rand.nextInt(9999) + 1);
                    s.setSesion(z);
                    f.guardarSesion(z, correo);
                    break;
                }
                else{
                    s.setSesion(Integer.parseInt(d.getInfo()));
                    break;
                }
            }
        }
        return s;
    }
    // [END echo_api_key]
    
    
    public tipoUsuario verificarUsuario(@Named("correo") String correo, @Named("password") String password){
        tipoUsuario t = new tipoUsuario();
        for(Usuario u : listaUsuarios){
            if(u.getCorreo().equals(correo) && u.getPassword().equals(password)){
		t.setTipoUsuario(u.getTipoUsuario());
		break;
            }
            else{
            	t.setTipoUsuario("Nada");
            }
        }
        return t;
    }
    
    public void rellenarUsuarios(){
        Usuario x = new Conductor();
        Usuario y = new Conductor();
        Usuario z = new Conductor();
        x.setCorreo("Correo1");x.setNombre("Yo");x.setPassword("123");x.setTipoUsuario("Conductor");
        listaUsuarios.add(x);
        y.setCorreo("Empoin");y.setNombre("Nombre");y.setPassword("1234");y.setTipoUsuario("Conductor");
        listaUsuarios.add(y);
        z.setCorreo("Prueba");z.setPassword("1234");z.setNombre("Namae");z.setTipoUsuario("Conductor");
        listaUsuarios.add(z);
    }
    
}
