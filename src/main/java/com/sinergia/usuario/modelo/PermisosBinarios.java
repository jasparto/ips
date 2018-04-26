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

public class PermisosBinarios implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codPermisoBinario;
    private String nombre;

    public PermisosBinarios() {
    }

    public PermisosBinarios(Integer codPermisoBinario) {
        this.codPermisoBinario = codPermisoBinario;
    }

    public Integer getCodPermisoBinario() {
        return codPermisoBinario;
    }

    public void setCodPermisoBinario(Integer codPermisoBinario) {
        this.codPermisoBinario = codPermisoBinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPermisoBinario != null ? codPermisoBinario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisosBinarios)) {
            return false;
        }
        PermisosBinarios other = (PermisosBinarios) object;
        if ((this.codPermisoBinario == null && other.codPermisoBinario != null) || (this.codPermisoBinario != null && !this.codPermisoBinario.equals(other.codPermisoBinario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.usuario.modelo.PermisosBinarios[ codPermisoBinario=" + codPermisoBinario + " ]";
    }
    
}
