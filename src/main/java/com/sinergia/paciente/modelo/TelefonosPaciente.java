/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juliano
 */

public class TelefonosPaciente implements Serializable {
    private static final long serialVersionUID = 1L;

    protected TelefonosPacientePK telefonosPacientePK;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private String usuario;
    private String aplicacion;
    private Pacientes pacientes;

    public TelefonosPaciente() {
    }

    public TelefonosPaciente(TelefonosPacientePK telefonosPacientePK) {
        this.telefonosPacientePK = telefonosPacientePK;
    }

    public TelefonosPaciente(TelefonosPacientePK telefonosPacientePK, Date fechaRegistro, Date fechaActualizacion) {
        this.telefonosPacientePK = telefonosPacientePK;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public TelefonosPaciente(short codIdentidad, String documentoBeneficiario, String numero, int codLista, int descripcionCodDetalle) {
        this.telefonosPacientePK = new TelefonosPacientePK(codIdentidad, documentoBeneficiario, numero, codLista, descripcionCodDetalle);
    }

    public TelefonosPacientePK getTelefonosPacientePK() {
        return telefonosPacientePK;
    }

    public void setTelefonosPacientePK(TelefonosPacientePK telefonosPacientePK) {
        this.telefonosPacientePK = telefonosPacientePK;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telefonosPacientePK != null ? telefonosPacientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelefonosPaciente)) {
            return false;
        }
        TelefonosPaciente other = (TelefonosPaciente) object;
        if ((this.telefonosPacientePK == null && other.telefonosPacientePK != null) || (this.telefonosPacientePK != null && !this.telefonosPacientePK.equals(other.telefonosPacientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.paciente.modelo.TelefonosPaciente[ telefonosPacientePK=" + telefonosPacientePK + " ]";
    }

    /**
     * @return the pacientes
     */
    public Pacientes getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }
    
}
