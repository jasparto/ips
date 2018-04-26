/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.dao;

import com.sinergia.publico.modelo.Establecimiento;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class EstablecimientoDAO {

    private Session session = null;

    public EstablecimientoDAO(Session session) {
        this.session = session;
    }

    public Establecimiento cargarEstablecimiento(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<? extends Establecimiento> cargarListaEstablecimientos(int codInstitucion) {
        Query query = session.createQuery(
                "FROM Establecimiento E"
                + " JOIN FETCH E.establecimientoPK PK"
                + " JOIN FETCH E.municipios M"
                + " JOIN FETCH E.institucion I"
                + " LEFT JOIN FETCH E.establecimientoParametrosSet EP"
                + " LEFT JOIN FETCH E.facturasConfiguracionSet"
                + " LEFT JOIN FETCH EP.parametros"
                + " WHERE PK.codInstitucion=?"
                + " ORDER BY PK.codEstablecimiento"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, codInstitucion);
        return query.list();
    }

    public Collection<? extends Establecimiento> cargarListaEstablecimientos() {
        Query query = session.createQuery(
                "FROM Establecimiento E"
                + " JOIN FETCH E.establecimientoPK PK"
                + " JOIN FETCH E.municipios M"
                + " JOIN FETCH E.institucion I"
                + " ORDER BY PK.codEstablecimiento"
        );
        return query.list();
    }

    public List<?> cargarListaEstablecimientosUsuario(String documentoUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Establecimiento cargarEstablecimiento(int codInstitucion, int codEstablecimiento) {
        Query query = session.createQuery(
                "FROM Establecimiento E"
                + " JOIN FETCH E.establecimientoPK PK"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=?"
        );
        query.setParameter(0, codInstitucion);
        query.setParameter(1, codEstablecimiento);
        return (Establecimiento) query.uniqueResult();

    }

    public int siguienteCodigoEstablecimiento(int codInstitucion) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(MAX(cod_establecimiento),0) + 1"
                + " FROM establecimiento"
                + " WHERE cod_institucion=?"
        );
        query.setParameter(0, codInstitucion);
        return (int) query.uniqueResult();
    }

}
