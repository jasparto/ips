/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.modelo;

import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juliano
 */
@ManagedBean
@SessionScoped

public class Usuarios implements Serializable {

    protected UsuariosPK usuariosPK;
    private String nombre;
    private String apellido;
    private String usuario;
    private String clave;
    private Boolean activo;
    private String correo;

    private Date fechaIngreso;
    private Date fechaRetiro;

    private Set<Establecimiento> relUsuariosEstablecimientoSet = new HashSet<>();
    private Set<Roles> relUsuariosRolesSet = new HashSet<>();
    private Set<Recurso> recursoSet = new HashSet<>();
    private Set<Institucion> institucionSet;

    private Recurso recurso;
    private Roles roles;

    public Usuarios() {
        this.usuariosPK = new UsuariosPK();
    }

    public Usuarios(UsuariosPK usuariosPK) {
        this.usuariosPK = usuariosPK;
    }

    public Usuarios(short codDocumento, String documentoUsuario) {
        this.usuariosPK = new UsuariosPK(codDocumento, documentoUsuario);
    }

    public UsuariosPK getUsuariosPK() {
        return usuariosPK;
    }

    public void setUsuariosPK(UsuariosPK usuariosPK) {
        this.usuariosPK = usuariosPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariosPK != null ? usuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuariosPK == null && other.usuariosPK != null) || (this.usuariosPK != null && !this.usuariosPK.equals(other.usuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.Usuarios[ usuariosPK=" + usuariosPK + " ]";
    }

//    /**
//     * @return the relUsuariosEstablecimientoSet
//     */
//    public Set<Establecimiento> getRelUsuariosEstablecimientoSet() {
//        return relUsuariosEstablecimientoSet;
//    }
    /**
     * @return the relUsuariosEstablecimientoSet
     */
    public Collection<Establecimiento> getRelUsuariosEstablecimientoSet() {
        return relUsuariosEstablecimientoSet;
    }

    /**
     * @param relUsuariosEstablecimientoSet the relUsuariosEstablecimientoSet to
     * set
     */
    public void setRelUsuariosEstablecimientoSet(Set<Establecimiento> relUsuariosEstablecimientoSet) {
        this.relUsuariosEstablecimientoSet = relUsuariosEstablecimientoSet;
    }

    /**
     * @return the relUsuariosRolesSet
     */
    public Set<Roles> getRelUsuariosRolesSet() {
        return relUsuariosRolesSet;
    }

    /**
     * @param relUsuariosRolesSet the relUsuariosRolesSet to set
     */
    public void setRelUsuariosRolesSet(Set<Roles> relUsuariosRolesSet) {
        this.relUsuariosRolesSet = relUsuariosRolesSet;
    }

    /**
     * @return the recursoSet
     */
    public Set<Recurso> getRecursoSet() {
        return recursoSet;
    }

    /**
     * @param recursoSet the recursoSet to set
     */
    public void setRecursoSet(Set<Recurso> recursoSet) {
        this.recursoSet = recursoSet;
    }

    /**
     * @return the recurso
     */
    public Recurso getRecurso() {
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    /**
     * @return the roles
     */
    public Roles getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    /**
     * @return the institucionSet
     */
     public Collection<Institucion> getInstitucionSetToList() {
        Collection<Institucion> institucionList = new ArrayList<>();
        for (Institucion i : institucionSet) {
            institucionList.add(i);
        }
        return institucionList;
    }

    /**
     * @return the institucionSet
     */
    public Set<Institucion> getInstitucionSet() {
        return institucionSet;
    }

    /**
     * @param institucionSet the institucionSet to set
     */
    public void setInstitucionSet(Set<Institucion> institucionSet) {
        this.institucionSet = institucionSet;
    }

}
