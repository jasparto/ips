/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.atencion.controlador.GestorCitas;
import com.sinergia.atencion.controlador.GestorRecursos;
import com.sinergia.atencion.controlador.GestorServicios;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.modelo.Citas_PK;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.atencion.vista.UICitas;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.paciente.controlador.GestorPacientes;
import com.sinergia.paciente.modelo.DocumentosIdentidad;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.paciente.modelo.PacientesPK;
import com.sinergia.publico.controlador.GestorLista;
import com.sinergia.publico.modelo.DetalleLista;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.publico.vista.UILista;
import com.sinergia.utilidades.modelo.UtilFecha;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.awt.event.ActionEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.commons.collections.map.HashedMap;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiAgenda")
@SessionScoped

public class UIAgenda {

    @ManagedProperty("#{gestorServicios}")
    private GestorServicios gestorServicios;
    @ManagedProperty("#{gestorPacientes}")
    private GestorPacientes gestorPacientes;
    @ManagedProperty("#{gestorCitas}")
    private GestorCitas gestorCitas;
    @ManagedProperty("#{gestorLista}")
    private GestorLista gestorLista;
    @ManagedProperty("#{gestorRecursos}")
    private GestorRecursos gestorRecursos;

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

    private List<Servicios> listaServicios = new ArrayList<>();
//    private List<DocumentosIdentidad> listaDocumentosIdentidad = new ArrayList<>();

    private List<TipoRecurso> tipoRecursoList = new ArrayList<>();

    private List<Servicios> servicio = new ArrayList<>();

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private int slotMinutes = 15;
    private double duracionHoras = 0;
    private Pacientes pacientes = new Pacientes();

    public UIAgenda() {
        try {

            this.gestorServicios = new GestorServicios();
            this.gestorPacientes = new GestorPacientes();
//            eventModel = new DefaultScheduleModel();
            GregorianCalendar gregorianCalendar = new GregorianCalendar(2015, 3, 23, 7, 0);
            GregorianCalendar end = new GregorianCalendar(2015, 3, 23, 7, 20);
            eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
            eventModel.addEvent(new DefaultScheduleEvent("Cita 666 (Pepito Perez)", gregorianCalendar.getTime(), end.getTime()));
            eventModel.addEvent(new DefaultScheduleEvent("Cita 667 (Pepito Perez)", gregorianCalendar.getTime(), end.getTime()));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    @PostConstruct
    private void init() {
        Institucion institucion = ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento().getInstitucion();
        this.listaServicios = new ArrayList<>();
        this.listaServicios.addAll(gestorServicios.cargarListaServicios(institucion));

        eventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
                cargarAgenda(start, end);
            }
        };
    }

    public String cargarAgendaMenu() {
//        this.cargarAgenda(new Date(), null);
        return ("/cita/agenda.xhtml?faces-redirect=true");
    }

    public void cargarAgenda(Date fechaInicial, Date fechaFinal) {
        try {
            Establecimiento establecimiento = ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            Collection<? extends Citas> citasList;
            if (fechaFinal == null) {
                citasList = gestorCitas.cargarCitas(establecimiento, fechaInicial);
            } else {
                citasList = gestorCitas.cargarCitas(establecimiento, fechaInicial, fechaFinal);
            }
            eventModel.clear();
            if (citasList != null) {
                for (Citas c : citasList) {
//                String tituloEvento = "cita: " + c.getPk().getCodCita() + " | ";
//                String tituloEvento += c.getObservacion();

                    GregorianCalendar fechaFin = new GregorianCalendar();
                    fechaFin.setTime(c.getFecha());
                    fechaFin.add(GregorianCalendar.MINUTE, c.getDuracion());

                    ScheduleEvent citaAgenda = new DefaultScheduleEvent(c.getObservacion(), c.getFecha(), fechaFin.getTime());
                    citaAgenda.setId(String.valueOf(c.getPk().getCodCita()));
                    eventModel.addEvent(citaAgenda);
                }
            }
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }

    }

    private List<DetalleLista> cargarListaEstadosCiviles() {
        List<DetalleLista> estadosCivil = new ArrayList<>();
        Lista lista = (Lista) gestorLista.cargarLista(UILista.ESTADOS_CIVIL);
        for (DetalleLista e : lista.getDetalleListaSet()) {
            estadosCivil.add(e);
        }
        return estadosCivil;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        setEvent((ScheduleEvent) selectEvent.getObject());
    }

