/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.modelo;

import com.sinergia.cliente.modelo.Entidades;
import com.sinergia.publico.modelo.Establecimiento;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juliano
 */
public class RipsGeneracion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codGeneracion;

    private int codInstitucion;

    private int codEstablecimiento;

    private String codEapb;

    private String nombreResponsable;

    private String usuarioResponsable;

    private int totalRegistros;

    private Date fecha;

    private Date fechaInicio;

    private Date fechaFinal;

    private int totalRegistrosCt;
    private int totalRegistrosAf;
    private int totalRegistrosUs;
    private int totalRegistrosAd;
    private int totalRegistrosAc;
    private int totalRegistrosAp;
    private String codigoArchivo;
    private String nombreEapb;
    private String nombreCaf;
    private int totalRegistrosAm;
    private Entidades entidades;
    private Establecimiento establecimiento;

    public RipsGeneracion() {
    }

    public RipsGeneracion(long codGeneracion) {
        this.codGeneracion = codGeneracion;
    }

    public RipsGeneracion(long codGeneracion, int codEstablecimiento, String codEapb, String usuarioResponsable, int totalRegistros, Date fecha, Date fechaInicio, Date fechaFinal, int totalRegistrosCt, int totalRegistrosAf, int totalRegistrosUs, int totalRegistrosAd, int totalRegistrosAc, int totalRegistrosAp, String codigoArchivo, String nombreEapb, String nombreCaf, int totalRegistrosAm) {
        this.codGeneracion = codGeneracion;
        this.codEstablecimiento = codEstablecimiento;
        this.codEapb = codEapb;
        this.usuarioResponsable = usuarioResponsable;
        this.totalRegistros = totalRegistros;
        this.fecha = fecha;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.totalRegistrosCt = totalRegistrosCt;
        this.totalRegistrosAf = totalRegistrosAf;
        this.totalRegistrosUs = totalRegistrosUs;
        this.totalRegistrosAd = totalRegistrosAd;
        this.totalRegistrosAc = totalRegistrosAc;
        this.totalRegistrosAp = totalRegistrosAp;
        this.codigoArchivo = codigoArchivo;
        this.nombreEapb = nombreEapb;
        this.nombreCaf = nombreCaf;
        this.totalRegistrosAm = totalRegistrosAm;
    }

    public Long getCodGeneracion() {
        return codGeneracion;
    }

    public void setCodGeneracion(long codGeneracion) {
        this.codGeneracion = codGeneracion;
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

    public String getCodEapb() {
        return codEapb;
    }

    public void setCodEapb(String codEapb) {
        this.codEapb = codEapb;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getTotalRegistrosCt() {
        return totalRegistrosCt;
    }

    public void setTotalRegistrosCt(int totalRegistrosCt) {
        this.totalRegistrosCt = totalRegistrosCt;
    }

    public int getTotalRegistrosAf() {
        return totalRegistrosAf;
    }

    public void setTotalRegistrosAf(int totalRegistrosAf) {
        this.totalRegistrosAf = totalRegistrosAf;
    }

    public int getTotalRegistrosUs() {
        return totalRegistrosUs;
    }

    public void setTotalRegistrosUs(int totalRegistrosUs) {
        this.totalRegistrosUs = totalRegistrosUs;
    }

    public int getTotalRegistrosAd() {
        return totalRegistrosAd;
    }

    public void setTotalRegistrosAd(int totalRegistrosAd) {
        this.totalRegistrosAd = totalRegistrosAd;
    }

    public int getTotalRegistrosAc() {
        return totalRegistrosAc;
    }

    public void setTotalRegistrosAc(int totalRegistrosAc) {
        this.totalRegistrosAc = totalRegistrosAc;
    }

    public int getTotalRegistrosAp() {
        return totalRegistrosAp;
    }

    public void setTotalRegistrosAp(int totalRegistrosAp) {
        this.totalRegistrosAp = totalRegistrosAp;
    }

    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    public void setCodigoArchivo(String codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }

    public String getNombreEapb() {
        return nombreEapb;
    }

    public void setNombreEapb(String nombreEapb) {
        this.nombreEapb = nombreEapb;
    }

    public String getNombreCaf() {
        return nombreCaf;
    }

    public void setNombreCaf(String nombreCaf) {
        this.nombreCaf = nombreCaf;
    }

    public int getTotalRegistrosAm() {
        return totalRegistrosAm;
    }

    public void setTotalRegistrosAm(int totalRegistrosAm) {
        this.totalRegistrosAm = totalRegistrosAm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codGeneracion != null ? codGeneracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RipsGeneracion)) {
            return false;
        }
        RipsGeneracion other = (RipsGeneracion) object;
        if ((this.codGeneracion == null && other.codGeneracion != null) || (this.codGeneracion != null && !this.codGeneracion.equals(other.codGeneracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.facturacion.modelo.RipsGeneracion[ codGeneracion=" + codGeneracion + " ]";
    }

    /**
     * @return the entidades
     */
    public Entidades getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(Entidades entidades) {
        this.entidades = entidades;
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
