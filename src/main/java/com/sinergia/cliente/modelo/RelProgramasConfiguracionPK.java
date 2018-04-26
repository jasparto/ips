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
public class RelProgramasConfiguracionPK implements Serializable {

    private int codInstitucion;
    private int codEstablecimiento;
    private Long codPrograma;
    private int codEntidad;
    private int codContrato;
    private int codCliente;
    private int codSubcuenta;
    private Long codConfiguracion;

    public RelProgramasConfiguracionPK() {
    }

    public RelProgramasConfiguracionPK(int codInstitucion, int codEstablecimiento, Long codPrograma, int codEntidad, int codContrato, int codCliente, int codSubcuenta, Long codConfiguracion) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
        this.codPrograma = codPrograma;
        this.codEntidad = codEntidad;
        this.codContrato = codContrato;
        this.codCliente = codCliente;
        this.codSubcuenta = codSubcuenta;
        this.codConfiguracion = codConfiguracion;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(int codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

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

    public Long getCodConfiguracion() {
        return codConfiguracion;
    }

    public void setCodConfiguracion(Long codConfiguracion) {
        this.codConfiguracion = codConfiguracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += (int) codEstablecimiento;
        hash += codPrograma;
        hash += (int) codEntidad;
        hash += (int) codContrato;
        hash += (int) codCliente;
        hash += (int) codSubcuenta;
        hash += codConfiguracion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelProgramasConfiguracionPK)) {
            return false;
        }
        RelProgramasConfiguracionPK other = (RelProgramasConfiguracionPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
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
        if (this.codConfiguracion != other.codConfiguracion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.RelProgramasConfiguracionPK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + ", codPrograma=" + codPrograma + ", codEntidad=" + codEntidad + ", codContrato=" + codContrato + ", codCliente=" + codCliente + ", codSubcuenta=" + codSubcuenta + ", codConfiguracion=" + codConfiguracion + " ]";
    }

}
