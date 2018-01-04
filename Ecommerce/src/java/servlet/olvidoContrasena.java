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
 * @author oswal
 */
@WebServlet(name = "olvidoContrasena", urlPatterns = {"/olvidoContrasena"})
public class olvidoContrasena extends HttpServlet {

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
        String user; 
            user = request.getParameter("usuario");
            System.out.println("el user en java es "+user);
            String respuesta = request.getParameter("respuesta");
            String contrasena = request.getParameter("contrasena");
           
        
        if(contrasena != "" && respuesta != "" ){
            if(verificarRespuesta(user, respuesta, response)){
                if(capchaCorrecto(request) && verificarContrasena(contrasena)){
                    Integer hash = toHash(contrasena);
                    DAOCliente dao = new DAOCliente(); 
                    Cliente cliente = dao.buscarCuenta(user);
                    cliente.setIntentos(0);
                    cliente.setContrasena(hash.toString());
                    dao.actualizarCliente(cliente);
                    response.sendRedirect("index.jsp");
                }else{
                    response.sendRedirect("olvidarContrasenaIngresarNombre.jsp");
                }
            }
            
        }else{
           response.sendRedirect("olvidarContrasenaIngresarNombre.jsp");

        }
        
    }
    
    /**
     * metodo que se encarga de convertir a hash la contrasena
     * @param clave clave a convertir
     * @return la clave convertida
     */
    private Integer toHash(String clave){
        Integer hash = 512;
        hash =  37*hash + clave.hashCode();
        return hash;
    }
    /**
     * Metodo que verifica si la contrasena tiene una longitud mayor a 10,
     * posee un caracter alfanumerico y si posee alguna letra mayuscula
     * @param password contrasena a verificar
     * @return boolean
     */
    private boolean verificarContrasena (String password){
        boolean respuesta = true;
        if((password.length() < 10)) {
            System.out.println("LA LONIGTUD DE LA CONTRASENA ES MENOR A 10");
            respuesta = false;
        }
        if(!password.contains("*") && !password.contains("!") && 
                !password.contains("@") && !password.contains("#") && 
                !password.contains("$") && !password.contains("%") && 
                !password.contains("^") && !password.contains("(") && 
                !password.contains(")") && !password.contains("-") && 
                !password.contains("_") && !password.contains("=") && 
                !password.contains("+") && !password.contains("|") && 
                !password.contains(".") && !password.contains(",") ){
            System.out.println("LA CONTRASENA NO POSEE CARACTER ALFANUMERICO");
            respuesta = false;
        }
        if(     !password.contains("A") && !password.contains("B") && 
                !password.contains("C") && !password.contains("D") && 
                !password.contains("E") && !password.contains("F") && 
                !password.contains("G") && !password.contains("H") && 
                !password.contains("I") && !password.contains("J") && 
                !password.contains("K") && !password.contains("L") && 
                !password.contains("M") && !password.contains("N") &&
                !password.contains("O") && !password.contains("P") && 
                !password.contains("Q") && !password.contains("T") && 
                !password.contains("R") && !password.contains("S") && 
                !password.contains("T") && !password.contains("U") && 
                !password.contains("V") && !password.contains("W") && 
                !password.contains("X") && !password.contains("Y") && 
                !password.contains("Z") ){
            System.out.println("LA CONTRASENA NO POSEE LETRA MAYUSCULA");
            respuesta = false;
        }
        
        return respuesta;
    }
    
    /**
     * metodo que se encarga de verificar si el capcha ingresado por el usuario 
     * es correcto.
     * @param request
     * @return true si coincide, false en caso contrario.
     */
    public Boolean capchaCorrecto(HttpServletRequest request){
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
    
    public boolean verificarRespuesta(String usuario,String respuestaSecreta, HttpServletResponse response){
        boolean respuesta = true;
        if(respuestaSecreta.equals(new DAOCliente().buscarCuenta(usuario).getRespuestaSecreta())){
            respuesta = true;
        }else{
            System.out.println("La respuesta no coinciden!");
            respuesta = false;
            try {
                response.sendRedirect("respuestasNoCoinciden.jsp");
            } catch (IOException ex) {
                Logger.getLogger(olvidoContrasena.class.getName()).log(Level.SEVERE, null, ex);
            }
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
