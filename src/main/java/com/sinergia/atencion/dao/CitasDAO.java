/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.dao;

import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilFecha;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Julian
 */
public class CitasDAO {

    private Session session = null;

    public CitasDAO() {
    }

    public CitasDAO(Session session) {
        this.session = session;
    }

    public long generarCodigoCita() {
        Query query = session.createSQLQuery("select nextval('atencion.citas_cod_cita_seq')");
        return ((BigInteger) query.uniqueResult()).longValue();
    }

    public void guardarCita(Citas citas) {
        session.save(citas);
    }

    public Collection<? extends Citas> cargarCitasUsuario(Establecimiento establecimiento, Usuarios usuario, Date fecha) {
        Query query = session.createQuery(
                "FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " JOIN FETCH C.recursoSet RS"
                + " LEFT JOIN FETCH C.seccionDetalleCombosCitasTextoSet"
                + " JOIN FETCH RS.recursoPK RSPK"
                + " LEFT JOIN FETCH RS.establecimientoSet"
                + " LEFT JOIN FETCH C.seccionDetalleCombosList SDC"
                + " LEFT JOIN FETCH SDC.seccionDetalle"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND cast(C.fecha as date)=? AND RSPK.codRecurso=?"
                + " ORDER BY PK.codCita"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, fecha);
        query.setParameter(3, usuario.getRecurso().getRecursoPK().getCodRecurso());
        return query.list();
    }

    public Set<Recurso> cargarRecursosCita(Citas citas) {
        Query query = session.createQuery(
                "FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " JOIN FETCH C.recursoSet"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND PK.codCita=?"
                + " ORDER BY PK.codCita").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, citas.getPk().getCodInstitucion());
        query.setParameter(1, citas.getPk().getCodEstablecimiento());
        query.setParameter(2, citas.getPk().getCodCita());

        return ((Citas) query.uniqueResult()).getRecursoSet();
    }

    public Collection<? extends Citas> cargarCitasFacturadas(Establecimiento establecimiento, Date fechaInicial, Date fechaFinal) {
        Query query = session.createQuery(
                "FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " JOIN FETCH C.recursoSet"
                + " JOIN FETCH C.facturasSet"
                + " JOIN FETCH C.pacientes P"
                + " JOIN FETCH P.documentosIdentidad"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND cast(C.fecha as date) BETWEEN ? AND ?"
                + " ORDER BY PK.codCita").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, fechaInicial);
        query.setParameter(3, fechaFinal);
        return query.list();
    }

    public Collection<? extends Citas> cargarCitas(Establecimiento establecimiento, Date fecha) {
        Query query = session.createQuery(
                "FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " JOIN FETCH C.recursoSet"
                + " JOIN FETCH C.pacientes P"
                + " JOIN FETCH P.documentosIdentidad"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND cast(C.fecha as date) = ?"
                + " ORDER BY PK.codCita").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, fecha);
        return query.list();
    }

    public Collection<? extends Citas> cargarCitas(Establecimiento establecimiento, Date fechaInicial, Date fechaFinal) {
        Query query = session.createQuery(
                "FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " JOIN FETCH C.recursoSet"
                + " JOIN FETCH C.pacientes P"
                + " JOIN FETCH P.documentosIdentidad"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND cast(C.fecha as date) BETWEEN ? AND ?"
                + " ORDER BY PK.codCita").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, fechaInicial);
        query.setParameter(3, fechaFinal);
        return query.list();
    }

}
