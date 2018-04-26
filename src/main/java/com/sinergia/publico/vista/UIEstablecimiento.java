/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.vista;

import com.sinergia.atencion.controlador.GestorSeccion;
import com.sinergia.atencion.controlador.GestorServicios;
import com.sinergia.atencion.controlador.GestorTipoRecurso;
import com.sinergia.atencion.modelo.Seccion;
import com.sinergia.atencion.modelo.SeccionServicios;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.ServiciosPK;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.atencion.modelo.TipoRecursoServiciosCantidad;
import com.sinergia.controlador.GestorGeneral;
import com.sinergia.facturacion.controlador.GestorFacturas;
import com.sinergia.facturacion.modelo.FacturasConfiguracion;
import com.sinergia.facturacion.modelo.FacturasConfiguracionPK;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.publico.controlador.GestorEstablecimiento;
import com.sinergia.publico.controlador.GestorMunicipios;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.EstablecimientoPK;
import com.sinergia.publico.modelo.EstablecimientoParametros;
import com.sinergia.publico.modelo.EstablecimientoParametrosPK;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.publico.modelo.Municipios;
import com.sinergia.publico.modelo.Parametros;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.event.ActionListener;
import org.hibernate.Hibernate;
import org.primefaces.model.DualListModel;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiEstablecimiento")
@SessionScoped

public class UIEstablecimiento implements Serializable {

    @ManagedProperty("#{gestorEstablecimiento}")
    private GestorEstablecimiento gestorEstablecimiento;
    @ManagedProperty("#{gestorGeneral}")
    private GestorGeneral gestorGeneral;
    @ManagedProperty("#{gestorMunicipios}")
    private GestorMunicipios gestorMunicipios;
    @ManagedProperty("#{gestorFacturas}")
    private GestorFacturas gestorFacturas;
    @ManagedProperty("#{gestorServicios}")
    private GestorServicios gestorServicios;
    @ManagedProperty("#{gestorTipoRecurso}")
    private GestorTipoRecurso gestorTipoRecurso;
    @ManagedProperty("#{gestorSeccion}")
    private GestorSeccion gestorSeccion;

    private Establecimiento establecimiento = new Establecimiento();

    private Servicios servicios = new Servicios();
    private List<Servicios> serviciosList = new ArrayList<>();
    private List<Establecimiento> establecimientoList = new ArrayList<>();

    private DualListModel<TipoRecurso> tipoRecursoDualList = new DualListModel<>();
    private DualListModel<Seccion> seccionDualList = new DualListModel<>();

    private List<Municipios> municipiosList = new ArrayList<>();
    private List<Institucion> institucionList = new ArrayList<>();
    private List<Parametros> parametrosList;
    private Parametros parametrosNuevo = new Parametros();

    @PostConstruct
    public void init() {
        this.cargarEstablecimientosInstitucion();
        this.cargarMunicipios();
        this.cargarInstitucionesUsuario();
        this.cargarParametros();
    }

    public void guardarTipoRecursoServiciosCantidad() {
        for (TipoRecurso tr : servicios.getTipoRecursoSet()) {
            try {
                if (tr != null && tr.getTipoRecursoServiciosCantidad() != null) {
                    gestorTipoRecurso.guardarActualizarEntidad(tr.getTipoRecursoServiciosCantidad());
                }
            } catch (Exception ex) {
                if (UtilLog.causaControlada(ex)) {
                    UtilMSG.addErrorMsg(ex.getMessage(), ex.getCause().getMessage());
                } else {
                    UtilMSG.addSupportMsg();
                    UtilLog.generarLog(this.getClass(), ex);
                }
            }
        }
    }

