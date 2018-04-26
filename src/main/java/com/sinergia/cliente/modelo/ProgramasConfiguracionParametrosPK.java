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
public class ProgramasConfiguracionParametrosPK implements Serializable {

    private int codInstitucion;
    private Long codConfiguracion;
    private Long codParametro;

    public ProgramasConfiguracionParametrosPK() {
    }

    public ProgramasConfiguracionParametrosPK(int codInstitucion, Long codConfiguracion, Long codParametro) {
        this.codInstitucion = codInstitucion;
        this.codConfiguracion = codConfiguracion;
        this.codParametro = codParametro;
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
        hash += codConfiguracion;
        hash += codParametro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramasConfiguracionParametrosPK)) {
            return false;
        }
        ProgramasConfiguracionParametrosPK other = (ProgramasConfiguracionParametrosPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codConfiguracion != other.codConfiguracion) {
            return false;
        }
        if (this.codParametro != other.codParametro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ProgramasConfiguracionParametros2PK[ codInstitucion=" + codInstitucion + ", codConfiguracion=" + codConfiguracion + ", codParametro=" + codParametro + " ]";
    }

}
