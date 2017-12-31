/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionServidor;

import DAO.DAOCliente;
import Modelo.Cliente;
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
                    if(datosCorrectos(mensaje)){
                       
                        salidaObjeto.writeObject("ACEPTADO");
                        
                        //envio
                       // HiloProcesaServidor.enviarABancoVendedor("25253393;4532314510308244;10;21;218;oswaldo;lopez;350000");
                    }else{
                        System.out.println("ENTRE EN EL ELSE WE");
                        salidaObjeto.writeObject("RECHAZADO");
                        //LOGICA PARA RESPONDERLE A LA PAGINA QUE LOS DATOS SON INCORRECTOS
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
    
    public static void enviarABancoVendedor(String mensaje){
        
        try {
            String[] split = mensaje.split(";");
            //System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
            System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket client;
            client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
            //client = clientFactory.createSocket("localhost", 4000);
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            if(HiloProcesaServidor.tieneDinero(mensaje)){
                salidaObjeto.writeObject("ACEPTADO;"+split[7]);
            }else{
                salidaObjeto.writeObject("RECHAZADO;"+split[7]);
            }
            
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        //"25253393;4532314510308244;10;21;218;oswaldo;lopez;350000"
      if(cliente != null) {
        if((cliente.getCedulaCliente() == Integer.parseInt(split[0])) &&
             (cliente.getTarjetaCliente() == Long.parseLong(split[1])) &&
             (cliente.getMesCaduciodad().equals(split[2])) &&
             (cliente.getAnoCaduciodad().equals(split[3])) &&
             (cliente.getCodigoSeguridad() == Integer.parseInt(split[4])) &&
             (cliente.getNombreCliente().equals(split[5]) &&
             (cliente.getApellidoCliente().equals(split[6])))){
            correcto = true;
          }else{
            correcto = false;
          }
      }else{
          correcto = false;
      }
        
        
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
