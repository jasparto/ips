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

public class DireccionesPaciente implements Serializable {
    private static final long serialVersionUID = 1L;

    protected DireccionesPacientePK direccionesPacientePK;

    private Date fechaRegistro;
    
    private Date fechaActualizacion;
    
    private String usuario;
    
    private String aplicacion;
    private Pacientes pacientes;

    public DireccionesPaciente() {
    }

    public DireccionesPaciente(DireccionesPacientePK direccionesPacientePK) {
        this.direccionesPacientePK = direccionesPacientePK;
    }

    public DireccionesPaciente(short codIdentidad, String documentoBeneficiario, String direccion, int codLista, int descripcionCodDetalle) {
        this.direccionesPacientePK = new DireccionesPacientePK(codIdentidad, documentoBeneficiario, direccion, codLista, descripcionCodDetalle);
    }

    public DireccionesPacientePK getDireccionesPacientePK() {
        return direccionesPacientePK;
    }

    public void setDireccionesPacientePK(DireccionesPacientePK direccionesPacientePK) {
        this.direccionesPacientePK = direccionesPacientePK;
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
        hash += (direccionesPacientePK != null ? direccionesPacientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesPaciente)) {
            return false;
        }
        DireccionesPaciente other = (DireccionesPaciente) object;
        if ((this.direccionesPacientePK == null && other.direccionesPacientePK != null) || (this.direccionesPacientePK != null && !this.direccionesPacientePK.equals(other.direccionesPacientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.paciente.modelo.DireccionesPaciente[ direccionesPacientePK=" + direccionesPacientePK + " ]";
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
