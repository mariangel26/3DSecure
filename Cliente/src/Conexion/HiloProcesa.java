/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Registro.Registro;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
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
                ObjectOutputStream salidaObjeto;      
                //Se colocan los datos del nodo (Direccion IP y Puerto).

                salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream());
                System.out.println("voy a procesar los datos");
                
                salidaObjeto.writeObject(escribirRespuesta(mensaje)); 
                clientSocket.close();
                //HiloProcesa.enviarAeCommerce(split[0]);
            
                
        } catch (Exception ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ESTOY AQUI POR ALGUN EXTRANO MOTIVO QUE ES POCO PROBABLE");
        }
    }
    /*
    public static void enviarABancoCliente(String mensaje){
        
        try {
            System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
            
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
            
            //INICIALIZANDO LA FABRICA PARA EL SOCKET
            SSLSocketFactory clientFactory = context.getSocketFactory();
            
            //CREANDO EL SOCKET Y ENVIANDO IP Y PUERTO DE CONEXION
            Socket client;
            client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
            
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            salidaObjeto.writeObject(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            System.out.println("ME QUEDE PEGADO EN ESTA EXCEPCION WE");
        }
        
    }*/
    
    public static String escribirRespuesta(String pregunta){
        boolean salir = true;
        boolean respuesta = true;
        System.out.println ("Para continuar con la transaccion debe responder su pregunta secreta: \n"+pregunta+"\n");
        
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
            entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
            
        
        
        return entradaTeclado;
    }
    
    
    public static void pintarCandadoConLlave(){
        System.out.println( "──▄▀▀▀▄───────────────\n" +
                            "──█───█───────────────\n" +
                            "─███████─────────▄▀▀▄─\n" +
                            "░██───██░░█▀█▀▀▀▀█░░█░\n" +
                            "░███▄███░░▀░▀░░░░░▀▀░░\n");
    }
    
    public static void pintarCandadoCerrado(){
        System.out.println( "────▄▀▀▀▄────\n" +
                            "────█───█────\n" +
                            "───███████───\n" +
                            "──░██───██░──\n" +
                            "──░███▄███░──\n");
    }
    
    public static void pintarCandadoAbierto(){
        System.out.println( "─▄▀▀▀▄────────\n" +
                            "─█───█────────\n" +
                            "────███████───\n" +
                            "───░██───██░──\n" +
                            "───░███▄███░──\n");
    }
    
    
}
