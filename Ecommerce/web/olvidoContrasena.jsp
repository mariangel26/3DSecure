<%-- 
    Document   : olvidoContrasena
    Created on : Dec 27, 2017, 6:09:53 PM
    Author     : oswal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <div>
            <form action="olvidoContrasena" method="post">
            <table align="center">
                <tr>
                    <th align="right">UserName:</th>
                    <td><input type="text" name="user" placeholder="Username"></td>
                </tr>
                 <tr>
                    <th align="right">Pregunta:</th>
                    <td><input type="text" name="pregunta" placeholder="Pregunta"></td>
                </tr>
                 <tr>
                    <th align="right">Respuesta:</th>
                    <td><input type="text" name="respuesta" placeholder="Respuesta"></td>
                </tr>
                <tr>
                    <th align="right">Contrasena</th>
                    <td><input type="password" name="contrasena" placeholder="Contrasena"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Log In" class=""></td>
                </tr>
              
                </table>
            </form>
        </div>
    </body>
</html>
