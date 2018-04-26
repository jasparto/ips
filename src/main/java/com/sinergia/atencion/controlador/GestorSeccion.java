/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.controlador;

import com.sinergia.atencion.dao.SeccionDAO;
import com.sinergia.atencion.modelo.Seccion;
import com.sinergia.atencion.modelo.SeccionDetalle;
import com.sinergia.atencion.modelo.SeccionDetalleCombos;
import com.sinergia.atencion.modelo.SeccionServicios;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilTexto;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class GestorSeccion extends Gestor implements Serializable {

    public GestorSeccion() throws Exception {
        super();
    }

    public Collection<? extends Seccion> cargarSeccionEstablecimiento(Establecimiento establecimiento, boolean filtroActivo) throws Exception {
        try {
            this.abrirConexion();
            SeccionDAO seccionDAO = new SeccionDAO(session);
            return seccionDAO.cargarSeccionEstablecimiento(establecimiento, filtroActivo);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Seccion> cargarSeccionServicioEstablecimiento(Establecimiento establecimiento, Long codServicio, boolean filtroActivo) throws Exception {
        try {
            this.abrirConexion();
            SeccionDAO seccionDAO = new SeccionDAO(session);
            return seccionDAO.cargarSeccionServicioEstablecimiento(establecimiento, codServicio, filtroActivo);
        } finally {
            this.cerrarConexion();
        }
    }

    public Seccion validarSeccion(Seccion seccion) throws Exception {
        if (seccion == null || seccion.getSeccionPK() == null || seccion.getNombre() == null || seccion.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Por favor ingrese el nombre de la sección.", UtilLog.TW_VALIDACION);
        }
        if (seccion.getSeccionPK().getCodEstablecimiento() == 0 || seccion.getSeccionPK().getCodInstitucion() == 0) {
            throw new Exception("Por favor seleccione el establecimiento.", UtilLog.TW_VALIDACION);
        }
        if (seccion.getSeccionPK().getCodSeccion() == 0) {
            throw new Exception("No fue posible asignar el consecutivo de la seccion, intente de nuevo.", UtilLog.TW_VALIDACION);
        }
        seccion.setNombre(UtilTexto.capitalizarCadena(seccion.getNombre()));
        return seccion;
    }

    public void guardarSeccion(Seccion seccion) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(seccion);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarSeccionDetalle(SeccionDetalle seccionDetalle) throws Exception {
        if (seccionDetalle == null || seccionDetalle.getSeccionDetallePK() == null || seccionDetalle.getNombre() == null || seccionDetalle.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Por favor ingrese el nombre de la sección.", UtilLog.TW_VALIDACION);
        }
        if (seccionDetalle.getSeccionDetallePK().getCodInstitucion() == 0 || seccionDetalle.getSeccionDetallePK().getCodEstablecimiento() == 0) {
            throw new Exception("Por favor seleccione el establecimiento.", UtilLog.TW_VALIDACION);
        }
        if (seccionDetalle.getSeccionDetallePK().getCodDetalle() == 0) {
            throw new Exception("No fue posible asignar el consecutivo de la seccion, intente de nuevo.", UtilLog.TW_VALIDACION);
        }
        if (seccionDetalle.getTipoComponentes() == null || seccionDetalle.getTipoComponentes().getNombre() == null) {
            throw new Exception("Seleccione el tipo del componente.", UtilLog.TW_VALIDACION);
        }
        switch (seccionDetalle.getTipoComponentes().getNombre()) {
            case SeccionDetalle.INPUT_TEXT:
            case SeccionDetalle.INPUT_TEXTAREA:
                boolean itemIngresado = false;
                for (SeccionDetalleCombos sdc : seccionDetalle.getSeccionDetalleCombosSet()) {
                    if (sdc.getDetalle() != null && !sdc.getDetalle().equalsIgnoreCase("")) {
                        itemIngresado = true;
                        sdc.setDetalle(UtilTexto.capitalizarCadena(sdc.getDetalle()));
                    }
                }
                if (!itemIngresado) {
                    throw new Exception("El tipo de componente requiere al menos un item en el detalle de la sección.", UtilLog.TW_VALIDACION);
                }
                break;
        }
        seccionDetalle.setNombre(UtilTexto.capitalizarCadena(seccionDetalle.getNombre()));
    }

    public void guardarSeccionDetalle(SeccionDetalle seccionDetalle) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(seccionDetalle);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void guardarSeccionDetalleCombo(SeccionDetalleCombos seccionDetalleCombos) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(seccionDetalleCombos);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarSeccionDetalle(SeccionDetalle seccionDetalle) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.delete(seccionDetalle);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public SeccionDetalleCombos validarSeccionDetalleItem(SeccionDetalleCombos seccionDetalleCombos) throws Exception {
        if (seccionDetalleCombos == null || seccionDetalleCombos.getDetalle() == null) {
            throw new Exception("Por favor indique el nombre del item.", UtilLog.TW_VALIDACION);
        }
        seccionDetalleCombos.setDetalle(UtilTexto.capitalizarCadena(seccionDetalleCombos.getDetalle()));
        return seccionDetalleCombos;
    }

    public void eliminarSeccionItemDetalle(SeccionDetalleCombos seccionDetalleCombos) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.delete(seccionDetalleCombos);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarSeccionServicios(Servicios servicios, Set<SeccionServicios> seccionServiciosSet) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            SeccionDAO seccionDAO = new SeccionDAO(session);
            seccionDAO.borrarSeccionServicios(servicios);
            for (SeccionServicios ss : seccionServiciosSet) {
                this.session.save(ss);
            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarSeccion(Seccion seccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
