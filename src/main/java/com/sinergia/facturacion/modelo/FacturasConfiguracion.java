/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.modelo;

import com.sinergia.publico.modelo.Establecimiento;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class FacturasConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;

    private FacturasConfiguracionPK facturasConfiguracionPK;

    private String descripcion;

    private String prefijoFactura;
    private String tipoResolucion;
    private String resolucion;

    private Integer numeroFactura;
    private Date fechaCierre;

    private Integer numeroInicio;
    private Integer numeroFin;
 
    
    private Date fechaLimiteResolucion;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;

    private Integer numeroFacturaLimite;

    private Set<Facturas> facturasSet;
    private Establecimiento establecimiento;

    public FacturasConfiguracion() {
    }

    public FacturasConfiguracion(FacturasConfiguracionPK facturasConfiguracionPK) {
        this.facturasConfiguracionPK = facturasConfiguracionPK;
    }

    public FacturasConfiguracion(FacturasConfiguracionPK facturasConfiguracionPK, String prefijoFactura) {
        this.facturasConfiguracionPK = facturasConfiguracionPK;
        this.prefijoFactura = prefijoFactura;
    }

    public FacturasConfiguracion(int codInstitucion, int codEstablecimiento, Long codConfiguracion) {
        this.facturasConfiguracionPK = new FacturasConfiguracionPK(codInstitucion, codEstablecimiento, codConfiguracion);
    }

    public FacturasConfiguracionPK getFacturasConfiguracionPK() {
        return facturasConfiguracionPK;
    }

    public void setFacturasConfiguracionPK(FacturasConfiguracionPK facturasConfiguracionPK) {
        this.facturasConfiguracionPK = facturasConfiguracionPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrefijoFactura() {
        return prefijoFactura;
    }

    public void setPrefijoFactura(String prefijoFactura) {
        this.prefijoFactura = prefijoFactura;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaLimiteResolucion() {
        return fechaLimiteResolucion;
    }

    public void setFechaLimiteResolucion(Date fechaLimiteResolucion) {
        this.fechaLimiteResolucion = fechaLimiteResolucion;
    }

    @XmlTransient
    public Set<Facturas> getFacturasSet() {
        return facturasSet;
    }

    public void setFacturasSet(Set<Facturas> facturasSet) {
        this.facturasSet = facturasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getFacturasConfiguracionPK() != null ? getFacturasConfiguracionPK().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturasConfiguracion)) {
            return false;
        }
        FacturasConfiguracion other = (FacturasConfiguracion) object;
        if ((this.getFacturasConfiguracionPK() == null && other.getFacturasConfiguracionPK() != null) || (this.getFacturasConfiguracionPK() != null && !this.facturasConfiguracionPK.equals(other.facturasConfiguracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.facturacion.modelo.FacturasConfiguracion[ facturasConfiguracionPK=" + getFacturasConfiguracionPK() + " ]";
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

    /**
     * @return the tipoResolucion
     */
    public String getTipoResolucion() {
        return tipoResolucion;
    }

    /**
     * @param tipoResolucion the tipoResolucion to set
     */
    public void setTipoResolucion(String tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    /**
     * @return the fechaInicioVigencia
     */
    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    /**
     * @param fechaInicioVigencia the fechaInicioVigencia to set
     */
    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    /**
     * @return the fechaFinVigencia
     */
    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    /**
     * @param fechaFinVigencia the fechaFinVigencia to set
     */
    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    /**
     * @return the numeroInicio
     */
    public Integer getNumeroInicio() {
        return numeroInicio;
    }

    /**
     * @param numeroInicio the numeroInicio to set
     */
    public void setNumeroInicio(Integer numeroInicio) {
        this.numeroInicio = numeroInicio;
    }

    /**
     * @return the numeroFin
     */
    public Integer getNumeroFin() {
        return numeroFin;
    }

    /**
     * @param numeroFin the numeroFin to set
     */
    public void setNumeroFin(Integer numeroFin) {
        this.numeroFin = numeroFin;
    }

    /**
     * @return the numeroFacturaLimite
     */
    public Integer getNumeroFacturaLimite() {
        return numeroFacturaLimite;
    }

    /**
     * @param numeroFacturaLimite the numeroFacturaLimite to set
     */
    public void setNumeroFacturaLimite(Integer numeroFacturaLimite) {
        this.numeroFacturaLimite = numeroFacturaLimite;
    }

    /**
     * @return the resolucion
     */
    public String getResolucion() {
        return resolucion;
    }

    /**
     * @param resolucion the resolucion to set
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

}
