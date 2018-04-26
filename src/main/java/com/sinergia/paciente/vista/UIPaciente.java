/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.vista;

import com.sinergia.paciente.controlador.GestorPacientes;
import com.sinergia.paciente.modelo.ContexturasPaciente;
import com.sinergia.paciente.modelo.DireccionesPaciente;
import com.sinergia.paciente.modelo.DocumentosIdentidad;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.paciente.modelo.PacientesPK;
import com.sinergia.paciente.modelo.TelefonosPaciente;
import com.sinergia.publico.controlador.GestorLista;
import com.sinergia.publico.modelo.DetalleLista;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.publico.vista.UILista;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Hibernate;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiPaciente")
@SessionScoped
public class UIPaciente implements Serializable {

    @ManagedProperty("#{gestorPacientes}")
    private GestorPacientes gestorPacientes;
    @ManagedProperty("#{gestorLista}")
    private GestorLista gestorLista;
    private Pacientes pacientes = new Pacientes(new PacientesPK());

    private List<DocumentosIdentidad> listaDocumentosIdentidad = new ArrayList<>();
    private List<DetalleLista> listaEstadosCiviles = new ArrayList<>();
    private List<DetalleLista> listaSexo = new ArrayList<>();
    private Lista telefonosPacienteLista;
    private Lista direccionesPacienteLista;
    private Lista contexturasPacienteLista;
    private LineChartModel animatedModel1;
    private List<LineChartModel> chartModelContexturasList = new ArrayList<>();

