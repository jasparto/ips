/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import com.sinergia.facturacion.modelo.Facturas;
import com.sinergia.paciente.modelo.Pacientes;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian
 */
@ManagedBean
@SessionScoped
public class Citas implements Serializable {

    private Citas_PK pk;

    private int codIdentidad;
    private String documentoBeneficiario;
    private Date fecha;
    private int duracion;
    private String estado;
    private String observacion;
    private Date fechaRegistro;
    private String sincroniza = "N";
    private Long codServicio;

//    private ArrayList<SeccionDetalleCombos> seccionDetalleCombosList;
    private Set<Recurso> recursoSet;
    private Set<SeccionDetalleCombos> seccionDetalleCombosList;
    private Set<Facturas> facturasSet;
    private Pacientes pacientes;
    private Facturas facturas;
    private Set<SeccionDetalleCombosCitasTexto> seccionDetalleCombosCitasTextoSet;

    public String getDocumentoBeneficiario() {
        return documentoBeneficiario;
    }

    public void setDocumentoBeneficiario(String documentoBeneficiario) {
        this.documentoBeneficiario = documentoBeneficiario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getSincroniza() {
        return sincroniza;
    }

    public void setSincroniza(String sincroniza) {
        this.sincroniza = sincroniza;
    }

    /**
     * @return the codServicio
     */
    public Long getCodServicio() {
        return codServicio;
    }

    /**
     * @param codServicio the codServicio to set
     */
    public void setCodServicio(Long codServicio) {
        this.codServicio = codServicio;
    }

    /**
     * @return the pk
     */
    public Citas_PK getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(Citas_PK pk) {
        this.pk = pk;
    }

    /**
     * @return the seccionDetalleCombosList
     */
    public Set<SeccionDetalleCombos> getSeccionDetalleCombosList() {
        return seccionDetalleCombosList;
    }

    /**
     * @param seccionDetalleCombosList the seccionDetalleCombosList to set
     */
    public void setSeccionDetalleCombosList(Set<SeccionDetalleCombos> seccionDetalleCombosList) {
        this.seccionDetalleCombosList = seccionDetalleCombosList;
    }

    /**
     * @return the codIdentidad
     */
    public int getCodIdentidad() {
        return codIdentidad;
    }

    /**
     * @param codIdentidad the codIdentidad to set
     */
    public void setCodIdentidad(int codIdentidad) {
        this.codIdentidad = codIdentidad;
    }

    /**
     * @return the recursoSet
     */
    public Set<Recurso> getRecursoSet() {
        return recursoSet;
    }

    /**
     * @param recursoSet the recursoSet to set
     */
    public void setRecursoSet(Set<Recurso> recursoSet) {
        this.recursoSet = recursoSet;
    }

    /**
     * @return the pacientes
     */
    public Pacientes getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * @return the facturas
     */
    public Facturas getFacturas() {
        for (Facturas f : facturasSet) {
            facturas = f;
        }
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the facturasSet
     */
    public Set<Facturas> getFacturasSet() {
        return facturasSet;
    }

    /**
     * @param facturasSet the facturasSet to set
     */
    public void setFacturasSet(Set<Facturas> facturasSet) {
        this.facturasSet = facturasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.pk == null && other != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.Citas[ pk=" + pk + " ]";
    }

    /**
     * @return the seccionDetalleCombosCitasTextoSet
     */
    public Set<SeccionDetalleCombosCitasTexto> getSeccionDetalleCombosCitasTextoSet() {
        return seccionDetalleCombosCitasTextoSet;
    }

    /**
     * @param seccionDetalleCombosCitasTextoSet the
     * seccionDetalleCombosCitasTextoSet to set
     */
    public void setSeccionDetalleCombosCitasTextoSet(Set<SeccionDetalleCombosCitasTexto> seccionDetalleCombosCitasTextoSet) {
        this.seccionDetalleCombosCitasTextoSet = seccionDetalleCombosCitasTextoSet;
    }

}
