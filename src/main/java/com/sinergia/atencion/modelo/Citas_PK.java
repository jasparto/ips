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
public class Citas_PK implements Serializable {

    private int codInstitucion;
    private int codEstablecimiento;
    private Long codCita;

    public Citas_PK() {
    }

    public Citas_PK(int codInstitucion, int codEstablecimiento) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
    }

    /**
     * @return the codInstitucion
     */
    public int getCodInstitucion() {
        return codInstitucion;
    }

    /**
     * @param codInstitucion the codInstitucion to set
     */
    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    /**
     * @return the codEstablecimiento
     */
    public int getCodEstablecimiento() {
        return codEstablecimiento;
    }

    /**
     * @param codEstablecimiento the codEstablecimiento to set
     */
    public void setCodEstablecimiento(int codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    /**
     * @return the codCita
     */
    public Long getCodCita() {
        return codCita;
    }

    /**
     * @param codCita the codCita to set
     */
    public void setCodCita(Long codCita) {
        this.codCita = codCita;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codInstitucion;
        hash += (int) codEstablecimiento;
        hash += (long) codCita;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas_PK)) {
            return false;
        }
        Citas_PK other = (Citas_PK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        if (this.codCita != other.codCita) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.CitasPK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + ", codCita=" + codCita + " ]";
    }
}
