/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.atencion.modelo.ServiciosPK;
import com.sinergia.atencion.modelo.TipoRecurso;
import com.sinergia.cliente.modelo.Clientes;
import com.sinergia.cliente.modelo.Entidades;
import com.sinergia.cliente.modelo.ListasPrecios;
import com.sinergia.cliente.modelo.ParametrosConfiguracion;
import com.sinergia.cliente.modelo.Programas;
import com.sinergia.cliente.modelo.ProgramasConfiguracion;
import com.sinergia.cliente.modelo.ServiciosLista;
import com.sinergia.cliente.modelo.ServiciosListaPK;
import com.sinergia.cliente.modelo.Subcuentas;
import com.sinergia.cliente.modelo.TiposContrato;
import com.sinergia.usuario.modelo.Menus;
import com.sinergia.usuario.modelo.Roles;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.primefaces.model.DualListModel;

/**
 *
 * @author juliano
 */
public class Institucion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public Integer codInstitucion;
    private String nombre;
    private String nit;
    private String direccion;
    private String razonSocial;
    private String telefono;
    private Set<Recurso> recursoSet = new HashSet<>();
    private Set<TipoRecurso> tipoRecursosSet = new HashSet<>();
    private Set<InstitucionParametros> institucionParametrosSet;
    private Set<Usuarios> usuariosSet;
    private Set<Establecimiento> establecimientosSet;
    private InstitucionParametros institucionParametros;
    private Recurso recurso = new Recurso();
    private TipoRecurso tipoRecurso = new TipoRecurso();
    private ListasPrecios listasPrecios;
    private Menus menus = new Menus();
    private Roles roles = new Roles();
    private Roles rolesCrear = new Roles();

    private List<Establecimiento> establecimientosListDisponibles = new ArrayList<>();
    private List<Establecimiento> establecimientosListAsignados = new ArrayList<>();
    private DualListModel<Establecimiento> establecimientoDualList = new DualListModel<>(establecimientosListDisponibles, establecimientosListAsignados);

    private Set<Clientes> clientesSet;
    private Set<Entidades> entidadesSet;
    private Set<Subcuentas> subcuentasSet;
    private Set<TiposContrato> tiposContratoSet;
    private Set<ListasPrecios> listasPreciosSet;

    private Set<Programas> programasSet;
    private Set<ProgramasConfiguracion> programasConfiguracionSet;
    private Set<ParametrosConfiguracion> parametrosConfiguracionSet;
    private Set<Menus> menusSet;
    private Set<Roles> rolesSet;

    public Institucion() {
        institucionParametros = new InstitucionParametros();
        establecimientoDualList = new DualListModel<>(establecimientosListDisponibles, establecimientosListAsignados);
        clientesSet = new HashSet<>();
    }

    public Institucion(Integer codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public Institucion(Integer codInstitucion, String nombre) {
        this.codInstitucion = codInstitucion;
        this.nombre = nombre;
    }

    public Integer getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(Integer codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codInstitucion != null ? codInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.codInstitucion == null && other.codInstitucion != null) || (this.codInstitucion != null && !this.codInstitucion.equals(other.codInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.publico.modelo.Institucion[ codInstitucion=" + codInstitucion + " ]";
    }

    /**
     * @return the tipoRecursosSet
     */
    public Collection<TipoRecurso> getTipoRecursosSetToList() {
        Collection<TipoRecurso> tipoRecursoList = new ArrayList<>();
        try {
            for (TipoRecurso tr : tipoRecursosSet) {
                if (tr.getClase() != null && tr.getClase().equalsIgnoreCase(TipoRecurso.CLASE_FISICO)) {
                    tipoRecursoList.add(tr);
                }
            }
        } catch (Exception e) {
            UtilLog.generarLog(this.getClass(), e);
        }
        return tipoRecursoList;
    }

    /**
     * @return the tipoRecursosSet
     */
    public Collection<TipoRecurso> getTipoRecursosHumanoSetToList() {
        Collection<TipoRecurso> tipoRecursoList = new ArrayList<>();
        try {
            for (TipoRecurso tr : tipoRecursosSet) {
                if (tr.getClase() != null && tr.getClase().equalsIgnoreCase(TipoRecurso.CLASE_HUMANO)) {
                    tipoRecursoList.add(tr);
                }
            }
        } catch (Exception e) {
            UtilLog.generarLog(this.getClass(), e);
        }
        return tipoRecursoList;
    }

    /**
     * @return the tipoRecursosSet
     */
    public Set<TipoRecurso> getTipoRecursosSet() {
        return tipoRecursosSet;
    }

    /**
     * @param tipoRecursosSet the tipoRecursosSet to set
     */
    public void setTipoRecursosSet(Set<TipoRecurso> tipoRecursosSet) {
        this.tipoRecursosSet = tipoRecursosSet;
    }

    /**
     * @return the tipoRecurso
     */
    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    /**
     * @param tipoRecurso the tipoRecurso to set
     */
    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    /**
     * @return the institucionParametrosSet
     */
    public Set<InstitucionParametros> getInstitucionParametrosSet() {
        return institucionParametrosSet;
    }

    /**
     * @param institucionParametrosSet the institucionParametrosSet to set
     */
    public void setInstitucionParametrosSet(Set<InstitucionParametros> institucionParametrosSet) {
        this.institucionParametrosSet = institucionParametrosSet;
    }

    /**
     * @return the institucionParametros
     */
    public InstitucionParametros getInstitucionParametros() {
        return institucionParametros;
    }

    /**
     * @param institucionParametros the institucionParametros to set
     */
    public void setInstitucionParametros(InstitucionParametros institucionParametros) {
        this.institucionParametros = institucionParametros;
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
     * @return the establecimientoDualList
     */
    public DualListModel<Establecimiento> getEstablecimientoDualList() {
        return establecimientoDualList;
    }

    /**
     * @param establecimientoDualList the establecimientoDualList to set
     */
    public void setEstablecimientoDualList(DualListModel<Establecimiento> establecimientoDualList) {
        this.establecimientoDualList = establecimientoDualList;
    }

    /**
     * @return the establecimientosSet
     */
    public Set<Establecimiento> getEstablecimientosSet() {
        return establecimientosSet;
    }

    /**
     * @param establecimientosSet the establecimientosSet to set
     */
    public void setEstablecimientosSet(Set<Establecimiento> establecimientosSet) {
        this.establecimientosSet = establecimientosSet;
    }

    /**
     * @return the recursoSet
     */
    public List<Recurso> getRecursoSetToArray() {
        List<Recurso> recursoList = new ArrayList<>();
        for (Recurso r : recursoSet) {
            recursoList.add(r);
        }
        return recursoList;
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
     * @return the clientesSet
     */
    public List<Clientes> getClientesSetToList() {
        List<Clientes> clientesList = new ArrayList<>();
        for (Clientes c : clientesSet) {
            clientesList.add(c);
        }
        return clientesList;
    }

    /**
     * @return the clientesSet
     */
    public Set<Clientes> getClientesSet() {
        return clientesSet;
    }

    /**
     * @param clientesSet the clientesSet to set
     */
    public void setClientesSet(Set<Clientes> clientesSet) {
        this.clientesSet = clientesSet;
    }

    /**
     * @return the entidadesSet
     */
    public List<Entidades> getEntidadesSetToList() {
        List<Entidades> entidadesList = new ArrayList<>();
        for (Entidades e : entidadesSet) {
            entidadesList.add(e);
        }
        return entidadesList;
    }

    /**
     * @return the entidadesSet
     */
    public Set<Entidades> getEntidadesSet() {
        return entidadesSet;
    }

    /**
     * @param entidadesSet the entidadesSet to set
     */
    public void setEntidadesSet(Set<Entidades> entidadesSet) {
        this.entidadesSet = entidadesSet;
    }

    /**
     * @return the subcuentasSet
     */
    public List<Subcuentas> getSubcuentasSetToList() {
        List<Subcuentas> subcuentasList = new ArrayList<>();
        for (Subcuentas s : subcuentasSet) {
            subcuentasList.add(s);
        }
        return subcuentasList;
    }

    /**
     * @return the subcuentasSet
     */
    public Set<Subcuentas> getSubcuentasSet() {
        return subcuentasSet;
    }

    /**
     * @param subcuentasSet the subcuentasSet to set
     */
    public void setSubcuentasSet(Set<Subcuentas> subcuentasSet) {
        this.subcuentasSet = subcuentasSet;
    }

    /**
     * @return the tiposContratoSet
     */
    public List<TiposContrato> getTiposContratoSetToList() {
        List<TiposContrato> tiposContratoList = new ArrayList<>();
        for (TiposContrato t : tiposContratoSet) {
            tiposContratoList.add(t);
        }
        return tiposContratoList;
    }

    /**
     * @return the tiposContratoSet
     */
    public Set<TiposContrato> getTiposContratoSet() {
        return tiposContratoSet;
    }

    /**
     * @param tiposContratoSet the tiposContratoSet to set
     */
    public void setTiposContratoSet(Set<TiposContrato> tiposContratoSet) {
        this.tiposContratoSet = tiposContratoSet;
    }

    /**
     * @return the listasPreciosSet
     */
    public List<ListasPrecios> getListasPreciosSetToList() {
        List<ListasPrecios> listasPreciosList = new ArrayList<>();
        if (listasPreciosSet != null && Hibernate.isInitialized(listasPreciosSet)) {
            for (ListasPrecios lp : listasPreciosSet) {
                lp.setServiciosLista(new ServiciosLista(new ServiciosListaPK(), new Servicios(new ServiciosPK())));
                listasPreciosList.add(lp);
            }
        }
        return listasPreciosList;
    }

    /**
     * @return the listasPreciosSet
     */
    public Set<ListasPrecios> getListasPreciosSet() {
        return listasPreciosSet;
    }

    /**
     * @param listasPreciosSet the listasPreciosSet to set
     */
    public void setListasPreciosSet(Set<ListasPrecios> listasPreciosSet) {
        this.listasPreciosSet = listasPreciosSet;
    }

    /**
     * @return the programasSet
     */
    public List<Programas> getProgramasSetToList() {
        List<Programas> programasList = new ArrayList<>();
        if (Hibernate.isInitialized(programasSet) && programasSet != null) {
            for (Programas p : programasSet) {
                programasList.add(p);
            }
        }
        return programasList;
    }

    /**
     * @return the programasSet
     */
    public Set<Programas> getProgramasSet() {
        return programasSet;
    }

    /**
     * @param programasSet the programasSet to set
     */
    public void setProgramasSet(Set<Programas> programasSet) {
        this.programasSet = programasSet;
    }

    /**
     * @return the programasConfiguracionSet
     */
    public List<ProgramasConfiguracion> getProgramasConfiguracionSetToList() {
        List<ProgramasConfiguracion> programasConfiguracionList = new ArrayList<>();
        for (ProgramasConfiguracion pc : programasConfiguracionSet) {
            programasConfiguracionList.add(pc);
        }
        return programasConfiguracionList;
    }

    /**
     * @return the programasConfiguracionSet
     */
    public Set<ProgramasConfiguracion> getProgramasConfiguracionSet() {
        return programasConfiguracionSet;
    }

    /**
     * @param programasConfiguracionSet the programasConfiguracionSet to set
     */
    public void setProgramasConfiguracionSet(Set<ProgramasConfiguracion> programasConfiguracionSet) {
        this.programasConfiguracionSet = programasConfiguracionSet;
    }

    /**
     * @return the parametrosConfiguracionSet
     */
    public List<ParametrosConfiguracion> getParametrosConfiguracionSetToList() {
        List<ParametrosConfiguracion> parametrosConfiguracionList = new ArrayList<>();
        for (ParametrosConfiguracion pc : parametrosConfiguracionSet) {
            parametrosConfiguracionList.add(pc);
        }
        return parametrosConfiguracionList;
    }

    /**
     * @return the parametrosConfiguracionSet
     */
    public Set<ParametrosConfiguracion> getParametrosConfiguracionSet() {
        return parametrosConfiguracionSet;
    }

    /**
     * @param parametrosConfiguracionSet the parametrosConfiguracionSet to set
     */
    public void setParametrosConfiguracionSet(Set<ParametrosConfiguracion> parametrosConfiguracionSet) {
        this.parametrosConfiguracionSet = parametrosConfiguracionSet;
    }

    /**
     * @return the listasPrecios
     */
    public ListasPrecios getListasPrecios() {
        return listasPrecios;
    }

    /**
     * @param listasPrecios the listasPrecios to set
     */
    public void setListasPrecios(ListasPrecios listasPrecios) {
        this.listasPrecios = listasPrecios;
    }

    /**
     * @return the menusSet
     */
    public Set<Menus> getMenusSet() {
        return menusSet;
    }

    /**
     * @param menusSet the menusSet to set
     */
    public void setMenusSet(Set<Menus> menusSet) {
        this.menusSet = menusSet;
    }

    /**
     * @return the menus
     */
    public Menus getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    /**
     * @return the rolesSetToList
     */
    public List<Roles> getRolesSetToList() {
        List<Roles> rolesList = new ArrayList<>();
        if (Hibernate.isInitialized(rolesSet) && rolesSet != null) {
            for (Roles r : rolesSet) {
                rolesList.add(r);
            }
        }
        return rolesList;
    }

    /**
     * @return the rolesSet
     */
    public Set<Roles> getRolesSet() {
        return rolesSet;
    }

    /**
     * @param rolesSet the rolesSet to set
     */
    public void setRolesSet(Set<Roles> rolesSet) {
        this.rolesSet = rolesSet;
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
     * @return the rolesCrear
     */
    public Roles getRolesCrear() {
        return rolesCrear;
    }

    /**
     * @param rolesCrear the rolesCrear to set
     */
    public void setRolesCrear(Roles rolesCrear) {
        this.rolesCrear = rolesCrear;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    

}
