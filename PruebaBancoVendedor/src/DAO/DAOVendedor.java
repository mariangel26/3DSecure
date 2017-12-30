/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Vendedor;
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
 *
 * @author oswalm
 */
public class DAOVendedor {
    
    private Element root;
    //private String fileLocation = "C:\\Users\\maria\\Documents\\Seguridad\\3DSecure\\3DSecure\\PruebaBancoVendedor\\src\\XML\\Vendedor.xml";
    //private String fileLocation = "C:\\Users\\oswal\\Documents\\NetBeansProjects\\ProyectoVendedor\\src\\java\\XML\\Vendedor.xml";
    private String fileLocation = Registro.UBICACION_XML_VENDEDOR;
    
    public DAOVendedor() {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            root = doc.getRootElement();
        } catch (JDOMException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + 
                    ex.getMessage());
        } catch (IOException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + 
                    ex.getMessage());
        }
    }
    
    /**
     * Convierte el objeto producto a un XML
     * @param nVendedor objeto cliente
     * @return El producto convertido
     */
    private Element VendedortoXmlElement(Vendedor vendedor ) {
        Element vendedortrans = new Element("Vendedor");
        
        Element id = new Element("id");

        Element dineroDepositado = new Element("dineroDepositado");

        
        id.setText(vendedor.getId().toString());
        dineroDepositado.setText(vendedor.getDineroDepositado().toString());
        
        
        vendedortrans.addContent(id);
        vendedortrans.addContent(dineroDepositado);
        
        return vendedortrans;
    }
    
    /**
     * Convierte de XML a Objeto
     * @param element el elemento xml
     * @return un objeto cliente
     * @throws ParseException 
     */
    private Vendedor VendedorToObject(Element element) throws ParseException {
       
        Vendedor nVendedor = new Vendedor (
                Integer.parseInt(element.getChildText("id")),
                Long.parseLong(element.getChildText("dineroDepositado"))
        );
        return nVendedor;
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
     * este metodo se encarga de agregar un vendedor
     * @param cliente
     * @return 
     */
    public boolean registrarVendedor(Vendedor vendedor) {
                
        boolean resultado = false;
        root.addContent(VendedortoXmlElement((Vendedor) vendedor));
        resultado = updateDocument();
        return resultado;
    }
    /**
     * Busca el producto dado la cedula
     * @param raiz Raiz
     * @param dato La cedula del cliente
     * @return el elemento
     */
    public static Element buscar(List raiz, Integer dato) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.toString().equals(e.getChild("id").getText())) {
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
    public Vendedor buscarId(Integer id) {
        Element aux = new Element("Vendedor");
        List Vendedor= this.root.getChildren("Vendedor");
        while (aux != null) {
            aux = DAOVendedor.buscar(Vendedor,id);
            if (aux != null) {
                try {
                    return VendedorToObject(aux);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return null;
    }
    /**
     * metodo que se encarga de actualizar los datos del cliente en el banco
     * @param nVendedor
     * @return 
     */
    public boolean actualizarVendedor(Vendedor nVendedor) {
        boolean resultado = false;
        Element aux = new Element("Vendedor");
        List Vendedor = this.root.getChildren("Vendedor");
        while (aux != null) {
            aux = DAOVendedor.buscar(Vendedor,nVendedor.getId());
            if (aux != null) {
                Vendedor.remove(aux);
                resultado = updateDocument();
            }
        }
        registrarVendedor(nVendedor);
        return resultado;
    }
    
}
