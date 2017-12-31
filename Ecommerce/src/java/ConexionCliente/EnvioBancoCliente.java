package ConexionCliente;

import ConexionServidor.HiloProcesaServidor;
import Registro.Registro;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author mariangel
 */
public class EnvioBancoCliente {
    
    public EnvioBancoCliente(){}
    
    public String enviarABancoCliente(String mensaje) throws ClassNotFoundException{
        
        try {
          
            System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket client;
            client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            
            salidaObjeto.writeObject(mensaje);
           
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
            //Mensaje que llega:
             String mensajeDelServer = (String)ois.readObject();
            
             client.close();
             
             return mensajeDelServer;
            
            
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
