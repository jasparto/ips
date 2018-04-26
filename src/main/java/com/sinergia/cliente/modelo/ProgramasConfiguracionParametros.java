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
public class ProgramasConfiguracionParametros implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProgramasConfiguracionParametrosPK programasConfiguracionParametrosPK;
    private String valor;
    private ProgramasConfiguracion programasConfiguracion;
    private ParametrosConfiguracion parametrosConfiguracion;

    public ProgramasConfiguracionParametros() {
    }

    public ProgramasConfiguracionParametros(ProgramasConfiguracionParametrosPK programasConfiguracionParametros2PK) {
        this.programasConfiguracionParametrosPK = programasConfiguracionParametros2PK;
    }

    public ProgramasConfiguracionParametros(int codInstitucion, Long codConfiguracion, Long codParametro) {
        this.programasConfiguracionParametrosPK = new ProgramasConfiguracionParametrosPK(codInstitucion, codConfiguracion, codParametro);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getProgramasConfiguracionParametrosPK() != null ? getProgramasConfiguracionParametrosPK().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramasConfiguracionParametros)) {
            return false;
        }
        ProgramasConfiguracionParametros other = (ProgramasConfiguracionParametros) object;
        if ((this.getProgramasConfiguracionParametrosPK() == null && other.getProgramasConfiguracionParametrosPK() != null) || (this.getProgramasConfiguracionParametrosPK() != null && !this.programasConfiguracionParametrosPK.equals(other.programasConfiguracionParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ProgramasConfiguracionParametros2[ programasConfiguracionParametros2PK=" + getProgramasConfiguracionParametrosPK() + " ]";
    }

    /**
     * @return the programasConfiguracionParametrosPK
     */
    public ProgramasConfiguracionParametrosPK getProgramasConfiguracionParametrosPK() {
        return programasConfiguracionParametrosPK;
    }

    /**
     * @param programasConfiguracionParametrosPK the
     * programasConfiguracionParametrosPK to set
     */
    public void setProgramasConfiguracionParametrosPK(ProgramasConfiguracionParametrosPK programasConfiguracionParametrosPK) {
        this.programasConfiguracionParametrosPK = programasConfiguracionParametrosPK;
    }

    /**
     * @return the programasConfiguracion
     */
    public ProgramasConfiguracion getProgramasConfiguracion() {
        return programasConfiguracion;
    }

    /**
     * @param programasConfiguracion the programasConfiguracion to set
     */
    public void setProgramasConfiguracion(ProgramasConfiguracion programasConfiguracion) {
        this.programasConfiguracion = programasConfiguracion;
    }

    /**
     * @return the parametrosConfiguracion
     */
    public ParametrosConfiguracion getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    /**
     * @param parametrosConfiguracion the parametrosConfiguracion to set
     */
    public void setParametrosConfiguracion(ParametrosConfiguracion parametrosConfiguracion) {
        this.parametrosConfiguracion = parametrosConfiguracion;
    }

}
