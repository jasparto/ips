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
public class ProgramasPK implements Serializable, Cloneable {

    private int codInstitucion;

//    private int codEstablecimiento;
    private Long codPrograma;

    private int codEntidad;

    private int codContrato;

    private int codCliente;

    private int codSubcuenta;

    public ProgramasPK() {
    }

    public ProgramasPK(int codInstitucion, Long codPrograma) {
        this.codInstitucion = codInstitucion;
        this.codPrograma = codPrograma;
    }

    public ProgramasPK(int codInstitucion, Long codPrograma, int codEntidad, int codContrato, int codCliente, int codSubcuenta) {
        this.codInstitucion = codInstitucion;
        this.codPrograma = codPrograma;
        this.codEntidad = codEntidad;
        this.codContrato = codContrato;
        this.codCliente = codCliente;
        this.codSubcuenta = codSubcuenta;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

//    public int getCodEstablecimiento() {
//        return codEstablecimiento;
//    }
//
//    public void setCodEstablecimiento(int codEstablecimiento) {
//        this.codEstablecimiento = codEstablecimiento;
//    }
    public Long getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Long codPrograma) {
        this.codPrograma = codPrograma;
    }

    public int getCodEntidad() {
        return codEntidad;
    }

    public void setCodEntidad(int codEntidad) {
        this.codEntidad = codEntidad;
    }

    public int getCodContrato() {
        return codContrato;
    }

    public void setCodContrato(int codContrato) {
        this.codContrato = codContrato;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodSubcuenta() {
        return codSubcuenta;
    }

    public void setCodSubcuenta(int codSubcuenta) {
        this.codSubcuenta = codSubcuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
//        hash += (int) codEstablecimiento;
        hash += codPrograma;
        hash += (int) codEntidad;
        hash += (int) codContrato;
        hash += (int) codCliente;
        hash += (int) codSubcuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramasPK)) {
            return false;
        }
        ProgramasPK other = (ProgramasPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
//        if (this.codEstablecimiento != other.codEstablecimiento) {
//            return false;
//        }
        if (this.codPrograma != other.codPrograma) {
            return false;
        }
        if (this.codEntidad != other.codEntidad) {
            return false;
        }
        if (this.codContrato != other.codContrato) {
            return false;
        }
        if (this.codCliente != other.codCliente) {
            return false;
        }
        if (this.codSubcuenta != other.codSubcuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ProgramasPK[ codInstitucion=" + codInstitucion + ", codPrograma=" + codPrograma + ", codEntidad=" + codEntidad + ", codContrato=" + codContrato + ", codCliente=" + codCliente + ", codSubcuenta=" + codSubcuenta + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = null;
        clone = super.clone();
        return clone;
    }

}
