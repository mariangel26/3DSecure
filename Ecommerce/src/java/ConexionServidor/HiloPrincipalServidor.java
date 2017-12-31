/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

import Registro.Registro;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oswalm
 */
public class HiloPrincipalServidor extends Thread{

    public HttpServletResponse response;
    public HiloPrincipalServidor(HttpServletResponse response) {
        this.response = response;
    
    }
    
    
    
    public void run(){
        System.out.println("VENDEDOR COMO Servidor empieza a correr");
        try {
            System.setProperty("javax.net.ssl.keyStore", Registro.KEY_STORE_SERVIDOR);
            System.setProperty("javax.net.ssl.keyStorePassword", Registro.KEY_STORE_PASSWORD);
            SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION_SERVIDOR);
            Socket clientSocket; 
            //for(;;){
                System.out.println("Servidor esperando que alguien se conecte");
                clientSocket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
               //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
               //Mensaje que llega:
                String mensaje = (String)ois.readObject();
                if(mensaje.equals("ACEPTADO")){
                    System.out.println("Mando aceptado");
                    //response.sendRedirect("welcome.jsp");
                    //logica para generar factura
                    //ademas de mostrar una pagina de exito en el front
                }else{
                    System.out.println("mando rechazado: ");
                    //response.sendRedirect("signUp.jsp");
                    //mostrar una pagina de fallo en el front
                }
                System.out.println("El cliente (EL BANCO DEL VENDEDOR) envio: "+mensaje);
                ois.close();
                clientSocket.close();
                //new HiloProcesaServidor(clientSocket).start();
            //}
        } catch (IOException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recibir(){
        System.out.println("VENDEDOR COMO Servidor empieza a correr");
        try {
            System.setProperty("javax.net.ssl.keyStore", Registro.KEY_STORE_SERVIDOR);
            System.setProperty("javax.net.ssl.keyStorePassword", Registro.KEY_STORE_PASSWORD);
            SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION_SERVIDOR);
            Socket clientSocket; 
            //for(;;){
                System.out.println("Servidor esperando que alguien se conecte");
                clientSocket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
               //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
               //Mensaje que llega:
                String mensaje = (String)ois.readObject();
                if(mensaje.equals("ACEPTADO")){
                    System.out.println("Mando aceptado");
                    response.sendRedirect("welcome.jsp");
                    response.sendRedirect("signUp.jsp");
                    //logica para generar factura
                    //ademas de mostrar una pagina de exito en el front
                }else{
                    System.out.println("mando rechazado: ");
                    response.sendRedirect("signUp.jsp");
                    //mostrar una pagina de fallo en el front
                }
                System.out.println("El cliente (EL BANCO DEL VENDEDOR) envio: "+mensaje);
                ois.close();
                clientSocket.close();
                //new HiloProcesaServidor(clientSocket).start();
            //}
        } catch (IOException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
