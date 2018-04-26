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
public class TipoRecursoPK implements Serializable {

    private int codInstitucion;
    private Long codTipoRecurso;

    public TipoRecursoPK() {
    }

    public TipoRecursoPK(Long codTipoRecurso) {
        this.codTipoRecurso = codTipoRecurso;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public Long getCodTipoRecurso() {
        return codTipoRecurso;
    }

    public void setCodTipoRecurso(Long codTipoRecurso) {
        this.codTipoRecurso = codTipoRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += codTipoRecurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecursoPK)) {
            return false;
        }
        TipoRecursoPK other = (TipoRecursoPK) object;
        if (this.codTipoRecurso != other.codTipoRecurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.TipoRecursoPK[codTipoRecurso=" + codTipoRecurso + " ]";
    }

}
