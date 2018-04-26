/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.dao;

import com.sinergia.cliente.modelo.Programas;
import com.sinergia.cliente.modelo.ProgramasConfiguracion;
import com.sinergia.publico.modelo.Establecimiento;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class ProgramasDAO {

    private final Session session;

    public ProgramasDAO(Session session) {
        this.session = session;
    }

    public Collection<? extends Establecimiento> cargarEstablecimientosProgramaConfiguracion(Programas programas, ProgramasConfiguracion programasConfiguracion) {
        Query query = session.createSQLQuery(
                "SELECT E.*"
                + " FROM cliente.rel_programas_configuracion RPC"
                + " JOIN public.establecimiento E ON (RPC.cod_institucion=E.cod_institucion AND RPC.cod_establecimiento=E.cod_establecimiento)"
                + " WHERE RPC.cod_institucion=? AND RPC.cod_programa=? AND RPC.cod_entidad=? AND RPC.cod_contrato=? AND RPC.cod_cliente=? AND RPC.cod_subcuenta=?"
                + " AND RPC.cod_configuracion=?"
        ).addEntity(Establecimiento.class);
        query.setParameter(0, programas.getProgramasPK().getCodInstitucion());
        query.setParameter(1, programas.getProgramasPK().getCodPrograma());
        query.setParameter(2, programas.getProgramasPK().getCodEntidad());
        query.setParameter(3, programas.getProgramasPK().getCodContrato());
        query.setParameter(4, programas.getProgramasPK().getCodCliente());
        query.setParameter(5, programas.getProgramasPK().getCodSubcuenta());
        query.setParameter(6, programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion());
        return query.list();
    }

    public void borrarProgramasConfiguracion(Programas programas, ProgramasConfiguracion programasConfiguracion) {
        Query query = session.createSQLQuery(
                "DELETE FROM cliente.rel_programas_configuracion RPC"
                + " WHERE RPC.cod_institucion=? AND RPC.cod_programa=? AND RPC.cod_entidad=? AND RPC.cod_contrato=? AND RPC.cod_cliente=? AND RPC.cod_subcuenta=?"
                + " AND RPC.cod_configuracion=?");
        query.setParameter(0, programas.getProgramasPK().getCodInstitucion());
        query.setParameter(1, programas.getProgramasPK().getCodPrograma());
        query.setParameter(2, programas.getProgramasPK().getCodEntidad());
        query.setParameter(3, programas.getProgramasPK().getCodContrato());
        query.setParameter(4, programas.getProgramasPK().getCodCliente());
        query.setParameter(5, programas.getProgramasPK().getCodSubcuenta());
        query.setParameter(6, programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion());
        query.executeUpdate();
    }

}
