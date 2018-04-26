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
public class SeccionDetalleCombosPK implements Serializable {

    //private int codInstitucion;
//    private int codEstablecimiento;
    private Long codSeccion;

    private Long codDetalle;

    private Long codCombo;

    public SeccionDetalleCombosPK() {
    }

//    public SeccionDetalleCombosPK(int codInstitucion, int codEstablecimiento, Long codSeccion, Long codDetalle, Long codCombo) {
    public SeccionDetalleCombosPK(Long codSeccion, Long codDetalle, Long codCombo) {
//        this.codInstitucion = codInstitucion;
//        this.codEstablecimiento = codEstablecimiento;
        this.codSeccion = codSeccion;
        this.codDetalle = codDetalle;
        this.codCombo = codCombo;
    }

//    public int getCodInstitucion() {
//        return codInstitucion;
//    }
//
//    public void setCodInstitucion(short codInstitucion) {
//        this.codInstitucion = codInstitucion;
//    }
//    public int getCodEstablecimiento() {
//        return codEstablecimiento;
//    }
//
//    public void setCodEstablecimiento(short codEstablecimiento) {
//        this.codEstablecimiento = codEstablecimiento;
//    }
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
        hash += (Long) codSeccion;
        hash += (Long) codDetalle;
        hash += (Long) codCombo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalleCombosPK)) {
            return false;
        }
        SeccionDetalleCombosPK other = (SeccionDetalleCombosPK) object;
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
}
