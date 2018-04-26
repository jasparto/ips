/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.facturacion.controlador.GestorFacturas;
import com.sinergia.facturacion.modelo.Facturas;
import com.sinergia.modelo.Sesion;
import com.sinergia.paciente.controlador.GestorPacientes;
import com.sinergia.publico.controlador.GestorLista;
import com.sinergia.publico.modelo.DetalleLista;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.publico.vista.UILista;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiFacturas")
@SessionScoped
public class UIFacturas implements Serializable {

    @ManagedProperty("#{gestorFacturas}")
    private GestorFacturas gestorFacturas;
    @ManagedProperty("#{gestorLista}")
    private GestorLista gestorLista;

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";
    public static final String TIPO_RESOLUCION_PREFACTURA = "P";
    public static final String TIPO_RESOLUCION_FACTURA = "F";
    public static final String TIPO_RESOLUCION_COPAGO = "C";

    private List<Facturas> facturasList = new ArrayList<>();
    private Lista tiposResolucion;
    private Date fechaInicial = new Date();
    private Date fechaFinal = new Date();

    @PostConstruct
    public void init() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(fechaInicial);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -10);
        this.fechaInicial = gregorianCalendar.getTime();
        this.consultarFacturas();
        this.cargarTiposResolucion();
    }

    private void cargarTiposResolucion() {
        tiposResolucion = new Lista();
        tiposResolucion = gestorLista.cargarLista(UILista.TIPOS_RESOLUCION);
    }

    public void consultarFacturas() {
        try {
            Establecimiento establecimiento = ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            facturasList.addAll(gestorFacturas.cargarFacturasEstablecimientoFecha(establecimiento, fechaInicial, fechaFinal));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void eliminar() {
        UtilMSG.addWarningMsg("Evento no soportado");
    }

    public void guardar() {
        UtilMSG.addWarningMsg("Evento no soportado");
    }

    public void nuevo() {
        UtilMSG.addWarningMsg("Evento no soportado");
    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the gestorFacturas
     */
    public GestorFacturas getGestorFacturas() {
        return gestorFacturas;
    }

    /**
     * @param gestorFacturas the gestorFacturas to set
     */
    public void setGestorFacturas(GestorFacturas gestorFacturas) {
        this.gestorFacturas = gestorFacturas;
    }

    /**
     * @return the facturasList
     */
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    /**
     * @param facturasList the facturasList to set
     */
    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    /**
     * @return the gestorLista
     */
    public GestorLista getGestorLista() {
        return gestorLista;
    }

    /**
     * @param gestorLista the gestorLista to set
     */
    public void setGestorLista(GestorLista gestorLista) {
        this.gestorLista = gestorLista;
    }

    /**
     * @return the tiposResolucion
     */
    public Lista getTiposResolucion() {
        return tiposResolucion;
    }

    /**
     * @param tiposResolucion the tiposResolucion to set
     */
    public void setTiposResolucion(Lista tiposResolucion) {
        this.tiposResolucion = tiposResolucion;
    }

}
