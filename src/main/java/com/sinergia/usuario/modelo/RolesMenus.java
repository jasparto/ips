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

public class RolesMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    protected RolesMenusPK rolesMenusPK;
    private Roles roles;
    private Integer pemisoBinario = 0;

    public RolesMenus() {
    }

    public RolesMenus(RolesMenusPK rolesMenusPK) {
        this.rolesMenusPK = rolesMenusPK;
    }

    public RolesMenus(int codInstitucion, Long codRol, Long codMenu) {
        this.rolesMenusPK = new RolesMenusPK(codInstitucion, codRol, codMenu);
    }

    public RolesMenusPK getRolesMenusPK() {
        return rolesMenusPK;
    }

    public void setRolesMenusPK(RolesMenusPK rolesMenusPK) {
        this.rolesMenusPK = rolesMenusPK;
    }

    public Integer getPemisoBinario() {
        return pemisoBinario;
    }

    public void setPemisoBinario(Integer pemisoBinario) {
        this.pemisoBinario = pemisoBinario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesMenusPK != null ? rolesMenusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesMenus)) {
            return false;
        }
        RolesMenus other = (RolesMenus) object;
        if ((this.rolesMenusPK == null && other.rolesMenusPK != null) || (this.rolesMenusPK != null && !this.rolesMenusPK.equals(other.rolesMenusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.RolesMenus[ rolesMenusPK=" + rolesMenusPK + " ]";
    }

    /**
     * @return the roles
     */
    public Roles getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    
}
