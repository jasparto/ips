/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class UsuariosInstitucion implements Serializable {
    private static final long serialVersionUID = 1L;

    protected UsuariosInstitucionPK usuariosInstitucionPK;
    private Institucion institucion;

    public UsuariosInstitucion() {
    }

    public UsuariosInstitucion(UsuariosInstitucionPK usuariosInstitucionPK) {
        this.usuariosInstitucionPK = usuariosInstitucionPK;
    }

    public UsuariosInstitucion(short codInstitucion, short codDocumento, String documentoUsuario) {
        this.usuariosInstitucionPK = new UsuariosInstitucionPK(codInstitucion, codDocumento, documentoUsuario);
    }

    public UsuariosInstitucionPK getUsuariosInstitucionPK() {
        return usuariosInstitucionPK;
    }

    public void setUsuariosInstitucionPK(UsuariosInstitucionPK usuariosInstitucionPK) {
        this.usuariosInstitucionPK = usuariosInstitucionPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariosInstitucionPK != null ? usuariosInstitucionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosInstitucion)) {
            return false;
        }
        UsuariosInstitucion other = (UsuariosInstitucion) object;
        if ((this.usuariosInstitucionPK == null && other.usuariosInstitucionPK != null) || (this.usuariosInstitucionPK != null && !this.usuariosInstitucionPK.equals(other.usuariosInstitucionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.UsuariosInstitucion[ usuariosInstitucionPK=" + usuariosInstitucionPK + " ]";
    }

    /**
     * @return the institucion
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
    
}
