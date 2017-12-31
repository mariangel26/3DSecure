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
import java.io.ObjectInputStream;
import java.net.Socket;
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
            /* TODO output your page here. You may use following sample code. */
             String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String tarjeta = request.getParameter("tarjeta");
        String nseguridad = request.getParameter("nseguridad");
        String fecha = request.getParameter("year_week");
        String cantidad = request.getParameter("cantidad");
        String auxiliarPrecio = request.getParameter("precio");
        Integer precio = Integer.parseInt(auxiliarPrecio);
        Integer auxiliarCantidad = Integer.parseInt(cantidad);
        
        //MULTIPLICACION DE LA CANTIDAD
            precio = (auxiliarCantidad * precio);
        
        //SE VERIFICA EL CAPTCHA
            String remoteAddr = request.getRemoteAddr();
            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey("6LcNwj4UAAAAAAb5k0Ynq0N4b7KI56LNl5kcrmj1");
            
            String challenge = request.getParameter("recaptcha_challenge_field");
            String uresponse = request.getParameter("recaptcha_response_field");
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
            
            if((cantidad!="")&&(nombre != "")&&(apellido != "")&&(cedula != "")&&
                (tarjeta != "")&&(nseguridad != "")&&(fecha != "")){
            if((reCaptchaResponse.isValid())){
                
                EnvioBancoCliente evc = new EnvioBancoCliente();
                String mes, year;
                String[] split = fecha.split("-");
                mes = split[1];
                year = split [0];
                String mensaje = cedula+";"+tarjeta+";"+mes+";"+year+";"
                      +nseguridad+";"+nombre+";"+apellido+";"+precio.toString()+";";
                
               //ENVIO DATOS AL BANCO CLIENTE
               String mensajeDelServer = evc.enviarABancoCliente(mensaje);
               
               
               if(mensajeDelServer.equals("ACEPTADO")){
                   response.sendRedirect("welcome.jsp");
                   
               }else{
                   request.setAttribute("precio", auxiliarPrecio);
                   request.getRequestDispatcher("datosIncorrectos.jsp").forward(request, response);
 
                   response.sendRedirect("datosIncorrectos.jsp");
               }
                   
                
               
               
                
            }else{
                response.sendRedirect("Compra.jsp");
            }
            
        }else{
                response.sendRedirect("Compra.jsp");
            }
        }
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
