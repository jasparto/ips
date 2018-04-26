/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class Servicios implements Serializable {

    protected ServiciosPK serviciosPK;
    private String nombre;
    private Set<TipoRecurso> tipoRecursoSet;
    private Set<TipoRecurso> tipoRecursoRemoverSet;
    private Set<SeccionServicios> seccionServiciosSet;
   
    public Servicios() {
    }

    public Servicios(ServiciosPK serviciosPK) {
        this.serviciosPK = serviciosPK;
    }

    public Servicios(ServiciosPK serviciosPK, String nombre) {
        this.serviciosPK = serviciosPK;
        this.nombre = nombre;
    }

    public Servicios(int codInstitucion, int codEstablecimiento, Long codServicio) {
        this.serviciosPK = new ServiciosPK(codInstitucion, codEstablecimiento, codServicio);
    }

    public ServiciosPK getServiciosPK() {
        return serviciosPK;
    }

    public void setServiciosPK(ServiciosPK serviciosPK) {
        this.serviciosPK = serviciosPK;
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
        hash += (serviciosPK != null ? serviciosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.serviciosPK == null && other.serviciosPK != null) || (this.serviciosPK != null && !this.serviciosPK.equals(other.serviciosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.Servicios[ serviciosPK=" + serviciosPK + " ]";
    }

    /**
     * @return the tipoRecursoSet
     */
    public Collection<? extends TipoRecurso> getTipoRecursoSetToList() {
        Collection<TipoRecurso> tipoRecursoCollection = new ArrayList<>();
        for (TipoRecurso tr : tipoRecursoSet) {
            tipoRecursoCollection.add(tr);
        }
        return tipoRecursoCollection;
    }

    /**
     * @return the tipoRecursoSet
     */
    public Set<TipoRecurso> getTipoRecursoSet() {
        return tipoRecursoSet;
    }

    /**
     * @param tipoRecursoSet the tipoRecursoSet to set
     */
    public void setTipoRecursoSet(Set<TipoRecurso> tipoRecursoSet) {
        this.tipoRecursoSet = tipoRecursoSet;
    }

    /**
     * @return the tipoRecursoRemoverSet
     */
    public Set<TipoRecurso> getTipoRecursoRemoverSet() {
        return tipoRecursoRemoverSet;
    }

    /**
     * @param tipoRecursoRemoverSet the tipoRecursoRemoverSet to set
     */
    public void setTipoRecursoRemoverSet(Set<TipoRecurso> tipoRecursoRemoverSet) {
        this.tipoRecursoRemoverSet = tipoRecursoRemoverSet;
    }

    /**
     * @return the seccionServiciosSet
     */
    public Set<SeccionServicios> getSeccionServiciosSet() {
        return seccionServiciosSet;
    }

    /**
     * @param seccionServiciosSet the seccionServiciosSet to set
     */
    public void setSeccionServiciosSet(Set<SeccionServicios> seccionServiciosSet) {
        this.seccionServiciosSet = seccionServiciosSet;
    }

}
