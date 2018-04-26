/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class ProgramasConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ProgramasConfiguracionPK programasConfiguracionPK;

    private boolean activo;
    private String nombre;
    private Integer codLista;

//    private Set<ParametrosConfiguracion> parametrosConfiguracionSet;
    private ProgramasConfiguracionParametros programasConfiguracionParametros = new ProgramasConfiguracionParametros(new ProgramasConfiguracionParametrosPK());
    private Set<ProgramasConfiguracionParametros> programasConfiguracionParametrosSet;

    private Set<Programas> programasSet;

    private ListasPrecios listasPrecios;
    private Institucion institucion;

    public ProgramasConfiguracion() {
    }

    public ProgramasConfiguracion(ListasPrecios listasPrecios) {
        this.listasPrecios = listasPrecios;
    }
    

    public ProgramasConfiguracion(ProgramasConfiguracionPK programasConfiguracionPK) {
        this.programasConfiguracionPK = programasConfiguracionPK;
    }

    public ProgramasConfiguracion(int codInstitucion, Long codConfiguracion) {
        this.programasConfiguracionPK = new ProgramasConfiguracionPK(codInstitucion, codConfiguracion);
    }

    public ProgramasConfiguracionPK getProgramasConfiguracionPK() {
        return programasConfiguracionPK;
    }

    public void setProgramasConfiguracionPK(ProgramasConfiguracionPK programasConfiguracionPK) {
        this.programasConfiguracionPK = programasConfiguracionPK;
    }

    @XmlTransient
    public Set<Programas> getProgramasSet() {
        return programasSet;
    }

    public void setProgramasSet(Set<Programas> programasSet) {
        this.programasSet = programasSet;
    }

    public ListasPrecios getListasPrecios() {
        return listasPrecios;
    }

    public void setListasPrecios(ListasPrecios listasPrecios) {
        this.listasPrecios = listasPrecios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programasConfiguracionPK != null ? programasConfiguracionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramasConfiguracion)) {
            return false;
        }
        ProgramasConfiguracion other = (ProgramasConfiguracion) object;
        if ((this.programasConfiguracionPK == null && other.programasConfiguracionPK != null) || (this.programasConfiguracionPK != null && !this.programasConfiguracionPK.equals(other.programasConfiguracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.ProgramasConfiguracion[ programasConfiguracionPK=" + programasConfiguracionPK + " ]";
    }

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

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the codLista
     */
    public Integer getCodLista() {
        return codLista;
    }

    /**
     * @param codLista the codLista to set
     */
    public void setCodLista(Integer codLista) {
        this.codLista = codLista;
    }

    /**
     * @return the programasConfiguracionParametrosSet
     */
    @XmlTransient
    public Set<ProgramasConfiguracionParametros> getProgramasConfiguracionParametrosSet() {
        return programasConfiguracionParametrosSet;
    }

    /**
     * @param programasConfiguracionParametrosSet the
     * programasConfiguracionParametrosSet to set
     */
    public void setProgramasConfiguracionParametrosSet(Set<ProgramasConfiguracionParametros> programasConfiguracionParametrosSet) {
        this.programasConfiguracionParametrosSet = programasConfiguracionParametrosSet;
    }

    /**
     * @return the programasConfiguracionParametros
     */
    public ProgramasConfiguracionParametros getProgramasConfiguracionParametros() {
        return programasConfiguracionParametros;
    }

    /**
     * @param programasConfiguracionParametros the
     * programasConfiguracionParametros to set
     */
    public void setProgramasConfiguracionParametros(ProgramasConfiguracionParametros programasConfiguracionParametros) {
        this.programasConfiguracionParametros = programasConfiguracionParametros;
    }

}
