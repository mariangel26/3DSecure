/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

import Registro.Registro;
import java.io.FileInputStream;
import java.io.IOException;
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
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * Para la conexion nos guiamos de las siguientes paginas web
 * https://www.programcreek.com/java-api-examples/javax.net.ssl.SSLServerSocket
 * http://codyaray.com/2013/04/java-ssl-with-multiple-keystores
 * https://www.programcreek.com/java-api-examples/javax.net.ssl.KeyManagerFactory
 * @author oswalm
 */
public class HiloPrincipalServidor extends Thread{
    
    public void run(){
        System.out.println("BANCO CLIENTE COMO Servidor empieza a correr");
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
            ServerSocketFactory serverFactory = context.getServerSocketFactory();
            
            //CREANDO EL SERVER SOCKET CON EL PUERTO DE CONEXION
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION_SERVIDOR);
            
            ((SSLServerSocket) serverSocket).setNeedClientAuth(false);
            Socket clientSocket; 
            for(;;){
                System.out.println("Servidor esperando que alguien se conecte");
                clientSocket = serverSocket.accept();
                new HiloProcesaServidor(clientSocket).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
