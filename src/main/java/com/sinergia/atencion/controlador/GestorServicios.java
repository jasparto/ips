/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.controlador;

import com.sinergia.atencion.dao.ServiciosDAO;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.atencion.modelo.TipoRecursoServicios;
import com.sinergia.atencion.modelo.TipoRecursoServiciosCantidad;
import com.sinergia.atencion.modelo.TipoRecursoServiciosCantidadPK;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author juliano
 */
public class GestorServicios extends Gestor implements Serializable {

    public GestorServicios() throws Exception {
        super();
    }

    public Collection<Servicios> cargarListaServicios(Institucion institucion) {
        return getHibernateTemplate().find("FROM Servicios S"
                + " JOIN FETCH S.serviciosPK PK"
                + " WHERE PK.codInstitucion=?", institucion.getCodInstitucion());
    }

    public Collection<Servicios> cargarServiciosEstablecimiento(Establecimiento establecimiento) throws Exception {

        try {
            this.abrirConexion();
            ServiciosDAO serviciosDAO = new ServiciosDAO(session);
            return serviciosDAO.cargarServiciosEstablecimiento(establecimiento);
        } finally {
            this.cerrarConexion();
        }
    }

    public Servicios validarServicio(Servicios servicios) throws Exception {
        if (servicios.getServiciosPK() == null || servicios.getServiciosPK().getCodServicio() == null
                || servicios.getServiciosPK().getCodServicio() == 0) {
            throw new Exception("No fue posible asignar el codigo del servicio, intenta de nuevo.", UtilLog.TW_VALIDACION);
        }
        if (servicios.getNombre() == null || servicios.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del servicio", UtilLog.TW_VALIDACION);
        }
        servicios.setNombre(servicios.getNombre().trim().toUpperCase());
        return servicios;
    }

    public void guardarTipoRecursoServicio(Servicios servicios) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
//            session.saveOrUpdate(servicios);

            for (TipoRecurso tr : servicios.getTipoRecursoSet()) {
                TipoRecursoServicios tipoRecursoServicios = new TipoRecursoServicios(servicios.getServiciosPK().getCodInstitucion(),
                        servicios.getServiciosPK().getCodEstablecimiento(), tr.getCodTipoRecurso(), servicios.getServiciosPK().getCodServicio());

                TipoRecursoServiciosCantidad tipoRecursoServiciosCantidad = new TipoRecursoServiciosCantidad(new TipoRecursoServiciosCantidadPK(servicios.getServiciosPK().getCodInstitucion(),
                        servicios.getServiciosPK().getCodEstablecimiento(), tr.getCodTipoRecurso(), servicios.getServiciosPK().getCodServicio()), TipoRecurso.CANTIDAD_DEFECTO);

                session.saveOrUpdate(tipoRecursoServicios);
                session.saveOrUpdate(tipoRecursoServiciosCantidad);
            }

            for (TipoRecurso tr : servicios.getTipoRecursoRemoverSet()) {
                TipoRecursoServicios tipoRecursoServicios = new TipoRecursoServicios(servicios.getServiciosPK().getCodInstitucion(),
                        servicios.getServiciosPK().getCodEstablecimiento(), tr.getCodTipoRecurso(), servicios.getServiciosPK().getCodServicio());
                session.delete(tipoRecursoServicios);
            }

            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<Servicios> cargarListaServicios(int codInstitucion, Long codServicio) throws Exception {
        try {
            this.abrirConexion();
            ServiciosDAO serviciosDAO = new ServiciosDAO(session);
            return serviciosDAO.cargarListaServicios(codInstitucion, codServicio);
        } finally {
            this.cerrarConexion();
        }
    }

}
