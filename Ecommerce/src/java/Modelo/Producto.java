package Modelo;

/**
 * Clase del dominio que tiene todos los atributos de un producto
 * @author mariangel
 */
public class Producto {
    public String nombreProducto; //Nombre del producto
    public String precioProducto; //Precio del producto
    public Integer idProducto; //Identificador del producto
    public String fotoProducto; //Ruta de la foto del producto

    public Producto(){}
    
    public Producto(String nombreProducto, String precioProducto, Integer idProducto, String fotoProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.idProducto = idProducto;
        this.fotoProducto = fotoProducto;
    }

    /**
     * Obtiene el nombre del producto
     * @return el nombre del producto 
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Obtiene el precio del producto
     * @return el precio del producto
     */
    public String getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Obtiene el identificador del producto
     * @return el identidicador del producto
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * Obtiene la ruta de la foto del producto
     * @return La ruta de la foto del producto
     */
    public String getFotoProducto() {
        return fotoProducto;
    }

    /**
     * Establece el nombre del producto
     * @param nombreProducto 
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Establece el precio del producto
     * @param precioProducto 
     */
    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Establece el identificador del producto
     * @param idProducto 
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Establece la ruta de la foto del producto
     * @param fotoProducto 
     */
    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
    
    
    
    
}
