/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import java.io.Serializable;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author juliano
 */
public class DetalleLista implements Serializable {

    protected DetalleListaPK detalleListaPK;
    private String nombre;
    private String activo;
    private Lista lista;
    private String detalle;
    private String valorIngresado;
    private LineChartModel chartModelLine = new LineChartModel();

    public DetalleLista() {
    }

    public DetalleLista(DetalleListaPK detalleListaPK) {
        this.detalleListaPK = detalleListaPK;
    }

    public DetalleLista(DetalleListaPK detalleListaPK, String activo) {
        this.detalleListaPK = detalleListaPK;
        this.activo = activo;
    }

    public DetalleLista(int codLista, int codDetalle) {
        this.detalleListaPK = new DetalleListaPK(codLista, codDetalle);
    }

    public DetalleListaPK getDetalleListaPK() {
        return detalleListaPK;
    }

    public void setDetalleListaPK(DetalleListaPK detalleListaPK) {
        this.detalleListaPK = detalleListaPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleListaPK != null ? detalleListaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleLista)) {
            return false;
        }
        DetalleLista other = (DetalleLista) object;
        if ((this.detalleListaPK == null && other.detalleListaPK != null) || (this.detalleListaPK != null && !this.detalleListaPK.equals(other.detalleListaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.DetalleLista[ detalleListaPK=" + detalleListaPK + " ]";
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the valorIngresado
     */
    public String getValorIngresado() {
        return valorIngresado;
    }

    /**
     * @param valorIngresado the valorIngresado to set
     */
    public void setValorIngresado(String valorIngresado) {
        this.valorIngresado = valorIngresado;
    }

    /**
     * @return the chartModelLine
     */
    public LineChartModel getChartModelLine() {
        return chartModelLine;
    }

    /**
     * @param chartModelLine the chartModelLine to set
     */
    public void setChartModelLine(LineChartModel chartModelLine) {
        this.chartModelLine = chartModelLine;
    }

}
