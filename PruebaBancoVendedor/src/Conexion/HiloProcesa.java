/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import DAO.DAOVendedor;
import Modelo.Vendedor;
import Registro.Registro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                clientSocket.close();
                String[] split = mensaje.split(";");
                if(split[0].equals("ACEPTADO")){
                    actualizarDineroVendedor(Long.parseLong(split[1]));
                }
                //HiloProcesa.enviarAeCommerce(split[0]);
            
                
        } catch (Exception ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ESTOY AQUI POR ALGUN EXTRANO MOTIVO QUE ES POCO PROBABLE");
        }
    }
    
    public static void enviarAeCommerce(String mensaje){
        
        try {
            System.setProperty("javax.net.ssl.trustStore", "ecommerce.store");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket client;
            client = clientFactory.createSocket("localhost", Registro.PUERTO_CONEXION_CLIENTE);
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            salidaObjeto.writeObject(mensaje + " HOLA HOLA SOY EL BANCO DEL VENDEDOR");
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            System.out.println("ME QUEDE PEGADO EN ESTA EXCEPCION WE");
        }
        
    }
    /**
     * Metodo que se encarga de  actualizar el dinero acumulado del vendedor
     * en el caso de que se haya 
     * @param dinero dinero recibido 
     */
    public static void actualizarDineroVendedor(Long dinero){
        System.out.println("VOY A EDITAR A BANCO VENDEDOR");
        DAOVendedor dao = new DAOVendedor();
        Vendedor vendedor = dao.buscarId(1);
        vendedor.setDineroDepositado(vendedor.getDineroDepositado() + dinero);
        dao.actualizarVendedor(vendedor);
    }
    
    
}
