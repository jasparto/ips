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

public class FacturasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;

    protected FacturasDetallePK facturasDetallePK;
    
    private int cantidad;
    
    private float valorCosto;
    
    private float valorVenta;
    
    private float valorIva;
    
    private float iva;
    
    private Facturas facturas;

    public FacturasDetalle() {
    }

    public FacturasDetalle(FacturasDetallePK facturasDetallePK) {
        this.facturasDetallePK = facturasDetallePK;
    }

    public FacturasDetalle(FacturasDetallePK facturasDetallePK, int cantidad, float valorCosto, float valorVenta, float valorIva, float iva) {
        this.facturasDetallePK = facturasDetallePK;
        this.cantidad = cantidad;
        this.valorCosto = valorCosto;
        this.valorVenta = valorVenta;
        this.valorIva = valorIva;
        this.iva = iva;
    }

    public FacturasDetalle(short codInstitucion, short codEstablecimiento, int codFactura, Long codServicio, int codIdentidad, String documentoBeneficiario) {
        this.facturasDetallePK = new FacturasDetallePK(codInstitucion, codEstablecimiento, codFactura, codServicio, codIdentidad, documentoBeneficiario);
    }

    public FacturasDetallePK getFacturasDetallePK() {
        return facturasDetallePK;
    }

    public void setFacturasDetallePK(FacturasDetallePK facturasDetallePK) {
        this.facturasDetallePK = facturasDetallePK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(float valorCosto) {
        this.valorCosto = valorCosto;
    }

    public float getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(float valorVenta) {
        this.valorVenta = valorVenta;
    }

    public float getValorIva() {
        return valorIva;
    }

    public void setValorIva(float valorIva) {
        this.valorIva = valorIva;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturasDetallePK != null ? facturasDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturasDetalle)) {
            return false;
        }
        FacturasDetalle other = (FacturasDetalle) object;
        if ((this.facturasDetallePK == null && other.facturasDetallePK != null) || (this.facturasDetallePK != null && !this.facturasDetallePK.equals(other.facturasDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.facturacion.modelo.FacturasDetalle[ facturasDetallePK=" + facturasDetallePK + " ]";
    }
    
}
