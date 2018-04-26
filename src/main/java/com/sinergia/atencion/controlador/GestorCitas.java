/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.controlador;

import com.sinergia.atencion.dao.CitasDAO;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.SeccionDetalleCombosCitasTexto;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;

/**
 *
 * @author Julian
 */
public class GestorCitas extends Gestor implements Serializable {

    CitasDAO citaDAO;

    public GestorCitas() throws Exception {
        super();
    }

    public long generarCodigoCita() throws Exception {
        try {
            this.abrirConexion();
            return new CitasDAO(this.session).generarCodigoCita();
        } finally {
            this.cerrarConexion();
        }
//        CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
//        customerDaoImpl.probar();
//        Session session = getSessionFactory().openSession();
//        session.beginTransaction();
//        return 0;
    }

    public void guardarCita(Citas citas, Set<SeccionDetalleCombosCitasTexto> seccionDetalleCombosCitasTextoSet) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(citas);
            if (seccionDetalleCombosCitasTextoSet != null) {
                for (SeccionDetalleCombosCitasTexto s : seccionDetalleCombosCitasTextoSet) {
                    this.session.saveOrUpdate(s);
                }
            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void actualizarCita(Citas citas) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.update(citas);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void guardarPaciente(Pacientes pacientes) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(pacientes);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarAgendamientoCita(Citas citas, Servicios servicio, List<TipoRecurso> tipoRecursosList) throws Exception {
        if (servicio == null || servicio.getServiciosPK() == null || servicio.getServiciosPK().getCodServicio() == 0) {
            throw new Exception("Por favor seleccione un servicio valido.", UtilLog.TW_VALIDACION);
        }
        if (tipoRecursosList == null) {
            throw new Exception("Seleccione el recurso de la cita.", UtilLog.TW_VALIDACION);
        }
        for (TipoRecurso tp : tipoRecursosList) {
//            if (tp.getRecursoSeleccionadoList().isEmpty()) {
            if (tp.getRecursoDualList().getTarget().isEmpty()) {
                throw new Exception("Seleccione un " + tp.getNombre() + " para la cita", UtilLog.TW_VALIDACION);
            }
        }

    }

    public Collection<? extends Citas> cargarCitasPaciente(Pacientes pacientes) {
        Object[] p = {pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario()};
        return getHibernateTemplate().find("FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " WHERE C.codIdentidad=? AND C.documentoBeneficiario=?"
                + " ORDER BY PK.codCita", p);
    }

    public Collection<? extends Citas> cargarCitasPacienteEstado(Establecimiento establecimiento, Pacientes pacientes, String estado) {
        Object[] p = {establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario(), estado};
        return getHibernateTemplate().find("FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " WHERE PK.codInstitucion=? AND PK.codEstablecimiento=? AND C.codIdentidad=? AND C.documentoBeneficiario=? AND C.estado=?"
                + " ORDER BY PK.codCita", p);
    }

    public Collection<? extends Citas> cargarCitasPacienteEstado(Pacientes pacientes, String estado) {
        Object[] p = {pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario(), estado};
        return getHibernateTemplate().find("FROM Citas C"
                + " JOIN FETCH C.pk PK"
                + " WHERE C.codIdentidad=? AND C.documentoBeneficiario=? AND C.estado=?"
                + " ORDER BY PK.codCita", p);
    }

    public Collection<? extends Citas> cargarCitasUsuario(Establecimiento establecimiento, Usuarios usuario, Date fecha) throws Exception {
        try {
            this.abrirConexion();
            CitasDAO citasDAO = new CitasDAO(session);
            return citasDAO.cargarCitasUsuario(establecimiento, usuario, fecha);
        } finally {
            this.cerrarConexion();
        }
    }

    public Set<Recurso> cargarRecursosCita(Citas citas) throws Exception {
        Object[] p = {citas.getPk().getCodInstitucion(), citas.getPk().getCodEstablecimiento(), citas.getPk().getCodCita()};
        try {
            this.abrirConexion();
            CitasDAO citasDAO = new CitasDAO(session);
            return citasDAO.cargarRecursosCita(citas);
        } finally {
            this.cerrarConexion();
        }
//        return getHibernateTemplate().find("FROM Citas C"
//                + " JOIN FETCH C.pk PK"
//                + " WHERE C.codIdentidad=? AND C.documentoBeneficiario=?"
//                + " ORDER BY PK.codCita", p);
    }

    public Collection<? extends Citas> cargarCitasFacturadas(Establecimiento establecimiento, Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            this.abrirConexion();
            return new CitasDAO(session).cargarCitasFacturadas(establecimiento, fechaInicial, fechaFinal);
        } finally {
            this.cerrarConexion();
        }

    }

    public Collection<? extends Citas> cargarCitas(Establecimiento establecimiento, Date fecha) throws Exception {
        try {
            this.abrirConexion();
            return new CitasDAO(session).cargarCitas(establecimiento, fecha);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Citas> cargarCitas(Establecimiento establecimiento, Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            this.abrirConexion();
            return new CitasDAO(session).cargarCitas(establecimiento, fechaInicial, fechaFinal);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarRecursoEstablecimiento(Establecimiento establecimiento, Usuarios usuario, Citas citas) throws Exception {
        if (usuario.getRecurso() == null || usuario.getRecurso().getRecursoPK() == null
                || usuario.getRecurso().getRecursoPK().getCodRecurso() == null || usuario.getRecurso().getRecursoPK().getCodRecurso() == 0) {
            throw new Exception("El usuario logueado no tiene un recurso asignado.", UtilLog.TW_VALIDACION);
        }
        for (Recurso r : citas.getRecursoSet()) {
            if (r.equals(usuario.getRecurso())) {
                if (r.getEstablecimientoSet() == null || !Hibernate.isInitialized(r.getEstablecimientoSet())
                        || r.getEstablecimientoSet().isEmpty()) {
                    throw new Exception("El recurso del usuario no tiene ningun establecimiento asignado.", UtilLog.TW_VALIDACION);
                }
                boolean existeRecurso = false;
                for (Establecimiento e : r.getEstablecimientoSet()) {
                    if (e.equals(establecimiento)) {
                        existeRecurso = true;
                        break;
                    }
                }
                if (!existeRecurso) {
                    throw new Exception("El recurso del usuario no tiene asignado el establecimiento.", UtilLog.TW_VALIDACION);
                }
            }
        }

    }

}
