/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.vista;

import com.sinergia.atencion.controlador.GestorRecursos;
import com.sinergia.atencion.controlador.GestorTipoRecurso;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.RecursoPK;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.controlador.GestorGeneral;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.publico.controlador.GestorInstitucion;
import com.sinergia.publico.controlador.GestorLista;
import com.sinergia.publico.modelo.DetalleLista;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.publico.modelo.InstitucionParametros;
import com.sinergia.publico.modelo.InstitucionParametrosPK;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.publico.modelo.Parametros;
import com.sinergia.usuario.controlador.GestorMenus;
import com.sinergia.usuario.controlador.GestorRoles;
import com.sinergia.usuario.modelo.Menus;
import com.sinergia.usuario.modelo.MenusPK;
import com.sinergia.usuario.modelo.PermisosBinarios;
import com.sinergia.usuario.modelo.Roles;
import com.sinergia.usuario.modelo.RolesMenus;
import com.sinergia.usuario.modelo.RolesMenusPK;
import com.sinergia.usuario.modelo.RolesPK;
import com.sinergia.utilidades.modelo.UtilBinario;
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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Hibernate;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiInstitucion")
@SessionScoped
public class UIInstitucion implements Serializable {

    @ManagedProperty("#{gestorInstitucion}")
    private GestorInstitucion gestorInstitucion;

    @ManagedProperty("#{gestorGeneral}")
    private GestorGeneral gestorGeneral;

    @ManagedProperty("#{gestorTipoRecurso}")
    private GestorTipoRecurso gestorTipoRecurso;

    @ManagedProperty("#{gestorRecursos}")
    private GestorRecursos gestorRecursos;

    @ManagedProperty("#{gestorLista}")
    private GestorLista gestorLista;

    @ManagedProperty("#{gestorMenus}")
    private GestorMenus gestorMenus;

    @ManagedProperty("#{gestorRoles}")
    private GestorRoles gestorRoles;

    private Institucion institucion = new Institucion();
    private List<Institucion> institucionsList;
    private List<Parametros> parametrosList;
    private Parametros parametrosNuevo = new Parametros();
    private DualListModel<Establecimiento> establecimientoDualList = new DualListModel<>();
    private DualListModel<Menus> menusDualList = new DualListModel<>();
    private DualListModel<PermisosBinarios> permisosBinariosDualList = new DualListModel<>();

    private Lista menus;
    private Lista roles;

    @PostConstruct
    public void init() {
        this.cargarInstituciones();
        this.cargarParametros();
        this.cargarMenus();
        this.cargarRoles();
    }

    private void cargarMenus() {
        menus = new Lista();
        menus = getGestorLista().cargarLista(UILista.MENUS);
    }

    private void cargarRoles() {
        roles = new Lista();
        roles = getGestorLista().cargarLista(UILista.ROLES);
    }

    public void asignarEstablecimientoRecurso() {
        for (Recurso r : institucion.getRecursoSet()) {
            if (institucion.getRecurso().equals(r)) {
                Set<Establecimiento> eSet = new HashSet<>();
                eSet.addAll(establecimientoDualList.getTarget());
                r.setEstablecimientoSet(eSet);
            }
        }
    }

