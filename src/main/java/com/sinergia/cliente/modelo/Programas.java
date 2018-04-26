/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Hibernate;

/**
 *
 * @author juliano
 */
public class Programas implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    protected ProgramasPK programasPK;
    private Set<ProgramasConfiguracion> programasConfiguracionSet = null;
    private Clientes clientes = new Clientes();
    private Entidades entidades = new Entidades();
    private Subcuentas subcuentas = new Subcuentas();
    private TiposContrato tiposContrato = new TiposContrato();
    private String nombre;
    private Institucion institucion;

    private ProgramasConfiguracion programasConfiguracion = new ProgramasConfiguracion();

    public Programas() {
    }

    public Programas(ProgramasPK programasPK) {
        this.programasPK = programasPK;
    }

    public Programas(ProgramasPK programasPK, Clientes clientes, Entidades entidades, Subcuentas subcuentas, TiposContrato tiposContrato) {
        this.programasPK = programasPK;
        this.clientes = clientes;
        this.entidades = entidades;
        this.subcuentas = subcuentas;
        this.tiposContrato = tiposContrato;
    }

    public Programas(int codInstitucion, int codEstablecimiento, Long codPrograma, int codEntidad, int codContrato, int codCliente, int codSubcuenta) {
        this.programasPK = new ProgramasPK(codInstitucion, codPrograma, codEntidad, codContrato, codCliente, codSubcuenta);
    }

    public ProgramasPK getProgramasPK() {
        return programasPK;
    }

    public void setProgramasPK(ProgramasPK programasPK) {
        this.programasPK = programasPK;
    }

    @XmlTransient
    public Set<ProgramasConfiguracion> getProgramasConfiguracionSet() {
        return programasConfiguracionSet;
    }

    public void setProgramasConfiguracionSet(Set<ProgramasConfiguracion> programasConfiguracionSet) {
        this.programasConfiguracionSet = programasConfiguracionSet;
//        if (programasConfiguracionSet != null && Hibernate.isInitialized(programasConfiguracionSet)) {
//            for (ProgramasConfiguracion pc : programasConfiguracionSet) {
//                programasConfiguracion = pc;
//            }
//        }
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Entidades getEntidades() {
        return entidades;
    }

    public void setEntidades(Entidades entidades) {
        this.entidades = entidades;
    }

    public Subcuentas getSubcuentas() {
        return subcuentas;
    }

    public void setSubcuentas(Subcuentas subcuentas) {
        this.subcuentas = subcuentas;
    }

    public TiposContrato getTiposContrato() {
        return tiposContrato;
    }

    public void setTiposContrato(TiposContrato tiposContrato) {
        this.tiposContrato = tiposContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programasPK != null ? programasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programas)) {
            return false;
        }
        Programas other = (Programas) object;
        if ((this.programasPK == null && other.programasPK != null) || (this.programasPK != null && !this.programasPK.equals(other.programasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.Programas[ programasPK=" + programasPK + " ]";
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = null;
        clone = super.clone();
        return clone;
    }

    /**
     * @return the programasConfiguracion
     */
    public ProgramasConfiguracion getProgramasConfiguracion() {
        if (programasConfiguracionSet != null && Hibernate.isInitialized(programasConfiguracionSet)) {
            for (ProgramasConfiguracion pc : programasConfiguracionSet) {
                programasConfiguracion = pc;
            }
        }
        return programasConfiguracion;
    }

    /**
     * @param programasConfiguracion the programasConfiguracion to set
     */
    public void setProgramasConfiguracion(ProgramasConfiguracion programasConfiguracion) {
        this.programasConfiguracion = programasConfiguracion;
    }

}
