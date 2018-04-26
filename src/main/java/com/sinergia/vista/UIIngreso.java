/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.atencion.controlador.GestorCitas;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.vista.UICitas;
import com.sinergia.cliente.controlador.GestorClientes;
import com.sinergia.cliente.modelo.Clientes;
import com.sinergia.cliente.modelo.ClientesPK;
import com.sinergia.cliente.modelo.Programas;
import com.sinergia.cliente.modelo.ProgramasConfiguracion;
import com.sinergia.cliente.modelo.ServiciosLista;
import com.sinergia.facturacion.controlador.GestorFacturas;
import com.sinergia.facturacion.modelo.Facturas;
import com.sinergia.facturacion.modelo.FacturasConfiguracion;
import com.sinergia.facturacion.modelo.FacturasDetalle;
import com.sinergia.facturacion.modelo.FacturasDetallePK;
import com.sinergia.facturacion.modelo.FacturasPK;
import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.paciente.controlador.GestorPacientes;
import com.sinergia.paciente.modelo.DireccionesPaciente;
import com.sinergia.paciente.modelo.DireccionesPacientePK;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.paciente.modelo.TelefonosPaciente;
import com.sinergia.paciente.modelo.TelefonosPacientePK;
import com.sinergia.publico.controlador.GestorLista;
import com.sinergia.publico.modelo.DetalleLista;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Lista;
import com.sinergia.publico.vista.UILista;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Hibernate;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiIngreso")
@SessionScoped
public class UIIngreso {

    @ManagedProperty("#{gestorCitas}")
    private GestorCitas gestorCitas;
    @ManagedProperty("#{gestorPacientes}")
    private GestorPacientes gestorPacientes;
    @ManagedProperty("#{gestorLista}")
    private GestorLista gestorLista;
    @ManagedProperty("#{gestorClientes}")
    private GestorClientes gestorClientes;
    @ManagedProperty("#{gestorFacturas}")
    private GestorFacturas gestorFacturas;

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

    @PostConstruct
    public void UIIngreso() {
        this.cargarDetallesPacientes();
        this.cargarClientesEstablecimiento();
    }

    private Pacientes pacientes = new Pacientes();
    private List<Citas> citasPacienteList = new ArrayList<>();
    private Lista telefonosPacienteLista;
    private Lista direccionesPacienteLista;
    private Citas citaIngreso;
    private List<Clientes> clientesList = new ArrayList<>();
    private Clientes clientesSeleccionado;
    private Programas programasSeleccionado;

    public void asignarProgramaCliente() {
        if (clientesSeleccionado.getProgramasSet() != null && Hibernate.isInitialized(clientesSeleccionado.getProgramasSet())) {
            for (Programas p : clientesSeleccionado.getProgramasSet()) {
                programasSeleccionado = p;
                break;
            }
        } else {
            programasSeleccionado = new Programas();
        }
    }

    private void cargarDetallesPacientes() {
        telefonosPacienteLista = getGestorLista().cargarLista(UILista.TELEFONOS_PACIENTE);
        direccionesPacienteLista = getGestorLista().cargarLista(UILista.DIRECCIONES_PACIENTE);
    }

