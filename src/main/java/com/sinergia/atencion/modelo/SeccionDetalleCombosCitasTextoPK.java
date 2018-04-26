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

public class SeccionDetalleCombosCitasTextoPK implements Serializable {
    
    private int codInstitucion;
    
    private int codEstablecimiento;
    
    private Long codCita;
    
    private Long codSeccion;
    
    private Long codDetalle;
    
    private Long codCombo;

    public SeccionDetalleCombosCitasTextoPK() {
    }

    public SeccionDetalleCombosCitasTextoPK(int codInstitucion, int codEstablecimiento, Long codCita, Long codSeccion, Long codDetalle, Long codCombo) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
        this.codCita = codCita;
        this.codSeccion = codSeccion;
        this.codDetalle = codDetalle;
        this.codCombo = codCombo;
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

    public Long getCodCita() {
        return codCita;
    }

    public void setCodCita(Long codCita) {
        this.codCita = codCita;
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

    public Long getCodCombo() {
        return codCombo;
    }

    public void setCodCombo(Long codCombo) {
        this.codCombo = codCombo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += (int) codEstablecimiento;
        hash += (Long) codCita;
        hash += (Long) codSeccion;
        hash += (Long) codDetalle;
        hash += (Long) codCombo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalleCombosCitasTextoPK)) {
            return false;
        }
        SeccionDetalleCombosCitasTextoPK other = (SeccionDetalleCombosCitasTextoPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        if (this.codCita != other.codCita) {
            return false;
        }
        if (this.codSeccion != other.codSeccion) {
            return false;
        }
        if (this.codDetalle != other.codDetalle) {
            return false;
        }
        if (this.codCombo != other.codCombo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.SeccionDetalleCombosCitasTextoPK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + ", codCita=" + codCita + ", codSeccion=" + codSeccion + ", codDetalle=" + codDetalle + ", codCombo=" + codCombo + " ]";
    }
    
}
