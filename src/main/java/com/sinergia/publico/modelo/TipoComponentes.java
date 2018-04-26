/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import com.sinergia.atencion.modelo.SeccionDetalle;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author juliano
 */

public class TipoComponentes implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer codComponente;
    private String nombre;
    private Set<SeccionDetalle> seccionDetallesSet;

    public TipoComponentes() {
    }

    public TipoComponentes(Integer codComponente) {
        this.codComponente = codComponente;
    }

    public Integer getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(Integer codComponente) {
        this.codComponente = codComponente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codComponente != null ? codComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComponentes)) {
            return false;
        }
        TipoComponentes other = (TipoComponentes) object;
        if ((this.codComponente == null && other.codComponente != null) || (this.codComponente != null && !this.codComponente.equals(other.codComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.TipoComponentes[ codComponente=" + codComponente + " ]";
    }

    /**
     * @return the seccionDetallesSet
     */
    public Set<SeccionDetalle> getSeccionDetallesSet() {
        return seccionDetallesSet;
    }

    /**
     * @param seccionDetallesSet the seccionDetallesSet to set
     */
    public void setSeccionDetallesSet(Set<SeccionDetalle> seccionDetallesSet) {
        this.seccionDetallesSet = seccionDetallesSet;
    }
    
}
