/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.modelo;

import com.sinergia.utilidades.modelo.UtilFecha;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juliano
 */
public class ContexturasPacientePK implements Serializable {

    private int codIdentidad;

    private String documentoBeneficiario;

    private int codLista;

    private int descripcionCodDetalle;

    private Date fechaRegistro;

    public ContexturasPacientePK() {
    }

    public ContexturasPacientePK(int codIdentidad, String documentoBeneficiario, int codLista, int descripcionCodDetalle, Date fechaRegistro) {
        this.codIdentidad = codIdentidad;
        this.documentoBeneficiario = documentoBeneficiario;
        this.codLista = codLista;
        this.descripcionCodDetalle = descripcionCodDetalle;
        this.fechaRegistro = fechaRegistro;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codIdentidad;
        hash += (documentoBeneficiario != null ? documentoBeneficiario.hashCode() : 0);
        hash += (int) codLista;
        hash += (int) descripcionCodDetalle;
        hash += (fechaRegistro != null ? fechaRegistro.hashCode() : 0);
//        hash += (fechaRegistro != null ? fechaRegistro.getTime() : 0);
//        long diferencia = (new Date().getTime() - (fechaRegistro != null ? fechaRegistro.getTime() : 0));
//        hash = (int) (diferencia / (24 * 60 * 60 * 1000));
//        System.out.println("fecha actual:" + UtilFecha.formatoFecha(new Date(), null, UtilFecha.PATRON_FECHA_YYYYMMDD_HH_MM));
//        System.out.println("fecha registro:" + UtilFecha.formatoFecha(fechaRegistro, null, UtilFecha.PATRON_FECHA_YYYYMMDD_HH_MM));
//        System.out.println("hash:" + hash);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContexturasPacientePK)) {
            return false;
        }
        ContexturasPacientePK other = (ContexturasPacientePK) object;
        if (this.codIdentidad != other.codIdentidad) {
            return false;
        }
        if ((this.documentoBeneficiario == null && other.documentoBeneficiario != null) || (this.documentoBeneficiario != null && !this.documentoBeneficiario.equals(other.documentoBeneficiario))) {
            return false;
        }
        if (this.codLista != other.codLista) {
            return false;
        }
        if (this.descripcionCodDetalle != other.descripcionCodDetalle) {
            return false;
        }
        if ((this.fechaRegistro == null && other.fechaRegistro != null) || (this.fechaRegistro != null && !this.fechaRegistro.equals(other.fechaRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.paciente.modelo.ContexturasPacientePK[ codIdentidad=" + codIdentidad + ", documentoBeneficiario=" + documentoBeneficiario + ", codLista=" + codLista + ", descripcionCodDetalle=" + descripcionCodDetalle + ", fechaRegistro=" + fechaRegistro + " ]";
    }

}
