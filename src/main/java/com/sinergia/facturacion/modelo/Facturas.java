/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.modelo;

import com.sinergia.atencion.modelo.Citas;
import com.sinergia.cliente.modelo.Entidades;
import com.sinergia.paciente.modelo.Pacientes;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class Facturas implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String ESTADO_FACTURADO = "F";

    protected FacturasPK facturasPK;

    private String prefijoFactura;

    private String numeroFactura;

    private int codCliente;
    private Long codConfiguracion;
    private int codEntidad;

    private int codSubcuenta;

    private int codLista;

    private Integer totalFactura;

    private Integer totalAnticipo;
    private int totalIva;
    private Date fecha;
    private Date fechaVencimiento;
    private String documentoUsuario;
    private String documentoFacturador;
    private Date fechaRegistro;
    private String texto;
    private String estado;
    private int codContrato;
    private Integer totalAnulado;
    private int totalDescuento;
    private Integer numeroCopias;
    private String resolucion;
    private String sincroniza = "N";
    private String monto;
    private String detallePaciente;
    private long codCita;
    private String numeroAutorizacion;
    private FacturasConfiguracion facturasConfiguracion;
    private Set<FacturasDetalle> facturasDetalleSet;
    private Pacientes pacientes;
    private Entidades entidades;
    private Citas citas;

    public Facturas() {
    }

    public Facturas(FacturasPK facturasPK) {
        this.facturasPK = facturasPK;
    }

    public Facturas(FacturasPK facturasPK, String prefijoFactura, int codCliente, int codEntidad, int codSubcuenta, int codLista, int totalIva, Date fecha, Date fechaVencimiento, String estado, int codContrato, int totalDescuento) {
        this.facturasPK = facturasPK;
        this.prefijoFactura = prefijoFactura;
        this.codCliente = codCliente;
        this.codEntidad = codEntidad;
        this.codSubcuenta = codSubcuenta;
        this.codLista = codLista;
        this.totalIva = totalIva;
        this.fecha = fecha;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.codContrato = codContrato;
        this.totalDescuento = totalDescuento;
    }

    public Facturas(int codInstitucion, int codEstablecimiento, int codFactura, int codIdentidad, String documentoBeneficiario) {
        this.facturasPK = new FacturasPK(codInstitucion, codEstablecimiento, codFactura, codIdentidad, documentoBeneficiario);
    }

    public FacturasPK getFacturasPK() {
        return facturasPK;
    }

    public void setFacturasPK(FacturasPK facturasPK) {
        this.facturasPK = facturasPK;
    }

    public String getPrefijoFactura() {
        return prefijoFactura;
    }

    public void setPrefijoFactura(String prefijoFactura) {
        this.prefijoFactura = prefijoFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodEntidad() {
        return codEntidad;
    }

    public void setCodEntidad(int codEntidad) {
        this.codEntidad = codEntidad;
    }

    public int getCodSubcuenta() {
        return codSubcuenta;
    }

    public void setCodSubcuenta(int codSubcuenta) {
        this.codSubcuenta = codSubcuenta;
    }

    public int getCodLista() {
        return codLista;
    }

    public void setCodLista(int codLista) {
        this.codLista = codLista;
    }

    public Integer getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Integer totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Integer getTotalAnticipo() {
        return totalAnticipo;
    }

    public void setTotalAnticipo(Integer totalAnticipo) {
        this.totalAnticipo = totalAnticipo;
    }

    public int getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(int totalIva) {
        this.totalIva = totalIva;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getDocumentoFacturador() {
        return documentoFacturador;
    }

    public void setDocumentoFacturador(String documentoFacturador) {
        this.documentoFacturador = documentoFacturador;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodContrato() {
        return codContrato;
    }

    public void setCodContrato(int codContrato) {
        this.codContrato = codContrato;
    }

    public Integer getTotalAnulado() {
        return totalAnulado;
    }

    public void setTotalAnulado(Integer totalAnulado) {
        this.totalAnulado = totalAnulado;
    }

    public int getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(int totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public Integer getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(Integer numeroCopias) {
        this.numeroCopias = numeroCopias;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public long getCodCita() {
        return codCita;
    }

    public void setCodCita(long codCita) {
        this.codCita = codCita;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public FacturasConfiguracion getFacturasConfiguracion() {
        return facturasConfiguracion;
    }

    public void setFacturasConfiguracion(FacturasConfiguracion facturasConfiguracion) {
        this.facturasConfiguracion = facturasConfiguracion;
    }

    @XmlTransient
    public Set<FacturasDetalle> getFacturasDetalleSet() {
        return facturasDetalleSet;
    }

    public void setFacturasDetalleSet(Set<FacturasDetalle> facturasDetalleSet) {
        this.facturasDetalleSet = facturasDetalleSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturasPK != null ? facturasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.facturasPK == null && other.facturasPK != null) || (this.facturasPK != null && !this.facturasPK.equals(other.facturasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.facturacion.modelo.Facturas[ facturasPK=" + facturasPK + " ]";
    }

    /**
     * @return the detallePaciente
     */
    public String getDetallePaciente() {
        return detallePaciente;
    }

    /**
     * @param detallePaciente the detallePaciente to set
     */
    public void setDetallePaciente(String detallePaciente) {
        this.detallePaciente = detallePaciente;
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
     * @return the codConfiguracion
     */
    public Long getCodConfiguracion() {
        return codConfiguracion;
    }

    /**
     * @param codConfiguracion the codConfiguracion to set
     */
    public void setCodConfiguracion(Long codConfiguracion) {
        this.codConfiguracion = codConfiguracion;
    }

    /**
     * @return the sincroniza
     */
    public String getSincroniza() {
        return sincroniza;
    }

    /**
     * @param sincroniza the sincroniza to set
     */
    public void setSincroniza(String sincroniza) {
        this.sincroniza = sincroniza;
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
     * @return the citas
     */
    public Citas getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(Citas citas) {
        this.citas = citas;
    }

}
