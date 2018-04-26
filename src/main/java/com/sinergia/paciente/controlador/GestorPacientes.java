/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.controlador;

import com.sinergia.atencion.modelo.SeccionDetalle;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.modelo.Sesion;
import com.sinergia.paciente.dao.PacientesDAO;
import com.sinergia.paciente.modelo.ContexturasPaciente;
import com.sinergia.paciente.modelo.ContexturasPacientePK;
import com.sinergia.paciente.modelo.DireccionesPaciente;
import com.sinergia.paciente.modelo.DocumentosIdentidad;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.paciente.modelo.PacientesPK;
import com.sinergia.paciente.modelo.TelefonosPaciente;
import com.sinergia.publico.modelo.DetalleLista;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliano
 */
public class GestorPacientes extends Gestor implements Serializable {

    public GestorPacientes() throws Exception {
        super();
    }

    public Collection<DocumentosIdentidad> cargarListaDocumentosIdentidad() {
        return getHibernateTemplate().find("FROM DocumentosIdentidad");
    }

    public Pacientes consultarPaciente(Pacientes pacientes) throws Exception {
//        PacientesPK pacientesPK = new PacientesPK(pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario());
//        pacientes = new Pacientes(pacientesPK);
//        Object[] p = {pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario()};
//        Collection<Pacientes> l = getHibernateTemplate().find("FROM Pacientes P "
//                + " JOIN FETCH P.pacientesPK PK"
//                + " LEFT JOIN FETCH P.direccionesPacienteSet"
//                + " LEFT JOIN FETCH P.telefonosPacienteSet"
//                + " WHERE PK.codIdentidad=? AND PK.documentoBeneficiario=?", p);
//        for (Object u : l) {
//            pacientes = (Pacientes) u;
//        }
        try {
            this.abrirConexion();
            PacientesDAO pacientesDAO = new PacientesDAO(session);
            return pacientesDAO.consultarPaciente(pacientes);
        } finally {
            this.cerrarConexion();
        }

    }

    public void guardarPaciente(Pacientes pacientes) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(pacientes);
            if (pacientes.getDireccionesPacienteSet() != null) {
                for (DireccionesPaciente d : pacientes.getDireccionesPacienteSet()) {
                    this.session.saveOrUpdate(d);
                }
            }
            if (pacientes.getTelefonosPacienteSet() != null) {
                for (TelefonosPaciente t : pacientes.getTelefonosPacienteSet()) {
                    this.session.saveOrUpdate(t);
                }
            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarContexturasPaciente(SeccionDetalle seccionDetalle, Lista contexturasPacienteLista, Pacientes pacientes, Usuarios usuarios) throws Exception {
        if (seccionDetalle.getNombre() != null && !seccionDetalle.getNombre().equalsIgnoreCase("")) {
            for (DetalleLista dl : contexturasPacienteLista.getDetalleListaSet()) {
                if (dl.getNombre().equalsIgnoreCase(seccionDetalle.getNombre().toUpperCase())) {
                    ContexturasPaciente cp = new ContexturasPaciente(new ContexturasPacientePK(pacientes.getPacientesPK().getCodIdentidad(),
                            pacientes.getPacientesPK().getDocumentoBeneficiario(), dl.getDetalleListaPK().getCodLista(), dl.getDetalleListaPK().getCodDetalle(), new Date()));
                    cp.setAplicacion(Sesion.APP_IPS);
                    cp.setValor(seccionDetalle.getValorIngresado());
                    cp.setUsuario(usuarios.getUsuario());
                    try {
                        this.abrirConexion();
                        this.inicioTransaccion();
                        this.session.save(cp);
                        this.finTransaccion();
                    } finally {
                        this.cerrarConexion();
                    }
                    break;
                }
            }
        }
    }

    public void validarPaciente(Pacientes pacientes) throws Exception {
        if (pacientes == null || pacientes.getPacientesPK() == null
                || pacientes.getPacientesPK().getDocumentoBeneficiario() == null
                || pacientes.getPacientesPK().getDocumentoBeneficiario().equalsIgnoreCase("")
                || pacientes.getPacientesPK().getDocumentoBeneficiario().equalsIgnoreCase("0")) {
            UtilJSF.execute("get('itDocumentoPaciente').focus()");
            throw new Exception("Ingresa los datos del paciente.", UtilLog.TW_VALIDACION);
        }
    }

}
