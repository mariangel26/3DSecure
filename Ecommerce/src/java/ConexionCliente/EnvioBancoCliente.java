package ConexionCliente;

import ConexionServidor.HiloProcesaServidor;
import Registro.Registro;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * Para la conexion nos guiamos de las siguientes paginas web
 * https://www.programcreek.com/java-api-examples/javax.net.ssl.SSLServerSocket
 * @author mariangel
 */
public class EnvioBancoCliente {
    
    public EnvioBancoCliente(){}
    
    public String enviarABancoCliente(String mensaje) throws ClassNotFoundException, NoSuchAlgorithmException,
           KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException{
        
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
