/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.dao;

import com.sinergia.publico.modelo.Institucion;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class ListasPreciosDAO {

    private final Session session;

    public ListasPreciosDAO(Session session) {
        this.session = session;
    }

    public int siguienteCodigoListasPrecios(Institucion institucion) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(cod_lista),0) + 1"
                + " FROM cliente.listas_precios"
                + " WHERE cod_institucion=?"
        );
        query.setParameter(0, institucion.getCodInstitucion());
        return (int) query.uniqueResult();
    }
}