    private void cargarClientesEstablecimiento() {
        try {
            Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
            clientesList.clear();
            clientesList.add(new Clientes(new ClientesPK(0, -1), "Seleccione un cliente"));
            clientesList.addAll(gestorClientes.cargarClientesEstablecimiento(sesion.getEstablecimiento()));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void registrarIngreso() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        Establecimiento establecimiento = sesion.getEstablecimiento();
        try {
            gestorClientes.validarClientePrograma(clientesSeleccionado, programasSeleccionado);
            gestorFacturas.validarResolucionEstablecimiento(establecimiento);

            for (ServiciosLista sl : programasSeleccionado.getProgramasConfiguracion().getListasPrecios().getServiciosListaSet()) {
                if (citaIngreso.getCodServicio().equals(sl.getServiciosListaPK().getCodServicio())) {
                    programasSeleccionado.getProgramasConfiguracion().getListasPrecios().setServiciosLista(sl);
                }
            }
            if (programasSeleccionado.getProgramasConfiguracion().getListasPrecios().getServiciosLista() == null) {
                throw new Exception("El servicio asociado a la cita no se encuentra valorizado, no se puede continuar.", UtilLog.TW_VALIDACION);
            }

            Set<TelefonosPaciente> tSet = new HashSet<>();
            for (DetalleLista dl : telefonosPacienteLista.getDetalleListaSet()) {
                if (dl.getValorIngresado() != null && !dl.getValorIngresado().equalsIgnoreCase("")) {
                    TelefonosPaciente t = new TelefonosPaciente(new TelefonosPacientePK(pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario(), dl.getValorIngresado(), dl.getDetalleListaPK().getCodLista(), dl.getDetalleListaPK().getCodDetalle()));
                    t.setAplicacion(Sesion.APP_IPS);
                    t.setFechaActualizacion(new Date());
                    t.setFechaRegistro(new Date());
                    t.setUsuario(sesion.getUsuario().getUsuario());
                    tSet.add(t);
                }
            }

            if (pacientes.getTelefonosPacienteSet() != null) {
                for (TelefonosPaciente tp : pacientes.getTelefonosPacienteSet()) {
                    for (TelefonosPaciente tset : tSet) {
                        if (tp.equals(tset)) {
                            tset.setFechaRegistro(tp.getFechaRegistro());
                        }
                    }
                }
            }
            pacientes.setTelefonosPacienteSet(tSet);

            Set<DireccionesPaciente> dSet = new HashSet<>();
            for (DetalleLista dl : direccionesPacienteLista.getDetalleListaSet()) {

                if (dl.getValorIngresado() != null && !dl.getValorIngresado().equalsIgnoreCase("")) {
                    DireccionesPaciente d = new DireccionesPaciente(new DireccionesPacientePK(pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario(), dl.getValorIngresado(), dl.getDetalleListaPK().getCodLista(), dl.getDetalleListaPK().getCodDetalle()));
                    d.setAplicacion(Sesion.APP_IPS);
                    d.setFechaActualizacion(new Date());
                    d.setFechaRegistro(new Date());
                    d.setUsuario(sesion.getUsuario().getUsuario());
                    dSet.add(d);
                }

            }

            if (pacientes.getDireccionesPacienteSet() != null) {
                for (DireccionesPaciente dp : pacientes.getDireccionesPacienteSet()) {
                    for (DireccionesPaciente dset : dSet) {
                        if (dp.equals(dset)) {
                            dset.setFechaRegistro(dp.getFechaRegistro());
                        }
                    }
                }
            }

            pacientes.setDireccionesPacienteSet(dSet);
            pacientes.setFechaActualizacion(new Date());
            gestorPacientes.guardarPaciente(pacientes);

            //prefactura
            if (establecimiento.getFacturasConfiguracionPrefactura() != null && establecimiento.getFacturasConfiguracionPrefactura().getFacturasConfiguracionPK() != null) {
                this.facturarCita(establecimiento, sesion.getUsuario(), programasSeleccionado, citaIngreso, pacientes, establecimiento.getFacturasConfiguracionPrefactura());
            }

            //copago
            if (establecimiento.getFacturasConfiguracionCopago() != null && establecimiento.getFacturasConfiguracionCopago().getFacturasConfiguracionPK() != null) {
                this.facturarCita(establecimiento, sesion.getUsuario(), programasSeleccionado, citaIngreso, pacientes, establecimiento.getFacturasConfiguracionCopago());
            }
            this.ingresarCita(citaIngreso);

            UtilMSG.addSuccessMsg("Cita ingresada correctamente", "Codigo Cita: " + citaIngreso.getPk().getCodCita().toString() + ", paciente: " + pacientes.getNombre() + " " + pacientes.getApellido());
//            UtilJSF.execute("PF('dialog').hide();");
            citasPacienteList.remove(citaIngreso);
            citaIngreso = null;

        } catch (Exception e) {
            if (UtilLog.causaControlada(e)) {
                UtilMSG.addWarningMsg(e.getCause().getMessage(), e.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), e);
                UtilMSG.addSupportMsg();
            }
        }
        UtilJSF.execute("PF('dialog').hide();");
    }

