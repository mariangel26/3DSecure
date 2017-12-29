package pruebabancocliente;

import ConexionServidor.HiloPrincipalServidor;
import ConexionServidor.HiloProcesaServidor;
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
        
        //new HiloPrincipalServidor().start();
        
        HiloProcesaServidor.enviarABancoVendedor("");
    }
    
}
