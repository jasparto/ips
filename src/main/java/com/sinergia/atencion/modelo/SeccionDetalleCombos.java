/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class SeccionDetalleCombos implements Cloneable, Serializable {

    private int codInstitucion;
    private int codEstablecimiento;
    protected SeccionDetalleCombosPK seccionDetalleCombosPK;
    private String detalle;
    private Set<SeccionDetalleCombosCitasTexto> seccionDetalleCombosCitasTextoSet;
    private Set<Citas> citasSet;
    private SeccionDetalle seccionDetalle;

    public SeccionDetalleCombos() {
    }

    public SeccionDetalleCombos(SeccionDetalleCombosPK seccionDetalleCombosPK) {
        this.seccionDetalleCombosPK = seccionDetalleCombosPK;
    }

    public SeccionDetalleCombos(int codInstitucion, int codEstablecimiento, Long codSeccion, Long codDetalle, Long codCombo) {
        this.seccionDetalleCombosPK = new SeccionDetalleCombosPK(codSeccion, codDetalle, codCombo);
    }

    public SeccionDetalleCombosPK getSeccionDetalleCombosPK() {
        return seccionDetalleCombosPK;
    }

    public void setSeccionDetalleCombosPK(SeccionDetalleCombosPK seccionDetalleCombosPK) {
        this.seccionDetalleCombosPK = seccionDetalleCombosPK;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the citasSet
     */
    public Set<Citas> getCitasSet() {
        return citasSet;
    }

    /**
     * @param citasSet the citasSet to set
     */
    public void setCitasSet(Set<Citas> citasSet) {
        this.citasSet = citasSet;
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

    /**
     * @return the seccionDetalleCombosCitasTextoSet
     */
    public Set<SeccionDetalleCombosCitasTexto> getSeccionDetalleCombosCitasTextoSet() {
        return seccionDetalleCombosCitasTextoSet;
    }

    /**
     * @param seccionDetalleCombosCitasTextoSet the
     * seccionDetalleCombosCitasTextoSet to set
     */
    public void setSeccionDetalleCombosCitasTextoSet(Set<SeccionDetalleCombosCitasTexto> seccionDetalleCombosCitasTextoSet) {
        this.seccionDetalleCombosCitasTextoSet = seccionDetalleCombosCitasTextoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionDetalleCombosPK != null ? seccionDetalleCombosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalleCombos)) {
            return false;
        }
        SeccionDetalleCombos other = (SeccionDetalleCombos) object;
        if ((this.seccionDetalleCombosPK == null && other.seccionDetalleCombosPK != null) || (this.seccionDetalleCombosPK != null && !this.seccionDetalleCombosPK.equals(other.seccionDetalleCombosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tmp.SeccionDetalleCombos[ seccionDetalleCombosPK=" + seccionDetalleCombosPK + " ]";
    }

    /**
     * @return the codInstitucion
     */
    public int getCodInstitucion() {
        return codInstitucion;
    }

    /**
     * @param codInstitucion the codInstitucion to set
     */
    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    /**
     * @return the codEstablecimiento
     */
    public int getCodEstablecimiento() {
        return codEstablecimiento;
    }

    /**
     * @param codEstablecimiento the codEstablecimiento to set
     */
    public void setCodEstablecimiento(int codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
