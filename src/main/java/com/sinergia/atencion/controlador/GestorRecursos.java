/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.controlador;

import com.sinergia.atencion.dao.RecursosDAO;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.RecursoUsuarios;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.atencion.vista.UITipoRecurso;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.primefaces.model.DualListModel;

/**
 *
 * @author juliano
 */
public class GestorRecursos extends Gestor implements Serializable {
    
    public GestorRecursos() throws Exception {
        super();
    }
    
    public Collection<? extends TipoRecurso> cargarRecursosServicio(Servicios servicio) {
        try {
            Object[] p = {servicio.getServiciosPK().getCodInstitucion(), servicio.getServiciosPK().getCodEstablecimiento(), servicio.getServiciosPK().getCodServicio()};
//            Collection<Servicios> serviciosList = getHibernateTemplate().find("FROM Servicios S"
//                    + " JOIN FETCH S.serviciosPK SPK"
//                    + " JOIN FETCH S.tipoRecursoSet TR"
//                    + " JOIN FETCH TR.recurso"
//                    + " WHERE SPK.codInstitucion=? AND SPK.codEstablecimiento=? AND SPK.codServicio=?", p);
//            return null;
            Collection<TipoRecurso> tipoRecursoList = getHibernateTemplate().find("FROM TipoRecurso TR"
                    + " JOIN FETCH TR.serviciosSet S"
                    + " JOIN FETCH S.serviciosPK SPK"
                    + " WHERE SPK.codInstitucion=? AND SPK.codEstablecimiento=? AND SPK.codServicio=?", p
            );
            Collection<Recurso> recursoList = getHibernateTemplate().find("FROM Recurso R"
                    + " JOIN FETCH R.establecimientoSet ES"
                    + " JOIN FETCH R.tipoRecurso TRS"
                    + " JOIN FETCH TRS.serviciosSet SS"
                    + " JOIN FETCH SS.serviciosPK SPK"
                    + " WHERE SPK.codInstitucion=? AND SPK.codEstablecimiento=? AND SPK.codServicio=?", p
            );
            for (TipoRecurso tr : tipoRecursoList) {
                Set<Recurso> rSet = new HashSet<>();
                for (Recurso r : recursoList) {
                    if (tr.getCodTipoRecurso() == r.getCodTipoRecurso()) {
                        rSet.add(r);
                    }
                }
                tr.setRecursoSet(rSet);
                tr.getRecursoOrigenList().addAll(rSet);
//                tr.setRecursoSeleccionadoList(new ArrayList<Recurso>(tr.getRecursoOrigenList().size()));
                tr.setRecursoDualList(new DualListModel<>(tr.getRecursoOrigenList(), tr.getRecursoSeleccionadoList()));
                
                switch (tr.getNombre()) {
                    case UITipoRecurso.CONSULTORIO:
                        tr.setIcon("ui-icon-bookmark");
                        break;
                    case UITipoRecurso.MEDICO:
                        tr.setIcon("ui-icon-person");
                        break;
                    default:
                        tr.setIcon("ui-icon-tag");
                }
            }
            return tipoRecursoList;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Recurso cargarRecursoUsuario(Institucion institucion, Usuarios usuarios) throws Exception {
        try {
            this.abrirConexion();
            return new RecursosDAO(session).cargarRecursoUsuario(institucion, usuarios);
        } finally {
            this.cerrarConexion();
        }
    }
    
    public void guardarRecurso(Recurso recurso) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(recurso);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }
    
    public void guardarRecursoUsuarios(RecursoUsuarios recursoUsuarios) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(recursoUsuarios);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }
    
    public Recurso validarRecurso(Recurso recurso) throws Exception {
        if (recurso == null || recurso.getNombre() == null
                || recurso.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del recurso.", UtilLog.TW_VALIDACION);
        }
        if (recurso.getTipoRecurso() == null || recurso.getTipoRecurso().getCodTipoRecurso() == null
                || recurso.getTipoRecurso().getCodTipoRecurso() == 0) {
            throw new Exception("Ingresa el tipo del recurso.", UtilLog.TW_VALIDACION);
        }
        recurso.setNombre(recurso.getNombre().trim().toUpperCase());
        return recurso;
    }
    
}
