package DAO;


import Modelo.Cliente;
import Registro.Registro;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
/**
 * Clase que se ocupara en obtener informacion del XML de cliente
 */
public class DAOCliente {
    private Element root;
    private String fileLocation = Registro.UBICACION_ARCHIVO_XML_CLIENTE;
    //private String fileLocation = "C:\\Users\\oswal\\Documents\\NetBeansProjects\\ProyectoVendedor\\src\\java\\XML\\Cliente.xml";
    
    public DAOCliente() {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            root = doc.getRootElement();
        } catch (JDOMException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        }
    }
    
    private Element ClientetoXmlElement(Cliente nCliente ) {
        Element Clientetrans = new Element("Cliente");
        
        Element nombreUsuario = new Element("nombreUsuario");
        Element contrasena = new Element("contrasena");
        Element nombre = new Element("nombre");
        Element apellido = new Element("apellido");
        Element correo = new Element("correo");
        Element intentos = new Element("intentos");
        Element preguntaSecreta = new Element("preguntaSecreta");
        Element respuestaSecreta = new Element("respuestaSecreta");
        
        nombreUsuario.setText(nCliente.getNombreUsuario());
        contrasena.setText(nCliente.getContrasena());
        nombre.setText(nCliente.getNombre());
        apellido.setText(nCliente.getApellido());
        correo.setText(nCliente.getCorreo());
        intentos.setText(nCliente.getIntentos().toString());
        preguntaSecreta.setText(nCliente.getPreguntaSecreta());
        respuestaSecreta.setText(nCliente.getRespuestaSecreta());
        
        Clientetrans.addContent(nombreUsuario);
        Clientetrans.addContent(contrasena);
        Clientetrans.addContent(nombre);
        Clientetrans.addContent(apellido);
        Clientetrans.addContent(correo);
        Clientetrans.addContent(intentos);
        Clientetrans.addContent(preguntaSecreta);
        Clientetrans.addContent(respuestaSecreta);
        
        return Clientetrans;
    }
    
    private Cliente ClienteToObject(Element element) throws ParseException {
       
        Cliente nCliente = new Cliente (element.getChildText("nombre"),
                element.getChildText("apellido"),
                element.getChildText("nombreUsuario"),
                element.getChildText("contrasena"),
                element.getChildText("correo"),
                Integer.parseInt(element.getChildText("intentos")),
                element.getChildText("preguntaSecreta"),
                element.getChildText("respuestaSecreta")
                
        );
        return nCliente;
    }
    
    public boolean registrarCliente(Cliente cliente) {
        boolean resultado = false;
        root.addContent(ClientetoXmlElement((Cliente) cliente));
        resultado = updateDocument();
        return resultado;
    }
    
    private boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(fileLocation);
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }
    
    public static Element buscar(List raiz, String dato) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.equals(e.getChild("nombreUsuario").getText())) {
                return e;
            }
        }
        return null;
    }
    
    public static Element buscarclave(List raiz, String dato,String clave) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if ((dato.equals(e.getChild("nombreUsuario").getText())) && 
                    (clave.equals(e.getChild("contrasena").getText()))) {
                return e;
            }
        }
        return null;
    }
    
    public Cliente buscarCuenta(String usuario) {
        Element aux = new Element("Cliente");
        List Cliente= this.root.getChildren("Cliente");
        while (aux != null) {
            aux = DAOCliente.buscar(Cliente,usuario);
            if (aux != null) {
                try {
                    return ClienteToObject(aux);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return null;
    }
    
    public Cliente buscarCuentaClave(String usuario,String clave) {
        Element aux = new Element("Cliente");
        List Cliente= this.root.getChildren("Cliente");
        while (aux != null) {
            aux = DAOCliente.buscarclave(Cliente,usuario,clave);
            if (aux != null) {
                try {
                    return ClienteToObject(aux);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return null;
    }
    
    public boolean actualizarCliente(Cliente nCliente) {
        boolean resultado = false;
        Element aux = new Element("Cliente");
        List Cliente = this.root.getChildren("Cliente");
        while (aux != null) {
            aux = DAOCliente.buscar(Cliente,nCliente.getNombreUsuario());
            if (aux != null) {
                Cliente.remove(aux);
                resultado = updateDocument();
            }
        }
        registrarCliente(nCliente);
        return resultado;
    } 
    
}
