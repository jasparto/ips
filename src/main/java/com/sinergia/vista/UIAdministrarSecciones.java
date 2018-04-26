/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.atencion.controlador.GestorSeccion;
import com.sinergia.atencion.modelo.Seccion;
import com.sinergia.atencion.modelo.SeccionDetalle;
import com.sinergia.atencion.modelo.SeccionDetalleCombos;
import com.sinergia.atencion.modelo.SeccionDetalleCombosPK;
import com.sinergia.atencion.modelo.SeccionDetallePK;
import com.sinergia.atencion.modelo.SeccionPK;
import com.sinergia.controlador.GestorGeneral;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.publico.controlador.GestorEstablecimiento;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "uiAdministrarSecciones")
@SessionScoped
public class UIAdministrarSecciones implements Serializable {

    @ManagedProperty("#{gestorSeccion}")
    private GestorSeccion gestorSeccion;
    @ManagedProperty("#{gestorEstablecimiento}")
    private GestorEstablecimiento gestorEstablecimiento;
    @ManagedProperty("#{gestorGeneral}")
    private GestorGeneral gestorGeneral;

    @PostConstruct
    public void UIAdministrarSecciones() {
        try {
            this.cargarEstablecimientosInstitucion();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    private Seccion seccion = new Seccion();

    private void cargarEstablecimientosInstitucion() throws Exception {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        establecimientosList = new ArrayList<>();
        establecimientosList.addAll(gestorEstablecimiento.cargarListaEstablecimientos(sesion.getEstablecimiento().getEstablecimientoPK().getCodInstitucion()));
    }

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

    private Establecimiento establecimientoSeleccionado;
    private List<Seccion> seccionList;
    private List<Establecimiento> establecimientosList;
    private String detalleItemSugerencia;

    public void eliminarSeccion() {
        try {
            Seccion s = (Seccion) UtilJSF.getBean("s");
            gestorSeccion.eliminarEntidad(s);
            seccionList.remove(s);
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
            UtilMSG.addWarningMsg("La sección no se puede eliminar por que tiene asociada historias clinicas.", "Sección Usada");
        }
    }

    public void modificarSeccion() {
        try {
            Seccion s = (Seccion) UtilJSF.getBean("s");
            gestorSeccion.guardarActualizarEntidad(s);
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void crearSeccion() {
        try {
            seccion.setSeccionPK(new SeccionPK(establecimientoSeleccionado.getEstablecimientoPK().getCodInstitucion(),
                    establecimientoSeleccionado.getEstablecimientoPK().getCodEstablecimiento(),
                    gestorGeneral.nextval(GestorGeneral.ATENCION_SECCION_COD_SECCION_SEQ)
            ));
            seccion = gestorSeccion.validarSeccion(seccion);
            seccion.setActivo(true);
            gestorSeccion.guardarSeccion(seccion);
            seccionList.add(seccion);
            seccion = new Seccion();
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addSuccessMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilMSG.addSupportMsg();
            }
        }
    }

    public void cargarDialogoSeccionDetalle() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            seccion = (Seccion) UtilJSF.getBean("s");
            seccion = (Seccion) seccion.clone();
            sesion.setDialogo(new Dialogo("dialogos/seccionesDetalle.xhtml", "Nueva Sección Detalle", "clip"));
            UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
            UtilJSF.execute("PF('dialog').show();");
        } catch (CloneNotSupportedException ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void crearSeccionDetalle() {
        try {
            SeccionDetalle seccionDetalle = (SeccionDetalle) new SeccionDetalle().clone();
            seccionDetalle.setSeccionDetallePK(new SeccionDetallePK(establecimientoSeleccionado.getEstablecimientoPK().getCodInstitucion(), establecimientoSeleccionado.getEstablecimientoPK().getCodEstablecimiento(),
                    seccion.getSeccionPK().getCodSeccion(), gestorGeneral.nextval(GestorGeneral.ATENCION_SECCION_DETALLE_COD_DETALLE_SEQ)));
            seccionDetalle.setTipoComponentes(seccion.getSeccionDetalle().getTipoComponentes());
            seccionDetalle.setNombre(seccion.getSeccionDetalle().getNombre());
            seccion.setSeccionDetalle(seccionDetalle);
            if (this.getItemRequiereSugerencia()) {
                SeccionDetalleCombos sdc = new SeccionDetalleCombos(new SeccionDetalleCombosPK(seccion.getSeccionPK().getCodSeccion(), seccion.getSeccionDetalle().getSeccionDetallePK().getCodDetalle(), gestorGeneral.nextval(GestorGeneral.ATENCION_SECCION_DETALLE_COMBOS_COD_COMBO_SEQ)));
                sdc.setCodInstitucion(establecimientoSeleccionado.getEstablecimientoPK().getCodInstitucion());
                sdc.setCodEstablecimiento(establecimientoSeleccionado.getEstablecimientoPK().getCodEstablecimiento());
                sdc.setDetalle(detalleItemSugerencia);
                seccion.getSeccionDetalle().getSeccionDetalleCombosSet().add(sdc);
            }

            gestorSeccion.validarSeccionDetalle(seccion.getSeccionDetalle());
            gestorSeccion.guardarSeccionDetalle(seccion.getSeccionDetalle());

            for (Seccion s : seccionList) {
                if (s.equals(seccion)) {
                    s.getSeccionDetalleSet().add(seccion.getSeccionDetalle());
                }
            }

            seccion = (Seccion) new Seccion().clone();
            UtilJSF.execute("PF('dialog').hide();");

        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addSuccessMsg(ex.getMessage(), ex.getCause().getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addSupportMsg();
            }
        }

    }

    public void eliminarSeccionItemDetalle() {
        try {
            seccion = (Seccion) UtilJSF.getBean("s");
            SeccionDetalle seccionDetalle = (SeccionDetalle) UtilJSF.getBean("sd");
            SeccionDetalleCombos seccionDetalleCombos = (SeccionDetalleCombos) UtilJSF.getBean("sdc");
            seccionDetalleCombos.setCodInstitucion(establecimientoSeleccionado.getEstablecimientoPK().getCodInstitucion());
            seccionDetalleCombos.setCodEstablecimiento(establecimientoSeleccionado.getEstablecimientoPK().getCodEstablecimiento());
            gestorSeccion.eliminarSeccionItemDetalle(seccionDetalleCombos);
            for (Seccion s : seccionList) {
                if (s.equals(seccion)) {
                    for (SeccionDetalle sd : s.getSeccionDetalleSet()) {
                        if (sd.equals(seccionDetalle)) {
                            sd.getSeccionDetalleCombosSet().remove(seccionDetalleCombos);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void eliminarSeccionDetalle() {
        try {
            seccion = (Seccion) UtilJSF.getBean("s");
            seccion = (Seccion) seccion.clone();
            SeccionDetalle seccionDetalle = (SeccionDetalle) UtilJSF.getBean("sd");
            seccionDetalle = (SeccionDetalle) seccionDetalle.clone();
            gestorSeccion.eliminarSeccionDetalle(seccionDetalle);
//            seccion = new Seccion(new SeccionPK(seccionDetalle.getSeccionDetallePK().getCodInstitucion(), seccionDetalle.getSeccionDetallePK().getCodEstablecimiento(), seccionDetalle.getSeccionDetallePK().getCodSeccion()));
//            seccion = (Seccion) seccion.clone();
            for (Seccion s : seccionList) {
                if (seccion.equals(s)) {
                    s.getSeccionDetalleSet().remove(seccionDetalle);
                }
            }
            seccion = (Seccion) new Seccion().clone();
        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Detalle en uso, no se puede eliminar.", "El detalle ya se encuentra diligenciado en alguna historia, por tal motivo no se puede eliminar.");
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void crearSeccionItemDetalle() {
        try {
            SeccionDetallePK seccionDetallePk = seccion.getSeccionDetalle().getSeccionDetallePK();
            SeccionDetalleCombos seccionDetalleCombos = seccion.getSeccionDetalle().getSeccionDetalleCombos();
            seccionDetalleCombos.setCodInstitucion(establecimientoSeleccionado.getEstablecimientoPK().getCodInstitucion());
            seccionDetalleCombos.setCodEstablecimiento(establecimientoSeleccionado.getEstablecimientoPK().getCodEstablecimiento());
            seccionDetalleCombos.setSeccionDetalleCombosPK(new SeccionDetalleCombosPK(seccionDetallePk.getCodSeccion(),
                    seccionDetallePk.getCodDetalle(), gestorGeneral.nextval(GestorGeneral.ATENCION_SECCION_DETALLE_COMBOS_COD_COMBO_SEQ)));
            seccionDetalleCombos = gestorSeccion.validarSeccionDetalleItem(seccionDetalleCombos);
            gestorSeccion.guardarSeccionDetalleCombo(seccionDetalleCombos);
            seccion.setSeccionPK(new SeccionPK(seccionDetalleCombos.getCodInstitucion(), seccionDetalleCombos.getCodEstablecimiento(), seccionDetallePk.getCodSeccion()));

            for (Seccion s : seccionList) {
                if (s.equals(seccion)) {
                    for (SeccionDetalle sd : s.getSeccionDetalleSet()) {
                        if (sd.equals(seccion.getSeccionDetalle())) {
                            sd.getSeccionDetalleCombosSet().add(seccionDetalleCombos);
                        }
                    }
                }
            }

            UtilJSF.execute("PF('dialog').hide();");
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }

    }

    public void cargarDialogoSeccionItemDetalle() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        SeccionDetalle seccionDetalle = (SeccionDetalle) UtilJSF.getBean("sd");
        seccion = new Seccion();
        seccionDetalle.setSeccionDetalleCombos(new SeccionDetalleCombos());
        seccion.setSeccionDetalle(seccionDetalle);
        sesion.setDialogo(new Dialogo("dialogos/seccionesItemDetalle.xhtml", "Nueva Item Sección Detalle", "clip"));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public boolean getItemRequiereSugerencia() {
        return seccion != null && seccion.getSeccionDetalle() != null && seccion.getSeccionDetalle().getTipoComponentes() != null && (seccion.getSeccionDetalle().getTipoComponentes().getNombre().equals(SeccionDetalle.INPUT_TEXT) || seccion.getSeccionDetalle().getTipoComponentes().getNombre().equals(SeccionDetalle.INPUT_TEXTAREA));
    }

    public void cargarSeccionesEstablecimiento() throws Exception {
        seccionList = new ArrayList<>();
        seccionList.addAll(gestorSeccion.cargarSeccionEstablecimiento(establecimientoSeleccionado, false));
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
     * @return the establecimientoSeleccionado
     */
    public Establecimiento getEstablecimientoSeleccionado() {
        return establecimientoSeleccionado;
    }

    /**
     * @param establecimientoSeleccionado the establecimientoSeleccionado to set
     */
    public void setEstablecimientoSeleccionado(Establecimiento establecimientoSeleccionado) {
        this.establecimientoSeleccionado = establecimientoSeleccionado;
    }

    /**
     * @return the establecimientosList
     */
    public List<Establecimiento> getEstablecimientosList() {
        return establecimientosList;
    }

    /**
     * @param establecimientosList the establecimientosList to set
     */
    public void setEstablecimientosList(List<Establecimiento> establecimientosList) {
        this.establecimientosList = establecimientosList;
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
     * @return the seccion
     */
    public Seccion getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
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
     * @return the detalleItemSugerencia
     */
    public String getDetalleItemSugerencia() {
        return detalleItemSugerencia;
    }

    /**
     * @param detalleItemSugerencia the detalleItemSugerencia to set
     */
    public void setDetalleItemSugerencia(String detalleItemSugerencia) {
        this.detalleItemSugerencia = detalleItemSugerencia;
    }

}
