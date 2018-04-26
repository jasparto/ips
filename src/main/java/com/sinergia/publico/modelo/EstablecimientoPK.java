/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import java.io.Serializable;


/**
 *
 * @author juliano
 */

public class EstablecimientoPK implements Serializable {
    
    private int codInstitucion;
    private int codEstablecimiento;

    public EstablecimientoPK() {
    }

    public EstablecimientoPK(int codInstitucion, int codEstablecimiento) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += (int) codEstablecimiento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstablecimientoPK)) {
            return false;
        }
        EstablecimientoPK other = (EstablecimientoPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.publico.EstablecimientoPK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + " ]";
    }
    
}
