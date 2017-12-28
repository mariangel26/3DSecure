/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author oswal
 */
public class Cliente {
    public String nombre; //nombre del usuario
    public String apellido; //apellido del usuario
    public String nombreUsuario; //nombre de usuario para la cuenta del usuario
    public String contrasena; // contrasena de la cuenta
    public String correo;
    public Integer intentos; // numero de intentos que el usuario ha intentado 
                                //ingresar sin exito a su cuenta
    
    protected String preguntaSecreta;
    protected String respuestaSecreta;

    public Cliente(String nombre, String apellido, String nombreUsuario, 
            String contrasena, String correo, Integer intentos,
            String preguntaSecreta, String respuestaSecreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.intentos = intentos;
        this.preguntaSecreta = preguntaSecreta;
        this.respuestaSecreta = respuestaSecreta;
        
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }
    
    
    
}
