/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.dao.EstablecimientoDAO;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.EstablecimientoParametros;
import com.sinergia.publico.modelo.Parametros;
import com.sinergia.utilidades.modelo.UtilCorreo;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author juliano
 */
public class GestorEstablecimiento extends Gestor implements Serializable {

    public GestorEstablecimiento() throws Exception {
        super();
    }

    public Establecimiento cargarEstablecimiento(int codInstitucion, int codEstablecimiento) throws Exception {
        try {
            this.abrirConexion();
            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(session);
            return establecimientoDAO.cargarEstablecimiento(codInstitucion, codEstablecimiento);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Establecimiento> cargarListaEstablecimientos(int codInstitucion) throws Exception {
        try {
            this.abrirConexion();
            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(this.session);
            return establecimientoDAO.cargarListaEstablecimientos(codInstitucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public List<?> cargarListaEstablecimientosUsuario(String documentoUsuario) throws Exception {
        try {
            this.abrirConexion();
            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(this.session);
            return establecimientoDAO.cargarListaEstablecimientosUsuario(documentoUsuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public Establecimiento validarEstablecimiento(Establecimiento establecimiento) throws Exception {
        if (establecimiento.getEstablecimientoPK() == null || establecimiento.getEstablecimientoPK().getCodEstablecimiento() == 0) {
            throw new Exception("No se pudo asignar el codigo del establecimiento, intentelo de nuevo", UtilLog.TW_VALIDACION);
        }
        if (establecimiento.getNombre() == null) {
            throw new Exception("Ingresa el nombre del establecimiento", UtilLog.TW_VALIDACION);
        }
        if (establecimiento.getMunicipios() == null
                || establecimiento.getMunicipios().getCodMunicipio() == null
                || establecimiento.getMunicipios().getCodMunicipio().equalsIgnoreCase("")) {
            throw new Exception("Seleccione el municipio del establecimiento", UtilLog.TW_VALIDACION);
        }
        if (!UtilCorreo.validarCorreo(establecimiento.getCorreo())) {
            throw new Exception("Ingresa un correo valido.", UtilLog.TW_VALIDACION);
        }
        if (establecimiento.getFechaCierreDiario() == null) {
            throw new Exception("No se pudo asignar la fecha de cierre, intentelo de nuevo.", UtilLog.TW_VALIDACION);
        }
        if (establecimiento.getDireccion() == null) {
            throw new Exception("Ingresa la dirección del establecimiento.", UtilLog.TW_VALIDACION);
        }
        establecimiento.setNombre(establecimiento.getNombre().trim().toUpperCase());
        establecimiento.setDireccion(establecimiento.getDireccion().trim().toUpperCase());
        return establecimiento;
    }

    public void guardarEstablecimiento(Establecimiento establecimiento) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            session.saveOrUpdate(establecimiento);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public int siguienteCodigoEstablecimiento(int codInstitucion) throws Exception {
        try {
            this.abrirConexion();

            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(session);
            return establecimientoDAO.siguienteCodigoEstablecimiento(codInstitucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public Parametros validarParametros(Parametros parametros) throws Exception {
        if (parametros.getCodParametro() == null || parametros.getCodParametro() == 0) {
            throw new Exception("No fue posible asignar el codigo del parametro, intentelo de nuevo", UtilLog.TW_VALIDACION);
        }
        if (parametros.getNombre() == null || parametros.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del parametro", UtilLog.TW_VALIDACION);
        }
        if (parametros.getEntidad() == null || parametros.getEntidad().equalsIgnoreCase("")) {
            throw new Exception("No se asigno el origen del parametro, " + UtilMSG.getSupportMsg(), UtilLog.TW_VALIDACION);
        }
        parametros.setNombre(parametros.getNombre().trim().toUpperCase());
        parametros.setEntidad(parametros.getEntidad().trim());
        return parametros;
    }

    public void guardarParametros(Parametros parametros) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(parametros);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public EstablecimientoParametros validarParametro(EstablecimientoParametros establecimientoParametros) throws Exception {
        if (establecimientoParametros.getEstablecimientoParametrosPK() == null
                || establecimientoParametros.getEstablecimientoParametrosPK().getCodParametro() == 0) {
            throw new Exception("No se puedo asignar el consecutivo del parametro, intentelo de nuevo", UtilLog.TW_VALIDACION);
        }
        if (establecimientoParametros.getValor() == null || establecimientoParametros.getValor().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el valor del parametro.", UtilLog.TW_VALIDACION);
        }
        establecimientoParametros.setValor(establecimientoParametros.getValor().trim().toUpperCase());
        return establecimientoParametros;
    }

    public void guardarEstablecimientoParametros(EstablecimientoParametros establecimientoParametros) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(establecimientoParametros);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void borrarEstablecimientoParametros(EstablecimientoParametros establecimientoParametros) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.delete(establecimientoParametros);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

}
