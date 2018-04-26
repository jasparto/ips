/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Hibernate;

/**
 *
 * @author juliano
 */
public class ListasPrecios implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ListasPreciosPK listasPreciosPK;

    private String nombre;

    private String tipo;

    private Set<ServiciosLista> serviciosListaSet;
    
    private ServiciosLista serviciosLista;

    private Set<ProgramasConfiguracion> programasConfiguracionSet;

    private Institucion institucion;

    public ListasPrecios() {
    }

    public ListasPrecios(ServiciosLista serviciosLista) {
        this.serviciosLista = serviciosLista;
    }

    public ListasPrecios(ListasPreciosPK listasPreciosPK) {
        this.listasPreciosPK = listasPreciosPK;
    }

    public ListasPrecios(ListasPreciosPK listasPreciosPK, String nombre, String tipo) {
        this.listasPreciosPK = listasPreciosPK;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public ListasPrecios(int codInstitucion, int codLista) {
        this.listasPreciosPK = new ListasPreciosPK(codInstitucion, codLista);
    }

    public ListasPreciosPK getListasPreciosPK() {
        return listasPreciosPK;
    }

    public void setListasPreciosPK(ListasPreciosPK listasPreciosPK) {
        this.listasPreciosPK = listasPreciosPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Set<ServiciosLista> getServiciosListaSet() {
        return serviciosListaSet;
    }

    public void setServiciosListaSet(Set<ServiciosLista> serviciosListaSet) {
        this.serviciosListaSet = serviciosListaSet;
    }

    @XmlTransient
    public Set<ProgramasConfiguracion> getProgramasConfiguracionSet() {
        return programasConfiguracionSet;
    }

    public void setProgramasConfiguracionSet(Set<ProgramasConfiguracion> programasConfiguracionSet) {
        this.programasConfiguracionSet = programasConfiguracionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listasPreciosPK != null ? listasPreciosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListasPrecios)) {
            return false;
        }
        ListasPrecios other = (ListasPrecios) object;
        if ((this.listasPreciosPK == null && other.listasPreciosPK != null) || (this.listasPreciosPK != null && !this.listasPreciosPK.equals(other.listasPreciosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ListasPrecios[ listasPreciosPK=" + listasPreciosPK + " ]";
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
     * @return the serviciosLista
     */
    public ServiciosLista getServiciosLista() {
        return serviciosLista;
    }

    /**
     * @param serviciosLista the serviciosLista to set
     */
    public void setServiciosLista(ServiciosLista serviciosLista) {
        this.serviciosLista = serviciosLista;
    }

}
