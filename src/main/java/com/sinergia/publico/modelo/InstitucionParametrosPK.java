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
public class InstitucionParametrosPK implements Serializable {

    private int codInstitucion;

    private Long codParametro;

    public InstitucionParametrosPK() {
    }

    public InstitucionParametrosPK(int codInstitucion, Long codParametro) {
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
        hash += codParametro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstitucionParametrosPK)) {
            return false;
        }
        InstitucionParametrosPK other = (InstitucionParametrosPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codParametro != other.codParametro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.InstitucionParametrosPK[ codInstitucion=" + codInstitucion + ", codParametro=" + codParametro + " ]";
    }

}
