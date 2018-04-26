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
public class TiposContratoDAO {

    private final Session session;

    public TiposContratoDAO(Session session) {
        this.session = session;
    }

    public int siguienteCodigoTipoContrato(Institucion institucion) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(cod_contrato),0) + 1"
                + " FROM cliente.tipos_contrato"
                + " WHERE cod_institucion=?"
        );
        query.setParameter(0, institucion.getCodInstitucion());
        return (int) query.uniqueResult();
    }

}
