/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author juliano
 */

public class SeccionDetallePK implements Serializable {
    
    private int codInstitucion;
    
    private int codEstablecimiento;
    
    private Long codSeccion;
    
    private Long codDetalle;

    public SeccionDetallePK() {
    }

    public SeccionDetallePK(int codInstitucion, int codEstablecimiento, Long codSeccion, Long codDetalle) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
        this.codSeccion = codSeccion;
        this.codDetalle = codDetalle;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(int codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    public Long getCodSeccion() {
        return codSeccion;
    }

    public void setCodSeccion(Long codSeccion) {
        this.codSeccion = codSeccion;
    }

    public Long getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(Long codDetalle) {
        this.codDetalle = codDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += (int) codEstablecimiento;
        hash += (Long) codSeccion;
        hash += (Long) codDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetallePK)) {
            return false;
        }
        SeccionDetallePK other = (SeccionDetallePK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        if (this.codSeccion != other.codSeccion) {
            return false;
        }
        if (this.codDetalle != other.codDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.SeccionDetallePK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + ", codSeccion=" + codSeccion + ", codDetalle=" + codDetalle + " ]";
    }
    
}
