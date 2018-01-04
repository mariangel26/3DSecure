<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<!DOCTYPE html>
<html>
     
    <title>DATOS INCORRECTOS</title>
       <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <form class='login-form-datos' action="Compra.jsp" method="post">
        <center><h1 class="big-text">TUS DATOS FUERON INCORRECTOS</h1></center>
        <input type="hidden" name="precio" value="<%= request.getAttribute("precio") %>">
        <input type="hidden" name="nombreP" value="<%= request.getAttribute("nombreP") %>">
        <input type="hidden" name="user" value="<%= request.getAttribute("user") %>">
        <input class='lf--submit' type='submit' value='ESTA BIEN' name="ok">
  
</form>

    
</html>

