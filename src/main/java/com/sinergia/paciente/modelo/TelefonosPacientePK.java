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

public class TelefonosPacientePK implements Serializable {
    
    private int codIdentidad;
    
    private String documentoBeneficiario;
    
    private String numero;
    
    private int codLista;
    
    private int descripcionCodDetalle;

    public TelefonosPacientePK() {
    }

    public TelefonosPacientePK(int codIdentidad, String documentoBeneficiario, String numero, int codLista, int descripcionCodDetalle) {
        this.codIdentidad = codIdentidad;
        this.documentoBeneficiario = documentoBeneficiario;
        this.numero = numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
        hash += (numero != null ? numero.hashCode() : 0);
        hash += (int) codLista;
        hash += (int) descripcionCodDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelefonosPacientePK)) {
            return false;
        }
        TelefonosPacientePK other = (TelefonosPacientePK) object;
        if (this.codIdentidad != other.codIdentidad) {
            return false;
        }
        if ((this.documentoBeneficiario == null && other.documentoBeneficiario != null) || (this.documentoBeneficiario != null && !this.documentoBeneficiario.equals(other.documentoBeneficiario))) {
            return false;
        }
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
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
        return "com.sinergia.paciente.modelo.TelefonosPacientePK[ codIdentidad=" + codIdentidad + ", documentoBeneficiario=" + documentoBeneficiario + ", numero=" + numero + ", codLista=" + codLista + ", descripcionCodDetalle=" + descripcionCodDetalle + " ]";
    }
    
}
