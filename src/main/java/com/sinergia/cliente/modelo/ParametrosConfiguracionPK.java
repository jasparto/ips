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
public class ParametrosConfiguracionPK implements Serializable {

    private int codInstitucion;
//    private int codEstablecimiento;
    private Long codParametro;

    public ParametrosConfiguracionPK() {
    }

    public ParametrosConfiguracionPK(int codInstitucion, Long codParametro) {
        this.codInstitucion = codInstitucion;
        this.codParametro = codParametro;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public Long getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(Long codParametro) {
        this.codParametro = codParametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += codParametro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosConfiguracionPK)) {
            return false;
        }
        ParametrosConfiguracionPK other = (ParametrosConfiguracionPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codParametro != other.codParametro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ParametrosConfiguracionPK[ codInstitucion=" + codInstitucion + ", codParametro=" + codParametro + " ]";
    }

}
