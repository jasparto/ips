/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.dao.UsuariosDAO;
import com.sinergia.usuario.modelo.Roles;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.usuario.vista.UIUsuario;
import com.sinergia.utilidades.modelo.UtilCorreo;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilTexto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Julian
 */
public class GestorUsuario extends Gestor implements Serializable {

    public GestorUsuario() throws Exception {
        super();
    }

    public boolean validarUsuario(String usuario, String clave) throws Exception {
        try {
            this.abrirConexion();
            UsuariosDAO usuariosDAO = new UsuariosDAO(this.session);
//            Usuarios u = (Usuarios) getHibernateTemplate().find("FROM Usuarios U WHERE U.usuario=?", usuario);
            Usuarios u = new Usuarios();
            u.setUsuario(usuario);
            u = usuariosDAO.cargarDatosUsuario(u, UIUsuario.FILTRO_USUARIO);
//            u = this.cargarDatosUsuario(u, UIUsuario.FILTRO_USUARIO);
            if (u.getUsuariosPK().getDocumentoUsuario() == null
                    || u.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("")
                    || u.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("0")) {
                throw new Exception("El usuario ingresado no esta registrado.", UtilLog.TW_VALIDACION);
            }
            if (!u.getActivo()) {
                throw new Exception("El usuario se encuentra inactivo.", UtilLog.TW_VALIDACION);
            }
            String claveMd5 = usuariosDAO.getMd5(clave);
            if (!u.getClave().trim().equalsIgnoreCase(claveMd5)) {
                throw new Exception("La clave ingresada no es valida intenta de nuevo.", UtilLog.TW_VALIDACION);
            }
            return true;
//            return new UsuariosDAO(this.session).validarUsuario(usuario, clave);
        } finally {
            this.cerrarConexion();
        }
    }

