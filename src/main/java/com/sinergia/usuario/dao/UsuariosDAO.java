/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.dao;

import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.modelo.Roles;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.usuario.vista.UIUsuario;
import com.sinergia.utilidades.modelo.UtilLog;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Julian
 */
public class UsuariosDAO {

    private Session session = null;

    public UsuariosDAO(Session session) {
        this.session = session;
    }

    public boolean validarUsuario(String usuario, String clave) throws Exception {
        String claveIngresada;
        String claveActual;
        boolean usuarioValido = false;
        return usuarioValido;
//        ResultSet rs = null;
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            String sql = "SELECT documento_usuario, nombre, apellido, usuario, clave, activo, correo"
//                    + " FROM usuarios"
//                    + " WHERE usuario='" + usuario.toUpperCase() + "'";
//            rs = consulta.ejecutar(sql);
//            if (rs.next()) {
//                claveActual = rs.getString("clave");
//            } else {
//                throw new Exception("usuarioNoExiste", UtilLog.TW_VALIDACION);
//            }
//            sql = "SELECT md5('" + clave + "') AS claveIngresada";
//            rs = consulta.ejecutar(sql);
//            rs.next();
//            claveIngresada = rs.getString("claveIngresada");
//            if (claveActual.equalsIgnoreCase(claveIngresada)) {
//                usuarioValido = true;
//            } else {
//                throw new Exception("usuarioClaveIncorrecta", UtilLog.TW_VALIDACION);
//            }
//
//            return usuarioValido;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public ArrayList<Usuarios> cargarListaUsuarios() throws Exception {
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        ResultSet rs = null;
        return listaUsuarios;
//        Consulta consulta = null;
//
//        try {
//            consulta = new Consulta(this.conexion);
//            String sql = "SELECT documento_usuario, nombre, apellido, usuario, clave, activo,"
//                    + " correo"
//                    + " FROM usuarios";
//            rs = consulta.ejecutar(sql);
//            while (rs.next()) {
//                Usuarios u = new Usuarios();
//                u.setDocumentoUsuario(rs.getString("documento_usuario"));
//                u.setNombre(rs.getString("nombre"));
//                u.setApellido(rs.getString("apellido"));
//                u.setUsuario(rs.getString("usuario"));
//                u.setClave(rs.getString("clave"));
//                u.setActivo(rs.getBoolean("activo"));
//                u.setCorreo(rs.getString("correo"));
//                listaUsuarios.add(u);
//            }
//            return listaUsuarios;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public Usuarios cargarDatosUsuario(Usuarios usuario, final String filtro) throws SQLException {
        Query query;
        switch (filtro) {
            case UIUsuario.FILTRO_USUARIO:
                query = session.createQuery(
                        "FROM Usuarios U"
                        + " LEFT JOIN FETCH U.relUsuariosEstablecimientoSet E"
                        + " LEFT JOIN FETCH E.institucion"
                        //                        + " LEFT JOIN FETCH E.facturasConfiguracionSet EFC WITH (current_date BETWEEN EFC.fechaInicioVigencia AND EFC.fechaFinVigencia)"
                        + " LEFT JOIN FETCH E.establecimientoParametrosSet EP"
                        + " LEFT JOIN FETCH EP.parametros"
                        + " WHERE U.usuario=?"
                ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                query.setParameter(0, usuario.getUsuario().toUpperCase());
                return (Usuarios) query.uniqueResult();
            case UIUsuario.FILTRO_DOCUMENTO:
                query = session.createQuery(
                        "FROM Usuarios U"
                        + " JOIN FETCH U.usuariosPK PK"
                        + " WHERE PK.codDocumento=? AND PK.documentoUsuario=?"
                ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                query.setParameter(0, usuario.getUsuariosPK().getCodDocumento());
                query.setParameter(1, usuario.getUsuariosPK().getDocumentoUsuario());
                return (Usuarios) query.uniqueResult();
            case UIUsuario.FILTRO_CORREO:
                query = session.createQuery(
                        "FROM Usuarios U"
                        + " JOIN FETCH U.usuariosPK PK"
                        + " WHERE U.correo=?"
                ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                query.setParameter(0, usuario.getCorreo());
                return (Usuarios) query.uniqueResult();
        }
        return usuario;
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "SELECT documento_usuario, nombre, apellido, usuario, clave, activo, correo"
//                    + " FROM usuarios"
//            );
//            switch (filtro) {
//                case Usuarios.FILTRO_USUARIO:
//                    sql.append(" WHERE usuario='").append(usuario.getUsuario().toUpperCase()).append("'");
//                    break;
//                case Usuarios.FILTRO_DOCUMENTO:
//                    sql.append(" WHERE documento_usuario='").append(usuario.getDocumentoUsuario()).append("'");
//                    break;
//                default:
//                    throw new SQLException("Filtro Usuarios Invalido.");
//            }
//
//            rs = consulta.ejecutar(sql);
//            if (rs.next()) {
//                usuario.setDocumentoUsuario(rs.getString("documento_usuario"));
//                usuario.setNombre(rs.getString("nombre"));
//                usuario.setApellido(rs.getString("apellido"));
//                usuario.setActivo(rs.getBoolean("activo"));
//                usuario.setCorreo(rs.getString("correo"));
//                usuario.setUsuario(rs.getString("usuario"));
//                usuario.setClave(rs.getString("clave"));
//                usuario.setClaveMd5(rs.getString("clave"));
////                Establecimiento establecimientoUsuario = new Establecimiento(rs.getInt("codigo_establecimiento"));
////                usuario.setEstablecimiento(establecimientoUsuario);
//            }
//            return usuario;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public Usuarios cargarRolUsuario(Establecimiento establecimiento, Usuarios usuario) throws SQLException {
        ResultSet rs = null;
        return usuario;
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "SELECT RE.codigo_rol, RE.nombre"
//                    + " FROM roles_usuarios RU "
//                    + " JOIN roles RE USING (codigo_rol)"
//                    + " WHERE RU.codigo_establecimiento=" + establecimiento.getCodigo() + " AND documento_usuario = '" + usuario.getDocumentoUsuario() + "'"
//            );
//            rs = consulta.ejecutar(sql);
//            Rol rol = new Rol();
//            if (rs.next()) {
//                rol.setCodigoRol(rs.getInt("codigo_rol"));
//                rol.setNombre(rs.getString("nombre"));
//            }
//            usuario.setRol(rol);
//            return usuario;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public Collection<? extends String> autoCompletaUsuario(Establecimiento establecimiento, String q) throws SQLException {
//        Query query = session.createSQLQuery("SELECT"
//                + " usuario"
//                + " FROM usuario.usuarios"
//                + " WHERE usuario LIKE '%" + q.toUpperCase() + "%'"
//        );
        Query query = session.createSQLQuery("SELECT"
                + " nombre ||' '|| apellido || ' - '||usuario"
                + " FROM usuario.usuarios"
                + " WHERE nombre || apellido ||usuario LIKE '%" + q.toUpperCase() + "%'"
        );
        return query.list();
    }

    public int actualizarUsuario(Establecimiento establecimiento, Usuarios usuario) throws SQLException {
        return 0;
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "UPDATE usuarios"
//                    + " SET documento_usuario='" + usuario.getDocumentoUsuario().trim() + "',"
//                    + " nombre='" + usuario.getNombre().toUpperCase() + "', apellido='" + usuario.getApellido().toUpperCase() + "',"
//                    + " usuario='" + usuario.getUsuario().toUpperCase() + "',"
//                    + " clave=" + (usuario.getClave().equalsIgnoreCase(usuario.getClaveMd5()) ? "'" + usuario.getClaveMd5() + "'" : "md5('" + usuario.getClave() + "')") + ","
//                    + " activo=" + usuario.isActivo() + ", correo='" + usuario.getCorreo() + "', "
//                    + " fecha_retiro=" + UtilFecha.formatoFecha(usuario.getFechaRetiro(), null, UtilFecha.PATRON_FECHA_YYYYMMDD, UtilFecha.CARACTER_COMILLA)
//                    + " WHERE documento_usuario='" + usuario.getDocumentoUsuario() + "'"
//            );
//            return consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public void insertarUsuario(Establecimiento establecimiento, Usuarios usuario) throws SQLException {
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "INSERT INTO usuarios("
//                    + " documento_usuario, nombre, apellido, "
//                    + " usuario, clave, activo, correo, fecha_ingreso, fecha_retiro)"
//                    + " VALUES ('" + usuario.getDocumentoUsuario().trim() + "', '" + usuario.getNombre().toUpperCase() + "',"
//                    + "  '" + usuario.getApellido().toUpperCase() + "', '" + usuario.getUsuario().toUpperCase() + "',"
//                    + " MD5('" + usuario.getClave() + "')," + usuario.isActivo() + ",'" + usuario.getCorreo() + "',"
//                    + " current_date," + UtilFecha.formatoFecha(usuario.getFechaRetiro(), null, UtilFecha.PATRON_FECHA_YYYYMMDD, UtilFecha.CARACTER_COMILLA) + ")"
//            );
//            consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public List<Roles> cargarRoles(Institucion institucion) throws SQLException {
        Query query = session.createQuery("From Roles R"
                + " JOIN FETCH R.rolesPK PK"
                + " WHERE PK.codInstitucion=?");
        query.setParameter(0, institucion.getCodInstitucion());
        return query.list();
    }

    public void eliminarEstablecimientosUsuario(Usuarios usuario) throws SQLException {
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "DELETE FROM rel_usuarios_establecimiento"
//                    + " WHERE documento_usuario='" + usuario.getDocumentoUsuario() + "'"
//            );
//            consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public void agregarEstablecimientosUsuario(Establecimiento establecimiento, Usuarios usuario) throws SQLException {
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "INSERT INTO rel_usuarios_establecimiento("
//                    + " codigo_establecimiento, documento_usuario)"
//                    + " VALUES (" + establecimiento.getCodigo() + ", '" + usuario.getDocumentoUsuario() + "')"
//            );
//            consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public void eliminarRolesUsuario(Usuarios usuario) throws SQLException {
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "DELETE FROM roles_usuarios"
//                    + " WHERE documento_usuario='" + usuario.getDocumentoUsuario() + "'"
//            );
//            consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public void eliminarRolUsuario(Establecimiento establecimiento, Usuarios usuario) throws SQLException {
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "DELETE FROM roles_usuarios"
//                    + " WHERE codigo_establecimiento=" + establecimiento.getCodigo() + " AND documento_usuario='" + usuario.getDocumentoUsuario() + "'"
//            );
//            consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public void agregarRolUsuario(Establecimiento establecimiento, Usuarios usuario) throws SQLException {
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "INSERT INTO roles_usuarios("
//                    + " codigo_establecimiento, documento_usuario, codigo_rol)"
//                    + " VALUES (" + establecimiento.getCodigo() + ", '" + usuario.getDocumentoUsuario() + "', " + usuario.getRol().getCodigoRol() + ")"
//            );
//            consulta.actualizar(sql);
//        } finally {
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public List<Usuarios> cargarListaUsuariosEstablecimiento(Establecimiento establecimiento) throws SQLException {
//        ResultSet rs = null;
//        Consulta consulta = null;
        List<Usuarios> listaUsuarios = new ArrayList<>();
        return listaUsuarios;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "SELECT RUE.codigo_establecimiento, U.documento_usuario, nombre, apellido,"
//                    + " usuario, clave, activo, correo, fecha_ingreso, fecha_retiro"
//                    + " FROM usuarios U"
//                    + " JOIN rel_usuarios_establecimiento RUE USING (documento_usuario)"
//                    + " WHERE RUE.codigo_establecimiento=" + establecimiento.getCodigo()
//            );
//            rs = consulta.ejecutar(sql);
//            while (rs.next()) {
//                Usuarios u = new Usuarios();
//                u.setDocumentoUsuario(rs.getString("documento_usuario"));
//                u.setNombre(rs.getString("nombre"));
//                u.setApellido(rs.getString("apellido"));
//                u.setUsuario(rs.getString("usuario"));
//                u.setClave(rs.getString("clave"));
//                u.setActivo(rs.getBoolean("activo"));
//                u.setCorreo(rs.getString("correo"));
//                listaUsuarios.add(u);
//            }
//            return listaUsuarios;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public boolean existeDocumentoUsuario(Usuarios usuario) throws SQLException {
        ResultSet rs = null;
        return false;
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "SELECT count(1) > 0 as existe"
//                    + " FROM usuarios"
//                    + " WHERE documento_usuario='" + usuario.getDocumentoUsuario() + "'"
//            );
//            rs = consulta.ejecutar(sql);
//            rs.next();
//            return rs.getBoolean("existe");
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public boolean existeEstablecimientoUsuario(Establecimiento e, Usuarios usuario) throws SQLException {
        return false;
//        ResultSet rs = null;
//        Consulta consulta = null;
//        try {
//            consulta = new Consulta(this.conexion);
//            StringBuilder sql = new StringBuilder(
//                    "SELECT COUNT(1)>0 AS existe"
//                    + " FROM rel_usuarios_establecimiento"
//                    + " WHERE codigo_establecimiento =" + e.getCodigo() + " AND documento_usuario = '" + usuario.getDocumentoUsuario() + "'"
//            );
//            rs = consulta.ejecutar(sql);
//            rs.next();
//            return rs.getBoolean("existe");
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (consulta != null) {
//                consulta.desconectar();
//            }
//        }
    }

    public String getMd5(String clave) {
        Query query = session.createSQLQuery("SELECT MD5('" + clave + "')");
        return (query.uniqueResult()).toString();
    }

    public Usuarios cargarDatosUsuario(Establecimiento establecimiento, Usuarios usuario) {
        Query query = session.createQuery(
                "FROM Usuarios U"
                + " LEFT JOIN FETCH U.relUsuariosEstablecimientoSet RUE"
                + " LEFT JOIN FETCH RUE.institucion"
                + " LEFT JOIN FETCH U.relUsuariosRolesSet RUR"
                + " LEFT JOIN FETCH U.recursoSet RS"
                + " LEFT JOIN FETCH RS.tipoRecurso TR"
                + " LEFT JOIN FETCH U.institucionSet I"
                + " LEFT JOIN FETCH I.tipoRecursosSet ITR"
                + " WHERE U.usuario=?"
        ).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter(0, usuario.getUsuario().toUpperCase());
        return (Usuarios) query.uniqueResult();

    }

    public boolean existeRelUsuariosEstablecimiento(Institucion institucion, Usuarios usuarios) {
        Query query = session.createSQLQuery(
                "SELECT COUNT(1)>0 AS existe"
                + " FROM usuario.rel_usuarios_establecimiento"
                + " WHERE cod_institucion=? AND cod_documento=? AND documento_usuario=?"
        );
        query.setParameter(0, institucion.getCodInstitucion());
        query.setParameter(1, usuarios.getUsuariosPK().getCodDocumento());
        query.setParameter(2, usuarios.getUsuariosPK().getDocumentoUsuario());
        return (boolean) query.uniqueResult();
    }
}
