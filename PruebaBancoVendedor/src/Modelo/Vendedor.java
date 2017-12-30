/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author oswalm
 */
public class Vendedor {
    public Integer id;
    public Long dineroDepositado;

    public Vendedor(Integer id, Long dineroDepositado) {
        this.id = id;
        this.dineroDepositado = dineroDepositado;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDineroDepositado() {
        return dineroDepositado;
    }

    public void setDineroDepositado(Long dineroDepositado) {
        this.dineroDepositado = dineroDepositado;
    }
    
    
}
