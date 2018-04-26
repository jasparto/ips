/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.vista;

import com.sinergia.atencion.controlador.GestorRecursos;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.RecursoPK;
import com.sinergia.atencion.modelo.RecursoUsuarios;
import com.sinergia.atencion.modelo.RecursoUsuariosPK;
import com.sinergia.controlador.GestorGeneral;
import com.sinergia.facturacion.controlador.GestorFacturas;
import com.sinergia.facturacion.modelo.FacturasConfiguracion;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.publico.controlador.GestorEstablecimiento;
import com.sinergia.publico.controlador.GestorInstitucion;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.EstablecimientoParametros;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.controlador.GestorUsuario;
import com.sinergia.usuario.modelo.PermisosBinarios;
import com.sinergia.usuario.modelo.Roles;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.*;
import com.sinergia.vista.UIFacturas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Julian
 */
@ManagedBean(name = "uiUsuario")
@SessionScoped

public class UIUsuario implements Serializable {

    public static final String DIALOGO_CREAR = "dialogoNuevoUsuario";
    public static final String COMPONENTES_REFRESCAR = "formNuevoUsuario";
    public static final String FILTRO_USUARIO = "USUARIO";
    public static final String FILTRO_DOCUMENTO = "DOCUMENTO";
    public static final String FILTRO_CORREO = "CORREO";
    private List<Roles> itemsRoles;
    private List<Establecimiento> itemsEstablecimiento;
//    private List<String> establecimientosDisponibles = new ArrayList<>();
//    private List<String> establecimientosAsignados = new ArrayList<>();
//    private DualListModel<String> itemsDualEstablecimientos = new DualListModel<>(establecimientosDisponibles, establecimientosAsignados);

    private List<Establecimiento> establecimientosListDisponibles = new ArrayList<>();
    private List<Establecimiento> establecimientosListAsignados = new ArrayList<>();
//    private DualListModel<Establecimiento> establecimientoDualList = new DualListModel<>(establecimientosListDisponibles, getEstablecimientosListAsignados());

    private List<Institucion> institucionListDisponibles = new ArrayList<>();
    private List<Institucion> institucionListAsignados = new ArrayList<>();
    private DualListModel<Institucion> institucionDualList = new DualListModel<>(institucionListDisponibles, institucionListAsignados);

    private String usuarioBuscar;
    private Establecimiento establecimiento = new Establecimiento();
    private Institucion institucion = new Institucion();

    @ManagedProperty("#{gestorEstablecimiento}")
    private GestorEstablecimiento gestorEstablecimiento;
    @ManagedProperty("#{gestorGeneral}")
    private GestorGeneral gestorGeneral;
    @ManagedProperty("#{gestorUsuario}")
    private GestorUsuario gestorUsuario;
    @ManagedProperty("#{gestorInstitucion}")
    private GestorInstitucion gestorInstitucion;
    @ManagedProperty("#{gestorRecursos}")
    private GestorRecursos gestorRecursos;
    @ManagedProperty("#{gestorFacturas}")
    private GestorFacturas gestorFacturas;

