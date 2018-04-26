/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class ParametrosConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ParametrosConfiguracionPK parametrosConfiguracionPK;

    private String nombre;

//    private Set<ProgramasConfiguracion> programasConfiguracionSet;
    private Set<ProgramasConfiguracionParametros> programasConfiguracionParametrosSet;

    public ParametrosConfiguracion() {
    }

    public ParametrosConfiguracion(ParametrosConfiguracionPK parametrosConfiguracionPK) {
        this.parametrosConfiguracionPK = parametrosConfiguracionPK;
    }

    public ParametrosConfiguracion(int codInstitucion, Long codParametro) {
        this.parametrosConfiguracionPK = new ParametrosConfiguracionPK(codInstitucion, codParametro);
    }

    public ParametrosConfiguracionPK getParametrosConfiguracionPK() {
        return parametrosConfiguracionPK;
    }

    public void setParametrosConfiguracionPK(ParametrosConfiguracionPK parametrosConfiguracionPK) {
        this.parametrosConfiguracionPK = parametrosConfiguracionPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//    @XmlTransient
//    public Set<ProgramasConfiguracion> getProgramasConfiguracionSet() {
//        return programasConfiguracionSet;
//    }
//
//    public void setProgramasConfiguracionSet(Set<ProgramasConfiguracion> programasConfiguracionSet) {
//        this.programasConfiguracionSet = programasConfiguracionSet;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametrosConfiguracionPK != null ? parametrosConfiguracionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosConfiguracion)) {
            return false;
        }
        ParametrosConfiguracion other = (ParametrosConfiguracion) object;
        if ((this.parametrosConfiguracionPK == null && other.parametrosConfiguracionPK != null) || (this.parametrosConfiguracionPK != null && !this.parametrosConfiguracionPK.equals(other.parametrosConfiguracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ParametrosConfiguracion[ parametrosConfiguracionPK=" + parametrosConfiguracionPK + " ]";
    }

    /**
     * @return the programasConfiguracionParametrosSet
     */
    @XmlTransient
    public Set<ProgramasConfiguracionParametros> getProgramasConfiguracionParametrosSet() {
        return programasConfiguracionParametrosSet;
    }

    /**
     * @param programasConfiguracionParametrosSet the
     * programasConfiguracionParametrosSet to set
     */
    public void setProgramasConfiguracionParametrosSet(Set<ProgramasConfiguracionParametros> programasConfiguracionParametrosSet) {
        this.programasConfiguracionParametrosSet = programasConfiguracionParametrosSet;
    }

}
