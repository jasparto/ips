/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class DetalleListaPK implements Serializable {
    private int codLista;
    private int codDetalle;

    public DetalleListaPK() {
    }

    public DetalleListaPK(int codLista, int codDetalle) {
        this.codLista = codLista;
        this.codDetalle = codDetalle;
    }

    public int getCodLista() {
        return codLista;
    }

    public void setCodLista(int codLista) {
        this.codLista = codLista;
    }

    public int getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(int codDetalle) {
        this.codDetalle = codDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codLista;
        hash += (int) codDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleListaPK)) {
            return false;
        }
        DetalleListaPK other = (DetalleListaPK) object;
        if (this.codLista != other.codLista) {
            return false;
        }
        if (this.codDetalle != other.codDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.DetalleListaPK[ codLista=" + codLista + ", codDetalle=" + codDetalle + " ]";
    }
    
}
