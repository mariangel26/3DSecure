/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ConexionServidor.HiloPrincipalServidor;
import DAO.DAOCliente;
import Modelo.Cliente;
import Registro.Registro;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.out;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
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
        
        //SE VERIFICA EL CAPTCHA
            String remoteAddr = request.getRemoteAddr();
            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey("6LcNwj4UAAAAAAb5k0Ynq0N4b7KI56LNl5kcrmj1");
            
            String challenge = request.getParameter("recaptcha_challenge_field");
            String uresponse = request.getParameter("recaptcha_response_field");
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
            
           if (reCaptchaResponse.isValid()) {
               response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
           
        /*
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
        */
        
        /*Este codigo se debe de pasar ala parte donde el usuario ingrese sus datos personales de banco
        de la pagina del banco*/
        //new HiloPrincipalServidor().start();//prueba de creacion del servidor
        /*System.setProperty("javax.net.ssl.trustStore", Registro.TRUST_STORE_CLIENTE);
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket client;
            client = clientFactory.createSocket(Registro.IP_CONEXION, Registro.PUERTO_CONEXION_CLIENTE);
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(client.getOutputStream());
            salidaObjeto.writeObject("HOLA SOY LA PAGINA DE ECOMMERCE");
        */
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
