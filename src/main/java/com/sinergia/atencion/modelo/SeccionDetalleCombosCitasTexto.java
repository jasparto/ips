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

public class SeccionDetalleCombosCitasTexto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected SeccionDetalleCombosCitasTextoPK seccionDetalleCombosCitasTextoPK;
    
    private String texto;
    private Citas citas;

    public SeccionDetalleCombosCitasTexto() {
    }

    public SeccionDetalleCombosCitasTexto(SeccionDetalleCombosCitasTextoPK seccionDetalleCombosCitasTextoPK) {
        this.seccionDetalleCombosCitasTextoPK = seccionDetalleCombosCitasTextoPK;
    }

    public SeccionDetalleCombosCitasTexto(short codInstitucion, short codEstablecimiento, Long codCita, Long codSeccion, Long codDetalle, Long codCombo) {
        this.seccionDetalleCombosCitasTextoPK = new SeccionDetalleCombosCitasTextoPK(codInstitucion, codEstablecimiento, codCita, codSeccion, codDetalle, codCombo);
    }

    public SeccionDetalleCombosCitasTextoPK getSeccionDetalleCombosCitasTextoPK() {
        return seccionDetalleCombosCitasTextoPK;
    }

    public void setSeccionDetalleCombosCitasTextoPK(SeccionDetalleCombosCitasTextoPK seccionDetalleCombosCitasTextoPK) {
        this.seccionDetalleCombosCitasTextoPK = seccionDetalleCombosCitasTextoPK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionDetalleCombosCitasTextoPK != null ? seccionDetalleCombosCitasTextoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalleCombosCitasTexto)) {
            return false;
        }
        SeccionDetalleCombosCitasTexto other = (SeccionDetalleCombosCitasTexto) object;
        if ((this.seccionDetalleCombosCitasTextoPK == null && other.seccionDetalleCombosCitasTextoPK != null) || (this.seccionDetalleCombosCitasTextoPK != null && !this.seccionDetalleCombosCitasTextoPK.equals(other.seccionDetalleCombosCitasTextoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.SeccionDetalleCombosCitasTexto[ seccionDetalleCombosCitasTextoPK=" + seccionDetalleCombosCitasTextoPK + " ]";
    }

    /**
     * @return the citas
     */
    public Citas getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(Citas citas) {
        this.citas = citas;
    }
    
}
