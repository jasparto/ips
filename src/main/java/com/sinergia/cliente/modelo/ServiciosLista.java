/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.publico.modelo.Establecimiento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliano
 */
public class ServiciosLista implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ServiciosListaPK serviciosListaPK;

    private float valor = 0;

    private ListasPrecios listasPrecios;
    private Servicios servicios;
    private List<Establecimiento> establecimientoList = new ArrayList<>();

    public ServiciosLista() {
    }

    public ServiciosLista(ServiciosListaPK serviciosListaPK, Servicios servicios) {
        this.serviciosListaPK = serviciosListaPK;
        this.servicios = servicios;
    }

    public ServiciosLista(ServiciosListaPK serviciosListaPK) {
        this.serviciosListaPK = serviciosListaPK;
    }

    public ServiciosLista(ServiciosListaPK serviciosListaPK, float valor) {
        this.serviciosListaPK = serviciosListaPK;
        this.valor = valor;
    }

    public ServiciosLista(short codInstitucion, short codEstablecimiento, short codLista, Long codServicio) {
        this.serviciosListaPK = new ServiciosListaPK(codInstitucion, codEstablecimiento, codLista, codServicio);
    }

    public ServiciosListaPK getServiciosListaPK() {
        return serviciosListaPK;
    }

    public void setServiciosListaPK(ServiciosListaPK serviciosListaPK) {
        this.serviciosListaPK = serviciosListaPK;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public ListasPrecios getListasPrecios() {
        return listasPrecios;
    }

    public void setListasPrecios(ListasPrecios listasPrecios) {
        this.listasPrecios = listasPrecios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviciosListaPK != null ? serviciosListaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosLista)) {
            return false;
        }
        ServiciosLista other = (ServiciosLista) object;
        if ((this.serviciosListaPK == null && other.serviciosListaPK != null) || (this.serviciosListaPK != null && !this.serviciosListaPK.equals(other.serviciosListaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ServiciosLista[ serviciosListaPK=" + serviciosListaPK + " ]";
    }

    /**
     * @return the servicios
     */
    public Servicios getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the establecimientoList
     */
    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    /**
     * @param establecimientoList the establecimientoList to set
     */
    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

}
