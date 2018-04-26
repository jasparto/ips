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

public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Short codPais;
    
    private String nombre;
    
    private Set<Departamentos> departamentosSet;

    public Pais() {
    }

    public Pais(Short codPais) {
        this.codPais = codPais;
    }

    public Short getCodPais() {
        return codPais;
    }

    public void setCodPais(Short codPais) {
        this.codPais = codPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<Departamentos> getDepartamentosSet() {
        return departamentosSet;
    }

    public void setDepartamentosSet(Set<Departamentos> departamentosSet) {
        this.departamentosSet = departamentosSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPais != null ? codPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.codPais == null && other.codPais != null) || (this.codPais != null && !this.codPais.equals(other.codPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.Pais[ codPais=" + codPais + " ]";
    }
    
}
