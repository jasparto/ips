/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class SeccionServiciosPK implements Serializable {

    private int codInstitucion;
    private int codEstablecimiento;
    private Long codSeccion;
    private Long codServicio;

    public SeccionServiciosPK() {
    }

    public SeccionServiciosPK(int codInstitucion, int codEstablecimiento, Long codSeccion, Long codServicio) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
        this.codSeccion = codSeccion;
        this.codServicio = codServicio;
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

    public Long getCodSeccion() {
        return codSeccion;
    }

    public void setCodSeccion(Long codSeccion) {
        this.codSeccion = codSeccion;
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
        hash += (int) codEstablecimiento;
        hash += codSeccion;
        hash += codServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionServiciosPK)) {
            return false;
        }
        SeccionServiciosPK other = (SeccionServiciosPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        if (this.codSeccion != other.codSeccion) {
            return false;
        }
        if (this.codServicio != other.codServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.SeccionServiciosPK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + ", codSeccion=" + codSeccion + ", codServicio=" + codServicio + " ]";
    }

}
