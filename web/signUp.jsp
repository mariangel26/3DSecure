<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Form</title>
    </head>
    <body>
        <center><h1>Sign Up</h1></center>
        
        <form action="singUp" method="post">
            <table align="center">
                <tr>
                    <th align="right">Nombre:</th>
                    <td><input type="text" name="txtnombre" placeholder="Nombre"></td>
                </tr>
                 <tr>
                    <th align="right">Apellido:</th>
                    <td><input type="text" name="txtapellido" placeholder="Apellido"></td>
                </tr>
                 <tr>
                    <th align="right">Correo:</th>
                    <td><input type="text" name="txtcorreo" placeholder="Correo"></td>
                </tr>
                <tr>
                    <th align="right">Pregunta secreta:</th>
                    <td><input type="text" name="txtpregunta" placeholder="Pregunta secreta"></td>
                </tr>
                <tr>
                    <th align="right">Respuesta secreta:</th>
                    <td><input type="text" name="txtrespuesta" placeholder="Respuesta secreta"></td>
                </tr>
                <tr>
                    <th align="right">UserName:</th>
                    <td><input type="text" name="txtusername" placeholder="Username"></td>
                </tr>
                 <tr>
                    <th align="right">Password:</th>
                    <td><input type="password" name="txtpassword" placeholder="Password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" class=""></td>
                </tr>
            </table>
        </form>
    </body>
</html>

