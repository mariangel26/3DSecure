package DAO;

import Modelo.Producto;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * Clase que se encarga de la logica de negocio de los productos
 * @author mariangel
 */
public class DAOProducto {
    private Element root;
    private String fileLocation = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\src\\java\\XML\\Producto.xml";// mariangel
    //private String fileLocation = "C:\\Users\\oswal\\Documents\\NetBeansProjects\\ProyectoVendedor\\src\\java\\XML\\Producto.xml";
    
    public DAOProducto() {
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
     * @param nProducto 
     * @return El producto convertido
     */
    private Element ProductotoXmlElement(Producto nProducto ) {
        Element Productotrans = new Element("Producto");
        
        Element nombreProducto = new Element("nombreProducto");
        Element precioProducto = new Element("precioProducto");
        Element idProducto = new Element("idProducto");
        Element fotoProducto = new Element("fotoProducto");
        
        nombreProducto.setText(nProducto.getNombreProducto());
        precioProducto.setText(nProducto.getPrecioProducto());
        idProducto.setText(nProducto.getIdProducto().toString());
        fotoProducto.setText(nProducto.getFotoProducto());
     
        
        Productotrans.addContent(nombreProducto);
        Productotrans.addContent(precioProducto);
        Productotrans.addContent(idProducto);
        Productotrans.addContent(fotoProducto);
    
        
        return Productotrans;
    }
    
    /**
     * Convierte de XML a Objeto
     * @param element
     * @return El objeto producto
     * @throws ParseException 
     */
    private Producto ProductoToObject(Element element) throws ParseException {
       
        Producto nProducto = new Producto (element.getChildText("nombreProducto"),
                element.getChildText("precioProducto"),
                Integer.parseInt(element.getChildText("idProducto")),
                element.getChildText("fotoProducto"));
                
        return nProducto;
    }
    
    
    /**
     * Registra un producto
     * @param nProducto 
     * @return verdadero o falso
     */
    public boolean registrarProducto(Producto nProducto) {
        boolean resultado = false;
        root.addContent(ProductotoXmlElement((Producto) nProducto));
        resultado = updateDocument();
        return resultado;
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
     * Busca el producto dado el identificador
     * @param raiz
     * @param id
     * @return el elemento
     */
    public static Element buscar(List raiz, Integer id) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if ((id.equals(e.getChild("idProducto").getText()))) {
                return e;
            }
        }
        return null;
    }
    
    /**
     * Busca el producto dado el identificador
     * @param id
     * @return retorna el objeto producto
     */
    public Producto buscarProducto(Integer id) {
        Element aux = new Element("Producto");
        List Producto= this.root.getChildren("Producto");
        while (aux != null) {
            aux = DAOProducto.buscar(Producto,id);
            if (aux != null) {
                try {
                    return ProductoToObject(aux);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return null;
    }
    
    /**
     * Obtiene la lista de todos los productos
     * @return la lista de todos los productos
     */
    public ArrayList<Producto> todosLosProductos() {
        ArrayList<Producto> resultado = new ArrayList<>();
        
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            try {
                resultado.add(ProductoToObject(xmlElem));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
               
        return resultado;
    }  
    
}
