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

public class SeccionServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected SeccionServiciosPK seccionServiciosPK;
    private Servicios servicios;

    public SeccionServicios() {
    }

    public SeccionServicios(SeccionServiciosPK seccionServiciosPK) {
        this.seccionServiciosPK = seccionServiciosPK;
    }

    public SeccionServicios(int codInstitucion, int codEstablecimiento, Long codSeccion, Long codServicio) {
        this.seccionServiciosPK = new SeccionServiciosPK(codInstitucion, codEstablecimiento, codSeccion, codServicio);
    }

    public SeccionServiciosPK getSeccionServiciosPK() {
        return seccionServiciosPK;
    }

    public void setSeccionServiciosPK(SeccionServiciosPK seccionServiciosPK) {
        this.seccionServiciosPK = seccionServiciosPK;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionServiciosPK != null ? seccionServiciosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionServicios)) {
            return false;
        }
        SeccionServicios other = (SeccionServicios) object;
        if ((this.seccionServiciosPK == null && other.seccionServiciosPK != null) || (this.seccionServiciosPK != null && !this.seccionServiciosPK.equals(other.seccionServiciosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.SeccionServicios[ seccionServiciosPK=" + seccionServiciosPK + " ]";
    }
    
}
