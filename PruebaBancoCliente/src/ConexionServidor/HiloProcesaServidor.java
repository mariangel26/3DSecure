/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class HiloProcesaServidor extends Thread{
    Socket clientSocket;
    
    public HiloProcesaServidor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try {
             ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
               //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
               //Mensaje que llega:
                String mensaje = (String)ois.readObject();
                System.out.println("El cliente envio: "+mensaje);
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
