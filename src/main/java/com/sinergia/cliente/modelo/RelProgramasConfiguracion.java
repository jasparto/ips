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

public class RelProgramasConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected RelProgramasConfiguracionPK relProgramasConfiguracionPK;

    public RelProgramasConfiguracion() {
    }

    public RelProgramasConfiguracion(RelProgramasConfiguracionPK relProgramasConfiguracionPK) {
        this.relProgramasConfiguracionPK = relProgramasConfiguracionPK;
    }

    public RelProgramasConfiguracion(int codInstitucion, int codEstablecimiento, Long codPrograma, int codEntidad, int codContrato, int codCliente, int codSubcuenta, Long codConfiguracion) {
        this.relProgramasConfiguracionPK = new RelProgramasConfiguracionPK(codInstitucion, codEstablecimiento, codPrograma, codEntidad, codContrato, codCliente, codSubcuenta, codConfiguracion);
    }

    public RelProgramasConfiguracionPK getRelProgramasConfiguracionPK() {
        return relProgramasConfiguracionPK;
    }

    public void setRelProgramasConfiguracionPK(RelProgramasConfiguracionPK relProgramasConfiguracionPK) {
        this.relProgramasConfiguracionPK = relProgramasConfiguracionPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relProgramasConfiguracionPK != null ? relProgramasConfiguracionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelProgramasConfiguracion)) {
            return false;
        }
        RelProgramasConfiguracion other = (RelProgramasConfiguracion) object;
        if ((this.relProgramasConfiguracionPK == null && other.relProgramasConfiguracionPK != null) || (this.relProgramasConfiguracionPK != null && !this.relProgramasConfiguracionPK.equals(other.relProgramasConfiguracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.RelProgramasConfiguracion[ relProgramasConfiguracionPK=" + relProgramasConfiguracionPK + " ]";
    }
    
}
