/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.dao;

import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.modelo.Usuarios;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class RecursosDAO {

    private Session session = null;

    public RecursosDAO(Session session) {
        this.session = session;
    }

    public Collection<? extends TipoRecurso> cargarRecursosServicio(Servicios servicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Recurso cargarRecursoUsuario(Institucion institucion, Usuarios usuarios) {
        Query query = session.createSQLQuery(
                "SELECT"
                + " R.*"
                + " FROM"
                + " atencion.recurso_usuarios RU"
                + " JOIN atencion.recurso R ON (RU.cod_institucion = R.cod_institucion AND"
                + " RU.cod_recurso = R.cod_recurso)"
                + " WHERE RU.cod_institucion=? AND RU.cod_documento=? AND RU.documento_usuario=?"
        ).addEntity(Recurso.class);
//        ).setResultTransformer(Criteria.ROOT_ENTITY);
        query.setParameter(0, institucion.getCodInstitucion());
        query.setParameter(1, usuarios.getUsuariosPK().getCodDocumento());
        query.setParameter(2, usuarios.getUsuariosPK().getDocumentoUsuario());
        return (Recurso) query.uniqueResult();
    }

}
