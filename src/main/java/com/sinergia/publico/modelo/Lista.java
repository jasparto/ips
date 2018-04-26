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

public class Lista implements Serializable {
    
    private Integer codLista;
    private String nombre;
    
    private String aplicativo;
    private Set<DetalleLista> detalleListaSet;

    public Lista() {
    }

    public Lista(Integer codLista) {
        this.codLista = codLista;
    }

    public Integer getCodLista() {
        return codLista;
    }

    public void setCodLista(Integer codLista) {
        this.codLista = codLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAplicativo() {
        return aplicativo;
    }

    public void setAplicativo(String aplicativo) {
        this.aplicativo = aplicativo;
    }

    @XmlTransient
    public Set<DetalleLista> getDetalleListaSet() {
        return detalleListaSet;
    }

    public void setDetalleListaSet(Set<DetalleLista> detalleListaSet) {
        this.detalleListaSet = detalleListaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLista != null ? codLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.codLista == null && other.codLista != null) || (this.codLista != null && !this.codLista.equals(other.codLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.Lista[ codLista=" + codLista + " ]";
    }
    
}
