<%-- 
    Document   : index
    Created on : Dec 27, 2017, 1:49:18 PM
    Author     : oswal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tutoriales</title>
    </head>
    <body align="center">
        <h1>Pagina vendedor</h1>
        
        <div>
            <form action="login" method="post">
            <table align="center">
                <tr>
                    <th align="right">UserName:</th>
                    <td><input type="text" name="user" placeholder="Username"></td>
                </tr>
                 <tr>
                    <th align="right">Password:</th>
                    <td><input type="password" name="pass" placeholder="Password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Log In" class=""></td>
                </tr>
              
                </table>
            </form>
            
        <a href="signUp.jsp" align="center">
            <td colspan="2" >
                <input type="submit" value="Register" class="">
            </td>
        </a>
        <a href="olvidoContrasena.jsp" align="center">
            <td colspan="2" >
                <input type="submit" value="Olvido contrasena" class="">
            </td>
        </a>
                
            
            
        </div>
        
               
        
    </body>
</html>
