/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codMunicipio;

    private String nombre;

    private Departamentos departamentos;
    private Set<Establecimiento> establecimientosSet;

    public Municipios() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCodMunicipio() != null ? getCodMunicipio().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        if ((this.getCodMunicipio() == null && other.getCodMunicipio() != null) || (this.getCodMunicipio() != null && !this.codMunicipio.equals(other.codMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.Municipios[ codMunicipio=" + getCodMunicipio() + " ]";
    }

    /**
     * @return the establecimientosSet
     */
    public Set<Establecimiento> getEstablecimientosSet() {
        return establecimientosSet;
    }

    /**
     * @param establecimientosSet the establecimientosSet to set
     */
    public void setEstablecimientosSet(Set<Establecimiento> establecimientosSet) {
        this.establecimientosSet = establecimientosSet;
    }

    /**
     * @return the codMunicipio
     */
    public String getCodMunicipio() {
        return codMunicipio;
    }

    /**
     * @param codMunicipio the codMunicipio to set
     */
    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

}
