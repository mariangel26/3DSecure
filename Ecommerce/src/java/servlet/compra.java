/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOCliente;
import Modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import ConexionCliente.*;
import ConexionServidor.HiloPrincipalServidor;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
@WebServlet(name = "compra", urlPatterns = {"/compra"})
public class compra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
         
             String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String tarjeta = request.getParameter("tarjeta");
        String nseguridad = request.getParameter("nseguridad");
        String fecha = request.getParameter("year_week");
        String cantidad = request.getParameter("cantidad");
        String auxiliarPrecio = request.getParameter("precio");
        String nombreProducto = request.getParameter("nombreP");
        String user = request.getParameter("user");
        Integer precio = Integer.parseInt(auxiliarPrecio);
        Integer auxiliarCantidad = Integer.parseInt(cantidad);
        
        
        //MULTIPLICACION DE LA CANTIDAD
            precio = (auxiliarCantidad * precio);
        

        if(( !"".equals(cantidad))&&(!"".equals(nombre))&&(!"".equals(apellido))&&(!"".equals(cedula))&&
            (!"".equals(tarjeta))&&(!"".equals(nseguridad))&&(!"".equals(fecha))){
            if(captchaCorrecto(request)){
                   
                String mes, year;
                String[] split = fecha.split("-");
                mes = split[1];
                year = split [0];
                System.out.println("user "+user);
                DAOCliente DAO = new DAOCliente();
                Cliente cliente = DAO.buscarCuenta(user);
                String pregunta = cliente.getPreguntaSecreta();
                String respuesta = cliente.getRespuestaSecreta();
            
               EnvioBancoCliente evc = new EnvioBancoCliente();
                
                    String mensaje = cedula+";"+toHash(tarjeta)+";"+toHash(mes)+";"+toHash(year)+";"
                      +toHash(nseguridad)+";"+nombre+";"+apellido+";"+precio.toString()+";"+pregunta+";";

                     System.out.println("mensaje "+mensaje);

                   //ENVIO DATOS AL BANCO CLIENTE
                    String mensajeDelServer = evc.enviarABancoCliente(mensaje, respuesta);

                    if(mensajeDelServer.equals("ACEPTADO")){
                        //DESCOMENTAR PARA PROBAR CONEXION COMPLETA
                        new HiloPrincipalServidor(response,request).recibir(nombre+" "+apellido,
                                cedula,nombreProducto,cantidad,auxiliarPrecio,
                                precio.toString(),user);
                    }else{
                        request.setAttribute("precio", auxiliarPrecio);
                        request.setAttribute("nombreP", nombreProducto);
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("datosIncorrectos.jsp").forward(request, response);
                    }              
                
            }else{
                System.out.println("El captcha no es correcto");
                response.sendRedirect("Compra.jsp");
            }
            
        }else{
                System.out.println("uno de los campos esta vacio");
                response.sendRedirect("Compra.jsp");
            }
           
    }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      /**
     * metodo que se encarga de convertir a hash la clave ingresada por el usuario.
     * @param clave clave a convertir.
     * @return la clave convertida.
     */
    private Integer toHash(String clave){
        Integer hash = 512;
        hash =  37*hash + clave.hashCode();
        return hash;
    }
    
    /**
     * metodo que se encarga de verificar si el capcha ingresado por el usuario 
     * es correcto.
     * @param request
     * @return true si coincide, false en caso contrario.
     */
     public Boolean captchaCorrecto(HttpServletRequest request){
        Boolean respuesta = true;
        String remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LcNwj4UAAAAAAb5k0Ynq0N4b7KI56LNl5kcrmj1");

        String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

       if (reCaptchaResponse.isValid()) {
           respuesta = true;
        } else {
           System.out.println("el capcha es incorrecto");
           respuesta = false;
        }
        return respuesta;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
