/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ConexionCliente.EnvioBancoCliente;
import ConexionServidor.HiloPrincipalServidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 *
 * @author maria
 */
@WebServlet(name = "verificarCompra", urlPatterns = {"/verificarCompra"})
public class verificarCompra extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String cedula = request.getParameter("cedula");
            String tarjeta = request.getParameter("tarjeta");
            String nseguridad = request.getParameter("nseguridad");
            String mes = request.getParameter("mes");
            String year = request.getParameter("year");
            String nombreProducto = request.getParameter("nombreP");
            String precio = request.getParameter("precio");
            String auxiliarPrecio = request.getParameter("auxiliar");
            String cantidad = request.getParameter("cantidad");
            String pregunta = request.getParameter("pregunta");
             //SE VERIFICA EL CAPTCHA
            String remoteAddr = request.getRemoteAddr();
            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey("6LcNwj4UAAAAAAb5k0Ynq0N4b7KI56LNl5kcrmj1");

            String challenge = request.getParameter("recaptcha_challenge_field");
            String uresponse = request.getParameter("recaptcha_response_field");
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
            
            if ((reCaptchaResponse.isValid()) && (!"".equals(pregunta))){
                
              EnvioBancoCliente evc = new EnvioBancoCliente();
                
               String mensaje = cedula+";"+toHash(tarjeta)+";"+toHash(mes)+";"+toHash(year)+";"
                      +toHash(nseguridad)+";"+nombre+";"
                              +apellido+";"+precio+";";
               
                System.out.println("mensaje "+mensaje);
 
              //ENVIO DATOS AL BANCO CLIENTE
               String mensajeDelServer = evc.enviarABancoCliente(mensaje);
               
               
               if(mensajeDelServer.equals("ACEPTADO")){
                   //DESCOMENTAR PARA PROBAR CONEXION COMPLETA
                   new HiloPrincipalServidor(response).recibir(nombre+" "+apellido,
                           cedula,nombreProducto,cantidad,auxiliarPrecio,
                           precio);
               }else{
                   request.setAttribute("precio", auxiliarPrecio);
                   request.setAttribute("nombreP", nombreProducto);
                   request.getRequestDispatcher("datosIncorrectos.jsp").forward(request, response);
               }
            
            }else{
                System.out.println("me equivoque");
                response.sendRedirect("verificarCompra.jsp");
            }
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(verificarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(verificarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(verificarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(verificarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(verificarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(verificarCompra.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
