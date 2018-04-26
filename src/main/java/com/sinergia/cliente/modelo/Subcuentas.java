/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class Subcuentas implements Serializable {

    private static final long serialVersionUID = 1L;

    private SubcuentasPK subcuentasPK;
    private String nombre;
    private boolean activo;

    private Institucion institucion;
    private Set<Programas> programasSet;

    public Subcuentas() {
    }

    public Subcuentas(SubcuentasPK subcuentasPK) {
        this.subcuentasPK = subcuentasPK;
    }

    public Subcuentas(SubcuentasPK subcuentasPK, String nombre) {
        this.subcuentasPK = subcuentasPK;
        this.nombre = nombre;
    }

    public Subcuentas(int codInstitucion, int codSubcuenta) {
        this.subcuentasPK = new SubcuentasPK(codInstitucion, codSubcuenta);
    }

    public SubcuentasPK getSubcuentasPK() {
        return subcuentasPK;
    }

    public void setSubcuentasPK(SubcuentasPK subcuentasPK) {
        this.subcuentasPK = subcuentasPK;
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
        hash += (getSubcuentasPK() != null ? getSubcuentasPK().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcuentas)) {
            return false;
        }
        Subcuentas other = (Subcuentas) object;
        if ((this.getSubcuentasPK() == null && other.getSubcuentasPK() != null) || (this.getSubcuentasPK() != null && !this.subcuentasPK.equals(other.subcuentasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.Subcuentas[ subcuentasPK=" + getSubcuentasPK() + " ]";
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

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }


}
