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
        /*String nombreCliente, String apellidoCliente, 
            Integer cedulaCliente, String correoCliente, Long tarjetaCliente,
            Long dineroDisponible,String mesCaduciodad,String anoCaduciodad,Integer codigoSeguridad*/
        //new HiloPrincipalServidor().start();
        //DAOCliente dao = new DAOCliente();
        //Cliente cliente = new Cliente("oswaldo","lopez",25253393,"oswaldo7365@hotmail.com",4532314510308244L,400000L,"10","21",218);
        //dao.registrarCliente(cliente);
        //HiloProcesaServidor.enviarABancoVendedor("SI");
        //HiloProcesaServidor.enviarABancoVendedor("NO");
        HiloProcesaServidor.tieneDinero("");
    }
    
}
