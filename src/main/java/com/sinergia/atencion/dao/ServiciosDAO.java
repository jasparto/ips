/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.dao;

import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.publico.modelo.Establecimiento;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class ServiciosDAO {

    private final Session session;

    public ServiciosDAO(Session session) {
        this.session = session;
    }

    public Collection<Servicios> cargarServiciosEstablecimiento(Establecimiento establecimiento) {
        Query query = session.createQuery(
                "FROM Servicios S"
                + " JOIN FETCH S.serviciosPK PK"
                + " LEFT JOIN FETCH S.seccionServiciosSet"
                + " LEFT JOIN FETCH S.tipoRecursoSet TR"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=?"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        return query.list();
    }

    public Collection<Servicios> cargarListaServicios(int codInstitucion, Long codServicio) {
        Query query = session.createQuery(
                "FROM Servicios S"
                + " JOIN FETCH S.serviciosPK PK"
                + " LEFT JOIN FETCH S.tipoRecursoSet TR"
                + " WHERE PK.codInstitucion=? AND PK.codServicio=?"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, codInstitucion);
        query.setParameter(1, codServicio);
        return query.list();
    }
}
