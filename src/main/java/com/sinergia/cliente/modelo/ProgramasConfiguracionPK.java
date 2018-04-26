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
public class ProgramasConfiguracionPK implements Serializable {

    private int codInstitucion;

//    private int codEstablecimiento;
    private Long codConfiguracion;

    public ProgramasConfiguracionPK() {
    }

    public ProgramasConfiguracionPK(int codInstitucion, Long codConfiguracion) {
        this.codInstitucion = codInstitucion;
        this.codConfiguracion = codConfiguracion;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
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
        hash += (int) codInstitucion;
        hash += codConfiguracion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramasConfiguracionPK)) {
            return false;
        }
        ProgramasConfiguracionPK other = (ProgramasConfiguracionPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codConfiguracion != other.codConfiguracion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ProgramasConfiguracionPK[ codInstitucion=" + codInstitucion + ", codConfiguracion=" + codConfiguracion + " ]";
    }

}
