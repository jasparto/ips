/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class ServiciosListaPK implements Serializable {

    private int codInstitucion;

//    private int codEstablecimiento;

    private int codLista;

    private Long codServicio;

    public ServiciosListaPK() {
    }

    public ServiciosListaPK(int codInstitucion, int codEstablecimiento, int codLista, Long codServicio) {
        this.codInstitucion = codInstitucion;
//        this.codEstablecimiento = codEstablecimiento;
        this.codLista = codLista;
        this.codServicio = codServicio;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodLista() {
        return codLista;
    }

    public void setCodLista(int codLista) {
        this.codLista = codLista;
    }

    public Long getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(Long codServicio) {
        this.codServicio = codServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += (int) codLista;
        hash += codServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosListaPK)) {
            return false;
        }
        ServiciosListaPK other = (ServiciosListaPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codLista != other.codLista) {
            return false;
        }
        if (this.codServicio != other.codServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ServiciosListaPK[ codInstitucion=" + codInstitucion + ", codLista=" + codLista + ", codServicio=" + codServicio + " ]";
    }

}
