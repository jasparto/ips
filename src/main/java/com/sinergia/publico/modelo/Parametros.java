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

public class Parametros implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long codParametro;
    private String nombre;
    private String entidad;
    
    private Set<InstitucionParametros> institucionParametrosSet;
    private Set<EstablecimientoParametros> establecimientoParametrosSet;

    public Parametros() {
    }

    public Parametros(Long codParametro) {
        this.codParametro = codParametro;
    }

    public Parametros(Long codParametro, String nombre, String entidad) {
        this.codParametro = codParametro;
        this.nombre = nombre;
        this.entidad = entidad;
    }

    public Long getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(Long codParametro) {
        this.codParametro = codParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @XmlTransient
    public Set<InstitucionParametros> getInstitucionParametrosSet() {
        return institucionParametrosSet;
    }

    public void setInstitucionParametrosSet(Set<InstitucionParametros> institucionParametrosSet) {
        this.institucionParametrosSet = institucionParametrosSet;
    }

    @XmlTransient
    public Set<EstablecimientoParametros> getEstablecimientoParametrosSet() {
        return establecimientoParametrosSet;
    }

    public void setEstablecimientoParametrosSet(Set<EstablecimientoParametros> establecimientoParametrosSet) {
        this.establecimientoParametrosSet = establecimientoParametrosSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codParametro != null ? codParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.codParametro == null && other.codParametro != null) || (this.codParametro != null && !this.codParametro.equals(other.codParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.Parametros[ codParametro=" + codParametro + " ]";
    }
    
}
