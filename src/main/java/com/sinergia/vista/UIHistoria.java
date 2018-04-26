/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.atencion.controlador.GestorCitas;
import com.sinergia.atencion.controlador.GestorSeccion;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.Seccion;
import com.sinergia.atencion.modelo.SeccionDetalle;
import com.sinergia.atencion.modelo.SeccionDetalleCombos;
import com.sinergia.atencion.modelo.SeccionDetalleCombosCitasTexto;
import com.sinergia.atencion.modelo.SeccionDetalleCombosCitasTextoPK;
import com.sinergia.atencion.modelo.SeccionDetalleCombosPK;
import com.sinergia.atencion.modelo.SeccionDetallePK;
import com.sinergia.atencion.vista.UICitas;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.paciente.controlador.GestorPacientes;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.paciente.modelo.PacientesPK;
import com.sinergia.publico.controlador.GestorLista;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.publico.vista.UILista;
import com.sinergia.usuario.controlador.GestorUsuario;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiHistoria")
@SessionScoped
public class UIHistoria implements Serializable {

    @ManagedProperty("#{gestorLista}")
    private GestorLista gestorLista;

    @PostConstruct
    public void UIHistoria() {
        this.cargarContexturasPaciente();

    }

    private void cargarContexturasPaciente() {
        contexturasPacienteLista = gestorLista.cargarLista(UILista.CONTEXTURAS_PACIENTE);
    }

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

    private List<Citas> citasList;
    private List<Seccion> seccionList;
    private Citas citaSeleccionada;
    private Lista contexturasPacienteLista;

    @ManagedProperty("#{gestorCitas}")
    private GestorCitas gestorCitas;
    @ManagedProperty("#{gestorPacientes}")
    private GestorPacientes gestorPacientes;
    @ManagedProperty("#{gestorSeccion}")
    private GestorSeccion gestorSeccion;
    @ManagedProperty("#{gestorUsuario}")
    private GestorUsuario gestorUsuario;

    private Pacientes pacientes;
    private Date fecha = new Date();

    public void cargarModalHistoriaPaciente() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            citaSeleccionada = (Citas) UtilJSF.getBean("varCitasPaciente");

            gestorCitas.validarRecursoEstablecimiento(sesion.getEstablecimiento(), sesion.getUsuario(), citaSeleccionada);

            pacientes = new Pacientes(new PacientesPK(citaSeleccionada.getCodIdentidad(), citaSeleccionada.getDocumentoBeneficiario()));
            pacientes = gestorPacientes.consultarPaciente(pacientes);
            seccionList = new ArrayList<>();
            seccionList.addAll(gestorSeccion.cargarSeccionServicioEstablecimiento(sesion.getEstablecimiento(), citaSeleccionada.getCodServicio(), true));

            for (Seccion s : seccionList) {
                for (SeccionDetalle sd : s.getSeccionDetalleSet()) {
                    for (SeccionDetalleCombos sdc : citaSeleccionada.getSeccionDetalleCombosList()) {
                        if (sd.equals(sdc.getSeccionDetalle())) {
                            sd.setValorIngresado(String.valueOf(sdc.getSeccionDetalleCombosPK().getCodCombo()));
                        }
                    }
                }
            }

            for (Seccion s : seccionList) {
                for (SeccionDetalle sd : s.getSeccionDetalleSet()) {
                    for (SeccionDetalleCombosCitasTexto sdcct : citaSeleccionada.getSeccionDetalleCombosCitasTextoSet()) {
                        SeccionDetalleCombosCitasTextoPK sdcctPk = sdcct.getSeccionDetalleCombosCitasTextoPK();
                        SeccionDetallePK pk = new SeccionDetallePK(sdcctPk.getCodInstitucion(), sdcctPk.getCodEstablecimiento(), sdcctPk.getCodSeccion(), sdcctPk.getCodDetalle());
                        if (sd.getSeccionDetallePK().equals(pk)) {
                            sd.setValorIngresado(sdcct.getTexto());
                        }
                    }
                }
            }

