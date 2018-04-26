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
public class ContexturasPaciente implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ContexturasPacientePK contexturasPacientePK;
    private String valor;
    private String usuario;
    private String aplicacion;
    private Pacientes pacientes;

    public ContexturasPaciente() {
    }

    public ContexturasPaciente(ContexturasPacientePK contexturasPacientePK) {
        this.contexturasPacientePK = contexturasPacientePK;
    }

    public ContexturasPaciente(ContexturasPacientePK contexturasPacientePK, String valor) {
        this.contexturasPacientePK = contexturasPacientePK;
        this.valor = valor;
    }

    public ContexturasPaciente(short codIdentidad, String documentoBeneficiario, int codLista, int descripcionCodDetalle, Date fechaRegistro) {
        this.contexturasPacientePK = new ContexturasPacientePK(codIdentidad, documentoBeneficiario, codLista, descripcionCodDetalle, fechaRegistro);
    }

    public ContexturasPacientePK getContexturasPacientePK() {
        return contexturasPacientePK;
    }

    public void setContexturasPacientePK(ContexturasPacientePK contexturasPacientePK) {
        this.contexturasPacientePK = contexturasPacientePK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        hash += (contexturasPacientePK != null ? contexturasPacientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContexturasPaciente)) {
            return false;
        }
        ContexturasPaciente other = (ContexturasPaciente) object;
        if ((this.contexturasPacientePK == null && other.contexturasPacientePK != null) || (this.contexturasPacientePK != null && !this.contexturasPacientePK.equals(other.contexturasPacientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.paciente.modelo.ContexturasPaciente[ contexturasPacientePK=" + contexturasPacientePK + " ]";
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
