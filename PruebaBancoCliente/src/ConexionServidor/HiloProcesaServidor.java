package ConexionServidor;

import DAO.DAOCliente;
import Modelo.Cliente;
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
               ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
               //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
               //Mensaje que llega:
                String mensaje = (String)ois.readObject();
                System.out.println("El cliente (PAGINA ECOMMERCE) envio: "+mensaje);
                
                
                /*
                    aqui deberia RECIBIR la informacion para verificar si los datos son correctos:
                    si los datos son incorrectos se le debe responder a la pagina que son incorrectos xd
                    si son los datos correctos se envia el mensaje al servidor
                */
                    ObjectOutputStream salidaObjeto;      
                        //Se colocan los datos del nodo (Direccion IP y Puerto).
                        
                salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream());
                String datos = mensaje;
                System.out.println("voy a procesar los datos");
                    if(datosCorrectos(mensaje)){
                        
                        
                        String respuestaCliente = HiloProcesaServidor.confirmarCliente(mensaje.split(";")[8]);
                        System.out.println("Esto esta dentro del if de datos correctos");
                            salidaObjeto.writeObject(respuestaCliente);
                            
                           // ois = new ObjectInputStream(clientSocket.getInputStream());
                            mensaje = (String)ois.readObject();
                            //creo que aqui iria si el banco vendedor envia correcto
                            if(mensaje.equals("ACEPTADO")){
                                HiloProcesaServidor.enviarABancoVendedor(datos);
                            }
                            
                         /*   
                        }else{
                            salidaObjeto.writeObject("RECHAZADO");
                        }*/
                    }else{
                        salidaObjeto.writeObject("RECHAZADO");
                    }   
                    
                
                
                
                /*
                    verificar si tiene el monto disponible:
                    en caso de que el monto disponible sea mayor al monto solicitado le envia al banco vendedor
                    que proceda la transaccion
                    sino es mayor le dice al banco del vendedor que no se puede efectuar la comunicacion
                */
                
                clientSocket.close();
            
        } catch (Exception ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void enviarABancoVendedor(String mensaje) throws NoSuchAlgorithmException, KeyStoreException,
            CertificateException, UnrecoverableKeyException, KeyManagementException{
        
        try {
            String[] split = mensaje.split(";");
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
            if(HiloProcesaServidor.tieneDinero(mensaje)){
                salidaObjeto.writeObject("ACEPTADO;"+split[7]);
            }else{
                salidaObjeto.writeObject("RECHAZADO;"+split[7]);
            }
            salidaObjeto.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String confirmarCliente(String mensaje) throws NoSuchAlgorithmException, KeyStoreException,
            CertificateException, UnrecoverableKeyException, KeyManagementException{
        String respuesta = "";
        try {
            
            String[] split = mensaje.split(";");
            System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE_CLIENTE);
            
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
            client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE_CLIENTE);
            
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            salidaObjeto.writeObject(mensaje);
            
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            //ObjectOutputStream salidaObjeto = new ObjectOutputStream(clientSocket.getOutputStream()); 
            //Mensaje que llega:
            respuesta = (String) ois.readObject();
            //System.out.println("La respuesta que llego es: "+ respuesta);
            salidaObjeto.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Metodo que se encarga de verificar los datos del cliente que la pagina 
     * envio al banco Cliente
     * @param mensaje Texto que posee la informacion bancaria del cliente
     * @return true si los datos son correctos y false si hay algun error en los datos
     */
    public static Boolean datosCorrectos(String mensaje){
        Boolean correcto = true;
        String[] split = mensaje.split(";");
        DAOCliente dao = new DAOCliente();
        Cliente cliente = dao.buscarCedula(Integer.parseInt(split[0]));
      if(cliente != null) {
        if((cliente.getCedulaCliente() == Integer.parseInt(split[0])) &&
             (cliente.getTarjetaCliente() == Long.parseLong(split[1])) &&
             (cliente.getMesCaduciodad().equals(split[2])) &&
             (cliente.getAnoCaduciodad().equals(split[3])) &&
             (cliente.getCodigoSeguridad() == Integer.parseInt(split[4])) &&
             (cliente.getNombreCliente().equals(split[5])) &&
             (cliente.getApellidoCliente().equals(split[6]))){
            System.out.println((cliente.getCedulaCliente() == Integer.parseInt(split[0])) +" "+
             (cliente.getTarjetaCliente() == Long.parseLong(split[1])) +" "+
             (cliente.getMesCaduciodad().equals(split[2])) +" "+
             (cliente.getAnoCaduciodad().equals(split[3])) +" "+
             (cliente.getCodigoSeguridad() == Integer.parseInt(split[4])) +" "+
             (cliente.getNombreCliente().equals(split[5])) +" "+
             (cliente.getApellidoCliente().equals(split[6])));
            correcto = true;
          }else{
            System.out.println((cliente.getCedulaCliente() == Integer.parseInt(split[0])) +" "+
             (cliente.getTarjetaCliente() == Long.parseLong(split[1])) +" "+
             (cliente.getMesCaduciodad().equals(split[2])) +" "+
             (cliente.getAnoCaduciodad().equals(split[3])) +" "+
             (cliente.getCodigoSeguridad() == Integer.parseInt(split[4])) +" "+
             (cliente.getNombreCliente().equals(split[5])) +" "+
             (cliente.getApellidoCliente().equals(split[6])));
            correcto = false;
          }
      }else{
          correcto = false;
      }
        
        System.out.println("voy a retornar correcto "+correcto);
        return correcto;
    }
    /**
     * metodo que se encarga de verificar si la persona posee el dinero solicitado
     * @return 
     */
    //"25253393;4532314510308244;10;21;218;oswaldo;lopez;350000"
    public static Boolean tieneDinero(String mensaje){
        Boolean correcto = true;
        String[] split = mensaje.split(";");
        DAOCliente dao = new DAOCliente();
            Cliente cliente = dao.buscarCedula(Integer.parseInt(split[0]));
            if(cliente.getDineroDisponible() <= Long.parseLong(split[7])){
                correcto = false;
            }else{
                cliente.setDineroDisponible(cliente.getDineroDisponible()-Long.parseLong(split[7]));
                dao.actualizarCliente(cliente);
                correcto = true;
            }
        return correcto;
    }
    
    
}
