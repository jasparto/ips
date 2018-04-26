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

public class TipoRecursoServicios implements Serializable {

    private static final long serialVersionUID = 1L;

    protected TipoRecursoServiciosPK tipoRecursoServiciosPK;
    private Servicios servicios;
    private TipoRecurso tipoRecurso;

    public TipoRecursoServicios() {
    }

    public TipoRecursoServicios(TipoRecursoServiciosPK tipoRecursoServiciosPK) {
        this.tipoRecursoServiciosPK = tipoRecursoServiciosPK;
    }

    public TipoRecursoServicios(int codInstitucion, int codEstablecimiento, Long codTipoRecurso, Long codServicio) {
        this.tipoRecursoServiciosPK = new TipoRecursoServiciosPK(codInstitucion, codEstablecimiento, codTipoRecurso, codServicio);
    }

    public TipoRecursoServiciosPK getTipoRecursoServiciosPK() {
        return tipoRecursoServiciosPK;
    }

    public void setTipoRecursoServiciosPK(TipoRecursoServiciosPK tipoRecursoServiciosPK) {
        this.tipoRecursoServiciosPK = tipoRecursoServiciosPK;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoRecursoServiciosPK != null ? tipoRecursoServiciosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecursoServicios)) {
            return false;
        }
        TipoRecursoServicios other = (TipoRecursoServicios) object;
        if ((this.tipoRecursoServiciosPK == null && other.tipoRecursoServiciosPK != null) || (this.tipoRecursoServiciosPK != null && !this.tipoRecursoServiciosPK.equals(other.tipoRecursoServiciosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.TipoRecursoServicios[ tipoRecursoServiciosPK=" + tipoRecursoServiciosPK + " ]";
    }
    
}
