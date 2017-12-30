<%@page import="DAO.DAOProducto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Producto"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>So Awesome</title>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>

      <style>
      /* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
      * {
    margin: 0;
    padding: 0;
}
body {
    display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
      -ms-flex-direction: column;
          flex-direction: column;
  position: relative;
  background: linear-gradient(135deg, rgba(36, 46, 77, 0.9), rgba(137, 126, 121, 0.9));
  font-family: 'Roboto', helvetica, arial, sans-serif;
  font-size: 1.5em;
}
.wrapper {
    width: 705px;
    margin: 20px auto;
    padding: 20px;
}
h1 {
    display: inline-block;
    background-color: #333;
    color: #fff;
    font-size: 20px;
    font-weight: normal;
    text-transform: uppercase;
    padding: 4px 20px;
    float: left;
}
.clear {
    clear: both;
}
.items {
    display: block;
    margin: 20px 0;
}
.item {
    background-color: #fff;
    float: left;
    margin: 0 10px 10px 0;
    width: 205px;
    padding: 10px;
    border-radius: 25px;
    height: 290px;
}
.item img {
    display: block;
    margin: auto;
}
h2 {
    font-size: 16px;
    display: block;
    border-bottom: 1px solid #ccc;
    margin: 0 0 10px 0;
    padding: 0 0 5px 0;
}
button {
    border: 1px solid #722A1B;
    padding: 4px 14px;
    background-color: #fff;
    color: #722A1B;
    text-transform: uppercase;
    float: right;
    margin: 5px 0;
    font-weight: bold;
    cursor: pointer;
}
span {
    float: right;
}
.shopping-cart {
    display: inline-block;
    background: url('http://cdn1.iconfinder.com/data/icons/jigsoar-icons/24/_cart.png') no-repeat 0 0;
    width: 24px;
    height: 24px;
    margin: 0 10px 0 0;
}
    </style>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body>
  
  <%! 
    Producto p = new Producto();
    DAOProducto dao = new DAOProducto();
    ArrayList<Producto> producto = new ArrayList<Producto>();
  %>
  
  
<div class="wrapper">
     <h1>So Awesome</h1>
 <span><i class="shopping-cart"></i></span>

    <div class="clear"></div>
    <!-- items -->
    <div class="items">
        <!-- single item -->
    <%
        producto = dao.todosLosProductos();
        for(Producto pr : producto){
    %>
           <div class="item">
            <img align=center border=0 src="<%=pr.getFotoProducto()%>" alt="item" />
            <h2> <%=pr.getNombreProducto()%> </h2>

            <p>Precio: <em><%=pr.getPrecioProducto()%></em>
            </p>
            <a href="Compra.jsp">
               <button class="add-to-cart" type="button">Comprar</button> 
            </a>
            
            </div>
     
    <%
     }
    %>
        
    
    </div>
    <!--/ items -->
</div>
<!--/ wrapper -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>

</body>
</html>

