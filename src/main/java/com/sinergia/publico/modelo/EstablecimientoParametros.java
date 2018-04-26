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
public class EstablecimientoParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    protected EstablecimientoParametrosPK establecimientoParametrosPK;
    private String valor;
    private Parametros parametros = new Parametros();
    private Establecimiento establecimiento;

    public EstablecimientoParametros() {
    }

    public EstablecimientoParametros(EstablecimientoParametrosPK establecimientoParametrosPK) {
        this.establecimientoParametrosPK = establecimientoParametrosPK;
    }

    public EstablecimientoParametros(EstablecimientoParametrosPK establecimientoParametrosPK, String valor) {
        this.establecimientoParametrosPK = establecimientoParametrosPK;
        this.valor = valor;
    }

    public EstablecimientoParametros(int codInstitucion, int codEstablecimiento, Long codParametro) {
        this.establecimientoParametrosPK = new EstablecimientoParametrosPK(codInstitucion, codEstablecimiento, codParametro);
    }

    public EstablecimientoParametrosPK getEstablecimientoParametrosPK() {
        return establecimientoParametrosPK;
    }

    public void setEstablecimientoParametrosPK(EstablecimientoParametrosPK establecimientoParametrosPK) {
        this.establecimientoParametrosPK = establecimientoParametrosPK;
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
        hash += (establecimientoParametrosPK != null ? establecimientoParametrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstablecimientoParametros)) {
            return false;
        }
        EstablecimientoParametros other = (EstablecimientoParametros) object;
        if ((this.establecimientoParametrosPK == null && other.establecimientoParametrosPK != null) || (this.establecimientoParametrosPK != null && !this.establecimientoParametrosPK.equals(other.establecimientoParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.EstablecimientoParametros[ establecimientoParametrosPK=" + establecimientoParametrosPK + " ]";
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

}
