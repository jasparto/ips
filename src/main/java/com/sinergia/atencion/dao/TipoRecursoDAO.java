/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.dao;

import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.atencion.modelo.TipoRecursoServiciosCantidad;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class TipoRecursoDAO {

    private final Session session;

    public TipoRecursoDAO(Session session) {
        this.session = session;
    }

    public Collection<? extends TipoRecurso> cargarTipoRecursoInstitucion(Integer codInstitucion) {
        Query query = session.createQuery(
                "FROM TipoRecurso T"
                + " JOIN FETCH T.institucion I"
                + " WHERE I.codInstitucion=?"
        );
        query.setParameter(0, codInstitucion);
        return query.list();
    }

    public TipoRecursoServiciosCantidad cargarTipoRecursoServiciosCantidad(Servicios servicios, TipoRecurso tipoRecurso) {
        Query query = session.createQuery(
                "FROM TipoRecursoServiciosCantidad T"
                + " JOIN FETCH T.tipoRecursoServiciosCantidadPK PK"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND PK.codTipoRecurso=? AND PK.codServicio=?"
        );
        query.setParameter(0, servicios.getServiciosPK().getCodInstitucion());
        query.setParameter(1, servicios.getServiciosPK().getCodEstablecimiento());
        query.setParameter(2, tipoRecurso.getCodTipoRecurso());
        query.setParameter(3, servicios.getServiciosPK().getCodServicio());
        return (TipoRecursoServiciosCantidad) query.uniqueResult();
    }
}
