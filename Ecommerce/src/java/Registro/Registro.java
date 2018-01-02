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
    //public static final String UBICACION_ARCHIVO_XML_CLIENTE = "/home/oswalm/Documentos/repositorios/3DSecure/Ecommerce/src/java/XML/Cliente.xml";
    //public static final String UBICACION_ARCHIVO_XML_PRODUCTO = "/home/oswalm/Documentos/repositorios/3DSecure/Ecommerce/src/java/XML/Producto.xml";
    public static final String UBICACION_ARCHIVO_XML_CLIENTE = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\Ecommerce\\src\\java\\XML\\Cliente.xml";
    public static final String UBICACION_ARCHIVO_XML_PRODUCTO= "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\Ecommerce\\src\\java\\XML\\Producto.xml";
    
    //DATOS PARA LOS TXT DE LA FACTURA
    public static final String UBICACION_ARCHIVO_FACTURAS = "/home/oswalm/Documentos/repositorios/3DSecure/Ecommerce/Facturas/";
    

    //DATOS PARA SOCKET
    public static final Integer PUERTO_CONEXION_CLIENTE = 3000;
    public static final Integer PUERTO_CONEXION_SERVIDOR = 5000;
    
    public static final String IP_CONEXION = "localhost";
    //DATOS PARA SSL
    //public static final String KEY_STORE_SERVIDOR = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\Ecommerce\\ecommerce.store";    
    public static final String KEY_STORE_SERVIDOR = "/home/oswalm/Documentos/repositorios/3DSecure/Ecommerce/ecommerce.store";    

    public static final String KEY_STORE_PASSWORD = "123456";
    //public static final String TRUST_STORE_CLIENTE = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\Ecommerce\\bancocliente.store";
    public static final String TRUST_STORE_CLIENTE = "/home/oswalm/Documentos/repositorios/3DSecure/Ecommerce/bancocliente.store";
    public static final String TRUST_STORE_PASSWORD = "123456";
}
