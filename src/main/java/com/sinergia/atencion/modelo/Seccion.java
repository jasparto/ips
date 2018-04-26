/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class Seccion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    protected SeccionPK seccionPK;

    private String nombre;
    private SeccionDetalle seccionDetalle = new SeccionDetalle();
    private Set<SeccionDetalle> seccionDetalleSet = new HashSet<>();
    private Set<SeccionServicios> seccionServiciosSet = new HashSet<>();
    
    private boolean activo;

    public Seccion() {
    }

    public Seccion(SeccionPK seccionPK) {
        this.seccionPK = seccionPK;
    }

    public Seccion(short codInstitucion, short codEstablecimiento, Long codSeccion) {
        this.seccionPK = new SeccionPK(codInstitucion, codEstablecimiento, codSeccion);
    }

    public SeccionPK getSeccionPK() {
        return seccionPK;
    }

    public void setSeccionPK(SeccionPK seccionPK) {
        this.seccionPK = seccionPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<SeccionDetalle> getSeccionDetalleSet() {
        return seccionDetalleSet;
    }

    public void setSeccionDetalleSet(Set<SeccionDetalle> seccionDetalleSet) {
        this.seccionDetalleSet = seccionDetalleSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionPK != null ? seccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.seccionPK == null && other.seccionPK != null) || (this.seccionPK != null && !this.seccionPK.equals(other.seccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.Seccion[ seccionPK=" + seccionPK + " ]";
    }

    /**
     * @return the seccionDetalle
     */
    public SeccionDetalle getSeccionDetalle() {
        return seccionDetalle;
    }

    /**
     * @param seccionDetalle the seccionDetalle to set
     */
    public void setSeccionDetalle(SeccionDetalle seccionDetalle) {
        this.seccionDetalle = seccionDetalle;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone;
        clone = super.clone();
        return clone;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the seccionServiciosSet
     */
    public Set<SeccionServicios> getSeccionServiciosSet() {
        return seccionServiciosSet;
    }

    /**
     * @param seccionServiciosSet the seccionServiciosSet to set
     */
    public void setSeccionServiciosSet(Set<SeccionServicios> seccionServiciosSet) {
        this.seccionServiciosSet = seccionServiciosSet;
    }
}
