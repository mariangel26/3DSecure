/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

import Factura.Factura;
import Registro.Registro;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oswalm
 */
public class HiloPrincipalServidor extends Thread{

    public HttpServletResponse response;
    public HttpServletRequest request;
    public HiloPrincipalServidor(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
    
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
     * @param user 
   */
    
    public void recibir(String nombreApellido,String cedula,String nombreProducto, String cantidad, 
            String precioDetal,String precioTotal, String user) throws ServletException{
        System.out.println("VENDEDOR COMO Servidor empieza a correr");
        try {
            System.setProperty("javax.net.ssl.keyStore", Registro.KEY_STORE_SERVIDOR);
            
            //ESCOJO EL PROTOCOLO QUE SE VA A UTILIZAR
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            
            //TIPO DE KEYSTORE
            KeyStore ks = KeyStore.getInstance("jceks");
            
            //CARGANDO EL KEYSTORE DEL SERVIDOR
            ks.load(new FileInputStream(Registro.KEY_STORE_SERVIDOR), null);
            
            //ESTABLECIENDO EL TIPO DE CERTIFICADO
            KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
            kf.init(ks, Registro.KEY_STORE_PASSWORD.toCharArray());
           
            //INICIALIZA EL CONTEXTO 
            context.init(kf.getKeyManagers(), null, null);
            
            //INICIALIZANDO LA FABRICA PARA EL SERVER SOCKET
            SSLServerSocketFactory serverFactory = context.getServerSocketFactory();
            
            //CREANDO EL SERVER SOCKET CON EL PUERTO DE CONEXION
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION_SERVIDOR);
            
            ((SSLServerSocket) serverSocket).setNeedClientAuth(false);

            Socket clientSocket; 
            //for(;;){
            System.out.println("Servidor esperando que alguien se conecte");
            clientSocket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
           //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
           //Mensaje que llega:
            String mensaje = (String)ois.readObject();
            if(mensaje.equals("ACEPTADO")){
                //Factura.generar(nombreApellido,cedula,nombreProducto,cantidad,precioDetal,precioTotal);
                
                request.setAttribute("user", user);
                request.getRequestDispatcher("CompraExitosa.jsp").forward(request, response);

            }else{
                request.setAttribute("user", user);
                request.getRequestDispatcher("FondosInsuficientes.jsp").forward(request, response);
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
        } catch (KeyManagementException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
