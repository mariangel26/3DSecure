/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOCliente;
import Modelo.Cliente;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oswal
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        //Consultas con = new Consultas();
        if((user != "") && (pass != "")){
            
            Integer hash = toHash(pass);
            DAOCliente DAO = new DAOCliente();
            Cliente cliente = DAO.buscarCuenta(user);
        
            if(cliente == null){
                
                out.println("EL USUARIO NO EXISTE");
                response.sendRedirect("index.jsp");

            }else if(cliente != null && cliente.getIntentos() < 3){
                if(cliente.getContrasena().equals(hash.toString())){
                    response.sendRedirect("welcome.jsp");
                }else{
                    cliente.setIntentos(cliente.getIntentos()+1);
                    DAO.actualizarCliente(cliente);
                    response.sendRedirect("index.jsp");
                }
            }else if (cliente != null && cliente.getIntentos() >= 3){
                response.sendRedirect("index.jsp");
            }
        }
    }
    
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
