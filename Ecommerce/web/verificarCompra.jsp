<%-- 
    Document   : index
    Created on : Dec 27, 2017, 1:49:18 PM
    Author     : oswal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<!DOCTYPE html>
<html>
    <title>Verificacion</title>
       <link href="css/style.css" rel="stylesheet" type="text/css"/>
       <form class='login-form' action="verificarCompra" method="post">
  <div class="flex-row">
      <h2 id="pregunta" class='lf--input' name="pregunta">
          ¿<%= request.getAttribute("pregunta") %>?
      </h2>
  </div>
  <div class="flex-row">
    <label class="lf--label" for="password">
      <svg x="0px" y="0px" width="15px" height="5px">
        <g>
          <path fill="#B1B7C4" d="M6,2L6,2c0-1.1-1-2-2.1-2H2.1C1,0,0,0.9,0,2.1v0.8C0,4.1,1,5,2.1,5h1.7C5,5,6,4.1,6,2.9V3h5v1h1V3h1v2h1V3h1 V2H6z M5.1,2.9c0,0.7-0.6,1.2-1.3,1.2H2.1c-0.7,0-1.3-0.6-1.3-1.2V2.1c0-0.7,0.6-1.2,1.3-1.2h1.7c0.7,0,1.3,0.6,1.3,1.2V2.9z"/>
        </g>
      </svg>
    </label>
    <input id="respuesta" class='lf--input' placeholder='Respuesta Secreta' type='text' name="respuesta">   
  </div>
        
  <div>
    <center>
    <%
    ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LcNwj4UAAAAANMSYNw1JZRJrJ2hUzL3SiZWkBPd"
                                       ,"6LcNwj4UAAAAAAb5k0Ynq0N4b7KI56LNl5kcrmj1", false);
    out.print(c.createRecaptchaHtml(null, null));
     %>   
  </center> 
  <svg x="0px" y="0px" width="15px" height="5px"></svg>
  </div>
  
  <input class='lf--submit' type='submit' value='VALIDAR'> 
  <input type="hidden" name="nombre" value="<%= request.getAttribute("nombre") %>">
  <input type="hidden" name="apellido" value="<%= request.getAttribute("apellido") %>">
  <input type="hidden" name="cedula" value="<%= request.getAttribute("cedula") %>">
  <input type="hidden" name="tarjeta" value="<%= request.getAttribute("tarjeta") %>">
  <input type="hidden" name="mes" value="<%= request.getAttribute("mes") %>">
  <input type="hidden" name="year" value="<%= request.getAttribute("year") %>">
  <input type="hidden" name="nseguridad" value="<%= request.getAttribute("nseguridad") %>">
  <input type="hidden" name="precio" value="<%= request.getAttribute("precio") %>">
  <input type="hidden" name="nombreP" value="<%= request.getAttribute("nombreP") %>">
  <input type="hidden" name="auxiliar" value="<%= request.getAttribute("auxiliar") %>">
  <input type="hidden" name="cantidad" value="<%= request.getAttribute("cantidad") %>">
   <input type="hidden" name="user" value="<%= request.getAttribute("user") %>">
</form>
  
    
</html>
