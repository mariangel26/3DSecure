/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Registro.Registro;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author oswal
 */
public class HiloFactura extends Thread{

    
    
    
    public void run(){
        Socket clientSocket;

        DataOutputStream output;
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;
        String nombreArchivo;

        try{
            //Servidor Socket en el puerto 5000
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
            ServerSocket serverSocket = serverFactory.createServerSocket(Registro.PUERTO_CONEXION_SERVIDOR_FACTURA);
            
            ((SSLServerSocket) serverSocket).setNeedClientAuth(false);
            System.out.println("la transferencia de la factura va a comenzar");
            while ( true ) {
                //Aceptar conexiones
                clientSocket = serverSocket.accept();
                //Buffer de 1024 bytes
                receivedData = new byte[1024];
                bis = new BufferedInputStream(clientSocket.getInputStream());
                DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
                //Recibimos el nombre del fichero
                nombreArchivo = dis.readUTF();
                nombreArchivo = nombreArchivo.substring(nombreArchivo.indexOf('\\')+1,nombreArchivo.length());
                //Para guardar fichero recibido
                bos = new BufferedOutputStream(new FileOutputStream(nombreArchivo));
                while ((in = bis.read(receivedData)) != -1){
                    bos.write(receivedData,0,in);
                }
                
                System.out.println("Se ha terminado la recepcion del archivo");
                bos.close();
                dis.close();
                //serverSocket.close();
                clientSocket.close();
                
            }
        }catch (Exception e ) {
            System.err.println(e);
        }
    }
}
