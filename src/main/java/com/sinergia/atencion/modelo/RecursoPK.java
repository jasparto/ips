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
public class RecursoPK implements Serializable, Cloneable {
    private int codInstitucion;
    private Long codRecurso;

    public RecursoPK() {
    }

    public RecursoPK(Long codRecurso) {
        this.codRecurso = codRecurso;
    }

    public RecursoPK(int codInstitucion, Long codRecurso) {
        this.codInstitucion = codInstitucion;
        this.codRecurso = codRecurso;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }
    public Long getCodRecurso() {
        return codRecurso;
    }

    public void setCodRecurso(Long codRecurso) {
        this.codRecurso = codRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
//        hash += (int) codInstitucion;
        hash += codRecurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecursoPK)) {
            return false;
        }
        RecursoPK other = (RecursoPK) object;
//        if (this.codInstitucion != other.codInstitucion) {
//            return false;
//        }
        if (this.codRecurso != other.codRecurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "com.tmp.RecursoPK[ codInstitucion=" + codInstitucion + ", codRecurso=" + codRecurso + " ]";
        return "com.tmp.RecursoPK[ codRecurso=" + codRecurso + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            // No deberia ocurrir
        }
        return clone;
    }

}
