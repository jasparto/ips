/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.dao;

import com.sinergia.facturacion.modelo.Facturas;
import com.sinergia.facturacion.modelo.FacturasConfiguracion;
import com.sinergia.publico.modelo.Establecimiento;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class FacturasDAO {

    private final Session session;

    public FacturasDAO(Session session) {
        this.session = session;
    }

    public long generarCodigoFactura() {
        Query query = session.createSQLQuery("select nextval('facturacion.facturas_cod_factura_seq')");
        return ((BigInteger) query.uniqueResult()).longValue();
    }

    public Facturas generarNumeroFactura(Establecimiento establecimiento, Facturas facturas, FacturasConfiguracion facturasConfiguracion) {
        Query query = session.createSQLQuery(
                "SELECT COALESCE(numero_factura,0) + 1"
                + " FROM facturacion.facturas_configuracion"
                + " WHERE cod_institucion=? AND cod_establecimiento=? AND cod_configuracion=?"
                + " FOR UPDATE");
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion());
        facturas.setNumeroFactura(String.valueOf((int) query.uniqueResult()));
        return facturas;
    }

    public void actualizarNumeroFactura(Establecimiento establecimiento, Facturas facturas, FacturasConfiguracion facturasConfiguracion) {
        Query query = session.createSQLQuery(
                "UPDATE facturacion.facturas_configuracion"
                + " SET numero_factura=?"
                + " WHERE cod_institucion=? AND cod_establecimiento=? AND cod_configuracion=?"
        );
        query.setParameter(0, Integer.valueOf(facturas.getNumeroFactura()));
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(2, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(3, facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion());
        query.executeUpdate();
    }

    public Collection<? extends Facturas> cargarFacturasEstablecimientoFecha(Establecimiento establecimiento, Date fechaInicial, Date fechaFinal) {
        Query query = session.createQuery(
                "FROM Facturas F"
                + " JOIN FETCH F.facturasPK PK"
                + " JOIN FETCH F.pacientes P"
                + " JOIN FETCH P.documentosIdentidad"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND F.fechaRegistro BETWEEN ? AND ?"
                + " ORDER BY F.numeroFactura"
        );
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, fechaInicial);
        query.setParameter(3, fechaFinal);
        return query.list();
    }

    public Collection<? extends FacturasConfiguracion> cargarFacturasConfiguracionVigencia(Establecimiento establecimiento, FacturasConfiguracion facturasConfiguracion) {
        Query query = session.createQuery(
                "FROM FacturasConfiguracion FC"
                + " JOIN FETCH FC.facturasConfiguracionPK PK"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND FC.tipoResolucion=?"
                + " AND (? BETWEEN FC.fechaInicioVigencia AND FC.fechaFinVigencia OR ? BETWEEN FC.fechaInicioVigencia AND FC.fechaFinVigencia) "
                + " ORDER BY PK.codConfiguracion"
        );
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        query.setParameter(2, facturasConfiguracion.getTipoResolucion());
        query.setParameter(3, facturasConfiguracion.getFechaInicioVigencia());
        query.setParameter(4, facturasConfiguracion.getFechaFinVigencia());
        return query.list();
    }

    public Collection<? extends FacturasConfiguracion> cargarFacturasConfiguracion(Establecimiento establecimiento) {
        Query query = session.createQuery(
                "FROM FacturasConfiguracion FC"
                + " JOIN FETCH FC.facturasConfiguracionPK PK"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=?"
                + " AND current_date BETWEEN FC.fechaInicioVigencia AND FC.fechaFinVigencia"
                + " ORDER BY PK.codConfiguracion"
        );
        query.setParameter(0, establecimiento.getEstablecimientoPK().getCodInstitucion());
        query.setParameter(1, establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        
        return query.list();
    }
}
