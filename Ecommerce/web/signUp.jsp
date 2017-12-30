<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<!DOCTYPE html>
<html>
    
    <title>Registrarse</title>
 <link href="css/style.css" rel="stylesheet" type="text/css"/>
 <div id = "divForm">
     <form class='login-form' action="singUp" method="post" onsubmit="return validar()">


  <div class="flex-row">
    <label class="lf--label" for="Nombre">
      <svg x="0px" y="0px" width="12px" height="13px">
        <path fill="#B1B7C4" d="M8.9,7.2C9,6.9,9,6.7,9,6.5v-4C9,1.1,7.9,0,6.5,0h-1C4.1,0,3,1.1,3,2.5v4c0,0.2,0,0.4,0.1,0.7 C1.3,7.8,0,9.5,0,11.5V13h12v-1.5C12,9.5,10.7,7.8,8.9,7.2z M4,2.5C4,1.7,4.7,1,5.5,1h1C7.3,1,8,1.7,8,2.5v4c0,0.2,0,0.4-0.1,0.6 l0.1,0L7.9,7.3C7.6,7.8,7.1,8.2,6.5,8.2h-1c-0.6,0-1.1-0.4-1.4-0.9L4.1,7.1l0.1,0C4,6.9,4,6.7,4,6.5V2.5z M11,12H1v-0.5 c0-1.6,1-2.9,2.4-3.4c0.5,0.7,1.2,1.1,2.1,1.1h1c0.8,0,1.6-0.4,2.1-1.1C10,8.5,11,9.9,11,11.5V12z"/>
      </svg>
    </label>
    <input id="txtnombre" class='lf--input' placeholder='Nombre' type='text' name="txtnombre">
  </div>
        
   <div class="flex-row">
    <label class="lf--label" for="Apellido">
      <svg x="0px" y="0px" width="12px" height="13px">
        <path fill="#B1B7C4" d="M8.9,7.2C9,6.9,9,6.7,9,6.5v-4C9,1.1,7.9,0,6.5,0h-1C4.1,0,3,1.1,3,2.5v4c0,0.2,0,0.4,0.1,0.7 C1.3,7.8,0,9.5,0,11.5V13h12v-1.5C12,9.5,10.7,7.8,8.9,7.2z M4,2.5C4,1.7,4.7,1,5.5,1h1C7.3,1,8,1.7,8,2.5v4c0,0.2,0,0.4-0.1,0.6 l0.1,0L7.9,7.3C7.6,7.8,7.1,8.2,6.5,8.2h-1c-0.6,0-1.1-0.4-1.4-0.9L4.1,7.1l0.1,0C4,6.9,4,6.7,4,6.5V2.5z M11,12H1v-0.5 c0-1.6,1-2.9,2.4-3.4c0.5,0.7,1.2,1.1,2.1,1.1h1c0.8,0,1.6-0.4,2.1-1.1C10,8.5,11,9.9,11,11.5V12z"/>
      </svg>
    </label>
    <input id="txtapellido" class='lf--input' placeholder='Apellido' type='text' name="txtapellido">
  </div>
        
        
   <div class="flex-row">
    <label class="lf--label" for="Correo">
      <svg x="0px" y="0px" width="12px" height="13px">
        <path fill="#B1B7C4" d="M8.9,7.2C9,6.9,9,6.7,9,6.5v-4C9,1.1,7.9,0,6.5,0h-1C4.1,0,3,1.1,3,2.5v4c0,0.2,0,0.4,0.1,0.7 C1.3,7.8,0,9.5,0,11.5V13h12v-1.5C12,9.5,10.7,7.8,8.9,7.2z M4,2.5C4,1.7,4.7,1,5.5,1h1C7.3,1,8,1.7,8,2.5v4c0,0.2,0,0.4-0.1,0.6 l0.1,0L7.9,7.3C7.6,7.8,7.1,8.2,6.5,8.2h-1c-0.6,0-1.1-0.4-1.4-0.9L4.1,7.1l0.1,0C4,6.9,4,6.7,4,6.5V2.5z M11,12H1v-0.5 c0-1.6,1-2.9,2.4-3.4c0.5,0.7,1.2,1.1,2.1,1.1h1c0.8,0,1.6-0.4,2.1-1.1C10,8.5,11,9.9,11,11.5V12z"/>
      </svg>
    </label>
    <input id="txtcorreo" class='lf--input' placeholder='Correo Electronico' type='text' name="txtcorreo">
  </div>
        
        
   <div class="flex-row">
    <label class="lf--label" for="Pregunta">
      <svg x="0px" y="0px" width="12px" height="13px">
        <path fill="#B1B7C4" d="M8.9,7.2C9,6.9,9,6.7,9,6.5v-4C9,1.1,7.9,0,6.5,0h-1C4.1,0,3,1.1,3,2.5v4c0,0.2,0,0.4,0.1,0.7 C1.3,7.8,0,9.5,0,11.5V13h12v-1.5C12,9.5,10.7,7.8,8.9,7.2z M4,2.5C4,1.7,4.7,1,5.5,1h1C7.3,1,8,1.7,8,2.5v4c0,0.2,0,0.4-0.1,0.6 l0.1,0L7.9,7.3C7.6,7.8,7.1,8.2,6.5,8.2h-1c-0.6,0-1.1-0.4-1.4-0.9L4.1,7.1l0.1,0C4,6.9,4,6.7,4,6.5V2.5z M11,12H1v-0.5 c0-1.6,1-2.9,2.4-3.4c0.5,0.7,1.2,1.1,2.1,1.1h1c0.8,0,1.6-0.4,2.1-1.1C10,8.5,11,9.9,11,11.5V12z"/>
      </svg>
    </label>
    <input id="txtpregunta" class='lf--input' placeholder='Pregunta Secreta' type='text' name="txtpregunta">
  </div>
        
        
  <div class="flex-row">
    <label class="lf--label" for="Respuesta">
      <svg x="0px" y="0px" width="12px" height="13px">
        <path fill="#B1B7C4" d="M8.9,7.2C9,6.9,9,6.7,9,6.5v-4C9,1.1,7.9,0,6.5,0h-1C4.1,0,3,1.1,3,2.5v4c0,0.2,0,0.4,0.1,0.7 C1.3,7.8,0,9.5,0,11.5V13h12v-1.5C12,9.5,10.7,7.8,8.9,7.2z M4,2.5C4,1.7,4.7,1,5.5,1h1C7.3,1,8,1.7,8,2.5v4c0,0.2,0,0.4-0.1,0.6 l0.1,0L7.9,7.3C7.6,7.8,7.1,8.2,6.5,8.2h-1c-0.6,0-1.1-0.4-1.4-0.9L4.1,7.1l0.1,0C4,6.9,4,6.7,4,6.5V2.5z M11,12H1v-0.5 c0-1.6,1-2.9,2.4-3.4c0.5,0.7,1.2,1.1,2.1,1.1h1c0.8,0,1.6-0.4,2.1-1.1C10,8.5,11,9.9,11,11.5V12z"/>
      </svg>
    </label>
    <input id="txtrespuesta" class='lf--input' placeholder='Respuesta Secreta' type='text' name="txtrespuesta">
  </div>
        
   <div class="flex-row">
    <label class="lf--label" for="username">
      <svg x="0px" y="0px" width="12px" height="13px">
        <path fill="#B1B7C4" d="M8.9,7.2C9,6.9,9,6.7,9,6.5v-4C9,1.1,7.9,0,6.5,0h-1C4.1,0,3,1.1,3,2.5v4c0,0.2,0,0.4,0.1,0.7 C1.3,7.8,0,9.5,0,11.5V13h12v-1.5C12,9.5,10.7,7.8,8.9,7.2z M4,2.5C4,1.7,4.7,1,5.5,1h1C7.3,1,8,1.7,8,2.5v4c0,0.2,0,0.4-0.1,0.6 l0.1,0L7.9,7.3C7.6,7.8,7.1,8.2,6.5,8.2h-1c-0.6,0-1.1-0.4-1.4-0.9L4.1,7.1l0.1,0C4,6.9,4,6.7,4,6.5V2.5z M11,12H1v-0.5 c0-1.6,1-2.9,2.4-3.4c0.5,0.7,1.2,1.1,2.1,1.1h1c0.8,0,1.6-0.4,2.1-1.1C10,8.5,11,9.9,11,11.5V12z"/>
      </svg>
    </label>
    <input id="txtusername" class='lf--input' placeholder='Username' type='text' name="txtusername">
  </div>
        
  <div class="flex-row">
    <label class="lf--label" for="password">
      <svg x="0px" y="0px" width="15px" height="5px">
        <g>
          <path fill="#B1B7C4" d="M6,2L6,2c0-1.1-1-2-2.1-2H2.1C1,0,0,0.9,0,2.1v0.8C0,4.1,1,5,2.1,5h1.7C5,5,6,4.1,6,2.9V3h5v1h1V3h1v2h1V3h1 V2H6z M5.1,2.9c0,0.7-0.6,1.2-1.3,1.2H2.1c-0.7,0-1.3-0.6-1.3-1.2V2.1c0-0.7,0.6-1.2,1.3-1.2h1.7c0.7,0,1.3,0.6,1.3,1.2V2.9z"/>
        </g>
      </svg>
    </label>
    <input id="txtpassword" class='lf--input' placeholder='Password' type='password' name="txtpassword">   
  </div>
        
  <div class="flex-row">
    <label class="lf--label" for="password2">
      <svg x="0px" y="0px" width="15px" height="5px">
        <g>
          <path fill="#B1B7C4" d="M6,2L6,2c0-1.1-1-2-2.1-2H2.1C1,0,0,0.9,0,2.1v0.8C0,4.1,1,5,2.1,5h1.7C5,5,6,4.1,6,2.9V3h5v1h1V3h1v2h1V3h1 V2H6z M5.1,2.9c0,0.7-0.6,1.2-1.3,1.2H2.1c-0.7,0-1.3-0.6-1.3-1.2V2.1c0-0.7,0.6-1.2,1.3-1.2h1.7c0.7,0,1.3,0.6,1.3,1.2V2.9z"/>
        </g>
      </svg>
    </label>
    <input id="txtpassword2" class='lf--input' placeholder='Repetir Password' type='password' name="txtpassword2">   
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
  
  <input class='lf--submit' type='submit' value='REGISTRARSE'>
</form>
 </div>
    
<script>     
    function validar(){
        var password = document.getElementById("txtpassword").value.toString();
        var respuesta = true;
        if(noHayVacio()){
            if((password===document.getElementById("txtpassword2").value)){ 

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
        }else{
            respuesta = false;
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
    function noHayVacio(){
        var respuesta = true;
        if(document.getElementById("txtnombre").value===""){
            alert("EL CAMPO DE NOMBRE NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtapellido").value===""){
            alert("EL CAMPO DE APELLIDO NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtcorreo").value===""){
            alert("EL CAMPO DE CORREO NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtpregunta").value===""){
            alert("EL CAMPO DE PREGUNTA SECRETA NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtrespuesta").value===""){
            alert("EL CAMPO DE RESPUESTA SECRETA NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtusername").value===""){
            alert("EL CAMPO DE NOMBRE DE USUARIO NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtpassword").value===""){
            alert("EL CAMPO DE CONTRASENA NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        if(document.getElementById("txtpassword2").value===""){
            alert("EL CAMPO DE REPETIR CONTRASENA NO PUEDE ESTAR VACIO");
            respuesta = false;
        }
        
        return respuesta
        
    }

</script>

</html>
