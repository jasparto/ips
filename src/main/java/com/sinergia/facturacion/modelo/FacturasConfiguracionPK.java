/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class FacturasConfiguracionPK implements Serializable {

    private int codInstitucion;
    private int codEstablecimiento;
    private Long codConfiguracion;

    public FacturasConfiguracionPK() {
    }

    public FacturasConfiguracionPK(int codInstitucion, int codEstablecimiento, Long codConfiguracion) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
        this.codConfiguracion = codConfiguracion;
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

    public Long getCodConfiguracion() {
        return codConfiguracion;
    }

    public void setCodConfiguracion(Long codConfiguracion) {
        this.codConfiguracion = codConfiguracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getCodInstitucion();
        hash += (int) codEstablecimiento;
        hash += codConfiguracion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturasConfiguracionPK)) {
            return false;
        }
        FacturasConfiguracionPK other = (FacturasConfiguracionPK) object;
        if (this.getCodInstitucion() != other.getCodInstitucion()) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        if (this.codConfiguracion != other.codConfiguracion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.facturacion.modelo.FacturasConfiguracionPK[ codInstitucion=" + getCodInstitucion() + ", codEstablecimiento=" + codEstablecimiento + ", codConfiguracion=" + codConfiguracion + " ]";
    }

}
