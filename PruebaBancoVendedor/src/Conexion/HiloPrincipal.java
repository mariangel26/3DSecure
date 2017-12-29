/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Registro.Registro;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author oswalm
 */
public class HiloPrincipal extends Thread{
    
    public void run(){
        System.out.println("Servidor empieza a correr");
        try {
            System.setProperty("javax.net.ssl.keyStore", Registro.KEY_STORE);
            System.setProperty("javax.net.ssl.keyStorePassword", Registro.KEY_STORE_PASSWORD);
            //System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE);
            //System.setProperty("javax.net.ssl.trustStorePassword", Registro.TRUST_STORE_PASSWORD);
            
            SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION);
            Socket clientSocket; 
            for(;;){
                System.out.println("Servidor esperando que alguien se conecte");
                clientSocket = serverSocket.accept();
                new HiloProcesa(clientSocket).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
