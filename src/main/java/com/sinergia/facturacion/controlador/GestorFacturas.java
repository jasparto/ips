/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.controlador;

import com.sinergia.facturacion.dao.FacturasDAO;
import com.sinergia.facturacion.modelo.Facturas;
import com.sinergia.facturacion.modelo.FacturasConfiguracion;
import com.sinergia.facturacion.modelo.FacturasConfiguracionPK;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.utilidades.modelo.UtilFecha;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class GestorFacturas extends Gestor implements Serializable {

    public GestorFacturas() throws Exception {
        super();
    }

    public long generarCodigoFactura() throws Exception {
        try {
            this.abrirConexion();
            return new FacturasDAO(this.session).generarCodigoFactura();
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarResolucionEstablecimiento(Establecimiento establecimiento) throws Exception {
        //prefactura
        if (establecimiento == null || establecimiento.getFacturasConfiguracionPrefactura() == null
                || establecimiento.getFacturasConfiguracionPrefactura().getPrefijoFactura() == null
                || establecimiento.getFacturasConfiguracionPrefactura().getPrefijoFactura().equalsIgnoreCase("")) {
            String mensaje = "Resolución de pre-facturación sin vigencia o no configurada";
            if (establecimiento.getEstablecimientoParametrosHashMap() != null
                    && establecimiento.getEstablecimientoParametrosHashMap().get("SALTAR_PREFACTURA") != null
                    && establecimiento.getEstablecimientoParametrosHashMap().get("SALTAR_PREFACTURA").equals("A")) {
                UtilMSG.addWarningMsg(mensaje + " no se genero pre-factura", "Se recomienda validar el proceso dado que el no generar la pre-factura, puede generar el no cobro de este servicio");
            } else {
                throw new Exception(mensaje + ", no se puede continuar con el proceso.", UtilLog.TW_VALIDACION);
            }
        }
        //copago
        if (establecimiento.getFacturasConfiguracionCopago() == null
                || establecimiento.getFacturasConfiguracionCopago().getPrefijoFactura() == null
                || establecimiento.getFacturasConfiguracionCopago().getPrefijoFactura().equalsIgnoreCase("")) {
            String mensaje = "Resolución de copago sin vigencia o no configurada";
            if (establecimiento.getEstablecimientoParametrosHashMap() != null
                    && establecimiento.getEstablecimientoParametrosHashMap().get("SALTAR_COPAGO") != null
                    && establecimiento.getEstablecimientoParametrosHashMap().get("SALTAR_COPAGO").equals("A")) {
                UtilMSG.addWarningMsg(mensaje + " no se genero factura copago", "Se recomienda validar el proceso dado que el no generar la factura de copago, puede generar el no cobro de este servicio");
            } else {
                throw new Exception(mensaje + ", no se puede continuar con el proceso.", UtilLog.TW_VALIDACION);
            }
        }
    }

    public void guardarFacturas(Establecimiento establecimiento, Facturas facturas) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            FacturasDAO facturasDAO = new FacturasDAO(session);
            facturas = facturasDAO.generarNumeroFactura(establecimiento, facturas, establecimiento.getFacturasConfiguracionPrefactura());
            this.session.saveOrUpdate(facturas);
            facturasDAO.actualizarNumeroFactura(establecimiento, facturas, establecimiento.getFacturasConfiguracionPrefactura());
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Facturas> cargarFacturasEstablecimientoFecha(Establecimiento establecimiento, Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            this.abrirConexion();
            FacturasDAO facturasDAO = new FacturasDAO(session);
            return facturasDAO.cargarFacturasEstablecimientoFecha(establecimiento, fechaInicial, fechaFinal);
        } finally {
            this.cerrarConexion();
        }
    }

    public FacturasConfiguracion validarFacturasConfiguracion(Establecimiento establecimiento, FacturasConfiguracion facturasConfiguracion) throws Exception {
//        if (facturasConfiguracion.getFacturasConfiguracionPK() == null
//                || facturasConfiguracion.getFacturasConfiguracionPK().getCodInstitucion() == 0
//                || facturasConfiguracion.getFacturasConfiguracionPK().getCodEstablecimiento() == 0) {
//            throw new Exception("No se pudo cargar la información del establecimiento, intentalo de nuevo", UtilLog.TW_VALIDACION);
//        }
//        if (facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion() == null
//                || facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion() == 0) {
//            throw new Exception("No se pudo asignar el consecutivo de configuración, intentalo de nuevo", UtilLog.TW_VALIDACION);
//        }

        if (facturasConfiguracion.getTipoResolucion() == null || facturasConfiguracion.getTipoResolucion().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el tipo de resolución.", UtilLog.TW_VALIDACION);
        }
        if (facturasConfiguracion.getFechaInicioVigencia() == null || facturasConfiguracion.getFechaFinVigencia() == null) {
            throw new Exception("Ingresa el rango de vigencia de la resolución.", UtilLog.TW_VALIDACION);
        }

        try {
            this.abrirConexion();
            FacturasDAO facturasDAO = new FacturasDAO(session);
            List<FacturasConfiguracion> facturasConfiguracionList = new ArrayList<>();
            facturasConfiguracionList.addAll(facturasDAO.cargarFacturasConfiguracionVigencia(establecimiento, facturasConfiguracion));

            FacturasConfiguracion facturasConfiguracionRemover = null;
            for (FacturasConfiguracion fc : facturasConfiguracionList) {
                if (fc.equals(facturasConfiguracion)) {
                    facturasConfiguracionRemover = fc;
                }
            }
            if (facturasConfiguracionRemover != null) {
                facturasConfiguracionList.remove(facturasConfiguracionRemover);
            }

            if (!facturasConfiguracionList.isEmpty()) {
                String mensaje = "La vigencia indicada se cruza con otras resoluciones";
                String detalle = null;
                for (FacturasConfiguracion fc : facturasConfiguracionList) {
                    detalle += " codigo: " + fc.getFacturasConfiguracionPK().getCodConfiguracion();
                    detalle += ", descripción: " + fc.getDescripcion();
                }
                UtilMSG.addErrorMsg("Resoluciones Cruce", detalle);
                throw new Exception(mensaje, UtilLog.TW_RESTRICCION);
            }
        } finally {
            this.cerrarConexion();
        }

        if (facturasConfiguracion.getDescripcion() == null || facturasConfiguracion.getDescripcion().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la descripción de la resolución.", UtilLog.TW_VALIDACION);
        }
        if (facturasConfiguracion.getPrefijoFactura() == null || facturasConfiguracion.getPrefijoFactura().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el prefijo de la resolución.", UtilLog.TW_VALIDACION);
        }
        if (facturasConfiguracion.getNumeroFacturaLimite() == null || facturasConfiguracion.getNumeroFacturaLimite() == 0) {
            throw new Exception("Ingresa el numero de factura limite.", UtilLog.TW_VALIDACION);
        }

        if (facturasConfiguracion.getNumeroInicio() == null || facturasConfiguracion.getNumeroFin() == null
                || facturasConfiguracion.getNumeroInicio() == 0 || facturasConfiguracion.getNumeroFin() == 0) {
            throw new Exception("Ingresa el numero inicio y fin de la resolución.", UtilLog.TW_VALIDACION);
        }
        if (facturasConfiguracion.getFechaLimiteResolucion() == null) {
            throw new Exception("Ingresa la fecha limite de la resolución.", UtilLog.TW_VALIDACION);
        }
        if (UtilFecha.diferenciaFechaDias(facturasConfiguracion.getFechaLimiteResolucion(), new Date()) <= 0) {
            throw new Exception("La fecha limite debe ser mayor a la actual. (" + UtilFecha.formatoFecha(facturasConfiguracion.getFechaLimiteResolucion(), null, UtilFecha.PATRON_FECHA_DD_MM_YYYY) + ")", UtilLog.TW_VALIDACION);
        }
        if (facturasConfiguracion.getFechaLimiteResolucion() == null) {
            throw new Exception("Ingresa la fecha limite de la resolución.", UtilLog.TW_VALIDACION);
        }

        if (facturasConfiguracion.getFechaCierre() == null) {
            GregorianCalendar fechaCierre = new GregorianCalendar();
            fechaCierre.setTime(new Date());
            fechaCierre.add(Calendar.DAY_OF_MONTH, -1);
            facturasConfiguracion.setFechaCierre(fechaCierre.getTime());
        }

        facturasConfiguracion.setDescripcion(facturasConfiguracion.getDescripcion().trim().toUpperCase());
        facturasConfiguracion.setResolucion(facturasConfiguracion.getResolucion().trim());

        return facturasConfiguracion;
    }

    public void guardarFacturasConfiguracion(FacturasConfiguracion facturasConfiguracion) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(facturasConfiguracion);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarFacturasConfiguracionPK(FacturasConfiguracionPK facturasConfiguracionPK) throws Exception {
        if (facturasConfiguracionPK == null
                || facturasConfiguracionPK.getCodInstitucion() == 0
                || facturasConfiguracionPK.getCodEstablecimiento() == 0) {
            throw new Exception("No se pudo cargar la información del establecimiento, intentalo de nuevo", UtilLog.TW_VALIDACION);
        }
        if (facturasConfiguracionPK.getCodConfiguracion() == null
                || facturasConfiguracionPK.getCodConfiguracion() == 0) {
            throw new Exception("No se pudo asignar el consecutivo de configuración, intentalo de nuevo", UtilLog.TW_VALIDACION);
        }
    }

    public Set<FacturasConfiguracion> cargarFacturasConfiguracion(Establecimiento establecimiento) throws Exception {
        try {
            this.abrirConexion();
            Set<FacturasConfiguracion> facturasConfiguracionSet = new HashSet<>();
            FacturasDAO facturasDAO = new FacturasDAO(session);
            Collection<? extends FacturasConfiguracion> facturasConfiguracionsList = facturasDAO.cargarFacturasConfiguracion(establecimiento);
            for (FacturasConfiguracion facturasConfiguracion : facturasConfiguracionsList) {
                facturasConfiguracionSet.add(facturasConfiguracion);
            }
            return facturasConfiguracionSet;
        } finally {
            this.cerrarConexion();
        }
    }

}
