/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class TipoRecursoServiciosCantidad implements Serializable {

    private static final long serialVersionUID = 1L;

    protected TipoRecursoServiciosCantidadPK tipoRecursoServiciosCantidadPK;
    
    private int cantidad;

    public TipoRecursoServiciosCantidad() {
    }

    public TipoRecursoServiciosCantidad(TipoRecursoServiciosCantidadPK tipoRecursoServiciosCantidadPK) {
        this.tipoRecursoServiciosCantidadPK = tipoRecursoServiciosCantidadPK;
    }

    public TipoRecursoServiciosCantidad(TipoRecursoServiciosCantidadPK tipoRecursoServiciosCantidadPK, int cantidad) {
        this.tipoRecursoServiciosCantidadPK = tipoRecursoServiciosCantidadPK;
        this.cantidad = cantidad;
    }

    public TipoRecursoServiciosCantidad(short codInstitucion, short codEstablecimiento, Long codTipoRecurso, Long codServicio) {
        this.tipoRecursoServiciosCantidadPK = new TipoRecursoServiciosCantidadPK(codInstitucion, codEstablecimiento, codTipoRecurso, codServicio);
    }

    public TipoRecursoServiciosCantidadPK getTipoRecursoServiciosCantidadPK() {
        return tipoRecursoServiciosCantidadPK;
    }

    public void setTipoRecursoServiciosCantidadPK(TipoRecursoServiciosCantidadPK tipoRecursoServiciosCantidadPK) {
        this.tipoRecursoServiciosCantidadPK = tipoRecursoServiciosCantidadPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoRecursoServiciosCantidadPK != null ? tipoRecursoServiciosCantidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecursoServiciosCantidad)) {
            return false;
        }
        TipoRecursoServiciosCantidad other = (TipoRecursoServiciosCantidad) object;
        if ((this.tipoRecursoServiciosCantidadPK == null && other.tipoRecursoServiciosCantidadPK != null) || (this.tipoRecursoServiciosCantidadPK != null && !this.tipoRecursoServiciosCantidadPK.equals(other.tipoRecursoServiciosCantidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.TipoRecursoServiciosCantidad[ tipoRecursoServiciosCantidadPK=" + tipoRecursoServiciosCantidadPK + " ]";
    }
    
}
