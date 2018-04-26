/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class UsuariosInstitucionPK implements Serializable {
    
    
    private int codInstitucion;
    
    private int codDocumento;
    
    private String documentoUsuario;

    public UsuariosInstitucionPK() {
    }

    public UsuariosInstitucionPK(int codInstitucion, int codDocumento, String documentoUsuario) {
        this.codInstitucion = codInstitucion;
        this.codDocumento = codDocumento;
        this.documentoUsuario = documentoUsuario;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(int codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += (int) codDocumento;
        hash += (documentoUsuario != null ? documentoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosInstitucionPK)) {
            return false;
        }
        UsuariosInstitucionPK other = (UsuariosInstitucionPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codDocumento != other.codDocumento) {
            return false;
        }
        if ((this.documentoUsuario == null && other.documentoUsuario != null) || (this.documentoUsuario != null && !this.documentoUsuario.equals(other.documentoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.UsuariosInstitucionPK[ codInstitucion=" + codInstitucion + ", codDocumento=" + codDocumento + ", documentoUsuario=" + documentoUsuario + " ]";
    }
    
}
