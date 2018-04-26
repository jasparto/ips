/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class ClientesPK implements Serializable {
    
    private int codInstitucion;
    
//    private int codEstablecimiento;
    
    private int codCliente;

    public ClientesPK() {
    }

    public ClientesPK(int codInstitucion, int codCliente) {
        this.codInstitucion = codInstitucion;
        this.codCliente = codCliente;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += (int) codCliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientesPK)) {
            return false;
        }
        ClientesPK other = (ClientesPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codCliente != other.codCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ClientesPK[ codInstitucion=" + codInstitucion + ", codCliente=" + codCliente + " ]";
    }
    
}
