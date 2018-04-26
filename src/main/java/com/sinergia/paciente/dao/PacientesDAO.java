/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.dao;

import com.sinergia.paciente.modelo.Pacientes;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class PacientesDAO {

    private final Session session;

    public PacientesDAO(Session session) {
        this.session = session;
    }

    public Pacientes consultarPaciente(Pacientes pacientes) {
        Query query = session.createQuery(
                "FROM Pacientes P"
                + " JOIN FETCH P.pacientesPK PK"
                + " LEFT JOIN FETCH P.direccionesPacienteSet"
                + " LEFT JOIN FETCH P.telefonosPacienteSet"
                + " LEFT JOIN FETCH P.contexturasPacienteSet CPS"
                + " LEFT JOIN FETCH CPS.contexturasPacientePK CPSPK"
                + " WHERE PK.codIdentidad=? AND PK.documentoBeneficiario=?"
                + " ORDER BY CPSPK.fechaRegistro");
        query.setParameter(0, pacientes.getPacientesPK().getCodIdentidad());
        query.setParameter(1, pacientes.getPacientesPK().getDocumentoBeneficiario());
        return (Pacientes) query.uniqueResult();
    }

}
