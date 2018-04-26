/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.dao;

import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.modelo.Seccion;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.publico.modelo.Establecimiento;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class SeccionDAO {

    private Session session = null;

    public SeccionDAO(Session session) {
        this.session = session;
    }

    public Collection<? extends Seccion> cargarSeccionEstablecimiento(Establecimiento establecimiento, boolean filtroActivo) {
        String filtro = "";
        if (filtroActivo) {
            filtro += " AND S.activo=TRUE";
        }
        Query query = session.createQuery(
                "FROM Seccion S"
                + " JOIN FETCH S.seccionPK SPK"
                + " LEFT JOIN FETCH S.seccionDetalleSet SD"
                + " LEFT JOIN FETCH SD.seccionDetalleCombosSet SDC"
                + " LEFT JOIN FETCH SDC.seccionDetalleCombosPK SDCPK"
                + " LEFT JOIN FETCH SD.tipoComponentes"
                + " WHERE SPK.codInstitucion=? AND SPK.codEstablecimiento=? " + filtro + " ORDER BY SD.orden"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        return query.list();
    }

    public Collection<? extends Seccion> cargarSeccionServicioEstablecimiento(Establecimiento establecimiento, Long codServicio, boolean filtroActivo) {
        String filtro = "";
        if (filtroActivo) {
            filtro += " AND S.activo=TRUE";
        }
        Query query = session.createQuery(
                "FROM Seccion S"
                + " JOIN FETCH S.seccionPK SPK"
                + " JOIN FETCH S.seccionServiciosSet SS"
                + " JOIN FETCH SS.seccionServiciosPK SSPK"
                + " LEFT JOIN FETCH S.seccionDetalleSet SD"
                + " LEFT JOIN FETCH SD.seccionDetalleCombosSet SDC"
                + " LEFT JOIN FETCH SDC.seccionDetalleCombosPK SDCPK"
                + " LEFT JOIN FETCH SD.tipoComponentes"
                + " WHERE SPK.codInstitucion=? AND SPK.codEstablecimiento=? AND SSPK.codServicio=? " + filtro + " ORDER BY SD.orden"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, codServicio);
        return query.list();
    }

    public void borrarSeccionServicios(Servicios servicios) {
        Query query = session.createSQLQuery(
                "DELETE FROM atencion.seccion_servicios"
                + " WHERE cod_institucion=? AND cod_establecimiento=? AND cod_servicio=?");
        query.setParameter(0, servicios.getServiciosPK().getCodInstitucion());
        query.setParameter(1, servicios.getServiciosPK().getCodEstablecimiento());
        query.setParameter(2, servicios.getServiciosPK().getCodServicio());
        query.executeUpdate();
    }

}