    private void facturarCita(Establecimiento establecimiento, Usuarios usuario, Programas programa, Citas cita, Pacientes pacientes, FacturasConfiguracion facturasConfiguracion) throws Exception {
        Long codigoFactura = gestorFacturas.generarCodigoFactura();
        ProgramasConfiguracion programasConfiguracion = programa.getProgramasConfiguracion();
        Facturas facturas = new Facturas(new FacturasPK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(),
                codigoFactura, pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario()));
        facturas.setFacturasConfiguracion(facturasConfiguracion);
        facturas.setCodConfiguracion(facturasConfiguracion.getFacturasConfiguracionPK().getCodConfiguracion());
        facturas.setPrefijoFactura(facturasConfiguracion.getPrefijoFactura());
        facturas.setCodCliente(programa.getClientes().getClientesPK().getCodCliente());
        facturas.setCodEntidad(programa.getEntidades().getEntidadesPK().getCodEntidad());
        facturas.setCodContrato(programa.getTiposContrato().getTiposContratoPK().getCodContrato());
        facturas.setCodSubcuenta(programa.getSubcuentas().getSubcuentasPK().getCodSubcuenta());
//        for (ProgramasConfiguracion pc : programa.getProgramasConfiguracionSet()) {
//            programasConfiguracion = pc;
//        }
        facturas.setCodLista(programasConfiguracion.getListasPrecios().getListasPreciosPK().getCodLista());
        facturas.setTotalFactura(0);
        facturas.setTotalAnticipo(0);
        facturas.setTotalIva(0);
        facturas.setFecha(new Date());
        facturas.setFechaVencimiento(new Date());
        facturas.setDocumentoUsuario(usuario.getUsuariosPK().getDocumentoUsuario());
        facturas.setDocumentoFacturador(usuario.getUsuariosPK().getDocumentoUsuario());
        facturas.setFechaRegistro(new Date());
        facturas.setCodCita(cita.getPk().getCodCita());
        facturas.setDetallePaciente(pacientes.getNombre() + " " + pacientes.getApellido());
        facturas.setEstado(Facturas.ESTADO_FACTURADO);

        ServiciosLista servicioLista = programasConfiguracion.getListasPrecios().getServiciosLista();
//        for (ServiciosLista s : programasConfiguracion.getListasPrecios().getServiciosListaSet()) {
//            servicioLista = s;
//        }

        Set<FacturasDetalle> facturasDetallesSet = new HashSet<>();
        FacturasDetalle fd = new FacturasDetalle(new FacturasDetallePK(establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento(), facturas.getFacturasPK().getCodFactura(), cita.getCodServicio(), pacientes.getPacientesPK().getCodIdentidad(), pacientes.getPacientesPK().getDocumentoBeneficiario()));
        fd.setCantidad(1);
        fd.setValorCosto(servicioLista.getValor());
        fd.setValorVenta(servicioLista.getValor());
        fd.setValorIva(0);
        fd.setIva(0);

        facturasDetallesSet.add(fd);

        facturas.setTotalAnticipo(2500);
        facturas.setTotalFactura((int) servicioLista.getValor() - facturas.getTotalAnticipo());

        facturas.setFacturasDetalleSet(facturasDetallesSet);

