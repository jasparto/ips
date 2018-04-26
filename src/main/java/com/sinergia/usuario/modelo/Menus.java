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
public class Menus implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    protected MenusPK menusPK;

    private String descripcion;
    private Institucion institucion;

    public Menus() {
    }

    public Menus(MenusPK menusPK) {
        this.menusPK = menusPK;
    }

    public Menus(int codInstitucion, Long codMenu) {
        this.menusPK = new MenusPK(codInstitucion, codMenu);
    }

    public Menus(int codInstitucion, Long codMenu, String descripcion) {
        this.menusPK = new MenusPK(codInstitucion, codMenu);
        this.descripcion = descripcion;
    }

    public MenusPK getMenusPK() {
        return menusPK;
    }

    public void setMenusPK(MenusPK menusPK) {
        this.menusPK = menusPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menusPK != null ? menusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.menusPK == null && other.menusPK != null) || (this.menusPK != null && !this.menusPK.equals(other.menusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.Menus[ menusPK=" + menusPK + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
