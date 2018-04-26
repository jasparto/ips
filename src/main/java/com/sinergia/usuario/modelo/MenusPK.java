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
public class MenusPK implements Serializable {

    private int codInstitucion;

    private Long codMenu;

    public MenusPK() {
    }

    public MenusPK(int codInstitucion, Long codMenu) {
        this.codInstitucion = codInstitucion;
        this.codMenu = codMenu;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
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
        hash += codMenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenusPK)) {
            return false;
        }
        MenusPK other = (MenusPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codMenu != other.codMenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.MenusPK[ codInstitucion=" + codInstitucion + ", codMenu=" + codMenu + " ]";
    }

}
