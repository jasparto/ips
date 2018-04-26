/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.dao;

import com.sinergia.publico.modelo.Institucion;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class InstitucionDAO {

    private final Session session;

    public InstitucionDAO(Session session) {
        this.session = session;
    }

    public Collection<? extends Institucion> cargarInstituciones() {
        Query query = session.createQuery(
                "FROM Institucion I"
                + " LEFT JOIN FETCH I.establecimientosSet"
                + " LEFT JOIN FETCH I.tipoRecursosSet TR"
                + " LEFT JOIN FETCH I.recursoSet R"
                + " LEFT JOIN FETCH R.establecimientoSet"
                + " LEFT JOIN FETCH I.institucionParametrosSet IP"
                + " LEFT JOIN FETCH IP.parametros"
                + " LEFT JOIN FETCH I.menusSet"
                + " LEFT JOIN FETCH I.rolesSet R"
                + " LEFT JOIN FETCH R.rolesMenusSet"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return query.list();
    }

    public int siguienteCodigoInstitucion() {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(cod_institucion),0) + 1"
                + " FROM institucion"
        );
        return (int) query.uniqueResult();
    }

    public Institucion cargarConfiguracionPrograma(Institucion institucion) {
        Query query = session.createQuery(
                "FROM Institucion I"
                + " LEFT JOIN FETCH I.tipoRecursosSet TR"
                + " LEFT JOIN FETCH I.recursoSet R"
                + " LEFT JOIN FETCH I.institucionParametrosSet IP"
                + " LEFT JOIN FETCH IP.parametros"
                + " LEFT JOIN FETCH I.clientesSet"
                + " LEFT JOIN FETCH I.entidadesSet"
                + " LEFT JOIN FETCH I.subcuentasSet"
                + " LEFT JOIN FETCH I.tiposContratoSet"
                + " LEFT JOIN FETCH I.listasPreciosSet L"
                + " LEFT JOIN FETCH L.serviciosListaSet"
                + " LEFT JOIN FETCH I.programasSet"
                + " LEFT JOIN FETCH I.programasConfiguracionSet PC"
                + " LEFT JOIN FETCH I.parametrosConfiguracionSet"
                + " LEFT JOIN FETCH PC.programasConfiguracionParametrosSet PCP"
                + " LEFT JOIN FETCH PCP.programasConfiguracion"
                + " LEFT JOIN FETCH PCP.parametrosConfiguracion"
                + " LEFT JOIN FETCH I.establecimientosSet"
                + " WHERE I.codInstitucion=?"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, institucion.getCodInstitucion());
        return (Institucion) query.uniqueResult();
    }
}
