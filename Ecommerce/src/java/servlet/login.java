/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ConexionServidor.HiloPrincipalServidor;
import DAO.DAOCliente;
import Factura.Factura;
import Modelo.Cliente;
import Registro.Registro;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        /*
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
           
        */
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        /*
        //Consultas con = new Consultas();
        if((user != "") && (pass != "")){
            
            Integer hash = toHash(pass);
            DAOCliente DAO = new DAOCliente();
            Cliente cliente = DAO.buscarCuenta(user);
        
            if(cliente == null){
                System.out.println("EL CLIENTE NO ES NULL");
                
                out.println("EL USUARIO NO EXISTE");
                response.sendRedirect("index.jsp");

            }else if(cliente != null && cliente.getIntentos() < 3){
                System.out.println("EL CLIENTE NO ES NULL Y SUS INTENTOS SON MENORES A 3");
                if(cliente.getContrasena().equals(hash.toString())){
                    System.out.println("LAS CLAVES COINCIDEN");
                    response.sendRedirect("welcome.jsp");
                }else{
                    System.out.println("LAS CLAVES NO COINCIDEN");
                    cliente.setIntentos(cliente.getIntentos()+1);
                    DAO.actualizarCliente(cliente);
                    response.sendRedirect("index.jsp");
                }
            }else if (cliente != null && cliente.getIntentos() >= 3){
                System.out.println("EL CLIENTE NO ES NULL PERO LOS INTENTOS SON MAAYORES A 3");
                response.sendRedirect("index.jsp");
            }
        }*/
        if(intentosMenorA3(user,response)){
            if(capchaCorrecto(request) && clienteNoEsNull(user)  && claveIgual(user,pass)){
                //se reincia la cantidad de intentos del usuario
                DAOCliente DAO = new DAOCliente();
                Cliente cliente = DAO.buscarCuenta(user);
                cliente.setIntentos(0);
                DAO.actualizarCliente(cliente);
                response.sendRedirect("welcome.jsp");
            }else{
                response.sendRedirect("index.jsp");
            }
        }
        
        
        //new HiloPrincipalServidor(response).recibir("Oswal Lopez","25253393","Donas","3","350","1050");
        //response.sendRedirect("index.jsp");
        
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
    /**
     * metodo que se encarga de validar si la clave es igual a la ingresada por el usuario.
     * @param user nombre de usuario ingresado por el usuario
     * @param clave clave ingresada por el usuario
     * @return true si las claves coinciden, false en caso contrario
     */
    public Boolean claveIgual(String user,String clave){
        Boolean respuesta = true;
        DAOCliente DAO = new DAOCliente();
        Cliente cliente = DAO.buscarCuenta(user);
        if(cliente.getContrasena().equals(toHash(clave).toString())){
            respuesta = true;
        }else{
            System.out.println("El usuario ingreso una clave erronea!");
            cliente.setIntentos(cliente.getIntentos()+1);
            DAO.actualizarCliente(cliente);
            respuesta = false;
        }
        return respuesta;
    }
    /**
     * Metodo que se encarga de verificar si el usuario ya se ha equivocado en la clave
     * 3 veces 
     * @param user nombre de usuario ingresado por el usuario
     * @return true si el usuario ha intentado menos de 3 veces el inicio de sesion 
     */
    public Boolean intentosMenorA3(String user,HttpServletResponse response){
        Boolean respuesta = true;
        DAOCliente DAO = new DAOCliente();
        Cliente cliente = DAO.buscarCuenta(user);
        if(cliente.getIntentos() < 3){
            respuesta = true;
        }else{
            System.out.println("El usuario tiene la cuenta bloqueada");
            respuesta = false;
            try {
                response.sendRedirect("CuentaBloqueada.jsp");
            } catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
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
