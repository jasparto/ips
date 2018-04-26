/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codDepartamento;

    private String nombre;

    private Set<Municipios> municipiosSet;

    private Pais pais;

    public Departamentos() {
    }

    public Departamentos(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<Municipios> getMunicipiosSet() {
        return municipiosSet;
    }

    public void setMunicipiosSet(Set<Municipios> municipiosSet) {
        this.municipiosSet = municipiosSet;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCodDepartamento() != null ? getCodDepartamento().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.getCodDepartamento() == null && other.getCodDepartamento() != null) || (this.getCodDepartamento() != null && !this.codDepartamento.equals(other.codDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.Departamentos[ codDepartamento=" + getCodDepartamento() + " ]";
    }

    /**
     * @return the codDepartamento
     */
    public String getCodDepartamento() {
        return codDepartamento;
    }

    /**
     * @param codDepartamento the codDepartamento to set
     */
    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

}
