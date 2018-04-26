/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.dao;

import com.sinergia.cliente.modelo.Clientes;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class ClientesDAO {

    private final Session session;

    public ClientesDAO(Session session) {
        this.session = session;
    }

    public Collection<? extends Clientes> cargarClientesEstablecimiento(Establecimiento establecimiento) {
//        Object[] p = {establecimiento.getEstablecimientoPK().getCodEstablecimiento(), establecimiento.getEstablecimientoPK().getCodInstitucion()};
        Query query = session.createQuery(
                "FROM Clientes C"
                + " JOIN FETCH C.clientesPK PK"
                + " JOIN FETCH C.programasSet P"
                + " JOIN FETCH P.entidades E"
                + " JOIN FETCH P.subcuentas S"
                + " JOIN FETCH P.programasConfiguracionSet PC"
                + " JOIN FETCH PC.programasConfiguracionPK PCPK"
                + " JOIN FETCH PC.listasPrecios L"
                + " LEFT JOIN FETCH L.serviciosListaSet SL"
                + " WHERE PK.codInstitucion=?").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
//        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodInstitucion());
        return query.list();
    }

    public int siguienteCodigoCliente(Institucion institucion) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(cod_cliente),0) + 1"
                + " FROM cliente.clientes"
                + " WHERE cod_institucion=?"
        );
        query.setParameter(0, institucion.getCodInstitucion());
        return (int) query.uniqueResult();
    }

}