    public ArrayList<Usuarios> cargarListaUsuarios() throws Exception {
        try {
            this.abrirConexion();
            return new UsuariosDAO(this.session).cargarListaUsuarios();
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Usuarios> cargarListaUsuariosEstablecimiento(Establecimiento establecimiento) throws Exception {
        try {
            this.abrirConexion();
            return new UsuariosDAO(this.session).cargarListaUsuariosEstablecimiento(establecimiento);
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuarios cargarDatosUsuario(Establecimiento establecimiento, Usuarios usuario, final String filtro) throws Exception {
        try {
            this.abrirConexion();
//            UsuariosDAO usuarioDAO = new UsuariosDAO(session);
//            usuario = this.cargarDatosUsuario(usuario, filtro);
//            Object[] p = {usuario.getUsuario().toUpperCase()};
//            Collection<Establecimiento> l = getHibernateTemplate().find("FROM Usuarios U"
//                    + " LEFT JOIN FETCH U.relUsuariosEstablecimientoSet RUE"
//                    + " LEFT JOIN FETCH U.relUsuariosRolesSet RUR"
//                    + " LEFT JOIN FETCH U.recursoSet RS"
//                    + " LEFT JOIN FETCH RS.tipoRecurso TR"
//                    + " LEFT JOIN FETCH U.institucionSet I"
//                    + " LEFT JOIN FETCH I.tipoRecursosSet ITR"
//                    + " LEFT JOIN FETCH U.relUsuariosEstablecimientoSet UE"
//                    + " WHERE U.usuario=?", p);
//            for (Object u : l) {
//                usuario = (Usuarios) u;
//            }
//            return usuario;
            return new UsuariosDAO(session).cargarDatosUsuario(establecimiento, usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuarios cargarDatosUsuario(Usuarios usuario, final String filtro) throws Exception {
        try {
            this.abrirConexion();
//            UsuariosDAO usuarioDAO = new UsuariosDAO(session);
//            usuario = usuarioDAO.cargarDatosUsuario(usuario, filtro);
//            if (filtro.equalsIgnoreCase(UIUsuario.FILTRO_USUARIO)) {
//                Collection<Establecimiento> l = getHibernateTemplate().find("FROM Usuarios U"
//                        + " JOIN FETCH U.relUsuariosEstablecimientoSet E"
//                        + " LEFT JOIN FETCH E.facturasConfiguracionSet EFC"
//                        + " WHERE U.usuario=?", usuario.getUsuario().toUpperCase());
//                for (Object u : l) {
//                    usuario = (Usuarios) u;
//                }
////                usuario = (Usuarios) getHibernateTemplate().find("FROM Usuarios");
//            }
//            if (filtro.equalsIgnoreCase(UIUsuario.FILTRO_DOCUMENTO)) {
//                usuario = (Usuarios) getHibernateTemplate().find("FROM Usuarios U WHERE U.documento_usuario=?", usuario.getUsuariosPK().getDocumentoUsuario());
//            }

            return new UsuariosDAO(session).cargarDatosUsuario(usuario, filtro);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends String> autoCompletaUsuario(Establecimiento establecimiento, String query) throws Exception {
        try {
            this.abrirConexion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(session);
            return usuarioDAO.autoCompletaUsuario(establecimiento, query);
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarUsuario(Establecimiento establecimiento, Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(session);
            int registrosActualizados = usuarioDAO.actualizarUsuario(establecimiento, usuario);
            if (registrosActualizados == 0) {
                usuario.setActivo(true);
                usuarioDAO.insertarUsuario(establecimiento, usuario);
            }
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Roles> cargarRoles(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(session);
            return usuarioDAO.cargarRoles(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarEstablecimientosUsuarios(Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(this.session);
//            usuarioDAO.eliminarRolesUsuarios(usuario);
//            usuarioDAO.eliminarEstablecimientosUsuarios(usuario);
//            for (Establecimiento e : usuario.getListaEstablecimientos()) {
////                if (!usuarioDAO.existeEstablecimientoUsuarios(e, usuario)) {
//                usuarioDAO.agregarEstablecimientosUsuarios(e, usuario);
////                }
//            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarRolUsuarios(Establecimiento establecimiento, Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(this.session);

//            for (Establecimiento e : usuario.getListaEstablecimientos()) {
//                usuarioDAO.eliminarRolUsuarios(e, usuario);
//                usuarioDAO.agregarRolUsuarios(e, usuario);
//            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public boolean existeDocumentoUsuario(Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(this.session);
            return usuarioDAO.existeDocumentoUsuario(usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public void guardarUsuario(Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(usuario);
            for (Roles r : usuario.getRelUsuariosRolesSet()) {
                this.session.saveOrUpdate(r);
            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuarios validarUsuario(Usuarios usuario) throws Exception {
        if (usuario == null || usuario.getUsuariosPK() == null || usuario.getUsuariosPK().getDocumentoUsuario() == null
                || usuario.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("")) {
            throw new Exception("Consulta el usuario a modificar.", UtilLog.TW_VALIDACION);
        }
        if (usuario.getRoles() == null || usuario.getRoles() == null || usuario.getRoles().getRolesPK() == null || usuario.getRoles().getRolesPK().getCodigoRol() == 0) {
            throw new Exception("Indica el rol del usuario.", UtilLog.TW_VALIDACION);
        }
        return usuario;
    }

    public Usuarios validarUsuarioNuevo(Usuarios usuario) throws Exception {
        if (usuario == null || usuario.getUsuariosPK() == null || usuario.getUsuariosPK().getCodDocumento() == 0
                || usuario.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("")
                || usuario.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("0")) {
            throw new Exception("Ingresa el documento del usuario a crear", UtilLog.TW_VALIDACION);
        }
        if (usuario.getUsuario() == null || usuario.getUsuario().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el usuario a crear", UtilLog.TW_VALIDACION);
        }
        if (usuario.getNombre() == null || usuario.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del usuario", UtilLog.TW_VALIDACION);
        }
        if (usuario.getApellido() == null || usuario.getApellido().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el apellido del usuario", UtilLog.TW_VALIDACION);
        }
        if (usuario.getCorreo() == null
                || usuario.getCorreo().equalsIgnoreCase("")
                || !UtilCorreo.validarCorreo(usuario.getCorreo())) {
            throw new Exception("Ingresa un correo valido", UtilLog.TW_VALIDACION);
        }
        if (usuario.getRoles() == null || usuario.getRoles().getRolesPK() == null
                || usuario.getRoles().getRolesPK().getCodigoRol() == 0 || usuario.getRoles().getNombre() == null || usuario.getRoles().getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa un rol de la lista", UtilLog.TW_VALIDACION);
        }

        usuario.setNombre(usuario.getNombre().trim().toUpperCase());
        usuario.setApellido(usuario.getApellido().trim().toUpperCase());
        usuario.setUsuario(usuario.getUsuario().trim().toUpperCase());
        usuario.setActivo(Boolean.TRUE);
        usuario.setFechaIngreso(new Date());
        usuario.setClave(UtilTexto.obtenerMD5(usuario.getClave()));
        usuario.setCorreo(usuario.getCorreo().trim());
        return usuario;
    }

    public boolean existeRelUsuariosEstablecimiento(Institucion institucion, Usuarios usuarios) throws Exception {
        try {
            this.abrirConexion();
            UsuariosDAO usuarioDAO = new UsuariosDAO(this.session);
            return usuarioDAO.existeRelUsuariosEstablecimiento(institucion, usuarios);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarUsuarioHistoriaClinica(Usuarios usuario) throws Exception {
        if (usuario == null || usuario.getRecurso() == null || usuario.getRecurso().getRecursoPK() == null
                || usuario.getRecurso().getRecursoPK().getCodRecurso() == null || usuario.getRecurso().getRecursoPK().getCodRecurso() == 0) {
            throw new Exception("El usuario logueado no tiene un recurso asociado o el recurso no esta asignado al establecimiento, no se puede continuar.", UtilLog.TW_VALIDACION);
        }
    }
}