    public void onDateSelect(SelectEvent selectEvent) {
        try {
            gestorPacientes.validarPaciente(pacientes);
            Citas citas = (Citas) UtilJSF.getBean("citas");
            GregorianCalendar inicio = new GregorianCalendar();
            GregorianCalendar fin = new GregorianCalendar();
            inicio.setTime((Date) selectEvent.getObject());
            fin.setTime((Date) selectEvent.getObject());
            fin.add(GregorianCalendar.MINUTE, citas.getDuracion());
            Servicios serviciosSeleccionado = null;
            for (Servicios s : servicio) {
                serviciosSeleccionado = s;
            }
            if (serviciosSeleccionado == null) {
                UtilMSG.addWarningMsg("Validación", "Selecciona un servicio valido.");
                return;
            }
            String tituloEvento = pacientes.getNombre() + " " + pacientes.getApellido();
            tituloEvento += " | " + serviciosSeleccionado.getNombre() + " | " + citas.getDuracion() + " min";
            setEvent(new DefaultScheduleEvent(tituloEvento, inicio.getTime(), fin.getTime()));

            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            sesion.setDialogo(new Dialogo("dialogos/asignar.xhtml", "Cita para " + pacientes.getNombre() + " " + pacientes.getApellido(), "clip"));
            UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
//        UtilJSF.execute("PF('dialogAgendar').show();");
            UtilJSF.execute("PF('dialog').show();");
        } catch (Exception e) {
            if (UtilLog.causaControlada(e)) {
                UtilMSG.addWarningMsg(e.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), e);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void agendarCita(ActionEvent actionEvent) {
        if (event.getId() == null) {
            try {
                Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
                Establecimiento establecimiento = sesion.getEstablecimiento();
                Citas citas = (Citas) UtilJSF.getBean("citas");

                citas.setPk(new Citas_PK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento()));
                citas.setFecha(event.getStartDate());

                citas.setEstado(UICitas.ESTADO_AGENDADO);
                citas.setObservacion(event.getTitle());

                Servicios servicioSeleccionado = null;
                for (Servicios s : servicio) {
                    servicioSeleccionado = s;
                }
                gestorCitas.validarAgendamientoCita(citas, servicioSeleccionado, tipoRecursoList);
                citas.setCodServicio(servicioSeleccionado.getServiciosPK().getCodServicio());
                citas.setCodIdentidad(pacientes.getPacientesPK().getCodIdentidad());
                citas.setDocumentoBeneficiario(pacientes.getPacientesPK().getDocumentoBeneficiario());

                citas.getPk().setCodCita(gestorCitas.generarCodigoCita());
                citas.setObservacion("cita: " + citas.getPk().getCodCita() + " | " + citas.getObservacion());
                event = new DefaultScheduleEvent(citas.getObservacion(), event.getStartDate(), event.getEndDate());
                event.setId(String.valueOf(citas.getPk().getCodCita()));

                Set<Recurso> rSet = new HashSet<>();
                for (TipoRecurso tr : tipoRecursoList) {
                    tr.setRecursoSeleccionadoList(tr.getRecursoDualList().getTarget());
                    rSet.addAll(tr.getRecursoSeleccionadoList());
                }
                citas.setRecursoSet(rSet);

                gestorCitas.guardarCita(citas, null);

                eventModel.addEvent(event);
                UtilJSF.execute("PF('myschedule').update();PF('dialog').hide();");

            } catch (Exception ex) {
                if (UtilLog.causaControlada(ex)) {
                    UtilMSG.addWarningMsg("Validación", ex.getMessage());
                } else {
                    UtilLog.generarLog(this.getClass(), ex);
                }
            }
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
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

    public void consultarPaciente() {
        try {
            Pacientes pacienteNuevo = pacientes;
            gestorPacientes.validarPaciente(pacientes);
            pacientes = gestorPacientes.consultarPaciente(pacientes);
            Date fechaActual = new Date();
            if (pacientes == null || pacientes.getFechaActualizacion() == null || UtilFecha.diferenciaFechaDias(fechaActual, pacientes.getFechaActualizacion()) > 10) {
                if (pacientes == null) {
                    pacientes = pacienteNuevo;
                }
                Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
                UtilJSF.execute("PF('dialog').show();");
                UtilMSG.addWarningMsg("Datos Paciente", "Debe actualizar los datos del paciente.");
                sesion.setDialogo(new Dialogo("dialogos/paciente.xhtml", "Ingreso y Actualización de Pacientes", "clip"));
                UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
            }
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void guardarPaciente() {
        try {
            Date fechaActual = new Date();
            if (pacientes.getFechaRegistro() == null) {
                pacientes.setFechaRegistro(fechaActual);
            }
            pacientes.setFechaActualizacion(fechaActual);
            gestorPacientes.guardarPaciente(pacientes);
            UtilMSG.addSuccessMsg("Paciente Actualizado", pacientes.getNombre() + " " + pacientes.getApellido());
        } catch (Exception ex) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), ex);
        }
        UtilJSF.hideDialog();
    }

    public void listarRecursosServicio() {
        Servicios serviciosSeleccionado = null;
        for (Servicios s : servicio) {
            serviciosSeleccionado = s;
        }
        if (serviciosSeleccionado == null) {
            UtilMSG.addWarningMsg("Selecciona un servicio");
            return;
        }
        tipoRecursoList.clear();
        tipoRecursoList.addAll(gestorRecursos.cargarRecursosServicio(serviciosSeleccionado));
    }

    public void onTransfer() {
        for (TipoRecurso tr : tipoRecursoList) {
            System.out.println("size:" + tr.getRecursoDualList().getTarget().size());
        }
    }

    /**
     * @return the listaServicios
     */
    public List<Servicios> getListaServicios() {
        return listaServicios;
    }

    /**
     * @param listaServicios the listaServicios to set
     */
    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }

    /**
     * @return the gestorServicios
     */
    public GestorServicios getGestorServicios() {
        return gestorServicios;
    }

    /**
     * @param gestorServicios the gestorServicios to set
     */
    public void setGestorServicios(GestorServicios gestorServicios) {
        this.gestorServicios = gestorServicios;
    }

//    /**
//     * @return the listaDocumentosIdentidad
//     */
//    public List<DocumentosIdentidad> getListaDocumentosIdentidad() {
//        return listaDocumentosIdentidad;
//    }
//    /**
//     * @param listaDocumentosIdentidad the listaDocumentosIdentidad to set
//     */
//    public void setListaDocumentosIdentidad(List<DocumentosIdentidad> listaDocumentosIdentidad) {
//        this.listaDocumentosIdentidad = listaDocumentosIdentidad;
//    }
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
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the event
     */
    public ScheduleEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    /**
     * @return the slotMinutes
     */
    public int getSlotMinutes() {
        return slotMinutes;
    }

    /**
     * @param slotMinutes the slotMinutes to set
     */
    public void setSlotMinutes(int slotMinutes) {
        this.slotMinutes = slotMinutes;
    }

    /**
     * @return the duracionHoras
     */
    public double getDuracionHoras() {
        Citas citas = (Citas) UtilJSF.getBean("citas");
        if (citas != null) {
            this.duracionHoras = (double) citas.getDuracion() / 60;
        }
        return duracionHoras;
    }

    /**
     * @param duracionHoras the duracionHoras to set
     */
    public void setDuracionHoras(double duracionHoras) {
        Citas citas = (Citas) UtilJSF.getBean("citas");
        if (citas != null) {
            citas.setDuracion((int) (duracionHoras * 60));
            UtilJSF.setBean("citas", citas, UtilJSF.SESSION_SCOPE);
        }
        this.duracionHoras = duracionHoras;
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
     * @return the servicio
     */
    public List<Servicios> getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(List<Servicios> servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the gestorRecursos
     */
    public GestorRecursos getGestorRecursos() {
        return gestorRecursos;
    }

    /**
     * @param gestorRecursos the gestorRecursos to set
     */
    public void setGestorRecursos(GestorRecursos gestorRecursos) {
        this.gestorRecursos = gestorRecursos;
    }

    /**
     * @return the tipoRecursoList
     */
    public List<TipoRecurso> getTipoRecursoList() {
        return tipoRecursoList;
    }

    /**
     * @param tipoRecursoList the tipoRecursoList to set
     */
    public void setTipoRecursoList(List<TipoRecurso> tipoRecursoList) {
        this.tipoRecursoList = tipoRecursoList;
    }

}
