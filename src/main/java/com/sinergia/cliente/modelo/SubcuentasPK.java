/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class SubcuentasPK implements Serializable {
    
    private int codInstitucion;
//    private int codEstablecimiento;
    private int codSubcuenta;

    public SubcuentasPK() {
    }

    public SubcuentasPK(int codInstitucion, int codSubcuenta) {
        this.codInstitucion = codInstitucion;
        this.codSubcuenta = codSubcuenta;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodSubcuenta() {
        return codSubcuenta;
    }

    public void setCodSubcuenta(int codSubcuenta) {
        this.codSubcuenta = codSubcuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += (int) codSubcuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubcuentasPK)) {
            return false;
        }
        SubcuentasPK other = (SubcuentasPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codSubcuenta != other.codSubcuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.SubcuentasPK[ codInstitucion=" + codInstitucion + ", codSubcuenta=" + codSubcuenta + " ]";
    }
    
}
