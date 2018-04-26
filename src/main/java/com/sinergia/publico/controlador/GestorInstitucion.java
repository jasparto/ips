/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.dao.InstitucionDAO;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.publico.modelo.InstitucionParametros;
import com.sinergia.publico.modelo.Parametros;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author juliano
 */
public class GestorInstitucion extends Gestor implements Serializable {

    public GestorInstitucion() throws Exception {
        super();
    }

    public Collection<? extends Institucion> cargarInstituciones() throws Exception {
        try {
            this.abrirConexion();
            Collection<Institucion> institucionCollection = new ArrayList<>();
            institucionCollection.addAll(new InstitucionDAO(session).cargarInstituciones());
            return institucionCollection;
        } finally {
            this.cerrarConexion();
        }
    }

    public int siguienteCodigoInstitucion() throws Exception {
        try {
            this.abrirConexion();
            return new InstitucionDAO(session).siguienteCodigoInstitucion();
        } finally {
            this.cerrarConexion();
        }
    }

    public Institucion validarInstitucion(Institucion institucion) throws Exception {

        if (institucion.codInstitucion == null || institucion.codInstitucion == 0) {
            throw new Exception("No se pudo asignar el codigo del establecimiento, intentelo de nuevo", UtilLog.TW_VALIDACION);
        }
        if (institucion.getNombre() == null || institucion.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre de la institución", UtilLog.TW_VALIDACION);
        }
        if (institucion.getNit() == null || institucion.getNit().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el Nit de la institución", UtilLog.TW_VALIDACION);
        }
        if (institucion.getDireccion() == null || institucion.getDireccion().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la Dirección de la institución", UtilLog.TW_VALIDACION);
        }
        if (institucion.getRazonSocial() == null || institucion.getRazonSocial().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la Razon Social de la institución", UtilLog.TW_VALIDACION);
        }
        if (institucion.getTelefono() == null || institucion.getTelefono().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el Telefono de la institución", UtilLog.TW_VALIDACION);
        }
        institucion.setNombre(institucion.getNombre().trim().toUpperCase());
        institucion.setDireccion(institucion.getDireccion().trim().toUpperCase());
        institucion.setRazonSocial(institucion.getRazonSocial().trim().toUpperCase());

        return institucion;
    }

    public void guardarInstitucion(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(institucion);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public InstitucionParametros validarParametro(InstitucionParametros institucionParametros) throws Exception {
        if (institucionParametros.getInstitucionParametrosPK() == null
                || institucionParametros.getInstitucionParametrosPK().getCodParametro() == 0) {
            throw new Exception("No se puedo asignar el consecutivo del parametro, intentelo de nuevo", UtilLog.TW_VALIDACION);
        }
        if (institucionParametros.getValor() == null || institucionParametros.getValor().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el valor del parametro.", UtilLog.TW_VALIDACION);
        }
        institucionParametros.setValor(institucionParametros.getValor().trim().toUpperCase());
        return institucionParametros;
    }

    public void guardarInstitucionParametros(InstitucionParametros institucionParametros) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(institucionParametros);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
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
        parametros.setEntidad(parametros.getEntidad().trim().toUpperCase());
        return parametros;
    }

    public Institucion cargarConfiguracionPrograma(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            return new InstitucionDAO(session).cargarConfiguracionPrograma(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

}
