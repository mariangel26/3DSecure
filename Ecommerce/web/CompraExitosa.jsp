<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
     
    <title>COMPRA EXITOSA</title>
       <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <form class='login-form-datos' action="welcome.jsp" method="post">
        <center><h1 class="big-text">LA COMPRA SE REALIZO CON EXITO</h1></center>
        <input type="hidden" name="user" value="<%= request.getAttribute("user") %>">
        <input class='lf--submit' type='submit' value='ESTA BIEN' name="ok">
        
  
</form>

    
</html>