/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

/**
 *
 * @author oswalm
 */
public class Registro {
    //DATOS PARA EL XML
        public static final String UBICACION_XML_VENDEDOR = "src/XML/Vendedor.xml";
        //public static final String UBICACION_XML_VENDEDOR = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\PruebaBancoVendedor\\src\\XML\\Vendedor.xml";
        //public static final String UBICACION_XML_VENDEDOR = "/home/oswalm/Documentos/repositorios/3DSecure/PruebaBancoVendedor/src/XML/Vendedor.xml";
        //public static final String UBICACION_XML_VENDEDOR = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\PruebaBancoVendedor\\src\\XML\\Vendedor.xml";

    
    //DATOS PARA SOCKET
    public static final Integer PUERTO_CONEXION_SERVIDOR = 4000;
    
    public static final Integer PUERTO_CONEXION_CLIENTE = 5000;
    public static final String IP_CONEXION = "192.168.1.9";
    //DATOS PARA SSL
    public static final String KEY_STORE_SERVIDOR = "bancovendedor.store";    

    public static final String KEY_STORE_PASSWORD = "123456";
    public static final String TRUST_STORE_CLIENTE = "ecommerce.store";
    public static final String TRUST_STORE_PASSWORD = "123456";
}
