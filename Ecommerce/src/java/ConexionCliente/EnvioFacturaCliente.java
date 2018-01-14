/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionCliente;

import Registro.Registro;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author oswal
 */
public class EnvioFacturaCliente extends Thread{
    String ubicacionFactura;

    public EnvioFacturaCliente(String ubicacionFactura) {
        this.ubicacionFactura = ubicacionFactura;
    }
  public void run(){
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        //Fichero a transferir

        try{
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
            SocketFactory clientFactory = context.getSocketFactory();
            
            //CREANDO EL SOCKET Y ENVIANDO IP Y PUERTO DE CONEXION
            Socket clientSocket;
            clientSocket = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE_FACTURA);
            final File localFile = new File( Registro.UBICACION_ARCHIVO_FACTURAS+ ubicacionFactura );
          
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(clientSocket.getOutputStream());
            //Enviamos el nombre del fichero
            DataOutputStream dos=new DataOutputStream(clientSocket.getOutputStream());
            dos.writeUTF(localFile.getName());
            //Enviamos el fichero
            byteArray = new byte[(int) localFile.length()];
            while ((in = bis.read(byteArray)) != -1){
                bos.write(byteArray,0,in);
            }
            System.out.println("Se ha terminado el envio del archivo");
            bis.close();
            bos.close();
            clientSocket.close();
        }catch ( Exception e ) {
            System.err.println(e);
        }
    }
}