        gestorFacturas.guardarFacturas(establecimiento, facturas);
    }

    public void eliminar() {
        UtilMSG.addWarningMsg("Evento no soportado");
    }

    public void guardar() {
        UtilMSG.addWarningMsg("Evento no soportado");
    }

    public void nuevo() {
        UtilMSG.addWarningMsg("Evento no soportado");
    }

    public void consultarCitasPaciente() {
        try {
            Establecimiento establecimiento = ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            pacientes = getGestorPacientes().consultarPaciente(pacientes);

            if (pacientes.getTelefonosPacienteSet() != null && !pacientes.getTelefonosPacienteSet().isEmpty()) {
                for (DetalleLista dl : telefonosPacienteLista.getDetalleListaSet()) {
                    for (TelefonosPaciente t : pacientes.getTelefonosPacienteSet()) {
                        if (dl.getDetalleListaPK().getCodLista() == t.getTelefonosPacientePK().getCodLista()
                                && dl.getDetalleListaPK().getCodDetalle() == t.getTelefonosPacientePK().getDescripcionCodDetalle()) {
                            dl.setValorIngresado(t.getTelefonosPacientePK().getNumero());
                        }
                    }
                }
            }

            if (pacientes.getDireccionesPacienteSet() != null && !pacientes.getDireccionesPacienteSet().isEmpty()) {
                for (DetalleLista dl : direccionesPacienteLista.getDetalleListaSet()) {
                    for (DireccionesPaciente t : pacientes.getDireccionesPacienteSet()) {
                        if (dl.getDetalleListaPK().getCodLista() == t.getDireccionesPacientePK().getCodLista()
                                && dl.getDetalleListaPK().getCodDetalle() == t.getDireccionesPacientePK().getDescripcionCodDetalle()) {
                            dl.setValorIngresado(t.getDireccionesPacientePK().getDireccion());
                        }
                    }
                }
            }
            citasPacienteList.clear();
            citasPacienteList.addAll(gestorCitas.cargarCitasPacienteEstado(establecimiento, pacientes, UICitas.ESTADO_AGENDADO));
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void cancelarCita() {
        try {
            Citas c = (Citas) UtilJSF.getBean("citasPacienteDataTable");
            c.setEstado(UICitas.ESTADO_CANCELADO);
            gestorCitas.guardarCita(c, null);
            citasPacienteList.remove(c);
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public void cargarModalDatosPaciente() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        citaIngreso = (Citas) UtilJSF.getBean("citasPacienteDataTable");
        sesion.setDialogo(new Dialogo("dialogos/datosPaciente.xhtml", "Ingreso Cita Paciente", "clip"));
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
        UtilJSF.execute("PF('dialog').show();");
    }

    public void ingresarCita(Citas citas) throws Exception {
        citas.setEstado(UICitas.ESTADO_INGRESADO);
        gestorCitas.guardarCita(citas, null);
        citasPacienteList.remove(citas);
    }

    public void inasistirCita() {
        try {
            Citas c = (Citas) UtilJSF.getBean("citasPacienteDataTable");
            c.setEstado(UICitas.ESTADO_INASISTIDO);
            gestorCitas.guardarCita(c, null);
            citasPacienteList.remove(c);
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

    /**
     * @return the pacientes
     */
    public Pacientes getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * @return the gestorPacientes
     */
    public GestorPacientes getGestorPacientes() {
        return gestorPacientes;
    }

    /**
     * @param gestorPacientes the gestorPacientes to set
     */
    public void setGestorPacientes(GestorPacientes gestorPacientes) {
        this.gestorPacientes = gestorPacientes;
    }

    /**
     * @return the gestorCitas
     */
    public GestorCitas getGestorCitas() {
        return gestorCitas;
    }

    /**
     * @param gestorCitas the gestorCitas to set
     */
    public void setGestorCitas(GestorCitas gestorCitas) {
        this.gestorCitas = gestorCitas;
    }

    /**
     * @return the citasPacienteList
     */
    public List<Citas> getCitasPacienteList() {
        return citasPacienteList;
    }

    /**
     * @param citasPacienteList the citasPacienteList to set
     */
    public void setCitasPacienteList(List<Citas> citasPacienteList) {
        this.citasPacienteList = citasPacienteList;
    }

    /**
     * @return the gestorLista
     */
    public GestorLista getGestorLista() {
        return gestorLista;
    }

    /**
     * @param gestorLista the gestorLista to set
     */
    public void setGestorLista(GestorLista gestorLista) {
        this.gestorLista = gestorLista;
    }

    /**
     * @return the telefonosPacienteLista
     */
    public Lista getTelefonosPacienteLista() {
        return telefonosPacienteLista;
    }

    /**
     * @param telefonosPacienteLista the telefonosPacienteLista to set
     */
    public void setTelefonosPacienteLista(Lista telefonosPacienteLista) {
        this.telefonosPacienteLista = telefonosPacienteLista;
    }

    /**
     * @return the direccionesPacienteLista
     */
    public Lista getDireccionesPacienteLista() {
        return direccionesPacienteLista;
    }

    /**
     * @param direccionesPacienteLista the direccionesPacienteLista to set
     */
    public void setDireccionesPacienteLista(Lista direccionesPacienteLista) {
        this.direccionesPacienteLista = direccionesPacienteLista;
    }

    /**
     * @return the clientesList
     */
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    /**
     * @param clientesList the clientesList to set
     */
    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    /**
     * @return the clientesSeleccionado
     */
    public Clientes getClientesSeleccionado() {
        return clientesSeleccionado;
    }

    /**
     * @param clientesSeleccionado the clientesSeleccionado to set
     */
    public void setClientesSeleccionado(Clientes clientesSeleccionado) {
        this.clientesSeleccionado = clientesSeleccionado;
    }

    /**
     * @return the gestorClientes
     */
    public GestorClientes getGestorClientes() {
        return gestorClientes;
    }

    /**
     * @param gestorClientes the gestorClientes to set
     */
    public void setGestorClientes(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
    }

    /**
     * @return the programasSeleccionado
     */
    public Programas getProgramasSeleccionado() {
        return programasSeleccionado;
    }

    /**
     * @param programasSeleccionado the programasSeleccionado to set
     */
    public void setProgramasSeleccionado(Programas programasSeleccionado) {
        this.programasSeleccionado = programasSeleccionado;
    }

    /**
     * @return the gestorFacturas
     */
    public GestorFacturas getGestorFacturas() {
        return gestorFacturas;
    }

    /**
     * @param gestorFacturas the gestorFacturas to set
     */
    public void setGestorFacturas(GestorFacturas gestorFacturas) {
        this.gestorFacturas = gestorFacturas;
    }

}
