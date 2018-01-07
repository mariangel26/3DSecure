/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factura;

import Registro.Registro;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author oswalm
 */
public class Factura {
    
    public static void generar(String nombreApellido,String cedula,String nombreProducto, String cantidad, 
            String precioDetal,String precioTotal) throws IOException{
        String fechaActual = Factura.obtenerFecha();
        String ruta = Registro.UBICACION_ARCHIVO_FACTURAS+fechaActual+".txt";//el nombre de la factura tendra fecha y hora
        File archivo = new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("Factura N. xxxx");
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write( "     SO AWESOME RIF XXXXXXXX      \n"
                    + "__________________________________\n"
                    + "           FACTURA N. XXX         \n"
                    + "CLIENTE: "+nombreApellido+"        \n"
                    + "CEDULA: "+cedula+"                  \n"
                    + "__________________________________\n"
                    + cantidad +"  " +nombreProducto +"           "+ precioDetal+"\n"
                    + "__________________________________\n"
                    + "                              "+precioTotal);
        }
        bw.close();
    }
    
    public static String obtenerFecha(){
        String fechaActual = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmmss");
        fechaActual = sdf.format(cal.getTime());
        return fechaActual;
    }
}