    public void cargarSeccionesServicio() {
        try {
            seccionDualList = new DualListModel<>();
            List<SeccionServicios> seccionServiciosList = new ArrayList<>();
            if (servicios.getSeccionServiciosSet() != null && Hibernate.isInitialized(servicios.getSeccionServiciosSet())) {
//            for (SeccionServicios ss : servicios.getSeccionServiciosSet()) {
//                seccionAsignadosList.add(ss);
//            }
                seccionServiciosList.addAll(servicios.getSeccionServiciosSet());
            }

            List<Seccion> seccionAsignadosList = new ArrayList<>();
            List<Seccion> seccionDisponiblesList = new ArrayList<>();
            seccionDisponiblesList.addAll(gestorSeccion.cargarSeccionEstablecimiento(establecimiento, true));

            for (Seccion s : seccionDisponiblesList) {
                for (SeccionServicios ss : seccionServiciosList) {
                    if (s.getSeccionPK().getCodSeccion().equals(ss.getSeccionServiciosPK().getCodSeccion())) {
                        seccionAsignadosList.add(s);
                        break;
                    }
                }
            }
            seccionDisponiblesList.removeAll(seccionAsignadosList);
            seccionDualList = new DualListModel<>(seccionDisponiblesList, seccionAsignadosList);

        } catch (Exception ex) {
            Logger.getLogger(UIEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarTipoRecursosServicio() {
        try {
            tipoRecursoDualList = new DualListModel<>();
            List<TipoRecurso> tipoRecursoAsignadosList = new ArrayList<>();
            if (servicios.getTipoRecursoSetToList() != null) {
                tipoRecursoAsignadosList.addAll(servicios.getTipoRecursoSetToList());
            }

            List<TipoRecurso> tipoRecursoDisponiblesList = new ArrayList<>();
            tipoRecursoDisponiblesList.addAll(gestorTipoRecurso.cargarTipoRecursoInstitucion(establecimiento.getEstablecimientoPK().getCodInstitucion()));

            tipoRecursoDisponiblesList.removeAll(tipoRecursoAsignadosList);
            tipoRecursoDualList = new DualListModel<>(tipoRecursoDisponiblesList, tipoRecursoAsignadosList);

            for (TipoRecurso tipoRecurso : servicios.getTipoRecursoSet()) {
                TipoRecursoServiciosCantidad trsc = new TipoRecursoServiciosCantidad();
                trsc = gestorTipoRecurso.cargarTipoRecursoServiciosCantidad(servicios, tipoRecurso);
                tipoRecurso.setTipoRecursoServiciosCantidad(trsc);
            }
        } catch (Exception ex) {
            Logger.getLogger(UIEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarServiciosEstablecimiento() {
        try {
            serviciosList = new ArrayList<>();
            serviciosList.addAll(gestorServicios.cargarServiciosEstablecimiento(establecimiento));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    private void cargarParametros() {
        this.parametrosList = new ArrayList<>();
        this.parametrosList.addAll(gestorGeneral.cargarParametros());
    }

    public void eliminarParametro(ActionListener actionListener) {
        try {
            EstablecimientoParametros establecimientoParametros = (EstablecimientoParametros) UtilJSF.getBean("varParametroEstablecimiento");
            establecimientoParametros.setValor("N");
//            gestorEstablecimiento.borrarEstablecimientoParametros(establecimientoParametros);
            establecimiento.getEstablecimientoParametrosSet().add(establecimientoParametros);
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }

    }

    public void limpiar() {
        this.cargarEstablecimientosInstitucion();
        this.cargarMunicipios();
        this.cargarInstitucionesUsuario();
        this.cargarParametros();
        this.establecimiento = new Establecimiento();
    }

    public void asignarSeccionServicio() {
        try {
            List<Seccion> seccionList = seccionDualList.getTarget();
            servicios.setSeccionServiciosSet(new HashSet<SeccionServicios>());
            Set<SeccionServicios> seccionServiciosSet = new HashSet<>();
            for (Seccion s : seccionList) {
                seccionServiciosSet.add(new SeccionServicios(s.getSeccionPK().getCodInstitucion(), s.getSeccionPK().getCodEstablecimiento(),
                        s.getSeccionPK().getCodSeccion(), servicios.getServiciosPK().getCodServicio()));
            }
            servicios.setSeccionServiciosSet(seccionServiciosSet);
            gestorSeccion.almacenarSeccionServicios(servicios, seccionServiciosSet);
            UtilMSG.addSuccessMsg("Sección asignada correctamente.");
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void asignarTipoRecursoServicio() {
        try {
            for (TipoRecurso t : tipoRecursoDualList.getTarget()) {
                System.out.println("nombre=>" + t.getNombre());
            }

            servicios.setTipoRecursoSet(new HashSet<TipoRecurso>());
            servicios.setTipoRecursoRemoverSet(new HashSet<TipoRecurso>());
            servicios.getTipoRecursoSet().addAll(tipoRecursoDualList.getTarget());
            servicios.getTipoRecursoRemoverSet().addAll(tipoRecursoDualList.getSource());

            gestorServicios.guardarTipoRecursoServicio(servicios);
            UtilMSG.addSuccessMsg("Tipo Recursos asciados al servicio " + servicios.getNombre());
        } catch (Exception ex) {
            Logger.getLogger(UIEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void asignarResolucion() {
        FacturasConfiguracion facturasConfiguracion = establecimiento.getFacturasConfiguracion();
        try {
            facturasConfiguracion = gestorFacturas.validarFacturasConfiguracion(establecimiento, facturasConfiguracion);
            if (facturasConfiguracion.getFacturasConfiguracionPK() == null
                    || facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion() == 0) {
                facturasConfiguracion.setFacturasConfiguracionPK(new FacturasConfiguracionPK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), gestorGeneral.nextval(GestorGeneral.FACTURACION_FACTURAS_CONFIGURACION_COD_CONFIGURACION_SEQ)));
                facturasConfiguracion.setNumeroFactura(0);
            }
            gestorFacturas.validarFacturasConfiguracionPK(facturasConfiguracion.getFacturasConfiguracionPK());
            FacturasConfiguracion facturasConfiguracionRemover = null;
            for (FacturasConfiguracion fc : establecimiento.getFacturasConfiguracionSet()) {
                if (fc.equals(facturasConfiguracion)) {
                    facturasConfiguracionRemover = fc;
                    break;
                }
            }
            if (facturasConfiguracionRemover != null) {
                establecimiento.getFacturasConfiguracionSet().remove(facturasConfiguracionRemover);
            }
            establecimiento.getFacturasConfiguracionSet().add(facturasConfiguracion);
            UtilMSG.addSuccessMsg("Resolución Almacenada Satisfactoriamente", "Registro Guardado, recuerda que se debe modificar el establecimiento para almacenar la configuración");
            UtilJSF.execute("PF('dialog').hide()");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void asignarParametro() {
        try {
            EstablecimientoParametros establecimientoParametros = establecimiento.getEstablecimientoParametros();
            establecimientoParametros.setEstablecimientoParametrosPK(new EstablecimientoParametrosPK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), establecimientoParametros.getParametros().getCodParametro()));
            establecimientoParametros = gestorEstablecimiento.validarParametro(establecimientoParametros);
//            gestorEstablecimiento.guardarEstablecimientoParametros(establecimientoParametros);

            EstablecimientoParametros establecimientoParametrosBorrar = null;
            for (EstablecimientoParametros ep : establecimiento.getEstablecimientoParametrosSet()) {
                if (establecimientoParametros.equals(ep)) {
                    establecimientoParametrosBorrar = ep;
                }
            }

            if (establecimientoParametros != null) {
                establecimiento.getEstablecimientoParametrosSet().remove(establecimientoParametrosBorrar);
            }
            establecimiento.getEstablecimientoParametrosSet().add(establecimientoParametros);
            establecimiento.setEstablecimientoParametros(new EstablecimientoParametros());
            UtilJSF.execute("PF('dialog').hide()");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }
       
    public void crearServicio() {
        try {
            Servicios servicios = establecimiento.getServicios();
            if (servicios.getServiciosPK() == null || servicios.getServiciosPK().getCodServicio() == null
                    || servicios.getServiciosPK().getCodServicio() == 0) {
                servicios.setServiciosPK(new ServiciosPK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), gestorGeneral.nextval(GestorGeneral.ATENCION_SERVICIOS_COD_SERVICIO_SEQ)));
                servicios.getServiciosPK().setCodEstablecimiento(establecimiento.getEstablecimientoPK().getCodEstablecimiento());
                servicios.getServiciosPK().setCodInstitucion(establecimiento.getEstablecimientoPK().getCodInstitucion());
            }
            servicios = gestorServicios.validarServicio(servicios);
            gestorServicios.guardarActualizarEntidad(servicios);

            Servicios serviciosRemover = null;
            for (Servicios s : serviciosList) {
                if (s.equals(establecimiento.getServicios())) {
                    serviciosRemover = s;
                }
            }
            if (serviciosRemover != null) {
                serviciosList.remove(serviciosRemover);
            }
            serviciosList.add(servicios);
            UtilJSF.execute("PF('dialog').hide()");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void crearParametro() {
        try {
            parametrosNuevo.setCodParametro(gestorGeneral.nextval(GestorGeneral.PARAMETROS_COD_PARAMETRO_SEQ));
            parametrosNuevo.setEntidad(Establecimiento.class.getSimpleName());
            parametrosNuevo = gestorEstablecimiento.validarParametros(parametrosNuevo);
            gestorEstablecimiento.guardarParametros(parametrosNuevo);
            UtilJSF.execute("PF('dialogNuevoParametro').hide()");
            UtilMSG.addSuccessMsg("Parametro Creado Correctamente", "Registro Satisfactorio");
            this.cargarParametros();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void cargarDialogoCrearServicio() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        establecimiento.setServicios(new Servicios());
        sesion.setDialogo(new Dialogo("dialogos/servicio.xhtml", "Nuevo Servicio Establecimiento", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoCrearResolucion() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/parametroResolucion.xhtml", "Nuevo Parametro Establecimiento", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoModificarResolucion() {
        FacturasConfiguracion facturasConfiguracion = (FacturasConfiguracion) UtilJSF.getBean("varPrefijosFacturacion");
        establecimiento.setFacturasConfiguracion(facturasConfiguracion);
        this.cargarDialogoCrearResolucion();
    }

    public void cargarDialogoCrearParametro() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/parametroEstablecimiento.xhtml", "Nuevo Resolución Establecimiento", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    private void cargarInstitucionesUsuario() {
        Usuarios usuarios = ((Sesion) UtilJSF.getBean("sesion")).getUsuario();
        this.institucionList = new ArrayList<>();
        this.institucionList.addAll(usuarios.getInstitucionSet());
    }

    private void cargarMunicipios() {
        this.getMunicipiosList().addAll(gestorMunicipios.cargarMunicipios());
    }

    private void cargarEstablecimientosInstitucion() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            this.establecimientoList = new ArrayList<>();
            this.establecimientoList.addAll(gestorEstablecimiento.cargarListaEstablecimientos(sesion.getEstablecimiento().getEstablecimientoPK().getCodInstitucion()));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void guardarEstablecimiento() {
        try {
//            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            if (establecimiento.getEstablecimientoPK() == null || establecimiento.getEstablecimientoPK().getCodEstablecimiento() == 0) {
                establecimiento.setEstablecimientoPK(new EstablecimientoPK(establecimiento.getInstitucion().getCodInstitucion(),
                        gestorEstablecimiento.siguienteCodigoEstablecimiento(establecimiento.getInstitucion().getCodInstitucion())));
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(new Date());
                gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
                establecimiento.setFechaCierreDiario(gregorianCalendar.getTime());
                establecimiento.setTipoEstablecimiento("I");
            }

            establecimiento = gestorEstablecimiento.validarEstablecimiento(establecimiento);
            gestorEstablecimiento.guardarEstablecimiento(establecimiento);
            establecimientoList.add(establecimiento);
            establecimiento = new Establecimiento();
            UtilMSG.addSuccesMsg();
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }

        }

    }

    public void subirItemServicio() {
        establecimiento.setServicios(new Servicios());
        Servicios s = (Servicios) UtilJSF.getBean("varServicioEstablecimiento");
        serviciosList.remove(s);
        establecimiento.setServicios(s);
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/servicio.xhtml", "Nuevo Servicio Establecimiento", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");

    }

    public void subirItemEstablecimiento() {
        establecimiento = (Establecimiento) UtilJSF.getBean("varEstablecimiento");
        this.cargarServiciosEstablecimiento();
        establecimientoList.remove(establecimiento);
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

    /**
     * @return the establecimientoList
     */
    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    /**
     * @param establecimientoList the establecimientoList to set
     */
    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
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
     * @return the gestorMunicipios
     */
    public GestorMunicipios getGestorMunicipios() {
        return gestorMunicipios;
    }

    /**
     * @param gestorMunicipios the gestorMunicipios to set
     */
    public void setGestorMunicipios(GestorMunicipios gestorMunicipios) {
        this.gestorMunicipios = gestorMunicipios;
    }

    /**
     * @return the municipiosList
     */
    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    /**
     * @param municipiosList the municipiosList to set
     */
    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
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
     * @return the parametrosNuevo
     */
    public Parametros getParametrosNuevo() {
        return parametrosNuevo;
    }

    /**
     * @param parametrosNuevo the parametrosNuevo to set
     */
    public void setParametrosNuevo(Parametros parametrosNuevo) {
        this.parametrosNuevo = parametrosNuevo;
    }

    /**
     * @return the parametrosList
     */
    public List<Parametros> getParametrosList() {
        List<Parametros> parametrosEstablecimientoList = new ArrayList<>();
        for (Parametros p : parametrosList) {
            if (p.getEntidad().equalsIgnoreCase(Establecimiento.class.getSimpleName())) {
                parametrosEstablecimientoList.add(p);
            }
        }
        return parametrosEstablecimientoList;
    }

    /**
     * @param parametrosList the parametrosList to set
     */
    public void setParametrosList(List<Parametros> parametrosList) {
        this.parametrosList = parametrosList;
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
     * @return the serviciosList
     */
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    /**
     * @param serviciosList the serviciosList to set
     */
    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
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
     * @return the servicios
     */
    public Servicios getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the tipoRecursoDualList
     */
    public DualListModel<TipoRecurso> getTipoRecursoDualList() {
        return tipoRecursoDualList;
    }

    /**
     * @param tipoRecursoDualList the tipoRecursoDualList to set
     */
    public void setTipoRecursoDualList(DualListModel<TipoRecurso> tipoRecursoDualList) {
        this.tipoRecursoDualList = tipoRecursoDualList;
    }

    /**
     * @return the gestorTipoRecurso
     */
    public GestorTipoRecurso getGestorTipoRecurso() {
        return gestorTipoRecurso;
    }

    /**
     * @param gestorTipoRecurso the gestorTipoRecurso to set
     */
    public void setGestorTipoRecurso(GestorTipoRecurso gestorTipoRecurso) {
        this.gestorTipoRecurso = gestorTipoRecurso;
    }

    /**
     * @return the seccionDualList
     */
    public DualListModel<Seccion> getSeccionDualList() {
        return seccionDualList;
    }

    /**
     * @param seccionDualList the seccionDualList to set
     */
    public void setSeccionDualList(DualListModel<Seccion> seccionDualList) {
        this.seccionDualList = seccionDualList;
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
}