    public void asignarPermisoMenuRol() {
        try {
            RolesMenus rolesMenus = null;
            if (Hibernate.isInitialized(institucion.getRoles().getRolesMenusSet())
                    && institucion.getRoles().getRolesMenusSet() != null) {
                for (RolesMenus rm : institucion.getRoles().getRolesMenusSet()) {
                    RolesMenusPK rolesMenusPK = new RolesMenusPK(institucion.getRoles().getRolesPK().getCodInstitucion(), institucion.getRoles().getRolesPK().getCodigoRol(), institucion.getRoles().getMenus().getMenusPK().getCodMenu());
                    if (rm.getRolesMenusPK().equals(rolesMenusPK)) {
                        rolesMenus = rm;
                        break;
                    }
                }
            }
            int permisobinario = 0;
            for (PermisosBinarios pb : permisosBinariosDualList.getTarget()) {
                permisobinario += pb.getCodPermisoBinario();
            }
            if (rolesMenus != null) {
                rolesMenus.setPemisoBinario(permisobinario);
                gestorRoles.guardarActualizarEntidad(rolesMenus);
            }
            UtilMSG.addSuccessMsg("permiso asignado correctamente.");
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void asignarMenuRol() {
        try {
            Set<RolesMenus> rolesMenusSet = new HashSet<>();
            for (Menus m : menusDualList.getTarget()) {
                RolesMenus rm = new RolesMenus(m.getMenusPK().getCodInstitucion(), institucion.getRoles().getRolesPK().getCodigoRol(), m.getMenusPK().getCodMenu());
                rolesMenusSet.add(rm);
            }
            institucion.getRoles().setRolesMenusSet(rolesMenusSet);
            gestorRoles.guardarRolesInstitucion(institucion.getRoles());
            UtilMSG.addSuccessMsg("Menu(s) asignado correctamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }

    }

    public void cargarPermisosMenusRol() {
        List<PermisosBinarios> permisosBinariosList = ((Sesion) UtilJSF.getBean("sesion")).getPermisosBinariosList();
        permisosBinariosDualList = new DualListModel<>();
        List<PermisosBinarios> permisosList = new ArrayList<>();
        List<PermisosBinarios> permisosListAsignados = new ArrayList<>();
        try {
            permisosList.addAll(permisosBinariosList);
            RolesMenus rolesMenus = null;
            if (institucion.getRoles().getMenus() != null && institucion.getRoles().getMenus().getMenusPK() != null) {
                for (RolesMenus rm : institucion.getRoles().getRolesMenusSet()) {
                    RolesMenusPK rolesMenusPK = new RolesMenusPK(institucion.getRoles().getRolesPK().getCodInstitucion(), institucion.getRoles().getRolesPK().getCodigoRol(), institucion.getRoles().getMenus().getMenusPK().getCodMenu());
                    if (rm.getRolesMenusPK().equals(rolesMenusPK)) {
                        rolesMenus = rm;
                        break;
                    }
                }
            }
            if (rolesMenus != null && rolesMenus.getPemisoBinario() != null) {
                for (PermisosBinarios pb : permisosBinariosList) {
                    if (UtilBinario.permisoBinario(pb.getCodPermisoBinario(), rolesMenus.getPemisoBinario())) {
                        permisosListAsignados.add(pb);
                    }
                }
            }
            permisosList.removeAll(permisosListAsignados);
            permisosBinariosDualList = new DualListModel<>(permisosList, permisosListAsignados);
        } catch (Exception e) {
            UtilLog.generarLog(this.getClass(), e);
            UtilMSG.addSupportMsg();
        }
    }

    public void cargarMenusRol() {
        menusDualList = new DualListModel<>();
        List<Menus> menusListAsignados = new ArrayList<>();
        List<Menus> menusList = new ArrayList<>();
        if (institucion.getRoles() != null) {
            if (Hibernate.isInitialized(institucion.getMenusSet()) && institucion.getMenusSet() != null) {
                menusList.addAll(institucion.getMenusSet());
                for (Menus m : institucion.getMenusSet()) {
                    if (Hibernate.isInitialized(institucion.getRoles().getRolesMenusSet()) && institucion.getRoles().getRolesMenusSet() != null) {
                        for (RolesMenus rm : institucion.getRoles().getRolesMenusSet()) {
                            MenusPK menusPK = new MenusPK(rm.getRolesMenusPK().getCodInstitucion(), rm.getRolesMenusPK().getCodMenu());
                            if (m.getMenusPK().equals(menusPK)) {
                                menusListAsignados.add(m);
                            }
                        }
                    }
                }
                menusList.removeAll(menusListAsignados);
            }
            institucion.getRoles().setMenusList(menusListAsignados);
        }
        menusDualList = new DualListModel<>(menusList, menusListAsignados);
    }

    public void cargarEstablecimientosRecurso() {
        establecimientoDualList = new DualListModel<>();
        List<Establecimiento> establecimientoListAsignados = new ArrayList<>();
        List<Establecimiento> establecimientoList = new ArrayList<>();

        if (institucion.getRecurso() != null) {
            if (institucion.getRecurso().getEstablecimientoSet() != null && Hibernate.isInitialized(institucion.getRecurso().getEstablecimientoSet())) {
                establecimientoListAsignados.addAll(institucion.getRecurso().getEstablecimientoSet());
            }
            establecimientoList.addAll(institucion.getEstablecimientosSet());
            establecimientoList.removeAll(establecimientoListAsignados);
        }

        establecimientoDualList = new DualListModel<>(establecimientoList, establecimientoListAsignados);
    }

    private void cargarInstituciones() {
        try {
            this.institucionsList = new ArrayList<>();
            this.institucionsList.addAll(gestorInstitucion.cargarInstituciones());
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    private void cargarParametros() {
        this.parametrosList = new ArrayList<>();
        this.parametrosList.addAll(gestorGeneral.cargarParametros());
    }

    public void limpiar() {
        this.cargarInstituciones();
        this.cargarParametros();
        this.institucion = new Institucion();
    }

    public void guardarInstitucion() {
        try {
            if (institucion.getCodInstitucion() == null || institucion.getCodInstitucion() == 0) {
                institucion.setCodInstitucion(gestorInstitucion.siguienteCodigoInstitucion());
            }
            institucion = gestorInstitucion.validarInstitucion(institucion);
            gestorInstitucion.guardarInstitucion(institucion);
            institucionsList.add(institucion);
            institucion = new Institucion();
            UtilMSG.addSuccesMsg();
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void subirItemInstitucion() {
        if (institucion != null && institucion.getCodInstitucion() != null) {
            UtilMSG.addWarningMsg("Lo cambios efectuados no se aplicaran.", "Lo cambios a la institución previamente cargar no se ejecutaron.");
            institucionsList.add(institucion);
        }
        institucion = new Institucion();
        institucion = (Institucion) UtilJSF.getBean("varInstitucion");
        institucion.setInstitucionParametros(new InstitucionParametros());
        institucion.setRoles(new Roles());
        institucionsList.remove(institucion);
    }

    public void cargarDialogoCrearTipoRecurso() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/tipoRecurso.xhtml", "Nuevo Tipo Recurso", "clip"));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void eliminarRol() {
        try {
            Roles roles = (Roles) UtilJSF.getBean("varRoles");
            gestorGeneral.eliminarEntidad(roles);
            Roles rolesRemover = null;
            for (Roles r : institucion.getRolesSet()) {
                if (r.equals(roles)) {
                    rolesRemover = r;
                }
            }
            if (rolesRemover != null) {
                institucion.getRolesSet().remove(rolesRemover);
            }
            UtilMSG.addSuccessMsg("Rol eliminado correctamente.");
        } catch (Exception ex) {
            UtilMSG.addWarningMsg("No es posible eliminar el Rol, posiblemente hay usuarios que lo tienen asociado.");
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void eliminarMenu() {
        try {
            Menus menus = (Menus) UtilJSF.getBean("varMenus");
            gestorGeneral.eliminarEntidad(menus);
            Menus menuRemover = null;
            for (Menus m : institucion.getMenusSet()) {
                if (m.equals(menus)) {
                    menuRemover = m;
                }
            }
            if (menuRemover != null) {
                institucion.getMenusSet().remove(menuRemover);
            }
            UtilMSG.addSuccessMsg("Menu eliminado correctamente.");
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void cargarDialogoCrearRol() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/rol.xhtml", "Nuevo Rol", "clip"));
        institucion.setRolesCrear(new Roles());
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoCrearMenu() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/menu.xhtml", "Nuevo Menú", "clip"));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoCrearRecurso() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        if (institucion.getRecurso() == null
                || institucion.getTipoRecurso() == null) {
            institucion.setRecurso(new Recurso(new TipoRecurso()));
        }
        sesion.setDialogo(new Dialogo("dialogos/recurso.xhtml", "Nuevo Recurso", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void cargarDialogoCrearParametro() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo("dialogos/parametro.xhtml", "Nuevo Parametro Institucion", Dialogo.EFECTO));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void subirItemRecurso() {
        Recurso r = (Recurso) UtilJSF.getBean("varRecurso");
        institucion.setRecurso(r);
        institucion.getRecursoSet().remove(r);
        cargarDialogoCrearRecurso();
    }

    public void subirItemTipoRecurso() {
        TipoRecurso tr = (TipoRecurso) UtilJSF.getBean("varTipoRecurso");
        institucion.setTipoRecurso(tr);
        institucion.getTipoRecursosSet().remove(tr);
        cargarDialogoCrearTipoRecurso();
    }

    public void crearParametro() {
        try {
            parametrosNuevo.setCodParametro(gestorGeneral.nextval(GestorGeneral.PARAMETROS_COD_PARAMETRO_SEQ));
            parametrosNuevo.setEntidad(Institucion.class.getSimpleName());
            parametrosNuevo = gestorInstitucion.validarParametros(parametrosNuevo);
            gestorInstitucion.guardarParametros(parametrosNuevo);
            UtilJSF.execute("PF('dialogNuevoParametro').hide()");
            UtilMSG.addSuccessMsg("Parametro Creado Correctamente", "Registro Satisfactorio");
            this.cargarParametros();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void asignarParametro() {
        try {
            InstitucionParametros institucionParametros = institucion.getInstitucionParametros();
            institucionParametros.setInstitucionParametrosPK(new InstitucionParametrosPK(institucion.getCodInstitucion(), institucionParametros.getParametros().getCodParametro()));
            institucionParametros = gestorInstitucion.validarParametro(institucionParametros);
            gestorInstitucion.guardarInstitucionParametros(institucionParametros);
            institucion.getInstitucionParametrosSet().add(institucionParametros);
            institucion.setInstitucionParametros(new InstitucionParametros());
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

    public void crearRecurso() {
        try {
            institucion.setRecurso(gestorRecursos.validarRecurso(institucion.getRecurso()));

            if (institucion.getRecurso().getRecursoPK() == null
                    || institucion.getRecurso().getRecursoPK().getCodRecurso() == null
                    || institucion.getRecurso().getRecursoPK().getCodRecurso() == 0) {
//                institucion.getRecurso().getRecursoPK().setCodRecurso(gestorGeneral.nextval(GestorGeneral.ATENCION_RECURSO_COD_RECURSO_SEQ));
//                institucion.getRecurso().getRecursoPK().setCodInstitucion(institucion.getCodInstitucion());
                institucion.getRecurso().setRecursoPK(new RecursoPK(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.ATENCION_RECURSO_COD_RECURSO_SEQ)));
                institucion.getRecurso().setInstitucion(institucion);
            }
            institucion.getRecurso().setCodTipoRecurso(institucion.getRecurso().getTipoRecurso().getCodTipoRecurso());
            if (institucion.getRecurso().getRecursoPK() == null
                    || institucion.getRecurso().getRecursoPK().getCodRecurso() == null
                    || institucion.getRecurso().getRecursoPK().getCodRecurso() == 0) {
                throw new Exception("No fue posible asignar el codigo del recurso, intentelo de nuevo", UtilLog.TW_VALIDACION);
            }

            Recurso recursoRemover = null;
            for (Recurso r : institucion.getRecursoSet()) {
                if (institucion.getRecurso().equals(r)) {
                    recursoRemover = r;
                }
            }
            if (recursoRemover != null) {
                institucion.getRecursoSet().remove(recursoRemover);
            }
            institucion.getRecursoSet().add(institucion.getRecurso());
            institucion.setRecurso(new Recurso());
            UtilJSF.execute("PF('dialog').hide();");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearTipoRecurso() {
        try {
            institucion.getTipoRecurso().setInstitucion(institucion);
            gestorTipoRecurso.validarTipoRecurso(institucion.getTipoRecurso());
            if (institucion.getTipoRecurso().getCodTipoRecurso() == null || institucion.getTipoRecurso().getCodTipoRecurso() == 0) {
                institucion.getTipoRecurso().setCodTipoRecurso(gestorGeneral.nextval(GestorGeneral.ATENCION_TIPO_RECURSO_COD_TIPO_RECURSO_SEQ));
            }
            if (institucion.getTipoRecurso().getCodTipoRecurso() == null || institucion.getTipoRecurso().getCodTipoRecurso() == 0) {
                throw new Exception("No fue posible asignar el codigo del tipo de recurso, intentelo de nuevo", UtilLog.TW_VALIDACION);
            }
            institucion.getTipoRecursosSet().add(institucion.getTipoRecurso());
            institucion.setTipoRecurso(new TipoRecurso());
            UtilJSF.execute("PF('dialog').hide();");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void crearRol() {
        try {

            institucion.setRoles(gestorRoles.validarRoles(institucion.getRolesCrear()));
            gestorRoles.validarRolInstitucion(institucion);
            institucion.getRoles().setRolesPK(new RolesPK(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.USUARIO_ROLES_CODIGO_ROL_SEQ)));
            gestorGeneral.guardarActualizarEntidad(institucion.getRoles());
            institucion.getRolesSet().add(institucion.getRoles());
            institucion.setRoles(new Roles());
            institucion.setRolesCrear(new Roles());
            UtilMSG.addSuccessMsg("Rol creado satisfactoriamente.");

        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
        UtilJSF.hideDialog();
    }

    public void crearRolTodos() {
        try {
            List<DetalleLista> rolesList = new ArrayList<>();
            rolesList.addAll(roles.getDetalleListaSet());

            List<DetalleLista> rolesAsignar = new ArrayList<>();

            for (DetalleLista dl : rolesList) {
                boolean existe = false;
                if (Hibernate.isInitialized(institucion.getRolesSet()) && institucion.getRolesSet() != null) {
                    for (Roles r : institucion.getRolesSet()) {
                        if (r.getNombre().equalsIgnoreCase(dl.getNombre())) {
                            existe = true;
                            break;
                        }
                    }
                }
                if (!existe) {
                    rolesAsignar.add(dl);
                }
            }

            for (DetalleLista detalleLista : rolesAsignar) {
                institucion.setRoles(new Roles());
                institucion.setRoles(new Roles(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.USUARIO_ROLES_CODIGO_ROL_SEQ), detalleLista.getNombre()));
                institucion.setRoles(gestorRoles.validarRoles(institucion.getRoles()));
                gestorRoles.validarRolInstitucion(institucion);
                gestorGeneral.guardarActualizarEntidad(institucion.getRoles());
                institucion.getRolesSet().add(institucion.getRoles());
            }
            institucion.setRoles(new Roles());
            institucion.setRolesCrear(new Roles());
            UtilMSG.addSuccessMsg("Roles creados satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
        UtilJSF.hideDialog();
    }

    public void crearMenu() {
        try {
            institucion.setMenus(gestorMenus.validarMenus(institucion.getMenus()));
            gestorMenus.validarMenuInstitucion(institucion);
            institucion.getMenus().setMenusPK(new MenusPK(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.USUARIO_MENUS_COD_MENU_SEQ)));
            gestorGeneral.guardarActualizarEntidad(institucion.getMenus());
            institucion.getMenusSet().add(institucion.getMenus());
            Menus m = (Menus) new Menus(new MenusPK()).clone();
            institucion.setMenus(m);
            UtilMSG.addSuccessMsg("Menu creado satisfactoriamente.");
            UtilJSF.hideDialog();
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void crearMenuTodos() {
        try {
            List<DetalleLista> menusList = new ArrayList<>();
            menusList.addAll(menus.getDetalleListaSet());

            List<DetalleLista> menusAsignar = new ArrayList<>();

            for (DetalleLista dl : menusList) {
                boolean existe = false;
                for (Menus m : institucion.getMenusSet()) {
                    if (m.getDescripcion().equalsIgnoreCase(dl.getNombre())) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    menusAsignar.add(dl);
                }
            }

            Menus m = (Menus) new Menus(new MenusPK()).clone();
            for (DetalleLista detalleLista : menusAsignar) {
                institucion.setMenus(m);
                institucion.setMenus(new Menus(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.USUARIO_MENUS_COD_MENU_SEQ), detalleLista.getNombre()));
                institucion.setMenus(gestorMenus.validarMenus(institucion.getMenus()));
                gestorMenus.validarMenuInstitucion(institucion);
//                institucion.getMenus().setMenusPK(new MenusPK(institucion.getCodInstitucion(), gestorGeneral.nextval(GestorGeneral.USUARIO_MENUS_COD_MENU_SEQ)));
                gestorGeneral.guardarActualizarEntidad(institucion.getMenus());
                institucion.getMenusSet().add(institucion.getMenus());
            }
            institucion.setMenus(m);
            UtilMSG.addSuccessMsg("Menus asignados satisfactoriamente.");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getCause().getMessage(), ex.getMessage());
            } else {
                UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
        UtilJSF.hideDialog();
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
     * @return the institucionsList
     */
    public List<Institucion> getInstitucionsList() {
        return institucionsList;
    }

    /**
     * @param institucionsList the institucionsList to set
     */
    public void setInstitucionsList(List<Institucion> institucionsList) {
        this.institucionsList = institucionsList;
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
     * @return the parametrosList
     */
    public List<Parametros> getParametrosList() {
        List<Parametros> parametrosInstitucionList = new ArrayList<>();
        for (Parametros p : parametrosList) {
            if (p.getEntidad().equalsIgnoreCase(Institucion.class.getSimpleName())) {
                parametrosInstitucionList.add(p);
            }
        }
        return parametrosInstitucionList;
    }

    /**
     * @param parametrosList the parametrosList to set
     */
    public void setParametrosList(List<Parametros> parametrosList) {
        this.parametrosList = parametrosList;
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

    /**
     * @return the menus
     */
    public Lista getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(Lista menus) {
        this.menus = menus;
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
     * @return the gestorMenus
     */
    public GestorMenus getGestorMenus() {
        return gestorMenus;
    }

    /**
     * @param gestorMenus the gestorMenus to set
     */
    public void setGestorMenus(GestorMenus gestorMenus) {
        this.gestorMenus = gestorMenus;
    }

    /**
     * @return the roles
     */
    public Lista getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Lista roles) {
        this.roles = roles;
    }

    /**
     * @return the gestorRoles
     */
    public GestorRoles getGestorRoles() {
        return gestorRoles;
    }

    /**
     * @param gestorRoles the gestorRoles to set
     */
    public void setGestorRoles(GestorRoles gestorRoles) {
        this.gestorRoles = gestorRoles;
    }

    /**
     * @return the menusDualList
     */
    public DualListModel<Menus> getMenusDualList() {
        return menusDualList;
    }

    /**
     * @param menusDualList the menusDualList to set
     */
    public void setMenusDualList(DualListModel<Menus> menusDualList) {
        this.menusDualList = menusDualList;
    }

    /**
     * @return the permisosBinariosDualList
     */
    public DualListModel<PermisosBinarios> getPermisosBinariosDualList() {
        return permisosBinariosDualList;
    }

    /**
     * @param permisosBinariosDualList the permisosBinariosDualList to set
     */
    public void setPermisosBinariosDualList(DualListModel<PermisosBinarios> permisosBinariosDualList) {
        this.permisosBinariosDualList = permisosBinariosDualList;
    }

}
