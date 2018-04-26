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

public class RecursoUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;

    protected RecursoUsuariosPK recursoUsuariosPK;

    public RecursoUsuarios() {
    }

    public RecursoUsuarios(RecursoUsuariosPK recursoUsuariosPK) {
        this.recursoUsuariosPK = recursoUsuariosPK;
    }

    public RecursoUsuarios(int codInstitucion, Long codRecurso, int codDocumento, String documentoUsuario) {
        this.recursoUsuariosPK = new RecursoUsuariosPK(codInstitucion, codRecurso, codDocumento, documentoUsuario);
    }

    public RecursoUsuariosPK getRecursoUsuariosPK() {
        return recursoUsuariosPK;
    }

    public void setRecursoUsuariosPK(RecursoUsuariosPK recursoUsuariosPK) {
        this.recursoUsuariosPK = recursoUsuariosPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recursoUsuariosPK != null ? recursoUsuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecursoUsuarios)) {
            return false;
        }
        RecursoUsuarios other = (RecursoUsuarios) object;
        if ((this.recursoUsuariosPK == null && other.recursoUsuariosPK != null) || (this.recursoUsuariosPK != null && !this.recursoUsuariosPK.equals(other.recursoUsuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.RecursoUsuarios[ recursoUsuariosPK=" + recursoUsuariosPK + " ]";
    }
    
}
