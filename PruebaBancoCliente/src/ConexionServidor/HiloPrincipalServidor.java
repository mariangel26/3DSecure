/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

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
public class HiloPrincipalServidor extends Thread{
    
    public void run(){
        System.out.println("VENDEDOR COMO Servidor empieza a correr");
        try {
            System.setProperty("javax.net.ssl.keyStore", Registro.KEY_STORE_SERVIDOR);
            System.setProperty("javax.net.ssl.keyStorePassword", Registro.KEY_STORE_PASSWORD);
            
            SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION_SERVIDOR);
            Socket clientSocket; 
            for(;;){
                System.out.println("Servidor esperando que alguien se conecte");
                clientSocket = serverSocket.accept();
                new HiloProcesaServidor(clientSocket).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
