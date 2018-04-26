/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.modelo;

import com.sinergia.atencion.modelo.Citas;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class Pacientes implements Serializable {

    private PacientesPK pacientesPK = new PacientesPK();

    private String nombre;
    private String apellido;
//    private String direccion;
//    private String telefono;
    private String sexo;
    private Date fechaNacimiento;
    private int codCategoria;
//    private Float peso;
//    private Float talla;
//    private String alergias;
//    private String obsEspeciales;
    private String activo;
//    private String celular;
    private Float porcentajeDescuento;
    private String estadoCivil;
    private int codIdentidadCotizante;
    private String documentoCotizante;
    private String codPaisNacimiento;
    private String codDepartamentoNacimiento;
    private String codMunicipioNacimiento;
//    private String paisDir;
//    private String deptoDir;
//    private String muniDir;
//    private String otroBarrio;
//    private Integer codBarrio;
    private Date fechaActualizacion;
    private Date fechaRegistro;
    private String regimen = "N";

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacientes")
    private Set<TelefonosPaciente> telefonosPacienteSet;
//    @JoinColumn(name = "cod_identidad", referencedColumnName = "cod_identidad", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
    private DocumentosIdentidad documentosIdentidad;
    private Set<DireccionesPaciente> direccionesPacienteSet;
    private Set<ContexturasPaciente> contexturasPacienteSet;
    private Set<Citas> citasSet;

    public Pacientes() {
    }

    public Pacientes(PacientesPK pacientesPK) {
        this.pacientesPK = pacientesPK;
    }

    public Pacientes(PacientesPK pacientesPK, short codCategoria) {
        this.pacientesPK = pacientesPK;
        this.codCategoria = codCategoria;
    }

    public Pacientes(short codIdentidad, String documentoBeneficiario) {
        this.pacientesPK = new PacientesPK(codIdentidad, documentoBeneficiario);
    }

    public PacientesPK getPacientesPK() {
        return pacientesPK;
    }

    public void setPacientesPK(PacientesPK pacientesPK) {
        this.pacientesPK = pacientesPK;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

//    @XmlTransient
//    public Set<TelefonosPaciente> getTelefonosPacienteSet() {
//        return telefonosPacienteSet;
//    }
//
//    public void setTelefonosPacienteSet(Set<TelefonosPaciente> telefonosPacienteSet) {
//        this.telefonosPacienteSet = telefonosPacienteSet;
//    }
    public DocumentosIdentidad getDocumentosIdentidad() {
        return documentosIdentidad;
    }

    public void setDocumentosIdentidad(DocumentosIdentidad documentosIdentidad) {
        this.documentosIdentidad = documentosIdentidad;
    }

    /**
     * @return the codIdentidadCotizante
     */
    public int getCodIdentidadCotizante() {
        return codIdentidadCotizante;
    }

    /**
     * @param codIdentidadCotizante the codIdentidadCotizante to set
     */
    public void setCodIdentidadCotizante(int codIdentidadCotizante) {
        this.codIdentidadCotizante = codIdentidadCotizante;
    }

    /**
     * @return the documentoCotizante
     */
    public String getDocumentoCotizante() {
        return documentoCotizante;
    }

    /**
     * @param documentoCotizante the documentoCotizante to set
     */
    public void setDocumentoCotizante(String documentoCotizante) {
        this.documentoCotizante = documentoCotizante;
    }

    /**
     * @return the codPaisNacimiento
     */
    public String getCodPaisNacimiento() {
        return codPaisNacimiento;
    }

    /**
     * @param codPaisNacimiento the codPaisNacimiento to set
     */
    public void setCodPaisNacimiento(String codPaisNacimiento) {
        this.codPaisNacimiento = codPaisNacimiento;
    }

    /**
     * @return the codDepartamentoNacimiento
     */
    public String getCodDepartamentoNacimiento() {
        return codDepartamentoNacimiento;
    }

    /**
     * @param codDepartamentoNacimiento the codDepartamentoNacimiento to set
     */
    public void setCodDepartamentoNacimiento(String codDepartamentoNacimiento) {
        this.codDepartamentoNacimiento = codDepartamentoNacimiento;
    }

    /**
     * @return the codMunicipioNacimiento
     */
    public String getCodMunicipioNacimiento() {
        return codMunicipioNacimiento;
    }

    /**
     * @param codMunicipioNacimiento the codMunicipioNacimiento to set
     */
    public void setCodMunicipioNacimiento(String codMunicipioNacimiento) {
        this.codMunicipioNacimiento = codMunicipioNacimiento;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the telefonosPacienteSet
     */
    public Set<TelefonosPaciente> getTelefonosPacienteSet() {
        return telefonosPacienteSet;
    }

    /**
     * @param telefonosPacienteSet the telefonosPacienteSet to set
     */
    public void setTelefonosPacienteSet(Set<TelefonosPaciente> telefonosPacienteSet) {
        this.telefonosPacienteSet = telefonosPacienteSet;
    }

    /**
     * @return the direccionesPacienteSet
     */
    public Set<DireccionesPaciente> getDireccionesPacienteSet() {
        return direccionesPacienteSet;
    }

    /**
     * @param direccionesPacienteSet the direccionesPacienteSet to set
     */
    public void setDireccionesPacienteSet(Set<DireccionesPaciente> direccionesPacienteSet) {
        this.direccionesPacienteSet = direccionesPacienteSet;
    }

    /**
     * @return the contexturasPacienteSet
     */
    public Set<ContexturasPaciente> getContexturasPacienteSet() {
        return contexturasPacienteSet;
    }

    /**
     * @param contexturasPacienteSet the contexturasPacienteSet to set
     */
    public void setContexturasPacienteSet(Set<ContexturasPaciente> contexturasPacienteSet) {
        this.contexturasPacienteSet = contexturasPacienteSet;
    }

    /**
     * @return the regimen
     */
    public String getRegimen() {
        return regimen;
    }

    /**
     * @param regimen the regimen to set
     */
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

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
}
