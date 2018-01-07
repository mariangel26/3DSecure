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
            final File localFile = new File( Registro.UBICACION_ARCHIVO_FACTURAS+ ubicacionFactura );
            Socket clientSocket = new Socket("localhost", Registro.PUERTO_CONEXION_CLIENTE_FACTURA );
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
