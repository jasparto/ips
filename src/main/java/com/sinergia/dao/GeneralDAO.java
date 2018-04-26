/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.dao;

import java.math.BigInteger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class GeneralDAO {

    private final Session session;

    public GeneralDAO(Session session) {
        this.session = session;
    }

    public Long nextval(String secuencia) {
        Query query = session.createSQLQuery("select nextval('" + secuencia + "')");
        return ((BigInteger) query.uniqueResult()).longValue();
    }

    public int siguienteCodigoEntidad(Integer codInstitucion, String campo, String entidad) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(" + campo + "),0) + 1"
                + " FROM " + entidad
                + " WHERE cod_institucion=?"
        );
        query.setParameter(0, codInstitucion);
        return (int) query.uniqueResult();
    }

}
