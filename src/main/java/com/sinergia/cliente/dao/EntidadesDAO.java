/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.dao;

import com.sinergia.cliente.modelo.Entidades;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class EntidadesDAO {

    private final Session session;

    public EntidadesDAO(Session session) {
        this.session = session;
    }

    public Entidades cargarEntidad(Establecimiento establecimiento, int codEntidad) {
        Query query = session.createQuery(
                "FROM Entidades E"
                + " JOIN FETCH E.entidadesPK PK"
                + " WHERE PK.codInstitucion=? AND PK.codEntidad=?");
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
//        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(1, codEntidad);
        return (Entidades) query.uniqueResult();

    }

    public int siguienteCodigoEntidad(Institucion institucion) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(cod_entidad),0) + 1"
                + " FROM cliente.entidades"
                + " WHERE cod_institucion=?"
        );
        query.setParameter(0, institucion.getCodInstitucion());
        return (int) query.uniqueResult();
    }
}
