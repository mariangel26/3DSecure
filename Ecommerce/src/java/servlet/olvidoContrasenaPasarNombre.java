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

/**
 *
 * @author oswalm
 */
@WebServlet(name = "olvidoContrasenaPasarNombre", urlPatterns = {"/olvidoContrasenaPasarNombre"})
public class olvidoContrasenaPasarNombre extends HttpServlet {

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
        String user = request.getParameter("user");
        //System.out.println("User es : "+user);
        if(clienteNoEsNull(user)){
            request.setAttribute("nombreUsuario", user);
            request.getRequestDispatcher("olvidoContrasena.jsp").forward(request, response);
            response.sendRedirect("olvidoContrasena.jsp");
        }else {
            System.out.println("El usuario no existe!");
            response.sendRedirect("olvidarContrasenaIngresarNombre.jsp");
        }
    }
    
    /**
     * metodo que se encarga de validar si el usuario existe o no.
     * @param user nombre de usuario ingresado por el usuario
     * @return true si el usuario existe, false en caso contrario
     */
    public Boolean clienteNoEsNull(String user){
        Boolean respuesta = true;
        DAOCliente DAO = new DAOCliente();
        Cliente cliente = DAO.buscarCuenta(user);
        if(cliente == null){
            System.out.println("El usuario no existe!");
            respuesta = false;
        }else{
            respuesta = true;
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
