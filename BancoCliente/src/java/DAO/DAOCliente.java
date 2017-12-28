package DAO;

import Modelo.Cliente;
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
 * Clase que se encarga de la logica del cliente del banco
 * @author mariangel
 */
public class DAOCliente {
    
    private Element root;
    private String fileLocation = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\BancoCliente\\src\\java\\XML\\Cliente.xml";
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
    
    /**
     * Convierte el objeto producto a un XML
     * @param nCliente objeto cliente
     * @return El producto convertido
     */
    private Element ClientetoXmlElement(Cliente nCliente ) {
        Element Clientetrans = new Element("Cliente");
        
        Element nombreCliente = new Element("nombreCliente");
        Element apellidoCliente = new Element("apellidoCliente");
        Element cedulaCliente = new Element("cedulaCliente");
        Element correoCliente = new Element("correoCliente");
        Element tarjetaCliente = new Element("tarjetaCliente");
    
        
        nombreCliente.setText(nCliente.getNombreCliente());
        apellidoCliente.setText(nCliente.getApellidoCliente());
        cedulaCliente.setText(nCliente.getCedulaCliente().toString());
        correoCliente.setText(nCliente.getCorreoCliente());
        tarjetaCliente.setText(nCliente.getTarjetaCliente().toString());
        
        Clientetrans.addContent(nombreCliente);
        Clientetrans.addContent(apellidoCliente);
        Clientetrans.addContent(cedulaCliente);
        Clientetrans.addContent(correoCliente);
        Clientetrans.addContent(tarjetaCliente);
        
        return Clientetrans;
    }
    
    /**
     * Convierte de XML a Objeto
     * @param element el elemento xml
     * @return un objeto cliente
     * @throws ParseException 
     */
    private Cliente ClienteToObject(Element element) throws ParseException {
       
        Cliente nCliente = new Cliente (element.getChildText("nombreCliente"),
                element.getChildText("apellidoCliente"),
                Long.parseLong(element.getChildText("cedulaCliente")),
                element.getChildText("correoCliente"),
                Long.parseLong(element.getChildText("tarjetaCliente"))           
        );
        return nCliente;
    }
    
    /**
     * Actualiza el documento XML
     * @return retorna verdadero o falso
     */
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
    
    /**
     * Busca el producto dado la cedula
     * @param raiz Raiz
     * @param dato La cedula del cliente
     * @return el elemento
     */
    public static Element buscar(List raiz, Long dato) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.equals(e.getChild("cedulaCliente").getText())) {
                return e;
            }
        }
        return null;
    }
    
    /**
     * Busca al cliente dada la cedula
     * @param cedula Identificador del cliente
     * @return el objeto cliente
     */
    public Cliente buscarCedula(Long cedula) {
        Element aux = new Element("Cliente");
        List Cliente= this.root.getChildren("Cliente");
        while (aux != null) {
            aux = DAOCliente.buscar(Cliente,cedula);
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
    

}