    public UIUsuario() {
        try {
            this.gestorEstablecimiento = new GestorEstablecimiento();
//            this.gestorUsuario = new GestorUsuario();
            this.itemsEstablecimiento = new ArrayList<>();
            this.itemsRoles = new ArrayList<>();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    @PostConstruct
    public void UIUsuario() {
//        this.cargarRoles();
    }

    public void asignarTipoRecursoUsuario() {
        try {
            Usuarios usuarios = (Usuarios) UtilJSF.getBean("usuarios");
            if (institucion == null || institucion.getCodInstitucion() == null || institucion.getCodInstitucion() == 0) {
                throw new Exception("Selecciona la institución.", UtilLog.TW_VALIDACION);
            }
            if (!gestorUsuario.existeRelUsuariosEstablecimiento(institucion, usuarios)) {
                throw new Exception("Se debe asignar y guardar primero el establecimiento, antes de asignar el recurso.", UtilLog.TW_VALIDACION);
            }
            Recurso recurso = gestorRecursos.cargarRecursoUsuario(institucion, usuarios);
            if (recurso == null || recurso.getRecursoPK() == null
                    || recurso.getRecursoPK().getCodRecurso() == null
                    || recurso.getRecursoPK().getCodRecurso() == 0) {
                recurso = new Recurso(new RecursoPK(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.ATENCION_RECURSO_COD_RECURSO_SEQ)), null);
//                recurso.setCodInstitucion(institucion.getCodInstitucion());
            }
            recurso.setInstitucion(institucion);
            recurso.setNombre(usuarios.getNombre() + " " + usuarios.getApellido());
            recurso.setTipoRecurso(institucion.getTipoRecurso());
            recurso.setCodTipoRecurso(institucion.getTipoRecurso().getCodTipoRecurso());

            recurso.setEstablecimientoSet(new HashSet<Establecimiento>());
            for (Establecimiento e : usuarios.getRelUsuariosEstablecimientoSet()) {
                recurso.getEstablecimientoSet().add(e);
            }
            gestorRecursos.guardarRecurso(recurso);

            RecursoUsuarios recursoUsuarios = new RecursoUsuarios(new RecursoUsuariosPK(institucion.getCodInstitucion(), recurso.getRecursoPK().getCodRecurso(), usuarios.getUsuariosPK().getCodDocumento(), usuarios.getUsuariosPK().getDocumentoUsuario()));
            gestorRecursos.guardarRecursoUsuarios(recursoUsuarios);

            Recurso recursoRemover = null;
            for (Recurso r : usuarios.getRecursoSet()) {
                if (r.equals(recurso)) {
                    recursoRemover = r;
                }
            }
            if (recursoRemover != null) {
                usuarios.getRecursoSet().remove(recursoRemover);
            }
            usuarios.getRecursoSet().add(recurso);
            UtilJSF.setBean("usuarios", usuarios, UtilJSF.SESSION_SCOPE);
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void cargarDialogoTipoRecursoUsuario() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/tipoRecursoUsuario.xhtml", "Tipo Recurso Usuarios", "clip"));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    /**
     * Valida si el usuario tiene el rol enviado.
     *
     * @param rol
     * @return
     */
    public boolean rol(String rol) {
        try {
            Usuarios usuarios = ((Sesion) UtilJSF.getBean("sesion")).getUsuario();
            if (usuarios.getRelUsuariosRolesSet() != null) {
                for (Roles r : usuarios.getRelUsuariosRolesSet()) {
                    if (r.getNombre().equalsIgnoreCase(rol)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            UtilLog.generarLog(this.getClass(), e);
        }
        return false;
    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

//    /**
//     * Cargar los datos del usuario.
//     *
//     * @param establecimiento
//     * @param usuario
//     *
//     */
//    private Usuarios cargarDatosUsuario(Establecimiento establecimiento, Usuarios usuario) throws Exception {
////        GestorUsuario gestorUsuario = new GestorUsuario();
//        usuario = gestorUsuario.cargarDatosUsuario(establecimiento, usuario, FILTRO_USUARIO);
//        return usuario;
//    }
    public void cargarEstablecimientosUsuario(String usuario) {
        try {
            Usuarios u = new Usuarios();
            if (usuario != null && !usuario.equalsIgnoreCase("")) {
                u.setUsuario(usuario.trim());
                u = gestorUsuario.cargarDatosUsuario(u, UIUsuario.FILTRO_USUARIO);
                if (u == null) {
                    throw new Exception("No existe el usuario ingresado", UtilLog.TW_VALIDACION);
                }
                for (Establecimiento e : u.getRelUsuariosEstablecimientoSet()) {
                    e.setFacturasConfiguracionSet(gestorFacturas.cargarFacturasConfiguracion(e));
                }
            }
            this.itemsEstablecimiento.clear();
            if (u != null) {
                for (Establecimiento e : u.getRelUsuariosEstablecimientoSet()) {
                    this.itemsEstablecimiento.add(e);
                }
            } else {
                UtilMSG.addWarningMsg("Usuario no registrado, " + UtilMSG.getSupportMsg());
            }
        } catch (Exception ex) {
            UtilMSG.addErrorMsg(ex.getMessage());
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void reporte() {
//        try {
//            ServletReporte u = new ServletReporte();
//            List<String> l = new ArrayList<>();
////            u.generarReporteJasper("cierreDiario", "pdf", l);
//        } catch (ServletException ex) {
//            Logger.getLogger(UIUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(UIUsuario.class.getName()).log(Level.SEVERE, null, ex);

//        RequestContext context = RequestContext.getCurrentInstance();
//        GestorReporte gestorReporte;
//        try {
//            gestorReporte = new GestorReporte();
//            String rutaReporte = "./visorIPS";
//            String comando = gestorReporte.cargarComandoWindowOpen(rutaReporte);
//            context.execute(comando);
//        } catch (Exception ex) {
//            Logger.getLogger(UIUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //String rutaReporte = gestorReporte.cargarRutaReporte(establecimiento, cierre, Reporte.CIERRE_DIARIO);
        //String rutaReporte = gestorReporte.cargarRutaReporte(establecimiento, cierre, Reporte.CIERRE_DIARIO);
        //String rutaReporte = gestorReporte.cargarRutaReporte(establecimiento, cierre, Reporte.CIERRE_DIARIO);
        //String rutaReporte = gestorReporte.cargarRutaReporte(establecimiento, cierre, Reporte.CIERRE_DIARIO);
    }

    public String validarUsuario() {
        Sesion sesion = new Sesion();
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");

        boolean usuarioValido;
        try {
            if (establecimiento == null) {
                throw new Exception("El usuario no tiene establecimientos asignados.", UtilLog.TW_VALIDACION);
            }
            usuario.setUsuario(usuario.getUsuario().trim());
            usuarioValido = gestorUsuario.validarUsuario(usuario.getUsuario(), usuario.getClave());
            if (usuarioValido) {
//                establecimiento = this.cargarEstablecimiento(establecimiento.getEstablecimientoPK().getCodEstablecimiento());
                usuario = gestorUsuario.cargarDatosUsuario(establecimiento, usuario, FILTRO_USUARIO);

//                if (establecimiento.getPrefijo() == null || establecimiento.getPrefijo().equalsIgnoreCase("")) {
//                    throw new Exception("No se pudieron cargar los prefijos.", UtilLog.TW_VALIDACION);
//                }
//                Properties propiedades = Propiedades.getInstancia().getPropiedades();
//                Sesion.RUTA_SERVICIO = propiedades.getProperty("urlServicio");
                if (usuario.getRecursoSet() != null) {
                    for (Recurso r : usuario.getRecursoSet()) {
                        usuario.setRecurso(r);
                    }
                } else if (usuario.getRecursoSet() == null || usuario.getRecurso() == null) {
                    String mensaje = "El usuario ingresado no tiene asignado un recurso.";
//                    UtilMSG.addWarningMsg(mensaje, "No puede diligenciar nuevas historias.");
                    UtilJSF.execute("alert('" + mensaje + "')");
                }
                sesion.setUsuario(usuario);

                if (establecimiento.getFacturasConfiguracionSet() != null && !establecimiento.getFacturasConfiguracionSet().isEmpty()) {
                    for (FacturasConfiguracion fc : establecimiento.getFacturasConfiguracionSet()) {
                        switch (fc.getTipoResolucion()) {
                            case UIFacturas.TIPO_RESOLUCION_PREFACTURA:
                                establecimiento.setFacturasConfiguracionPrefactura(fc);
                                break;
                            case UIFacturas.TIPO_RESOLUCION_FACTURA:
                                establecimiento.setFacturasConfiguracionFactura(fc);
                                break;
                            case UIFacturas.TIPO_RESOLUCION_COPAGO:
                                establecimiento.setFacturasConfiguracionCopago(fc);
                                break;
                        }
                    }
                } else {
//                    UtilMSG.addWarningMsg("Establecimiento sin resolución", "El establecimiento no tiene configurada la resolución de facturación, lo que limitara algunas funcionalidades.");
                    UtilJSF.execute("alert('El establecimiento no tiene configurada la resolución de facturación, lo que limitara algunas funcionalidades.')");
                }

                if (establecimiento.getEstablecimientoParametrosSet() != null && !establecimiento.getEstablecimientoParametrosSet().isEmpty()) {
                    HashMap hm = new HashMap();
                    for (EstablecimientoParametros ep : establecimiento.getEstablecimientoParametrosSet()) {
                        hm.put(ep.getParametros().getNombre(), ep.getValor());
                    }
                    establecimiento.setEstablecimientoParametrosHashMap(hm);
                }

                sesion.setEstablecimiento(establecimiento);
                sesion.getTipoComponentesList().addAll(gestorGeneral.cargarTipoComponentes());
                this.cargarRoles(establecimiento.getInstitucion());

                List<PermisosBinarios> permisosBinarioses = new ArrayList<>();
                permisosBinarioses.addAll(gestorGeneral.cargarPermisos());
                sesion.setPermisosBinariosList(permisosBinarioses);

                usuario = new Usuarios();
                UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
                UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
                return ("/inicio/principal.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            if (UtilLog.causaControlada(e)) {
                UtilMSG.addWarningMsg(e.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), e);
                UtilMSG.addSupportMsg();
            }
        }
        return "";
    }

    public List<String> autoCompletaUsuario(String query) {
        List<String> resultado = new ArrayList<>();
        try {
            Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
//            GestorUsuario gestorUsuario = new GestorUsuario();
            resultado.addAll(gestorUsuario.autoCompletaUsuario(establecimiento, query));

        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Error al consultar el usuario");
            UtilLog.generarLog(this.getClass(), ex);
        }
        return resultado;
    }

    public void cargarEstablecimientosUsuario() {
        try {
            Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
            establecimientosListDisponibles.clear();
            establecimientosListAsignados.clear();

            establecimientosListDisponibles.addAll(gestorEstablecimiento.cargarListaEstablecimientos(institucion.getCodInstitucion()));
            for (Establecimiento e : usuario.getRelUsuariosEstablecimientoSet()) {
                if (e.getInstitucion().equals(institucion)) {
                    establecimientosListAsignados.add(e);
                }
            }
            establecimientosListDisponibles.removeAll(establecimientosListAsignados);
            institucion.setEstablecimientoDualList(new DualListModel<>(establecimientosListDisponibles, establecimientosListAsignados));

        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void cargarUsuario() {
        try {
            Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
            Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            usuario.setUsuario(usuarioBuscar.split("-")[1].trim());
            usuario = gestorUsuario.cargarDatosUsuario(establecimiento, usuario, FILTRO_USUARIO);
//            for (Roles r : itemsRoles) {
//                if (usuario.getRoles() != null && usuario.getRol().getCodigoRol() == r.getCodigoRol()) {
//                    usuario.setRol(r);
//                }
//            }

            for (Roles r : usuario.getRelUsuariosRolesSet()) {
                if (r.getRolesPK() != null) {
                    for (Roles ir : itemsRoles) {
                        if (ir.getRolesPK() != null) {
                            if (r.getRolesPK().getCodigoRol() == ir.getRolesPK().getCodigoRol()) {
                                usuario.setRoles(ir);
                                break;
                            }
                        }
                    }
                }
            }

//            this.establecimientosDisponibles = this.transformarLista(gestorEstablecimiento.cargarListaEstablecimientos());
//            this.establecimientosAsignados = this.transformarLista(gestorEstablecimiento.cargarListaEstablecimientosUsuario(usuario.getUsuariosPK().getDocumentoUsuario()));
            establecimientosListDisponibles.clear();
            establecimientosListAsignados.clear();
//            establecimientosListDisponibles.addAll(gestorEstablecimiento.cargarListaEstablecimientos(1));
//            getEstablecimientosListAsignados().addAll(usuario.getRelUsuariosEstablecimientoSet());
//            establecimientoDualList = new DualListModel<>(establecimientosListDisponibles, getEstablecimientosListAsignados());

            institucionListDisponibles.clear();
            getInstitucionListAsignados().clear();
            institucionListDisponibles.addAll(gestorInstitucion.cargarInstituciones());

            if (usuario.getInstitucionSet() != null) {
                getInstitucionListAsignados().addAll(usuario.getInstitucionSet());
            }
            institucionListDisponibles.removeAll(getInstitucionListAsignados());

            institucionDualList = new DualListModel<>(institucionListDisponibles, getInstitucionListAsignados());
            institucion = (Institucion) new Institucion().clone();
//            this.itemsDualEstablecimientos = new DualListModel<>((List<String>) this.removerElementosAsignados(establecimientosDisponibles, establecimientosAsignados), establecimientosAsignados);
            UtilJSF.setBean("usuarios", usuario, UtilJSF.SESSION_SCOPE);
        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Error al cargar el usuario");
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public List<String> transformarLista(final List<?> objects) {
        List<String> lista = new ArrayList<>();
        for (Object ob : objects) {
            Establecimiento e = (Establecimiento) ob;
            lista.add(e.getNombre());
        }
        return lista;
    }

    public List<?> removerElementosAsignados(List<?> disponibles, List<?> asignados) {
        CopyOnWriteArrayList origen = new CopyOnWriteArrayList(disponibles);
        CopyOnWriteArrayList destino = new CopyOnWriteArrayList(asignados);
        for (Object obj1 : origen) {
            for (Object obj2 : destino) {
                if (obj1.equals(obj2)) {
                    origen.remove(obj2);
                }
            }
        }
        return new ArrayList(origen);
    }

    public String cerrarSesion() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) externalContext.getSession(true)).invalidate();
        return ("/ingreso.xhtml?faces-redirect=true");
    }

    private Establecimiento cargarEstablecimiento(int codInstitucion, int codEstablecimiento) throws Exception {
        return gestorEstablecimiento.cargarEstablecimiento(codInstitucion, codEstablecimiento);

    }

    private List<Establecimiento> cargarEstablecimientosAsignados() throws Exception {
        GestorEstablecimiento gestorEstablecimiento = new GestorEstablecimiento();
        List<Establecimiento> listaEstablecimientosAsignados = new ArrayList<>();
//        List<Establecimiento> listaEstablecimientos = (List<Establecimiento>) gestorEstablecimiento.cargarListaEstablecimientos();
        List<Establecimiento> listaEstablecimientos = new ArrayList<>();
//        List<String> asignados = this.itemsDualEstablecimientos.getTarget();

//        for (Establecimiento obe : listaEstablecimientos) {
//            for (String e : asignados) {
//                if (obe.getNombre().equalsIgnoreCase(e)) {
//                    listaEstablecimientosAsignados.add(obe);
//                }
//            }
//        }
        return listaEstablecimientosAsignados;
    }

    public void nuevo() {
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
        Institucion institucionUsuario = ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento().getInstitucion();
//        Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
        try {
//            GestorUsuario gestorUsuario = new GestorUsuario();
//            if (gestorUsuario.existeDocumentoUsuario(usuario)) {
//                throw new Exception("El documento de identificación ya existe por favor valide.", UtilLog.TW_VALIDACION);
//            }
            Usuarios usuarioTemporal = new Usuarios();

            usuario = gestorUsuario.validarUsuarioNuevo(usuario);

            usuarioTemporal = gestorUsuario.cargarDatosUsuario(usuario, UIUsuario.FILTRO_DOCUMENTO);
            if (usuarioTemporal != null && usuarioTemporal.getUsuariosPK().getDocumentoUsuario() != null
                    && !usuarioTemporal.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("0")) {
                throw new Exception("El tipo de documento y documento ya se encuentra registrado.", UtilLog.TW_VALIDACION);
            }

            usuarioTemporal = null;
            usuarioTemporal = gestorUsuario.cargarDatosUsuario(usuario, UIUsuario.FILTRO_CORREO);
            if (usuarioTemporal != null && usuarioTemporal.getUsuariosPK().getDocumentoUsuario() != null
                    && !usuarioTemporal.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("0")) {
                throw new Exception("El CORREO ingresado, ya esta asignado a otro usuario, por favor valida la información.", UtilLog.TW_VALIDACION);
            }

            usuarioTemporal = null;
            usuarioTemporal = gestorUsuario.cargarDatosUsuario(usuario, UIUsuario.FILTRO_USUARIO);
            if (usuarioTemporal != null && usuarioTemporal.getUsuariosPK().getDocumentoUsuario() != null
                    && !usuarioTemporal.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("0")) {
                throw new Exception("El USUARIO ingresado, ya esta asignado a otro usuario, por favor valida la información.", UtilLog.TW_VALIDACION);
            }

            Set<Institucion> iSet = new HashSet<>();
            iSet.add(institucionUsuario);
            usuario.setInstitucionSet(iSet);
            usuario.getRoles().getRolesPK().setCodInstitucion(institucionUsuario.getCodInstitucion());
            usuario.getRelUsuariosRolesSet().add(usuario.getRoles());

            gestorUsuario.guardarUsuario(usuario);
            usuario = new Usuarios();
            this.usuarioBuscar = null;
            UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
            UtilJSF.execute("PF('dialogoNuevoUsuario').hide();");
            UtilMSG.addSuccessMsg("Usuario creado");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), "Validación");
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void guardar() {
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
        Establecimiento establecimiento = (Establecimiento) ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
        try {
            usuario = gestorUsuario.validarUsuario(usuario);
            usuario.setInstitucionSet(new HashSet<Institucion>());
            usuario.getInstitucionSet().addAll(institucionDualList.getTarget());
//            List<Establecimiento> establecimientosListNuevos = new ArrayList<>();
//            for (Establecimiento e : institucion.getEstablecimientoDualList().getTarget()) {
//                for (Establecimiento eAsignado : usuario.getRelUsuariosEstablecimientoSet()) {
//                    if (!e.equals(eAsignado)) {
//                        establecimientosListNuevos.add(e);
//                    }
//                }
//            }
            if (institucion != null) {
                List<Establecimiento> establecimientosListActuales = new ArrayList<>();
                establecimientosListActuales.addAll(usuario.getRelUsuariosEstablecimientoSet());
                usuario.getRelUsuariosEstablecimientoSet().clear();
                usuario.getRelUsuariosEstablecimientoSet().addAll(institucion.getEstablecimientoDualList().getTarget());
                establecimientosListActuales.removeAll(institucion.getEstablecimientoDualList().getTarget());
                usuario.getRelUsuariosEstablecimientoSet().addAll(establecimientosListActuales);
                usuario.getRelUsuariosEstablecimientoSet().removeAll(institucion.getEstablecimientoDualList().getSource());
            }

            usuario.getRelUsuariosRolesSet().clear();
//            usuario.getRelUsuariosRolesSet().add(usuario.getRoles());
            for (Institucion i : usuario.getInstitucionSet()) {
                Roles r = usuario.getRoles();
                r.getRolesPK().setCodInstitucion(i.getCodInstitucion());
                usuario.getRelUsuariosRolesSet().add(r);
            }

            gestorUsuario.guardarUsuario(usuario);
//            if (usuario.ge tRol() == null || usuario.getRol().getCodigoRol() == 0) {
//                UtilMSG.addWarningMsg("Por favor seleccione el rol del usuario, contacte al adminsitrador del sistema.");
//                return;
//            }
//            GestorUsuario gestorUsuario = new GestorUsuario();
//            usuario.setListaEstablecimientos(this.cargarEstablecimientosAsignados());
//            gestorUsuario.almacenarUsuario(establecimiento, usuario);
//            gestorUsuario.almacenarEstablecimientosUsuario(usuario);
//            gestorUsuario.almacenarRolUsuario(establecimiento, usuario);
            usuario = new Usuarios();
            this.usuarioBuscar = null;
            UtilJSF.setBean("usuario", usuario, UtilJSF.SESSION_SCOPE);
            UtilMSG.addSuccessMsg("Usuario modificado correctamente");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilMSG.addErrorMsg("Ocurrio una excepción al modificar usuario");
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void eliminar() {
        UtilMSG.addWarningMsg("No se permite eliminar usuario, por favor inactivelo.");
    }

    private void cargarRoles(Institucion institucion) throws Exception {
        try {
            this.itemsRoles = new ArrayList<>();
            this.itemsRoles.addAll(gestorUsuario.cargarRoles(institucion));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
            throw ex;
        }
    }

//    /**
//     * @return the itemsDualEstablecimientos
//     */
//    public DualListModel<String> getItemsDualEstablecimientos() {
//        return itemsDualEstablecimientos;
//    }
//
//    /**
//     * @param itemsDualEstablecimientos the itemsDualEstablecimientos to set
//     */
//    public void setItemsDualEstablecimientos(DualListModel<String> itemsDualEstablecimientos) {
//        this.itemsDualEstablecimientos = itemsDualEstablecimientos;
//    }
    /**
     * @return the usuarioBuscar
     */
    public String getUsuarioBuscar() {
        return usuarioBuscar;
    }

    /**
     * @param usuarioBuscar the usuarioBuscar to set
     */
    public void setUsuarioBuscar(String usuarioBuscar) {
        this.usuarioBuscar = usuarioBuscar;
    }

    /**
     * @return the itemsEstablecimiento
     */
    public List<Establecimiento> getItemsEstablecimiento() {
        return itemsEstablecimiento;
    }

    /**
     * @param itemsEstablecimiento the itemsEstablecimiento to set
     */
    public void setItemsEstablecimiento(List<Establecimiento> itemsEstablecimiento) {
        this.itemsEstablecimiento = itemsEstablecimiento;
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
     * @return the itemsRoles
     */
    public List<Roles> getItemsRoles() {
        return itemsRoles;
    }

    /**
     * @param itemsRoles the itemsRoles to set
     */
    public void setItemsRoles(List<Roles> itemsRoles) {
        this.itemsRoles = itemsRoles;
    }

    /**
     * @return the institucionDualList
     */
    public DualListModel<Institucion> getInstitucionDualList() {
        return institucionDualList;
    }

    /**
     * @param institucionDualList the institucionDualList to set
     */
    public void setInstitucionDualList(DualListModel<Institucion> institucionDualList) {
        this.institucionDualList = institucionDualList;
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
     * @return the establecimientosListAsignados
     */
    public List<Establecimiento> getEstablecimientosListAsignados() {
        return establecimientosListAsignados;
    }

    /**
     * @param establecimientosListAsignados the establecimientosListAsignados to
     * set
     */
    public void setEstablecimientosListAsignados(List<Establecimiento> establecimientosListAsignados) {
        this.establecimientosListAsignados = establecimientosListAsignados;
    }

    /**
     * @return the institucionListAsignados
     */
    public List<Institucion> getInstitucionListAsignados() {
        return institucionListAsignados;
    }

    /**
     * @param institucionListAsignados the institucionListAsignados to set
     */
    public void setInstitucionListAsignados(List<Institucion> institucionListAsignados) {
        this.institucionListAsignados = institucionListAsignados;
    }

    /**
     * @return the institucion
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
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

}
