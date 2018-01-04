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
        new HiloPrincipalServidor().start();
        //SI SE NECESITA CREAR UNA CUENTA BANCARIA SE USA LO SIGUIENTE
        /*String numeroCuenta = "4532314510308244";
        Long cuentahash = new Long(PruebaBancoCliente.toHash(numeroCuenta));
        String mes = "10";
        String meshash = PruebaBancoCliente.toHash(mes).toString();
        String ano = "2021";
        String anohash = PruebaBancoCliente.toHash(ano).toString();
        String numSeguridad = "218";
        Integer numSeguridadhash = PruebaBancoCliente.toHash(numSeguridad);
        DAOCliente dao = new DAOCliente();
        Cliente cliente = new Cliente("oswaldo","lopez",25253393,"oswaldo7365@hotmail.com",cuentahash,400000L,meshash,anohash,numSeguridadhash);
        dao.registrarCliente(cliente);
        *///HiloProcesaServidor.enviarABancoVendedor("25253393;4532314510308244;10;21;218;oswaldo;lopez;350000");
    }
    
    private static Integer toHash(String clave){
        Integer hash = 512;
        hash =  37*hash + clave.hashCode();
        return hash;
    }
    
}
