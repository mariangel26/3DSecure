package Modelo;

/**
 * Clase que se encarga de almacenar la estructura de los clientes en el banco
 * @author mariangel
 */
public class Cliente {
    public String nombreCliente; //Nombre del cliente
    public String apellidoCliente; //Apellido del cliente
    public Integer cedulaCliente; //Cedula del cliente
    public String correoCliente; //Correo del cliente
    public Long tarjetaCliente; //Tarjeta de credito del cliente
    public Long dineroDisponible; //Cantidad de dinero disponible en la cuenta del cliente

    public Cliente(){}
    
    public Cliente(String nombreCliente, String apellidoCliente, 
            Integer cedulaCliente, String correoCliente, Long tarjetaCliente,
            Long dineroDisponible) {
        
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.cedulaCliente = cedulaCliente;
        this.correoCliente = correoCliente;
        this.tarjetaCliente = tarjetaCliente;
        this.dineroDisponible = dineroDisponible;
    }

    public Long getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(Long dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    /**
     * Obtiene el nombre del cliente
     * @return el nombre del cliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Obtiene el apellido del cliente
     * @return el apellido del cliente
     */
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    /**
     * Obtiene la cedula del cliente
     * @return la cedula del cliente
     */
    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    /**
     * Obtiene el correo del cliente
     * @return el correo del cliente
     */
    public String getCorreoCliente() {
        return correoCliente;
    }

    /**
     * Obtiene la tarjeta del cliente
     * @return la tarjeta del cliente
     */
    public Long getTarjetaCliente() {
        return tarjetaCliente;
    }

    /**
     * Establece el nombre del cliente
     * @param nombreCliente (Nombre del cliente)
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Establece el apellido del cliente
     * @param apellidoCliente (Apellido del cliente)
     */
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    /**
     * Establece la cedula del cliente
     * @param cedulaCliente (Cedula del Cliente)
     */
    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    /**
     * Establece el correo del cliente 
     * @param correoCliente (Correo del cliente)
     */
    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    /**
     * Establece la tarjeta del cliente
     * @param tarjetaCliente (Tarjeta del cliente)
     */
    public void setTarjetaCliente(Long tarjetaCliente) {
        this.tarjetaCliente = tarjetaCliente;
    }
    
    
    
}