    @PostConstruct
    public void UIPaciente() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(2);
        this.listaDocumentosIdentidad = (List<DocumentosIdentidad>) getGestorPacientes().cargarListaDocumentosIdentidad();
        this.listaEstadosCiviles = this.cargarListaEstadosCiviles();
        this.listaSexo = this.cargarListaSexo();
        this.cargarDetallesPacientes();
    }

    private void cargarDetallesPacientes() {
        telefonosPacienteLista = gestorLista.cargarLista(UILista.TELEFONOS_PACIENTE);
        direccionesPacienteLista = gestorLista.cargarLista(UILista.DIRECCIONES_PACIENTE);
        contexturasPacienteLista = gestorLista.cargarLista(UILista.CONTEXTURAS_PACIENTE);
    }

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

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

    public void consultarPaciente() {
        try {
            pacientes = gestorPacientes.consultarPaciente(pacientes);
            if (pacientes == null) {
                UtilMSG.addWarningMsg("No se encontro pacientes con la información ingresada.");
                pacientes = new Pacientes(new PacientesPK());
                return;
            }
            if (pacientes.getTelefonosPacienteSet() != null && !pacientes.getTelefonosPacienteSet().isEmpty()) {
                for (DetalleLista dl : telefonosPacienteLista.getDetalleListaSet()) {
                    for (TelefonosPaciente t : pacientes.getTelefonosPacienteSet()) {
                        if (dl.getDetalleListaPK().getCodLista() == t.getTelefonosPacientePK().getCodLista()
                                && dl.getDetalleListaPK().getCodDetalle() == t.getTelefonosPacientePK().getDescripcionCodDetalle()) {
                            dl.setValorIngresado(t.getTelefonosPacientePK().getNumero());
                        }
                    }
                }
            }

            if (pacientes.getDireccionesPacienteSet() != null && !pacientes.getDireccionesPacienteSet().isEmpty()) {
                for (DetalleLista dl : direccionesPacienteLista.getDetalleListaSet()) {
                    for (DireccionesPaciente t : pacientes.getDireccionesPacienteSet()) {
                        if (dl.getDetalleListaPK().getCodLista() == t.getDireccionesPacientePK().getCodLista()
                                && dl.getDetalleListaPK().getCodDetalle() == t.getDireccionesPacientePK().getDescripcionCodDetalle()) {
                            dl.setValorIngresado(t.getDireccionesPacientePK().getDireccion());
                        }
                    }
                }
            }

            if (Hibernate.isInitialized(pacientes.getContexturasPacienteSet())
                    && pacientes.getContexturasPacienteSet() != null && !pacientes.getContexturasPacienteSet().isEmpty()) {
                List<ContexturasPaciente> contexturasPacienteList = new ArrayList<>();
                contexturasPacienteList.addAll(pacientes.getContexturasPacienteSet());
                Collections.sort(contexturasPacienteList, new Comparator<ContexturasPaciente>() {
                    @Override
                    public int compare(final ContexturasPaciente object1, final ContexturasPaciente object2) {
                        return object1.getContexturasPacientePK().getFechaRegistro().compareTo(object2.getContexturasPacientePK().getFechaRegistro());
                    }
                });

                LineChartSeries peso = new LineChartSeries();
                LineChartSeries talla = new LineChartSeries();
                int t = 0, p = 0;
                for (DetalleLista dl : contexturasPacienteLista.getDetalleListaSet()) {
                    dl.getChartModelLine().setTitle(pacientes.getNombre() + " " + pacientes.getApellido() + "(" + dl.getNombre() + ")");
                    if (dl.getNombre().equalsIgnoreCase("PESO")) {
                        peso.setLabel(dl.getNombre());
                        dl.setChartModelLine(new LineChartModel());
//                        dl.getChartModelLine().setTitle(pacientes.getNombre() + " " + pacientes.getApellido() + "(" + dl.getNombre() + ")");
                        dl.getChartModelLine().setAnimate(true);
                        dl.getChartModelLine().getAxis(AxisType.Y).setMin(0);
                        dl.getChartModelLine().getAxis(AxisType.Y).setLabel("Kilogramos");
//                        dl.getChartModelLine().getAxis(AxisType.Y).setMax(200);
                        double maximo = 200;
                        for (ContexturasPaciente cp : contexturasPacienteList) {
                            if (dl.getDetalleListaPK().getCodLista() == cp.getContexturasPacientePK().getCodLista()
                                    && dl.getDetalleListaPK().getCodDetalle() == cp.getContexturasPacientePK().getDescripcionCodDetalle()) {
                                peso.set(p, Double.parseDouble(cp.getValor()));
                                maximo = Double.parseDouble(cp.getValor());
                                p++;
                            }
                        }
                        maximo += 10;
                        dl.getChartModelLine().getAxis(AxisType.Y).setMax(maximo);
                        peso.setLabel(dl.getDetalle());
                        dl.getChartModelLine().addSeries(peso);

                    } else if (dl.getNombre().equalsIgnoreCase("TALLA")) {
                        talla.setLabel(dl.getNombre());
                        dl.setChartModelLine(new LineChartModel());
//                        dl.getChartModelLine().setTitle(dl.getNombre());
                        dl.getChartModelLine().setAnimate(true);
                        dl.getChartModelLine().getAxis(AxisType.Y).setMin(0);
                        dl.getChartModelLine().getAxis(AxisType.Y).setLabel("Metros");

                        double maximo = 2.5;
                        for (ContexturasPaciente cp : contexturasPacienteList) {
                            if (dl.getDetalleListaPK().getCodLista() == cp.getContexturasPacientePK().getCodLista()
                                    && dl.getDetalleListaPK().getCodDetalle() == cp.getContexturasPacientePK().getDescripcionCodDetalle()) {
                                talla.set(t, Double.parseDouble(cp.getValor()));
                                maximo = Double.parseDouble(cp.getValor());
                                t++;
                            }
                        }
                        maximo += 0.5;
                        dl.getChartModelLine().getAxis(AxisType.Y).setMax(maximo);
                        talla.setLabel(dl.getDetalle());
                        dl.getChartModelLine().addSeries(talla);
                    }

                }
            }

//            animatedModel1 = initLinearModel();
//            animatedModel1.setTitle("Line Chart");
//            animatedModel1.setAnimate(true);
//            animatedModel1.setLegendPosition("se");
//            Axis yAxis = animatedModel1.getAxis(AxisType.Y);
//            yAxis.setMin(0);
//            yAxis.setMax(2);
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Peso");

        series1.set(1, 0.48);
        series1.set(2, 0.51);
        series1.set(3, 0.54);
        series1.set(4, 0.59);
        series1.set(5, 0.60);
        series1.set(6, 0.62);
        series1.set(7, 0.65);
        series1.set(8, 0.68);

//        LineChartSeries series2 = new LineChartSeries();
//        series2.setLabel("Series 2");
//
//        series2.set(1, 6);
//        series2.set(2, 3);
//        series2.set(3, 2);
//        series2.set(4, 7);
//        series2.set(5, 9);
        model.addSeries(series1);
//        model.addSeries(series2);

        return model;
    }

    private List<DetalleLista> cargarListaEstadosCiviles() {
        List<DetalleLista> estadosCivil = new ArrayList<>();
        Lista lista = (Lista) getGestorLista().cargarLista(UILista.ESTADOS_CIVIL);
        for (DetalleLista e : lista.getDetalleListaSet()) {
            estadosCivil.add(e);
        }
        return estadosCivil;
    }

    private List<DetalleLista> cargarListaSexo() {
        List<DetalleLista> sexoList = new ArrayList<>();
        Lista lista = (Lista) getGestorLista().cargarLista(UILista.SEXO);
        for (DetalleLista e : lista.getDetalleListaSet()) {
            sexoList.add(e);
        }
        return sexoList;
    }

    /**
     * @return the listaDocumentosIdentidad
     */
    public List<DocumentosIdentidad> getListaDocumentosIdentidad() {
        return listaDocumentosIdentidad;
    }

    /**
     * @param listaDocumentosIdentidad the listaDocumentosIdentidad to set
     */
    public void setListaDocumentosIdentidad(List<DocumentosIdentidad> listaDocumentosIdentidad) {
        this.listaDocumentosIdentidad = listaDocumentosIdentidad;
    }

    /**
     * @return the gestorPacientes
     */
    public GestorPacientes getGestorPacientes() {
        return gestorPacientes;
    }

    /**
     * @param gestorPacientes the gestorPacientes to set
     */
    public void setGestorPacientes(GestorPacientes gestorPacientes) {
        this.gestorPacientes = gestorPacientes;
    }

    /**
     * @return the listaEstadosCiviles
     */
    public List<DetalleLista> getListaEstadosCiviles() {
        return listaEstadosCiviles;
    }

    /**
     * @param listaEstadosCiviles the listaEstadosCiviles to set
     */
    public void setListaEstadosCiviles(List<DetalleLista> listaEstadosCiviles) {
        this.listaEstadosCiviles = listaEstadosCiviles;
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
     * @return the listaSexo
     */
    public List<DetalleLista> getListaSexo() {
        return listaSexo;
    }

    /**
     * @param listaSexo the listaSexo to set
     */
    public void setListaSexo(List<DetalleLista> listaSexo) {
        this.listaSexo = listaSexo;
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
     * @return the telefonosPacienteLista
     */
    public Lista getTelefonosPacienteLista() {
        return telefonosPacienteLista;
    }

    /**
     * @param telefonosPacienteLista the telefonosPacienteLista to set
     */
    public void setTelefonosPacienteLista(Lista telefonosPacienteLista) {
        this.telefonosPacienteLista = telefonosPacienteLista;
    }

    /**
     * @return the direccionesPacienteLista
     */
    public Lista getDireccionesPacienteLista() {
        return direccionesPacienteLista;
    }

    /**
     * @param direccionesPacienteLista the direccionesPacienteLista to set
     */
    public void setDireccionesPacienteLista(Lista direccionesPacienteLista) {
        this.direccionesPacienteLista = direccionesPacienteLista;
    }

    /**
     * @return the animatedModel1
     */
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    /**
     * @param animatedModel1 the animatedModel1 to set
     */
    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

    /**
     * @return the chartModelContexturasList
     */
    public List<LineChartModel> getChartModelContexturasList() {
        return chartModelContexturasList;
    }

    /**
     * @param chartModelContexturasList the chartModelContexturasList to set
     */
    public void setChartModelContexturasList(List<LineChartModel> chartModelContexturasList) {
        this.chartModelContexturasList = chartModelContexturasList;
    }

    /**
     * @return the contexturasPacienteLista
     */
    public Lista getContexturasPacienteLista() {
        return contexturasPacienteLista;
    }

    /**
     * @param contexturasPacienteLista the contexturasPacienteLista to set
     */
    public void setContexturasPacienteLista(Lista contexturasPacienteLista) {
        this.contexturasPacienteLista = contexturasPacienteLista;
    }
}
