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
public class ListasPreciosPK implements Serializable {

    private int codInstitucion;

//    private int codEstablecimiento;
    private int codLista;

    public ListasPreciosPK() {
    }

    public ListasPreciosPK(int codInstitucion, int codLista) {
        this.codInstitucion = codInstitucion;
        this.codLista = codLista;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += (int) codLista;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListasPreciosPK)) {
            return false;
        }
        ListasPreciosPK other = (ListasPreciosPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codLista != other.codLista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ListasPreciosPK[ codInstitucion=" + codInstitucion + ", codLista=" + codLista + " ]";
    }

}
