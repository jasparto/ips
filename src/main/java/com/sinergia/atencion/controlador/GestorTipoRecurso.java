/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.controlador;

import com.sinergia.atencion.dao.TipoRecursoDAO;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.atencion.modelo.TipoRecursoServiciosCantidad;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

import java.util.Collection;

/**
 *
 * @author juliano
 */
public class GestorTipoRecurso extends Gestor implements Serializable {

    public GestorTipoRecurso() throws Exception {
        super();
    }

    public Collection<? extends TipoRecurso> cargarTipoRecursoInstitucion(Integer codInstitucion) throws Exception {
        try {
            this.abrirConexion();
            return new TipoRecursoDAO(session).cargarTipoRecursoInstitucion(codInstitucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarTipoRecurso(TipoRecurso tipoRecurso) throws Exception {
        if (tipoRecurso.getNombre() == null || tipoRecurso.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del tipo de recurso", UtilLog.TW_VALIDACION);
        }
        if (tipoRecurso.getClase() == null || tipoRecurso.getClase().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la clase del tipo de recurso (H / F)", UtilLog.TW_VALIDACION);
        }
    }

    public TipoRecursoServiciosCantidad cargarTipoRecursoServiciosCantidad(Servicios servicios, TipoRecurso tipoRecurso) throws Exception {
        try {
            this.abrirConexion();
            return new TipoRecursoDAO(session).cargarTipoRecursoServiciosCantidad(servicios, tipoRecurso);
        } finally {
            this.cerrarConexion();
        }
    }

}
