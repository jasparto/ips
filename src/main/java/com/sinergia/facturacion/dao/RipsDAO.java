/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.dao;

import com.sinergia.facturacion.modelo.Facturas;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class RipsDAO {

    private final Session session;

    public RipsDAO(Session session) {
        this.session = session;
    }

    public long generarCodigoGeneracion() {
        Query query = session.createSQLQuery("select nextval('facturacion.rips_generacion_cod_generacion_seq')");
        return ((BigInteger) query.uniqueResult()).longValue();
    }
}
