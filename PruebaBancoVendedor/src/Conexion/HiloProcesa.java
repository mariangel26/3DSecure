/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Registro.Registro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author oswalm
 */
public class HiloProcesa extends Thread {
    
    Socket clientSocket;
    
    public HiloProcesa(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try {
            
            //El servidor recibe:
               ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
               //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
               //Mensaje que llega:
                String mensaje = (String)ois.readObject();
                System.out.println("El cliente (EL BANCO CLIENTE) envio: "+mensaje);
                enviarAeCommerce(mensaje);
                
        } catch (Exception ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ESTOY AQUI POR ALGUN EXTRANO MOTIVO QUE ES POCO PROBABLE");
        }
    }
    
    public void enviarAeCommerce(String mensaje){
        System.out.println("ENTRE EN EL METODO DE ENVIAR AL ECOMMERCE");
        try {
            System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
        SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
        ObjectOutputStream salidaObjeto;      
        //Se colocan los datos del nodo (Direccion IP y Puerto).
        salidaObjeto = new ObjectOutputStream(client.getOutputStream());
        //El cliente manda:
        salidaObjeto.writeObject("NO");
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            System.out.println("ME QUEDE PEGADO EN ESTA EXCEPCION WE");
        }
        
    }
    
    
}
