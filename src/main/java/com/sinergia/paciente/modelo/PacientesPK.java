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

public class PacientesPK implements Serializable {
    private int codIdentidad;
    private String documentoBeneficiario;

    public PacientesPK() {
    }

    public PacientesPK(int codIdentidad, String documentoBeneficiario) {
        this.codIdentidad = codIdentidad;
        this.documentoBeneficiario = documentoBeneficiario;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codIdentidad;
        hash += (documentoBeneficiario != null ? documentoBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacientesPK)) {
            return false;
        }
        PacientesPK other = (PacientesPK) object;
        if (this.codIdentidad != other.codIdentidad) {
            return false;
        }
        if ((this.documentoBeneficiario == null && other.documentoBeneficiario != null) || (this.documentoBeneficiario != null && !this.documentoBeneficiario.equals(other.documentoBeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.PacientesPK[ codIdentidad=" + codIdentidad + ", documentoBeneficiario=" + documentoBeneficiario + " ]";
    }
    
}
