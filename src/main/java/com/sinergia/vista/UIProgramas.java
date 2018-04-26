/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.atencion.controlador.GestorServicios;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.ServiciosPK;
import com.sinergia.cliente.controlador.GestorClientes;
import com.sinergia.cliente.controlador.GestorEntidades;
import com.sinergia.cliente.controlador.GestorListasPrecios;
import com.sinergia.cliente.controlador.GestorProgramas;
import com.sinergia.cliente.controlador.GestorProgramasConfiguracion;
import com.sinergia.cliente.controlador.GestorProgramasConfiguracionParametros;
import com.sinergia.cliente.controlador.GestorSubcuentas;
import com.sinergia.cliente.controlador.GestorTiposContrato;
import com.sinergia.cliente.modelo.Clientes;
import com.sinergia.cliente.modelo.ClientesPK;
import com.sinergia.cliente.modelo.Entidades;
import com.sinergia.cliente.modelo.EntidadesPK;
import com.sinergia.cliente.modelo.ListasPrecios;
import com.sinergia.cliente.modelo.ListasPreciosPK;
import com.sinergia.cliente.modelo.ParametrosConfiguracion;
import com.sinergia.cliente.modelo.ParametrosConfiguracionPK;
import com.sinergia.cliente.modelo.Programas;
import com.sinergia.cliente.modelo.ProgramasConfiguracion;
import com.sinergia.cliente.modelo.ProgramasConfiguracionPK;
import com.sinergia.cliente.modelo.ProgramasConfiguracionParametros;
import com.sinergia.cliente.modelo.ProgramasConfiguracionParametrosPK;
import com.sinergia.cliente.modelo.ProgramasPK;
import com.sinergia.cliente.modelo.RelProgramasConfiguracion;
import com.sinergia.cliente.modelo.RelProgramasConfiguracionPK;
import com.sinergia.cliente.modelo.ServiciosLista;
import com.sinergia.cliente.modelo.ServiciosListaPK;
import com.sinergia.cliente.modelo.Subcuentas;
import com.sinergia.cliente.modelo.SubcuentasPK;
import com.sinergia.cliente.modelo.TiposContrato;
import com.sinergia.cliente.modelo.TiposContratoPK;
import com.sinergia.controlador.GestorGeneral;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.publico.controlador.GestorEstablecimiento;
import com.sinergia.publico.controlador.GestorInstitucion;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Hibernate;
import org.primefaces.model.DualListModel;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiProgramas")
@SessionScoped
public class UIProgramas implements Serializable {

    @ManagedProperty("#{gestorInstitucion}")
    private GestorInstitucion gestorInstitucion;
    @ManagedProperty("#{gestorClientes}")
    private GestorClientes gestorClientes;
    @ManagedProperty("#{gestorEntidades}")
    private GestorEntidades gestorEntidades;
    @ManagedProperty("#{gestorSubcuentas}")
    private GestorSubcuentas gestorSubcuentas;
    @ManagedProperty("#{gestorTiposContrato}")
    private GestorTiposContrato gestorTiposContrato;
    @ManagedProperty("#{gestorListasPrecios}")
    private GestorListasPrecios gestorListasPrecios;
    @ManagedProperty("#{gestorGeneral}")
    private GestorGeneral gestorGeneral;
    @ManagedProperty("#{gestorProgramas}")
    private GestorProgramas gestorProgramas;
    @ManagedProperty("#{gestorProgramasConfiguracion}")
    private GestorProgramasConfiguracion gestorProgramasConfiguracion;
    @ManagedProperty("#{gestorProgramasConfiguracionParametros}")
    private GestorProgramasConfiguracionParametros gestorProgramasConfiguracionParametros;
    @ManagedProperty("#{gestorServicios}")
    private GestorServicios gestorServicios;
    @ManagedProperty("#{gestorEstablecimiento}")
    private GestorEstablecimiento gestorEstablecimiento;

    private Institucion institucionSeleccionado = new Institucion();
    private List<Institucion> institucionList;
    private Clientes clientes = new Clientes();
    private Entidades entidades = new Entidades();
    private Subcuentas subcuentas = new Subcuentas();
    private TiposContrato tiposContrato = new TiposContrato();
    private ListasPrecios listasPrecios = new ListasPrecios();
    private Programas programas = new Programas();
    private ProgramasConfiguracion programasConfiguracion;
    private ParametrosConfiguracion parametrosConfiguracionCrear = new ParametrosConfiguracion();
    private List<Servicios> serviciosEstablecimientoList;
    private List<ServiciosLista> serviciosListasEstablecimientoList;
    private DualListModel<Establecimiento> establecimientoDualList = new DualListModel<>();