            sesion.setDialogo(new Dialogo("dialogos/historiaPaciente.xhtml", "Registro Historia (" + pacientes.getNombre() + " " + pacientes.getApellido() + ")", "clip"));
            UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
            UtilJSF.execute("PF('dialog').show();");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void cargarCitasUsuario() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            citasList = new ArrayList<>();
            gestorUsuario.validarUsuarioHistoriaClinica(sesion.getUsuario());
            citasList.addAll(gestorCitas.cargarCitasUsuario(sesion.getEstablecimiento(), sesion.getUsuario(), fecha));
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void guardarHistoria() {
        try {
            citaSeleccionada.setEstado(UICitas.ESTADO_GUARDADO);
            citaSeleccionada = this.guardarCita(citaSeleccionada);
            UtilMSG.addSuccessMsg("Historia Guardada.");
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public Citas guardarCita(Citas citas) throws Exception {
        Usuarios usuarios = ((Sesion) UtilJSF.getBean("sesion")).getUsuario();
        Set<SeccionDetalleCombos> sdcSet = new HashSet<>();
        Set<SeccionDetalleCombosCitasTexto> sdcTextSet = new HashSet<>();
        for (Seccion s : seccionList) {
            for (SeccionDetalle sd : s.getSeccionDetalleSet()) {
                if (sd.getTipoComponentes() != null && sd.getTipoComponentes().getNombre() != null) {
                    if (sd.getValorIngresado() != null && !sd.getValorIngresado().equalsIgnoreCase("")) {
                        if (citas.getEstado().equalsIgnoreCase(UICitas.ESTADO_FINALIZADO)) {
                            gestorPacientes.almacenarContexturasPaciente(sd, contexturasPacienteLista, citas.getPacientes(), usuarios);
                        }
                        switch (sd.getTipoComponentes().getNombre()) {
                            case SeccionDetalle.SELECT_ONE_MENU:
                                SeccionDetalleCombos sdc = new SeccionDetalleCombos(new SeccionDetalleCombosPK(s.getSeccionPK().getCodSeccion(), sd.getSeccionDetallePK().getCodDetalle(), Long.valueOf(sd.getValorIngresado())));
                                sdc.setCodInstitucion(s.getSeccionPK().getCodInstitucion());
                                sdc.setCodEstablecimiento(s.getSeccionPK().getCodEstablecimiento());
                                sdc.setSeccionDetalle(sd);
                                sdcSet.add(sdc);
                                break;
                            case SeccionDetalle.INPUT_TEXTAREA:
                            case SeccionDetalle.INPUT_TEXT:
                                SeccionDetalleCombos seccionDetalleCombos = (SeccionDetalleCombos) new SeccionDetalleCombos().clone();
                                for (SeccionDetalleCombos sdcText : sd.getSeccionDetalleCombosSet()) {
                                    seccionDetalleCombos = sdcText;
                                }
                                if (seccionDetalleCombos != null) {
                                    SeccionDetalleCombosCitasTexto sdcText = new SeccionDetalleCombosCitasTexto();
                                    SeccionDetalleCombosCitasTextoPK sdcTextPk = new SeccionDetalleCombosCitasTextoPK(s.getSeccionPK().getCodInstitucion(), s.getSeccionPK().getCodEstablecimiento(),
                                            citas.getPk().getCodCita(), s.getSeccionPK().getCodSeccion(), sd.getSeccionDetallePK().getCodDetalle(), seccionDetalleCombos.getSeccionDetalleCombosPK().getCodCombo());
                                    sdcText.setSeccionDetalleCombosCitasTextoPK(sdcTextPk);
                                    sdcText.setTexto(sd.getValorIngresado());
                                    sdcTextSet.add(sdcText);
                                } else {
                                    UtilMSG.addErrorMsg("No se almaceno el campo " + sd.getNombre() + " de la sección " + s.getNombre(),
                                            "Este detalle no tiene creado su item, por tal motivo no se puede almacenar el valor, solicite la adición del item del detalle de la sección.");
                                }
                                break;
                        }
                    }
                }
            }
        }
        citas.setSeccionDetalleCombosList(sdcSet);
        Set<Recurso> recursosSet = new HashSet<>();
        recursosSet.addAll(gestorCitas.cargarRecursosCita(citas));
        citas.setRecursoSet(recursosSet);

        gestorCitas.guardarCita(citas, sdcTextSet);
        return citas;
    }

    public void finalizarHistoria() {
        try {
            citaSeleccionada.setEstado(UICitas.ESTADO_FINALIZADO);
            citaSeleccionada = this.guardarCita(citaSeleccionada);
            UtilMSG.addSuccessMsg("Historia Finalizada.");
            Citas citaEliminar = null;
            for (Citas c : citasList) {
                if (c.equals(citaSeleccionada)) {
                    citaEliminar = c;
                }
            }
            citasList.remove(citaEliminar);
            citasList.add(citaSeleccionada);
            citaSeleccionada = null;
            UtilJSF.execute("PF('dialog').hide();");
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the gestorCitas
     */
    public GestorCitas getGestorCitas() {
        return gestorCitas;
    }

    /**
     * @param gestorCitas the gestorCitas to set
     */
    public void setGestorCitas(GestorCitas gestorCitas) {
        this.gestorCitas = gestorCitas;
    }

    /**
     * @return the citasList
     */
    public List<Citas> getCitasList() {
        return citasList;
    }

    /**
     * @param citasList the citasList to set
     */
    public void setCitasList(List<Citas> citasList) {
        this.citasList = citasList;
    }

    /**
     * @return the citaSeleccionada
     */
    public Citas getCitaSeleccionada() {
        return citaSeleccionada;
    }

    /**
     * @param citaSeleccionada the citaSeleccionada to set
     */
    public void setCitaSeleccionada(Citas citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
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
     * @return the seccionList
     */
    public List<Seccion> getSeccionList() {
        return seccionList;
    }

    /**
     * @param seccionList the seccionList to set
     */
    public void setSeccionList(List<Seccion> seccionList) {
        this.seccionList = seccionList;
    }

    /**
     * @return the gestorSeccion
     */
    public GestorSeccion getGestorSeccion() {
        return gestorSeccion;
    }

    /**
     * @param gestorSeccion the gestorSeccion to set
     */
    public void setGestorSeccion(GestorSeccion gestorSeccion) {
        this.gestorSeccion = gestorSeccion;
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
     * @return the gestorUsuario
     */
    public GestorUsuario getGestorUsuario() {
        return gestorUsuario;
    }

    /**
     * @param gestorUsuario the gestorUsuario to set
     */
    public void setGestorUsuario(GestorUsuario gestorUsuario) {
        this.gestorUsuario = gestorUsuario;
    }

}
