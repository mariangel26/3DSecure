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
                clientSocket.close();
                enviarABancoVendedor(mensaje);
            
        } catch (Exception ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void enviarABancoVendedor(String mensaje){
        
        try {
            //System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
            System.setProperty("javax.net.ssl.trustStore", "server.store");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket client;
            //client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
            client = clientFactory.createSocket("localhost", 4000);
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            //El cliente manda:
            DAOCliente dao = new DAOCliente();
            //Cliente cliente = new Cliente("oswaldo","Lopez",25253393,"oswaldo7365@hotmail.com",123456789L,400000L);
            //dao.registrarCliente(cliente);
            /*
            if(verificarDatos()){
                
            }*/
            Cliente cliente = dao.buscarCedula(25253393);

            if(cliente.getDineroDisponible() <= 200000){
                salidaObjeto.writeObject(mensaje+ " NO HOLA SOY EL BANCO DEL CLIENTE");
            }else{
                cliente.setDineroDisponible(cliente.getDineroDisponible()-200000);
                dao.actualizarCliente(cliente);
                salidaObjeto.writeObject(mensaje + " SI HOLA SOY EL BANCO DEL CLIENTE");
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo que se encarga de verificar los datos del cliente que la pagina 
     * envio al banco Cliente
     * @return true si los datos son correctos y false si hay algun error en los datos
     */
    public Boolean verificarDatos(){
        Boolean correcto = false;
        
        return correcto;
    }
    
    
}
