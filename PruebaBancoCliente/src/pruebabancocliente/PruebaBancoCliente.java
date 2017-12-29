package pruebabancocliente;

import ConexionServidor.HiloPrincipalServidor;
import DAO.DAOCliente;
import Modelo.Cliente;
import Registro.Registro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author oswalm
 */
public class PruebaBancoCliente {

    public static void main(String[] args) throws IOException {
        
        new HiloPrincipalServidor().start();
        //todo este codigo se debe mover al hiloProcesa que se creara despues.
        /*
        System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
        SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
        ObjectOutputStream salidaObjeto;      
        //Se colocan los datos del nodo (Direccion IP y Puerto).
        salidaObjeto = new ObjectOutputStream(client.getOutputStream());
        //El cliente manda:
        DAOCliente dao = new DAOCliente();
        //Cliente cliente = new Cliente("oswaldo","Lopez",25253393,"oswaldo7365@hotmail.com",123456789L,400000L);
        //dao.registrarCliente(cliente);
        Cliente cliente = dao.buscarCedula(25253393);
        
        if(cliente.getDineroDisponible() <= 200000){
            salidaObjeto.writeObject("NO");
        }else{
            cliente.setDineroDisponible(cliente.getDineroDisponible()-200000);
            dao.actualizarCliente(cliente);
            salidaObjeto.writeObject("SI");
        }
        */
        
        
        
    }
    
}
