/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class TiposContrato implements Serializable {

    private static final long serialVersionUID = 1L;

    protected TiposContratoPK tiposContratoPK;

    private String nombre;

    private Set<Programas> programasSet;
//    private Establecimiento establecimiento;
    private Institucion institucion;

    public TiposContrato() {
    }

    public TiposContrato(TiposContratoPK tiposContratoPK) {
        this.tiposContratoPK = tiposContratoPK;
    }

    public TiposContrato(TiposContratoPK tiposContratoPK, String nombre) {
        this.tiposContratoPK = tiposContratoPK;
        this.nombre = nombre;
    }

    public TiposContrato(int codInstitucion, int codContrato) {
        this.tiposContratoPK = new TiposContratoPK(codInstitucion, codContrato);
    }

    public TiposContratoPK getTiposContratoPK() {
        return tiposContratoPK;
    }

    public void setTiposContratoPK(TiposContratoPK tiposContratoPK) {
        this.tiposContratoPK = tiposContratoPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<Programas> getProgramasSet() {
        return programasSet;
    }

    public void setProgramasSet(Set<Programas> programasSet) {
        this.programasSet = programasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiposContratoPK != null ? tiposContratoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposContrato)) {
            return false;
        }
        TiposContrato other = (TiposContrato) object;
        if ((this.tiposContratoPK == null && other.tiposContratoPK != null) || (this.tiposContratoPK != null && !this.tiposContratoPK.equals(other.tiposContratoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.TiposContrato[ tiposContratoPK=" + tiposContratoPK + " ]";
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
