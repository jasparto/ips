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

public class EntidadesPK implements Serializable {
    
    private int codInstitucion;
//    private int codEstablecimiento;
    private int codEntidad;

    public EntidadesPK() {
    }

    public EntidadesPK(int codInstitucion, int codEntidad) {
        this.codInstitucion = codInstitucion;
//        this.codEstablecimiento = codEstablecimiento;
        this.codEntidad = codEntidad;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodEntidad() {
        return codEntidad;
    }

    public void setCodEntidad(int codEntidad) {
        this.codEntidad = codEntidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += (int) codEntidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadesPK)) {
            return false;
        }
        EntidadesPK other = (EntidadesPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codEntidad != other.codEntidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.EntidadesPK[ codInstitucion=" + codInstitucion + ", codEntidad=" + codEntidad + " ]";
    }
    
}
