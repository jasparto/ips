/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.dao.EntidadesDAO;
import com.sinergia.cliente.modelo.Entidades;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorEntidades extends Gestor implements Serializable {

    public GestorEntidades() throws Exception {
        super();
    }

    public Entidades cargarEntidad(Establecimiento establecimiento, int codEntidad) throws Exception {
        try {
            this.abrirConexion();
            return new EntidadesDAO(session).cargarEntidad(establecimiento, codEntidad);
        } finally {
            this.cerrarConexion();
        }
    }

    public int siguienteCodigoEntidad(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            EntidadesDAO entidadesDAO = new EntidadesDAO(session);
            return entidadesDAO.siguienteCodigoEntidad(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public Entidades validarEntidad(Entidades entidades) throws Exception {
        if (entidades == null || entidades.getEntidadesPK() == null || entidades.getEntidadesPK().getCodEntidad() == 0) {
            throw new Exception("No fue posible asignar el consecutivo del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (entidades.getNombre() == null || entidades.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (entidades.getDireccion() == null || entidades.getDireccion().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la dirección del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (entidades.getTelefono() == null || entidades.getTelefono().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el telefono del cliente.", UtilLog.TW_RESTRICCION);
        }
        entidades.setNombre(entidades.getNombre().trim().toUpperCase());
        entidades.setDireccion(entidades.getDireccion().trim().toUpperCase());
        return entidades;
    }

}
