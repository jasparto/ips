/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.modelo;

import com.sinergia.atencion.modelo.Recurso;
import com.sinergia.atencion.modelo.Servicios;
import com.sinergia.facturacion.modelo.FacturasConfiguracion;
import com.sinergia.usuario.modelo.Usuarios;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class Establecimiento implements Serializable {

    protected EstablecimientoPK establecimientoPK;

    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private Date fechaCierreDiario;
    private String tipoEstablecimiento;
    private Set<Usuarios> relUsuariosEstablecimientoSet = new HashSet<>();
    private Set<FacturasConfiguracion> facturasConfiguracionSet;
    private Set<Recurso> recursoSet;
    private Set<EstablecimientoParametros> establecimientoParametrosSet;
    private HashMap establecimientoParametrosHashMap;
    private FacturasConfiguracion facturasConfiguracionPrefactura;
    private FacturasConfiguracion facturasConfiguracionFactura;
    private FacturasConfiguracion facturasConfiguracionCopago;
    private FacturasConfiguracion facturasConfiguracion;
    private Municipios municipios;
    private Institucion institucion;
    private Servicios servicios = new Servicios();
    private EstablecimientoParametros establecimientoParametros = new EstablecimientoParametros();

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "establecimiento")
//    private Capital capital;
//    @JoinColumn(name = "cod_institucion", referencedColumnName = "cod_institucion", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Institucion institucion;
//    @JoinColumn(name = "cod_municipio", referencedColumnName = "cod_municipio")
//    @ManyToOne(optional = false)
//    private Municipios codMunicipio;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimiento")
//    private Collection<Movimientos> movimientosCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimiento")
//    private Collection<RegistroServicios> registroServiciosCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimiento")
//    private Collection<Gastos> gastosCollection;
    public Establecimiento() {
        facturasConfiguracionFactura = new FacturasConfiguracion();
        facturasConfiguracionPrefactura = new FacturasConfiguracion();
        facturasConfiguracionCopago = new FacturasConfiguracion();
        facturasConfiguracion = new FacturasConfiguracion();
    }

    public Establecimiento(EstablecimientoPK establecimientoPK) {
        this.establecimientoPK = establecimientoPK;
    }

    public Establecimiento(EstablecimientoPK establecimientoPK, String nit, Date fechaCierreDiario, String tipoEstablecimiento) {
        this.establecimientoPK = establecimientoPK;
        this.nit = nit;
        this.fechaCierreDiario = fechaCierreDiario;
        this.tipoEstablecimiento = tipoEstablecimiento;
    }

    public Establecimiento(int codInstitucion, int codEstablecimiento) {
        this.establecimientoPK = new EstablecimientoPK(codInstitucion, codEstablecimiento);
    }

    public EstablecimientoPK getEstablecimientoPK() {
        return establecimientoPK;
    }

    public void setEstablecimientoPK(EstablecimientoPK establecimientoPK) {
        this.establecimientoPK = establecimientoPK;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCierreDiario() {
        return fechaCierreDiario;
    }

    public void setFechaCierreDiario(Date fechaCierreDiario) {
        this.fechaCierreDiario = fechaCierreDiario;
    }

    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (establecimientoPK != null ? establecimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.establecimientoPK == null && other.establecimientoPK != null) || (this.establecimientoPK != null && !this.establecimientoPK.equals(other.establecimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.publico.Establecimiento[ establecimientoPK=" + establecimientoPK + " ]";
    }

    /**
     * @return the relUsuariosEstablecimientoSet
     */
    public Set<Usuarios> getRelUsuariosEstablecimientoSet() {
        return relUsuariosEstablecimientoSet;
    }

    /**
     * @param relUsuariosEstablecimientoSet the relUsuariosEstablecimientoSet to
     * set
     */
    public void setRelUsuariosEstablecimientoSet(Set<Usuarios> relUsuariosEstablecimientoSet) {
        this.relUsuariosEstablecimientoSet = relUsuariosEstablecimientoSet;
    }

    /**
     * @return the facturasConfiguracionSet
     */
    public Set<FacturasConfiguracion> getFacturasConfiguracionSet() {
        return facturasConfiguracionSet;
    }

    /**
     * @param facturasConfiguracionSet the facturasConfiguracionSet to set
     */
    public void setFacturasConfiguracionSet(Set<FacturasConfiguracion> facturasConfiguracionSet) {
        this.facturasConfiguracionSet = facturasConfiguracionSet;
    }

    /**
     * @return the municipios
     */
    public Municipios getMunicipios() {
        return municipios;
    }

    /**
     * @param municipios the municipios to set
     */
    public void setMunicipios(Municipios municipios) {
        this.municipios = municipios;
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
     * @return the establecimientoParametrosSet
     */
    public Set<EstablecimientoParametros> getEstablecimientoParametrosSet() {
        return establecimientoParametrosSet;
    }

    /**
     * @param establecimientoParametrosSet the establecimientoParametrosSet to
     * set
     */
    public void setEstablecimientoParametrosSet(Set<EstablecimientoParametros> establecimientoParametrosSet) {
        this.establecimientoParametrosSet = establecimientoParametrosSet;
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
     * @return the establecimientoParametros
     */
    public EstablecimientoParametros getEstablecimientoParametros() {
        return establecimientoParametros;
    }

    /**
     * @param establecimientoParametros the establecimientoParametros to set
     */
    public void setEstablecimientoParametros(EstablecimientoParametros establecimientoParametros) {
        this.establecimientoParametros = establecimientoParametros;
    }

    /**
     * @return the facturasConfiguracionPrefactura
     */
    public FacturasConfiguracion getFacturasConfiguracionPrefactura() {
        return facturasConfiguracionPrefactura;
    }

    /**
     * @param facturasConfiguracionPrefactura the
     * facturasConfiguracionPrefactura to set
     */
    public void setFacturasConfiguracionPrefactura(FacturasConfiguracion facturasConfiguracionPrefactura) {
        this.facturasConfiguracionPrefactura = facturasConfiguracionPrefactura;
    }

    /**
     * @return the facturasConfiguracionFactura
     */
    public FacturasConfiguracion getFacturasConfiguracionFactura() {
        return facturasConfiguracionFactura;
    }

    /**
     * @param facturasConfiguracionFactura the facturasConfiguracionFactura to
     * set
     */
    public void setFacturasConfiguracionFactura(FacturasConfiguracion facturasConfiguracionFactura) {
        this.facturasConfiguracionFactura = facturasConfiguracionFactura;
    }

    /**
     * @return the facturasConfiguracionCopago
     */
    public FacturasConfiguracion getFacturasConfiguracionCopago() {
        return facturasConfiguracionCopago;
    }

    /**
     * @param facturasConfiguracionCopago the facturasConfiguracionCopago to set
     */
    public void setFacturasConfiguracionCopago(FacturasConfiguracion facturasConfiguracionCopago) {
        this.facturasConfiguracionCopago = facturasConfiguracionCopago;
    }

    /**
     * @return the facturasConfiguracion
     */
    public FacturasConfiguracion getFacturasConfiguracion() {
        return facturasConfiguracion;
    }

    /**
     * @param facturasConfiguracion the facturasConfiguracion to set
     */
    public void setFacturasConfiguracion(FacturasConfiguracion facturasConfiguracion) {
        this.facturasConfiguracion = facturasConfiguracion;
    }

    /**
     * @return the establecimientoParametrosHashMap
     */
    public HashMap getEstablecimientoParametrosHashMap() {
        return establecimientoParametrosHashMap;
    }

    /**
     * @param establecimientoParametrosHashMap the establecimientoParametrosHashMap to set
     */
    public void setEstablecimientoParametrosHashMap(HashMap establecimientoParametrosHashMap) {
        this.establecimientoParametrosHashMap = establecimientoParametrosHashMap;
    }

    /**
     * @return the servicios
     */
    public Servicios getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

}
