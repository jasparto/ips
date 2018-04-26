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
public class Entidades implements Serializable {

    private static final long serialVersionUID = 1L;

    protected EntidadesPK entidadesPK;
    private String nombre;
    private String regimen;
    private String direccion;
    private String telefono;
    private boolean activo;

    private Set<Programas> programasSet;
    private Institucion institucion;

    public Entidades() {
    }

    public Entidades(EntidadesPK entidadesPK) {
        this.entidadesPK = entidadesPK;
    }

    public Entidades(EntidadesPK entidadesPK, String nombre) {
        this.entidadesPK = entidadesPK;
        this.nombre = nombre;
    }

    public Entidades(int codInstitucion, int codEntidad) {
        this.entidadesPK = new EntidadesPK(codInstitucion, codEntidad);
    }

    public EntidadesPK getEntidadesPK() {
        return entidadesPK;
    }

    public void setEntidadesPK(EntidadesPK entidadesPK) {
        this.entidadesPK = entidadesPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (entidadesPK != null ? entidadesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidades)) {
            return false;
        }
        Entidades other = (Entidades) object;
        if ((this.entidadesPK == null && other.entidadesPK != null) || (this.entidadesPK != null && !this.entidadesPK.equals(other.entidadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.Entidades[ entidadesPK=" + entidadesPK + " ]";
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