    {
        this.cargarListaInstituciones();
    }

    public UIProgramas() {
        institucionSeleccionado = new Institucion();
        programasConfiguracion = new ProgramasConfiguracion(new ListasPrecios());
    }

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

    public void cargarServiciosListaEstablecimiento() {
        try {
            serviciosListasEstablecimientoList = new ArrayList<>();
            if (institucionSeleccionado == null || institucionSeleccionado.getListasPrecios() == null
                    || institucionSeleccionado.getListasPrecios().getServiciosListaSet() == null
                    || !Hibernate.isInitialized(institucionSeleccionado.getListasPrecios().getServiciosListaSet())) {
                return;
            }
            for (ServiciosLista sl : institucionSeleccionado.getListasPrecios().getServiciosListaSet()) {
                List<Servicios> serviciosList = new ArrayList<>();
                serviciosList.addAll(gestorServicios.cargarListaServicios(sl.getServiciosListaPK().getCodInstitucion(), sl.getServiciosListaPK().getCodServicio()));
                String nombreServicio = null;
                for (Servicios s : serviciosList) {
                    Establecimiento e = gestorEstablecimiento.cargarEstablecimiento(sl.getServiciosListaPK().getCodInstitucion(), s.getServiciosPK().getCodEstablecimiento());
                    sl.getEstablecimientoList().add(e);
                    nombreServicio = s.getNombre();
                }
                sl.setServicios(new Servicios(new ServiciosPK(), nombreServicio));
                serviciosListasEstablecimientoList.add(sl);
            }
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void asignarEstablecimientosProgramaConfiguracion() {
        try {
            if (programas == null) {
                UtilMSG.addWarningMsg("Indica el programa.");
                return;
            }
            if (programasConfiguracion == null) {
                UtilMSG.addWarningMsg("Indica la configuración.");
                return;
            }
            List<Establecimiento> establecimientosListAsignados = establecimientoDualList.getTarget();
            List<RelProgramasConfiguracion> relProgramasConfiguracionList = new ArrayList<>();
            for (Establecimiento e : establecimientosListAsignados) {
                RelProgramasConfiguracion r = new RelProgramasConfiguracion(new RelProgramasConfiguracionPK(programas.getProgramasPK().getCodInstitucion(), e.getEstablecimientoPK().getCodEstablecimiento(),
                        programas.getProgramasPK().getCodPrograma(), programas.getProgramasPK().getCodEntidad(),
                        programas.getProgramasPK().getCodContrato(), programas.getProgramasPK().getCodCliente(),
                        programas.getProgramasPK().getCodSubcuenta(), programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion()));
                relProgramasConfiguracionList.add(r);
            }
            gestorProgramas.almacenarEstablecimientosProgramasConfiguracion(programas, programasConfiguracion, relProgramasConfiguracionList);
            UtilMSG.addSuccessMsg("Establecimiento asignado correctamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void cargarEstablecimientosProgramaConfiguracion() {
        try {
            List<Establecimiento> establecimientosListDisponibles = new ArrayList<>();
            List<Establecimiento> establecimientosListAsignados = new ArrayList<>();

            if (programas == null) {
                UtilMSG.addWarningMsg("Indica el programa.");
                return;
            }
            if (programasConfiguracion == null || programasConfiguracion.getProgramasConfiguracionPK() == null) {
                UtilMSG.addWarningMsg("Indica la configuración.");
                return;
            }

            establecimientosListDisponibles.addAll(institucionSeleccionado.getEstablecimientosSet());
            establecimientosListAsignados.addAll(gestorProgramas.cargarEstablecimientosProgramaConfiguracion(programas, programasConfiguracion));

            establecimientosListDisponibles.removeAll(establecimientosListAsignados);
            establecimientoDualList = new DualListModel<>(establecimientosListDisponibles, establecimientosListAsignados);
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void cargarServiciosEstablecimiento() {
        try {
            serviciosEstablecimientoList = new ArrayList<>();
            Establecimiento establecimiento = new Establecimiento(institucionSeleccionado.getCodInstitucion(),
                    institucionSeleccionado.getListasPrecios().getServiciosLista().getServicios().getServiciosPK().getCodEstablecimiento());
            serviciosEstablecimientoList.addAll(gestorServicios.cargarServiciosEstablecimiento(establecimiento));
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
            }
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

    public void crearParametrosConfiguracion() {

        try {
            if (parametrosConfiguracionCrear.getNombre() == null || parametrosConfiguracionCrear.getNombre().equalsIgnoreCase("")) {
                UtilMSG.addWarningMsg("Ingresa el nombre del parametro configuración", "Nombre Parametro");
                return;
            }
            parametrosConfiguracionCrear.setParametrosConfiguracionPK(new ParametrosConfiguracionPK(institucionSeleccionado.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.CLIENTE_PARAMETROS_CONFIGURACION_COD_PARAMETRO_SEQ)));
            gestorGeneral.guardarActualizarEntidad(parametrosConfiguracionCrear);
            UtilJSF.execute("PF('dialogNuevoParametro').hide();");
            UtilMSG.addSuccessMsg("Parametro Creado correctamente.");
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

    public void crearProgramasConfiguracionParametros() {
        try {
            ProgramasConfiguracionParametros programasConfiguracionParametros = programasConfiguracion.getProgramasConfiguracionParametros();

            programasConfiguracionParametros.getProgramasConfiguracionParametrosPK().setCodInstitucion(institucionSeleccionado.getCodInstitucion());
            programasConfiguracionParametros.getProgramasConfiguracionParametrosPK().setCodConfiguracion(programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion());

            programasConfiguracionParametros = getGestorProgramasConfiguracionParametros().validarProgramasConfiguracionParametros(programasConfiguracionParametros, GestorGeneral.VALIDA_PK);

            ProgramasConfiguracionParametros programasConfiguracionParametrosRemover = null;
            getGestorTiposContrato().guardarActualizarEntidad(programasConfiguracionParametros);

            if (programasConfiguracion.getProgramasConfiguracionParametrosSet() != null) {
                for (ProgramasConfiguracionParametros pc : programasConfiguracion.getProgramasConfiguracionParametrosSet()) {
                    if (pc.equals(programasConfiguracionParametros)) {
                        programasConfiguracionParametrosRemover = pc;
                    }
                }
            }
            if (programasConfiguracionParametrosRemover != null) {
                programasConfiguracion.getProgramasConfiguracionParametrosSet().remove(programasConfiguracionParametrosRemover);
            }
            programasConfiguracion.getProgramasConfiguracionParametrosSet().add(programasConfiguracionParametros);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Parametro de la Configuración del Programa Agregada Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearProgramasConfiguracion() {
        try {
            ProgramasConfiguracion programasConfiguracionRemover = null;
            programasConfiguracion = gestorProgramasConfiguracion.validarProgramasConfiguracion(programasConfiguracion, GestorGeneral.NO_VALIDA_PK);
            if (programasConfiguracion.getProgramasConfiguracionPK() == null
                    || programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion() == 0) {
                programasConfiguracion.setProgramasConfiguracionPK(new ProgramasConfiguracionPK(institucionSeleccionado.getCodInstitucion(),
                        gestorGeneral.nextval(GestorGeneral.CLIENTE_PROGRAMAS_CONFIGURACION_COD_CONFIGURACION_SEQ)));
                programasConfiguracion.setActivo(true);
                programasConfiguracion.setInstitucion(institucionSeleccionado);
            }
            programasConfiguracion = getGestorProgramasConfiguracion().validarProgramasConfiguracion(programasConfiguracion, GestorGeneral.VALIDA_PK);
            getGestorTiposContrato().guardarActualizarEntidad(programasConfiguracion);
            if (institucionSeleccionado.getProgramasConfiguracionSet() != null) {
                for (ProgramasConfiguracion pc : institucionSeleccionado.getProgramasConfiguracionSet()) {
                    if (pc.equals(programasConfiguracion)) {
                        programasConfiguracionRemover = pc;
                    }
                }
            }
            if (programasConfiguracionRemover != null) {
                institucionSeleccionado.getProgramasConfiguracionSet().remove(programasConfiguracionRemover);
            }
            institucionSeleccionado.getProgramasConfiguracionSet().add(programasConfiguracion);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Configuración Programa Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearProgramas() {
        try {
            Programas programasRemover = null;
            programas = getGestorProgramas().validarProgramas(programas, GestorGeneral.NO_VALIDA_PK);

//            Set<ProgramasConfiguracion> programasConfiguracionSet = new HashSet<>();
//            programasConfiguracionSet.add(programas.getProgramasConfiguracion());
//            programas.setProgramasConfiguracionSet(programasConfiguracionSet);
            if (programas.getProgramasPK() == null
                    || programas.getProgramasPK().getCodPrograma() == null || programas.getProgramasPK().getCodPrograma() == 0) {
                programas.setProgramasPK(new ProgramasPK(institucionSeleccionado.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.CLIENTE_PROGRAMAS_COD_PROGRAMA_SEQ)));
            } else {
                programasRemover = (Programas) new Programas((ProgramasPK) programas.getProgramasPK().clone()).clone();
                programasRemover.setNombre(programas.getNombre());
            }
            programas.getProgramasPK().setCodEntidad(programas.getEntidades().getEntidadesPK().getCodEntidad());
            programas.getProgramasPK().setCodContrato(programas.getTiposContrato().getTiposContratoPK().getCodContrato());
            programas.getProgramasPK().setCodCliente(programas.getClientes().getClientesPK().getCodCliente());
            programas.getProgramasPK().setCodSubcuenta(programas.getSubcuentas().getSubcuentasPK().getCodSubcuenta());

            programas = getGestorProgramas().validarProgramas(programas, GestorGeneral.VALIDA_PK);
//            if (programasRemover != null) {
//                gestorProgramas.actualizarPrograma(programas, programasRemover);
            gestorProgramas.guardarActualizarEntidad(programas);
//            } else {
//                getGestorTiposContrato().guardarActualizarEntidad(programas);
//            }
//            Programas programasRemover = null;
            if (institucionSeleccionado.getTiposContratoSet() != null) {
                for (Programas l : institucionSeleccionado.getProgramasSet()) {
                    if (l.equals(programas)) {
                        programasRemover = l;
                    }
                }
            }
            if (programasRemover != null) {
                institucionSeleccionado.getProgramasSet().remove(programasRemover);
            }
            institucionSeleccionado.getProgramasSet().add(programas);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Programa Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearServicioListaPrecios() {
        try {
            ServiciosLista serviciosLista = institucionSeleccionado.getListasPrecios().getServiciosLista();
            serviciosLista.getServiciosListaPK().setCodInstitucion(institucionSeleccionado.getCodInstitucion());
            serviciosLista.setListasPrecios(institucionSeleccionado.getListasPrecios());
            serviciosLista = gestorListasPrecios.validarServiciosLista(serviciosLista);
            serviciosLista.getServiciosListaPK().setCodLista(serviciosLista.getListasPrecios().getListasPreciosPK().getCodLista());
            gestorListasPrecios.guardarActualizarEntidad(serviciosLista);

            ServiciosLista serviciosListaRemover = null;

            if (Hibernate.isInitialized(institucionSeleccionado.getListasPrecios().getServiciosListaSet())
                    && institucionSeleccionado.getListasPrecios().getServiciosListaSet() != null) {
                for (ServiciosLista sl : institucionSeleccionado.getListasPrecios().getServiciosListaSet()) {
                    if (serviciosLista.equals(sl)) {
                        serviciosListaRemover = sl;
                    }
                }
            } else {
                institucionSeleccionado.getListasPrecios().setServiciosListaSet(new HashSet<ServiciosLista>());
            }
            if (serviciosListaRemover != null) {
                institucionSeleccionado.getListasPrecios().getServiciosListaSet().remove(serviciosListaRemover);
            }
            institucionSeleccionado.getListasPrecios().getServiciosListaSet().add(serviciosLista);
            cargarServiciosListaEstablecimiento();
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Servicio Asignado a la Lista");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void crearListaPrecios() {
        try {
            if (getListasPrecios().getListasPreciosPK() == null || getListasPrecios().getListasPreciosPK().getCodLista() == 0) {
                getListasPrecios().setListasPreciosPK(new ListasPreciosPK(institucionSeleccionado.getCodInstitucion(),
                        //                        gestorListasPrecios.siguienteCodigoListasPrecios(institucionSeleccionado)
                        gestorGeneral.siguienteCodigoEntidad(institucionSeleccionado.getCodInstitucion(), "cod_lista", "cliente.listas_precios")
                ));
            }
            setListasPrecios(gestorListasPrecios.validarListasPrecios(getListasPrecios()));
            getGestorTiposContrato().guardarActualizarEntidad(getListasPrecios());
            ListasPrecios listasPreciosRemover = null;
            if (institucionSeleccionado.getTiposContratoSet() != null) {
                for (ListasPrecios l : institucionSeleccionado.getListasPreciosSet()) {
                    if (l.equals(getListasPrecios())) {
                        listasPreciosRemover = l;
                    }
                }
            }
            if (listasPreciosRemover != null) {
                institucionSeleccionado.getListasPreciosSet().remove(listasPreciosRemover);
            }
            institucionSeleccionado.getListasPreciosSet().add(getListasPrecios());
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Lista Precios Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearTipoContrato() {
        try {
            if (tiposContrato.getTiposContratoPK() == null || tiposContrato.getTiposContratoPK().getCodContrato() == 0) {
                tiposContrato.setTiposContratoPK(new TiposContratoPK(institucionSeleccionado.getCodInstitucion(), getGestorTiposContrato().siguienteCodigoTipoContrato(institucionSeleccionado)));
            }
            tiposContrato = getGestorTiposContrato().validarTipoContrato(tiposContrato);
            getGestorTiposContrato().guardarActualizarEntidad(tiposContrato);
            TiposContrato tiposContratoRemover = null;
            if (institucionSeleccionado.getTiposContratoSet() != null) {
                for (TiposContrato s : institucionSeleccionado.getTiposContratoSet()) {
                    if (s.equals(tiposContrato)) {
                        tiposContratoRemover = s;
                    }
                }
            }
            if (tiposContratoRemover != null) {
                institucionSeleccionado.getTiposContratoSet().remove(tiposContratoRemover);
            }
            institucionSeleccionado.getTiposContratoSet().add(tiposContrato);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Tipo Contrato Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearSubcuenta() {
        try {
            if (subcuentas.getSubcuentasPK() == null || subcuentas.getSubcuentasPK().getCodSubcuenta() == 0) {
                subcuentas.setSubcuentasPK(new SubcuentasPK(institucionSeleccionado.getCodInstitucion(), gestorSubcuentas.siguienteCodigoSubcuenta(institucionSeleccionado)));
                subcuentas.setActivo(true);
            }
            subcuentas = gestorSubcuentas.validarSubcuenta(subcuentas);
            gestorSubcuentas.guardarActualizarEntidad(subcuentas);
            Subcuentas subcuentasRemover = null;
            if (institucionSeleccionado.getSubcuentasSet() != null) {
                for (Subcuentas s : institucionSeleccionado.getSubcuentasSet()) {
                    if (s.equals(subcuentas)) {
                        subcuentasRemover = s;
                    }
                }
            }
            if (subcuentasRemover != null) {
                institucionSeleccionado.getSubcuentasSet().remove(subcuentasRemover);
            }
            institucionSeleccionado.getSubcuentasSet().add(subcuentas);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Subcuenta Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearEntidad() {

        try {
            if (entidades.getEntidadesPK() == null || entidades.getEntidadesPK().getCodEntidad() == 0) {
                entidades.setEntidadesPK(new EntidadesPK(institucionSeleccionado.getCodInstitucion(), gestorEntidades.siguienteCodigoEntidad(institucionSeleccionado)));
                entidades.setActivo(true);
            }
            entidades = gestorEntidades.validarEntidad(entidades);
            gestorEntidades.guardarActualizarEntidad(entidades);
            Entidades entidadesRemover = null;
            if (institucionSeleccionado.getEntidadesSet() != null) {
                for (Entidades e : institucionSeleccionado.getEntidadesSet()) {
                    if (e.equals(entidades)) {
                        entidadesRemover = e;
                    }
                }
            }
            if (entidadesRemover != null) {
                institucionSeleccionado.getEntidadesSet().remove(entidadesRemover);
            }
            institucionSeleccionado.getEntidadesSet().add(entidades);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Entidad Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearCliente() {

        try {
            if (clientes.getClientesPK() == null || clientes.getClientesPK().getCodCliente() == 0) {
                clientes.setClientesPK(new ClientesPK(institucionSeleccionado.getCodInstitucion(), gestorClientes.siguienteCodigoCliente(institucionSeleccionado)));
                clientes.setActivo(true);
            }
            clientes = gestorClientes.validarCliente(clientes);
            gestorClientes.guardarActualizarEntidad(clientes);
            Clientes clientesRemover = null;
            if (institucionSeleccionado.getClientesSet() != null) {
                for (Clientes c : institucionSeleccionado.getClientesSet()) {
                    if (c.equals(clientes)) {
                        clientesRemover = c;
                    }
                }
            }
            if (clientesRemover != null) {
                institucionSeleccionado.getClientesSet().remove(clientesRemover);
            }
            institucionSeleccionado.getClientesSet().add(clientes);
            UtilJSF.execute("PF('dialog').hide();");
            UtilMSG.addSuccessMsg("Cliente Creado Satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void cargarDialogoCrearCliente() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/cliente.xhtml", "Nuevo Cliente", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarCliente() {
        clientes = new Clientes();
        clientes = (Clientes) UtilJSF.getBean("varParametroClientes");
        this.cargarDialogoCrearCliente();
    }

    public void cargarDialogoCrearEntidad() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/entidad.xhtml", "Nuevo Entidad", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarEntidad() {
        entidades = new Entidades();
        entidades = (Entidades) UtilJSF.getBean("varParametroEntidades");
        this.cargarDialogoCrearEntidad();
    }

    public void cargarDialogoCrearSubcuenta() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/subcuenta.xhtml", "Nuevo Subcuenta", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarSubcuenta() {
        subcuentas = new Subcuentas();
        subcuentas = (Subcuentas) UtilJSF.getBean("varParametroSubcuentas");
        this.cargarDialogoCrearSubcuenta();
    }

    public void cargarDialogoCrearParametroConfiguracionPrograma() {
        if (programasConfiguracion == null || programasConfiguracion.getProgramasConfiguracionPK() == null) {
            UtilMSG.addWarningMsg("Selecciona la configuración que le desea adicionar el parametro.");
            return;
        }
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/programasConfiguracionParametro.xhtml", "Nuevo Parametro de Configuración Programa", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoCrearConfiguracionPrograma() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/programasConfiguracion.xhtml", "Asignar Parametro a Configuración de Programa", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarConfiguracionPrograma() {
        programasConfiguracion = new ProgramasConfiguracion();
        programasConfiguracion = (ProgramasConfiguracion) UtilJSF.getBean("varConfiguracionProgramasInstitucion");
        this.cargarDialogoCrearConfiguracionPrograma();
    }

    public void cargarDialogoCrearPrograma() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        programas = new Programas();
        sesion.setDialogo(new Dialogo("dialogos/programas.xhtml", "Nuevo Programa", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarPrograma() {
        programas = (Programas) UtilJSF.getBean("varProgramasInstitucion");
        if (programas == null) {
            programas = new Programas();
        }
        this.cargarDialogoCrearPrograma();
    }

    public void cargarDialogoCrearServiciosLista() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/serviciosLista.xhtml", "Asignar Servicio Lista", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarServiciosLista() {
        setListasPrecios(new ListasPrecios());
        setListasPrecios((ListasPrecios) UtilJSF.getBean("varServiciosLista"));
        this.cargarDialogoCrearServiciosLista();
    }

    public void cargarDialogoCrearListaPrecios() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/listaPrecios.xhtml", "Nuevo Lista Precios", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarListaPrecios() {
        setListasPrecios(new ListasPrecios());
        setListasPrecios((ListasPrecios) UtilJSF.getBean("varListaPreciosInstitucion"));
        this.cargarDialogoCrearListaPrecios();
    }

    public void cargarDialogoCrearTipoContrato() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/tipoContrato.xhtml", "Nuevo Tipo Contrato", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarTipoContrato() {
        tiposContrato = new TiposContrato();
        tiposContrato = (TiposContrato) UtilJSF.getBean("varParametroTiposContrato");
        this.cargarDialogoCrearTipoContrato();
    }

    public void cargarConfiguracionInstitucion() {
        try {
            institucionSeleccionado = gestorInstitucion.cargarConfiguracionPrograma(institucionSeleccionado);

            Institucion institucionRemover = null;
            for (Institucion i : institucionList) {
                if (institucionSeleccionado.equals(i)) {
                    institucionRemover = i;
                }
            }
            if (institucionRemover != null) {
                institucionList.remove(institucionRemover);
                institucionList.add(institucionSeleccionado);
            } else {
                institucionSeleccionado = new Institucion();
                UtilMSG.addSupportMsg();
            }
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex);
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    private void cargarListaInstituciones() {
        institucionList = new ArrayList<>();
        institucionList.addAll(((Sesion) UtilJSF.getBean("sesion")).getUsuario().getInstitucionSetToList());
    }

    /**
     * @return the institucionSeleccionado
     */
    public Institucion getInstitucionSeleccionado() {
        return institucionSeleccionado;
    }

    /**
     * @param institucionSeleccionado the institucionSeleccionado to set
     */
    public void setInstitucionSeleccionado(Institucion institucionSeleccionado) {
        this.institucionSeleccionado = institucionSeleccionado;
    }

    /**
     * @return the institucionList
     */
    public List<Institucion> getInstitucionList() {
        return institucionList;
    }

    /**
     * @param institucionList the institucionList to set
     */
    public void setInstitucionList(List<Institucion> institucionList) {
        this.institucionList = institucionList;
    }

    /**
     * @return the gestorInstitucion
     */
    public GestorInstitucion getGestorInstitucion() {
        return gestorInstitucion;
    }

    /**
     * @param gestorInstitucion the gestorInstitucion to set
     */
    public void setGestorInstitucion(GestorInstitucion gestorInstitucion) {
        this.gestorInstitucion = gestorInstitucion;
    }

    /**
     * @return the clientes
     */
    public Clientes getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the gestorClientes
     */
    public GestorClientes getGestorClientes() {
        return gestorClientes;
    }

    /**
     * @param gestorClientes the gestorClientes to set
     */
    public void setGestorClientes(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
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
     * @return the gestorEntidades
     */
    public GestorEntidades getGestorEntidades() {
        return gestorEntidades;
    }

    /**
     * @param gestorEntidades the gestorEntidades to set
     */
    public void setGestorEntidades(GestorEntidades gestorEntidades) {
        this.gestorEntidades = gestorEntidades;
    }

    /**
     * @return the subcuentas
     */
    public Subcuentas getSubcuentas() {
        return subcuentas;
    }

    /**
     * @param subcuentas the subcuentas to set
     */
    public void setSubcuentas(Subcuentas subcuentas) {
        this.subcuentas = subcuentas;
    }

    /**
     * @return the gestorSubcuentas
     */
    public GestorSubcuentas getGestorSubcuentas() {
        return gestorSubcuentas;
    }

    /**
     * @param gestorSubcuentas the gestorSubcuentas to set
     */
    public void setGestorSubcuentas(GestorSubcuentas gestorSubcuentas) {
        this.gestorSubcuentas = gestorSubcuentas;
    }

    /**
     * @return the tiposContrato
     */
    public TiposContrato getTiposContrato() {
        return tiposContrato;
    }

    /**
     * @param tiposContrato the tiposContrato to set
     */
    public void setTiposContrato(TiposContrato tiposContrato) {
        this.tiposContrato = tiposContrato;
    }

    /**
     * @return the gestorTiposContrato
     */
    public GestorTiposContrato getGestorTiposContrato() {
        return gestorTiposContrato;
    }

    /**
     * @param gestorTiposContrato the gestorTiposContrato to set
     */
    public void setGestorTiposContrato(GestorTiposContrato gestorTiposContrato) {
        this.gestorTiposContrato = gestorTiposContrato;
    }

    /**
     * @return the listasPrecios
     */
    public ListasPrecios getListasPrecios() {
        return listasPrecios;
    }

    /**
     * @param listasPrecios the listasPrecios to set
     */
    public void setListasPrecios(ListasPrecios listasPrecios) {
        this.listasPrecios = listasPrecios;
    }

    /**
     * @return the gestorListasPrecios
     */
    public GestorListasPrecios getGestorListasPrecios() {
        return gestorListasPrecios;
    }

    /**
     * @param gestorListasPrecios the gestorListasPrecios to set
     */
    public void setGestorListasPrecios(GestorListasPrecios gestorListasPrecios) {
        this.gestorListasPrecios = gestorListasPrecios;
    }

    /**
     * @return the gestorGeneral
     */
    public GestorGeneral getGestorGeneral() {
        return gestorGeneral;
    }

    /**
     * @param gestorGeneral the gestorGeneral to set
     */
    public void setGestorGeneral(GestorGeneral gestorGeneral) {
        this.gestorGeneral = gestorGeneral;
    }

    /**
     * @return the programas
     */
    public Programas getProgramas() {
        return programas;
    }

    /**
     * @param programas the programas to set
     */
    public void setProgramas(Programas programas) {
        this.programas = programas;
    }

    /**
     * @return the gestorProgramas
     */
    public GestorProgramas getGestorProgramas() {
        return gestorProgramas;
    }

    /**
     * @param gestorProgramas the gestorProgramas to set
     */
    public void setGestorProgramas(GestorProgramas gestorProgramas) {
        this.gestorProgramas = gestorProgramas;
    }

    /**
     * @return the programasConfiguracion
     */
    public ProgramasConfiguracion getProgramasConfiguracion() {
        return programasConfiguracion;
    }

    /**
     * @param programasConfiguracion the programasConfiguracion to set
     */
    public void setProgramasConfiguracion(ProgramasConfiguracion programasConfiguracion) {
        this.programasConfiguracion = programasConfiguracion;
    }

    /**
     * @return the gestorProgramasConfiguracion
     */
    public GestorProgramasConfiguracion getGestorProgramasConfiguracion() {
        return gestorProgramasConfiguracion;
    }

    /**
     * @param gestorProgramasConfiguracion the gestorProgramasConfiguracion to
     * set
     */
    public void setGestorProgramasConfiguracion(GestorProgramasConfiguracion gestorProgramasConfiguracion) {
        this.gestorProgramasConfiguracion = gestorProgramasConfiguracion;
    }

    /**
     * @return the parametrosConfiguracionCrear
     */
    public ParametrosConfiguracion getParametrosConfiguracionCrear() {
        return parametrosConfiguracionCrear;
    }

    /**
     * @param parametrosConfiguracionCrear the parametrosConfiguracionCrear to
     * set
     */
    public void setParametrosConfiguracionCrear(ParametrosConfiguracion parametrosConfiguracionCrear) {
        this.parametrosConfiguracionCrear = parametrosConfiguracionCrear;
    }

    /**
     * @return the gestorProgramasConfiguracionParametros
     */
    public GestorProgramasConfiguracionParametros getGestorProgramasConfiguracionParametros() {
        return gestorProgramasConfiguracionParametros;
    }

    /**
     * @param gestorProgramasConfiguracionParametros the
     * gestorProgramasConfiguracionParametros to set
     */
    public void setGestorProgramasConfiguracionParametros(GestorProgramasConfiguracionParametros gestorProgramasConfiguracionParametros) {
        this.gestorProgramasConfiguracionParametros = gestorProgramasConfiguracionParametros;
    }

    /**
     * @return the serviciosEstablecimientoList
     */
    public List<Servicios> getServiciosEstablecimientoList() {
        return serviciosEstablecimientoList;
    }

    /**
     * @param serviciosEstablecimientoList the serviciosEstablecimientoList to
     * set
     */
    public void setServiciosEstablecimientoList(List<Servicios> serviciosEstablecimientoList) {
        this.serviciosEstablecimientoList = serviciosEstablecimientoList;
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

    /**
     * @return the serviciosListasEstablecimientoList
     */
    public List<ServiciosLista> getServiciosListasEstablecimientoList() {
        return serviciosListasEstablecimientoList;
    }

    /**
     * @param serviciosListasEstablecimientoList the
     * serviciosListasEstablecimientoList to set
     */
    public void setServiciosListasEstablecimientoList(List<ServiciosLista> serviciosListasEstablecimientoList) {
        this.serviciosListasEstablecimientoList = serviciosListasEstablecimientoList;
    }

    /**
     * @return the gestorEstablecimiento
     */
    public GestorEstablecimiento getGestorEstablecimiento() {
        return gestorEstablecimiento;
    }

    /**
     * @param gestorEstablecimiento the gestorEstablecimiento to set
     */
    public void setGestorEstablecimiento(GestorEstablecimiento gestorEstablecimiento) {
        this.gestorEstablecimiento = gestorEstablecimiento;
    }

    /**
     * @return the establecimientoDualList
     */
    public DualListModel<Establecimiento> getEstablecimientoDualList() {
        return establecimientoDualList;
    }

    /**
     * @param establecimientoDualList the establecimientoDualList to set
     */
    public void setEstablecimientoDualList(DualListModel<Establecimiento> establecimientoDualList) {
        this.establecimientoDualList = establecimientoDualList;
    }

}
