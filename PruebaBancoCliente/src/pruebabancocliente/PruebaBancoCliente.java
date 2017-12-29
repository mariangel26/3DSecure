/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabancocliente;

import Registro.Registro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author oswalm
 */
public class PruebaBancoCliente {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE);
        SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION);
        BufferedReader sbr = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter pw = new PrintWriter(client.getOutputStream(),true);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("mete un nombre");
        pw.println(br2.readLine());
        String mensaje = null;
        while (true){
            System.out.println("mete un mensaje");
            mensaje = br2.readLine();
            if(mensaje.equals("salir")){
                client.close();
                break;
            }
            pw.println(mensaje);
            System.out.println("el servidor responde: ");
            System.out.println(sbr.readLine());
        }
    }
    
}
