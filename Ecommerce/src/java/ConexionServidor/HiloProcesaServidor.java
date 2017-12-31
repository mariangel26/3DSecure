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
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oswalm
 */
public class HiloProcesaServidor extends Thread {
    
    Socket clientSocket;
    
    public HiloProcesaServidor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try {
            
            //El servidor recibe:
            System.out.println("Entre en el try");
               ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
               //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
               //Mensaje que llega:
                String mensaje = (String)ois.readObject();
                if(mensaje.equals("ACEPTADO")){
                    //logica para generar factura
                    //ademas de mostrar una pagina de exito en el front
                }else{
                    //mostrar una pagina de fallo en el front
                }
                System.out.println("El cliente (EL BANCO DEL VENDEDOR) envio: "+mensaje);
                clientSocket.close();
                
            
        } catch (Exception ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("SOY YO SOY YO, SOY LA EXCEPCION MALIGNA");
        }
    }
    
    
}
