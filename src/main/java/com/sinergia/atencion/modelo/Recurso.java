/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.modelo.Usuarios;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class Recurso implements Serializable, Cloneable {

    protected RecursoPK recursoPK;

//    private int codInstitucion;
    private String nombre;
    private TipoRecurso tipoRecurso;
//    private Set<TipoRecurso> tipoRecursoSet;
    private Long codTipoRecurso;
    private Set<Citas> citasSet;
    private Set<Usuarios> usuariosSet;
    private Set<Establecimiento> establecimientoSet;
    private Institucion institucion;

    public Recurso() {
        tipoRecurso = new TipoRecurso();
    }

    public Recurso(RecursoPK recursoPK) {
        this.recursoPK = recursoPK;
    }

    public Recurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Recurso(RecursoPK recursoPK, String nombre) {
        this.recursoPK = recursoPK;
        this.nombre = nombre;
    }

//    public Recurso(int codRecurso) {
//        this.recursoPK = new RecursoPK(codInstitucion, codRecurso);
//    }
    public Recurso(Long codRecurso) {
        this.recursoPK = new RecursoPK(codRecurso);
    }

    public RecursoPK getRecursoPK() {
        return recursoPK;
    }

    public void setRecursoPK(RecursoPK recursoPK) throws CloneNotSupportedException {
        this.recursoPK = (RecursoPK) recursoPK.clone();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recursoPK != null ? recursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.recursoPK == null && other.recursoPK != null) || (this.recursoPK != null && !this.recursoPK.equals(other.recursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.Recurso[ recursoPK=" + recursoPK + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            // No deberia ocurrir
        }
        return clone;
    }

    /**
     * @return the codTipoRecurso
     */
    public Long getCodTipoRecurso() {
        return codTipoRecurso;
    }

    /**
     * @param codTipoRecurso the codTipoRecurso to set
     */
    public void setCodTipoRecurso(Long codTipoRecurso) {
        this.codTipoRecurso = codTipoRecurso;
    }

//    /**
//     * @return the tipoRecursoSet
//     */
//    public Set<TipoRecurso> getTipoRecursoSet() {
//        return tipoRecursoSet;
//    }
//
//    /**
//     * @param tipoRecursoSet the tipoRecursoSet to set
//     */
//    public void setTipoRecursoSet(Set<TipoRecurso> tipoRecursoSet) {
//        this.tipoRecursoSet = tipoRecursoSet;
//    }
    /**
     * @return the citasSet
     */
    public Set<Citas> getCitasSet() {
        return citasSet;
    }

    /**
     * @param citasSet the citasSet to set
     */
    public void setCitasSet(Set<Citas> citasSet) {
        this.citasSet = citasSet;
    }

    /**
     * @return the usuariosSet
     */
    public Set<Usuarios> getUsuariosSet() {
        return usuariosSet;
    }

    /**
     * @param usuariosSet the usuariosSet to set
     */
    public void setUsuariosSet(Set<Usuarios> usuariosSet) {
        this.usuariosSet = usuariosSet;
    }

    /**
     * @return the establecimientoSet
     */
    public Set<Establecimiento> getEstablecimientoSet() {
        return establecimientoSet;
    }

    /**
     * @param establecimientoSet the establecimientoSet to set
     */
    public void setEstablecimientoSet(Set<Establecimiento> establecimientoSet) {
        this.establecimientoSet = establecimientoSet;
    }
//
//    /**
//     * @return the codInstitucion
//     */
//    public int getCodInstitucion() {
//        return codInstitucion;
//    }
//
//    /**
//     * @param codInstitucion the codInstitucion to set
//     */
//    public void setCodInstitucion(int codInstitucion) {
//        this.codInstitucion = codInstitucion;
//        recursoPK.setCodInstitucion(codInstitucion);
//    }

    /**
     * @return the institucion
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

}
