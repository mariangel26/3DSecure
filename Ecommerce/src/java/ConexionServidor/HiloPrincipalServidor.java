/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

import Factura.Factura;
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
    
    
  /**
   * metodo que se encarga de recibir del banco vendedor si se acepto la operacion
   * o no
   * @param nombreApellido
   * @param cedula
   * @param nombreProducto
   * @param cantidad
   * @param precioDetal
   * @param precioTotal 
   */
    
    public void recibir(String nombreApellido,String cedula,String nombreProducto, String cantidad, String precioDetal,String precioTotal){
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
                Factura.generar(nombreApellido,cedula,nombreProducto,cantidad,precioDetal,precioTotal);
                response.sendRedirect("CompraExitosa.jsp");

            }else{
                response.sendRedirect("FondosInsuficientes.jsp");
            }
            System.out.println("El cliente (EL BANCO DEL VENDEDOR) envio: "+mensaje);
            ois.close();
            serverSocket.close();
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
