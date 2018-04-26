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
public class TiposContratoPK implements Serializable {

    private int codInstitucion;
//    private int codEstablecimiento;
    private int codContrato;

    public TiposContratoPK() {
    }

    public TiposContratoPK(int codInstitucion, int codContrato) {
        this.codInstitucion = codInstitucion;
        this.codContrato = codContrato;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodContrato() {
        return codContrato;
    }

    public void setCodContrato(int codContrato) {
        this.codContrato = codContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += (int) codContrato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposContratoPK)) {
            return false;
        }
        TiposContratoPK other = (TiposContratoPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codContrato != other.codContrato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.TiposContratoPK[ codInstitucion=" + codInstitucion + ", codContrato=" + codContrato + " ]";
    }

}
