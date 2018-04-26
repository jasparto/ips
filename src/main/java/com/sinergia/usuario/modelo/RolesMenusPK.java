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

public class RolesMenusPK implements Serializable {

    
    private int codInstitucion;
    private Long codRol;
    private Long codMenu;

    public RolesMenusPK() {
    }

    public RolesMenusPK(int codInstitucion, Long codRol, Long codMenu) {
        this.codInstitucion = codInstitucion;
        this.codRol = codRol;
        this.codMenu = codMenu;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public Long getCodRol() {
        return codRol;
    }

    public void setCodRol(Long codRol) {
        this.codRol = codRol;
    }

    public Long getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(Long codMenu) {
        this.codMenu = codMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += codRol;
        hash += codMenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesMenusPK)) {
            return false;
        }
        RolesMenusPK other = (RolesMenusPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codRol != other.codRol) {
            return false;
        }
        if (this.codMenu != other.codMenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.RolesMenusPK[ codInstitucion=" + codInstitucion + ", codRol=" + codRol + ", codMenu=" + codMenu + " ]";
    }
    
}
