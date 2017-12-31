/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabancovendedor;

import Conexion.HiloPrincipal;
import Conexion.HiloProcesa;
import DAO.DAOVendedor;
import Modelo.Vendedor;
import Registro.Registro;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author oswalm
 */
public class PruebaBancoVendedor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //new HiloPrincipal().start();
        HiloProcesa.enviarAeCommerce("ACEPTADO");
        //en el caso que se desee registrar a un vendedor
        //Vendedor vendedor = new Vendedor(2,0L);
        //DAOVendedor dao = new DAOVendedor();
        //dao.registrarVendedor(vendedor);
        
    }
    
}
