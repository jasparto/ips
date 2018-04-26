/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class InstitucionParametros implements Serializable {

    private static final long serialVersionUID = 1L;

    protected InstitucionParametrosPK institucionParametrosPK;
    private String valor;
    private Parametros parametros;
    private Institucion institucion;

    public InstitucionParametros() {
        parametros = new Parametros();
    }

    public InstitucionParametros(InstitucionParametrosPK institucionParametrosPK) {
        this.institucionParametrosPK = institucionParametrosPK;
    }

    public InstitucionParametros(InstitucionParametrosPK institucionParametrosPK, String valor) {
        this.institucionParametrosPK = institucionParametrosPK;
        this.valor = valor;
    }

    public InstitucionParametros(int codInstitucion, Long codParametro) {
        this.institucionParametrosPK = new InstitucionParametrosPK(codInstitucion, codParametro);
    }

    public InstitucionParametrosPK getInstitucionParametrosPK() {
        return institucionParametrosPK;
    }

    public void setInstitucionParametrosPK(InstitucionParametrosPK institucionParametrosPK) {
        this.institucionParametrosPK = institucionParametrosPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (institucionParametrosPK != null ? institucionParametrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstitucionParametros)) {
            return false;
        }
        InstitucionParametros other = (InstitucionParametros) object;
        if ((this.institucionParametrosPK == null && other.institucionParametrosPK != null) || (this.institucionParametrosPK != null && !this.institucionParametrosPK.equals(other.institucionParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.InstitucionParametros[ institucionParametrosPK=" + institucionParametrosPK + " ]";
    }

    /**
     * @return the institucion
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

}
