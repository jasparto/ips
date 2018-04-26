/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class DireccionesPacientePK implements Serializable {
    
    private int codIdentidad;
    
    private String documentoBeneficiario;
    
    private String direccion;
    
    private int codLista;
    
    private int descripcionCodDetalle;

    public DireccionesPacientePK() {
    }

    public DireccionesPacientePK(int codIdentidad, String documentoBeneficiario, String direccion, int codLista, int descripcionCodDetalle) {
        this.codIdentidad = codIdentidad;
        this.documentoBeneficiario = documentoBeneficiario;
        this.direccion = direccion;
        this.codLista = codLista;
        this.descripcionCodDetalle = descripcionCodDetalle;
    }

    public int getCodIdentidad() {
        return codIdentidad;
    }

    public void setCodIdentidad(int codIdentidad) {
        this.codIdentidad = codIdentidad;
    }

    public String getDocumentoBeneficiario() {
        return documentoBeneficiario;
    }

    public void setDocumentoBeneficiario(String documentoBeneficiario) {
        this.documentoBeneficiario = documentoBeneficiario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodLista() {
        return codLista;
    }

    public void setCodLista(int codLista) {
        this.codLista = codLista;
    }

    public int getDescripcionCodDetalle() {
        return descripcionCodDetalle;
    }

    public void setDescripcionCodDetalle(int descripcionCodDetalle) {
        this.descripcionCodDetalle = descripcionCodDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codIdentidad;
        hash += (documentoBeneficiario != null ? documentoBeneficiario.hashCode() : 0);
        hash += (direccion != null ? direccion.hashCode() : 0);
        hash += (int) codLista;
        hash += (int) descripcionCodDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesPacientePK)) {
            return false;
        }
        DireccionesPacientePK other = (DireccionesPacientePK) object;
        if (this.codIdentidad != other.codIdentidad) {
            return false;
        }
        if ((this.documentoBeneficiario == null && other.documentoBeneficiario != null) || (this.documentoBeneficiario != null && !this.documentoBeneficiario.equals(other.documentoBeneficiario))) {
            return false;
        }
        if ((this.direccion == null && other.direccion != null) || (this.direccion != null && !this.direccion.equals(other.direccion))) {
            return false;
        }
        if (this.codLista != other.codLista) {
            return false;
        }
        if (this.descripcionCodDetalle != other.descripcionCodDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.paciente.modelo.DireccionesPacientePK[ codIdentidad=" + codIdentidad + ", documentoBeneficiario=" + documentoBeneficiario + ", direccion=" + direccion + ", codLista=" + codLista + ", descripcionCodDetalle=" + descripcionCodDetalle + " ]";
    }
    
}
