/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class FacturasPK implements Serializable {

    private int codInstitucion;
    private int codEstablecimiento;
    private long codFactura;
    private int codIdentidad;
    private String documentoBeneficiario;

    public FacturasPK() {
    }

    public FacturasPK(int codInstitucion, int codEstablecimiento, long codFactura, int codIdentidad, String documentoBeneficiario) {
        this.codInstitucion = codInstitucion;
        this.codEstablecimiento = codEstablecimiento;
        this.codFactura = codFactura;
        this.codIdentidad = codIdentidad;
        this.documentoBeneficiario = documentoBeneficiario;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public int getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(int codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    public long getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(long codFactura) {
        this.codFactura = codFactura;
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
        hash += (int) codInstitucion;
        hash += (int) codEstablecimiento;
        hash += (int) codFactura;
        hash += (int) codIdentidad;
        hash += (documentoBeneficiario != null ? documentoBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturasPK)) {
            return false;
        }
        FacturasPK other = (FacturasPK) object;
        if (this.codInstitucion != other.codInstitucion) {
            return false;
        }
        if (this.codEstablecimiento != other.codEstablecimiento) {
            return false;
        }
        if (this.codFactura != other.codFactura) {
            return false;
        }
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
        return "com.sinergia.facturacion.modelo.FacturasPK[ codInstitucion=" + codInstitucion + ", codEstablecimiento=" + codEstablecimiento + ", codFactura=" + codFactura + ", codIdentidad=" + codIdentidad + ", documentoBeneficiario=" + documentoBeneficiario + " ]";
    }

}
