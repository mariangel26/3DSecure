<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Form</title>
    </head>
    <body>
        <center><h1>Sign Up</h1></center>
        
        <form action="singUp" method="post" onsubmit="return validar()">
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
                    <td><input type="password" id = "txtpassword" name="txtpassword" placeholder="Password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" class=""></td>
                </tr>
            </table>
        </form>
            <script>     
            function validar(){
                var password = document.getElementById("txtpassword").value.toString();
                var respuesta = true;
              if((password===document.getElementById("txtpassword").value)){ 
                  
                  if(!poseeMayusculas(password)){
                      alert("LA CONTRASENA DEBE TENER AL MENOS 1 LETRA MAYUSCULA");
                      respuesta = false;
                  }
                  if(!poseeNoalfanumericos(password)){
                      alert("LA CONTRASENA DEBE TENER AL MENOS 1 CARACTER ALFANUMERICO");
                      respuesta = false;
                  }
                  if(!esMayorA10(password)){
                      alert("LA CONTRASENA DEBE TENER AL MENOS 10 CARACTERES");
                      respuesta = false;
                  }
                  
              }else{
                  alert("Las contrasenas son distintas!");
                  return false;
              }
              return respuesta;
              
              
            }
            var letras="ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";
            function poseeMayusculas(texto){
               for(i=0; i<texto.length; i++){
                  if (letras.indexOf(texto.charAt(i),0)!=-1){
                     return true;
                  }
               }
               return false;
            }
            var noalfanumericos="|@#~ª¨^€¬=)(!*Ç{[]}-./&%$·,+<>";
            function poseeNoalfanumericos(texto){
               for(i=0; i<texto.length; i++){
                  if (noalfanumericos.indexOf(texto.charAt(i),0)!=-1){
                     return true;
                  }
               }
               return false;
            }
            
            function esMayorA10(texto){
                if(texto.length > 10){
                    return true;
                }
                return false;
            }

        </script>
    </body>
    
    
</html>

