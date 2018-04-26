/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.atencion.controlador.GestorCitas;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.cliente.controlador.GestorEntidades;
import com.sinergia.facturacion.controlador.GestorRips;
import com.sinergia.facturacion.modelo.RipsGeneracion;
import com.sinergia.modelo.*;
import com.sinergia.publico.controlador.GestorEstablecimiento;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.usuario.modelo.Usuarios;
import com.sinergia.utilidades.modelo.UtilFecha;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import com.sinergia.utilidades.modelo.UtilMSG;
import com.sinergia.utilidades.modelo.UtilNum;
import com.sinergia.utilidades.modelo.UtilTexto;
import ips.modelo.rips.RipsControl;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiRips")
@SessionScoped
public class UIRips {

    public static String UNIDAD_MEDIDA_EDAD_AÑOS = "1";
    public static String UNIDAD_MEDIDA_EDAD_MESES = "2";
    public static String UNIDAD_MEDIDA_EDAD_DIAS = "3";

    @ManagedProperty("#{gestorRips}")
    private GestorRips gestorRips;
    @ManagedProperty("#{gestorCitas}")
    private GestorCitas gestorCitas;
    @ManagedProperty("#{gestorEntidades}")
    private GestorEntidades gestorEntidades;
    @ManagedProperty("#{gestorEstablecimiento}")
    private GestorEstablecimiento gestorEstablecimiento;

    public static final String DIALOGO_CREAR = "";
    public static final String COMPONENTES_REFRESCAR = "";

    private Date fechaInicial;
    private Date fechaFinal;
    private List<RipsGeneracion> ripsGeneracionList = new ArrayList<>();
    private List<String> cadenaErrores;
    private List<RegistroCT> archivoCT = new ArrayList<>();
    private List<RegistroAF> archivoAF = new ArrayList<>();
    private List<RegistroUS> archivoUS = new ArrayList<>();
    private List<RegistroAC> archivoAC = new ArrayList<>();
    private List<RegistroAD> archivoAD = new ArrayList<>();
    private List<RegistroAP> archivoAP = new ArrayList<>();
    private List<RegistroAM> archivoAM = new ArrayList<>();
//    private RipsControl ripsControl = new RipsControl();
    private RipsGeneracion ripsGeneracion = new RipsGeneracion();

    private boolean erroresGenerados = false;
    private boolean archivosGenerados = false;
    private boolean archivoCTGenerado = false;
    private boolean archivoAFGenerado = false;
    private boolean recargarListaRips = false;

    @PostConstruct
    public void UIRips() {
        try {
            Establecimiento establecimiento = ((Sesion) UtilJSF.getBean("sesion")).getEstablecimiento();
            ripsGeneracionList.addAll(gestorRips.cargarRipsGeneracion(establecimiento));
            for (RipsGeneracion rg : ripsGeneracionList) {
                rg.setEstablecimiento(gestorEstablecimiento.cargarEstablecimiento(rg.getCodInstitucion(), rg.getCodEstablecimiento()));
            }
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
    }

    private boolean validarArchivo(List archivo, Class clase) {
        BeanInfo bean = null;
        try {
            bean = Introspector.getBeanInfo(clase);
        } catch (IntrospectionException ex) {
            cadenaErrores.add("Error al tratar de validar: " + ex.getMessage());
        }

        for (Object registro : archivo) {
            System.out.println("-InicioLinea-");
            for (PropertyDescriptor propertyDesc : bean.getPropertyDescriptors()) {
                System.out.println(
                        "Campo: " + propertyDesc.getDisplayName() + " Tipo: " + propertyDesc.getPropertyType().getName());
                // Validación para cadenas
                if (propertyDesc.getPropertyType().getName().equals(
                        "ips.modelo.rips.RipsString")) {
                    try {
                        RipsString ripsString = (RipsString) propertyDesc.getReadMethod().invoke(
                                registro);
                        System.out.println("valor:" + ripsString.getValor());
                        if (!ripsString.getValor().equals("") && !ripsString.getValor().matches(
                                "^[a-zA-Z0-9ñÑÓóÁá\\.\\-\\/\\%\\+\\(\\) ]+$")) {

                            cadenaErrores.add(
                                    "El campo " + propertyDesc.getDisplayName()
                                    + " del registro " + clase.getName()
                                    + " contiene caracteres no válidos. Valor: '" + ripsString.getValor() + "'");

                        } else if (ripsString.getLongitud() < ripsString.getValor().length()) {
                            cadenaErrores.add("El campo " + propertyDesc.getDisplayName()
                                    + " del registro " + clase.getName() + " sobrepasa el tamaño legal. Valor: '"
                                    + ripsString.getValor() + "'");
                        }

                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        cadenaErrores.add("Error al tratar de validar: " + ex.getMessage());
                    }

                }

                // Validación para double
                if (propertyDesc.getPropertyType().getName().equals("ips.modelo.rips.RipsDouble")) {
                    try {
                        RipsDouble ripsDouble = (RipsDouble) propertyDesc.getReadMethod().invoke(registro);
                        if (ripsDouble.getValor() != 0 && Double.toString(ripsDouble.getValor()).length() > ripsDouble.getLongitud()) {
                            cadenaErrores.add(
                                    "El campo " + propertyDesc.getDisplayName()
                                    + " del registro " + clase.getName()
                                    + " sobrepasa el tamaño legal. Valor: '" + ripsDouble.getValor() + "'");
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        cadenaErrores.add("Error al tratar de validar: " + ex.getMessage());
                    }
                }

                // Validación para int
                if (propertyDesc.getPropertyType().getName().equals("ips.modelo.rips.RipsInt")) {
                    try {
                        RipsInt ripsInt = (RipsInt) propertyDesc.getReadMethod().invoke(registro);

                        if (ripsInt.getValor() != 0 && Integer.toString(ripsInt.getValor()).length() > ripsInt.getLongitud()) {
                            cadenaErrores.add(
                                    "El campo " + propertyDesc.getDisplayName()
                                    + " del registro " + clase.getName()
                                    + " sobrepasa el tamaño legal. Valor: '" + ripsInt.getValor() + "'");
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        cadenaErrores.add("Error al tratar de validar: " + ex.getMessage());
                    }
                }
            }

            if (cadenaErrores.size() > 5) {
                return false;
            }
        }

        if (!cadenaErrores.isEmpty()) {
            return false;
        }
        return true;
    }

    public void generarRips() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        Establecimiento establecimiento = sesion.getEstablecimiento();
        Usuarios usuarios = sesion.getUsuario();
        erroresGenerados = false;
        System.out.println("Generando archivos RIPS...");

        cadenaErrores = new ArrayList<>();
        archivoCT = new ArrayList<>();
        archivoAF = new ArrayList<>();
        archivoAC = new ArrayList<>();
        archivoAD = new ArrayList<>();
        archivoUS = new ArrayList<>();
        archivoAP = new ArrayList<>();
//        ripsControl = new RipsControl();
        ripsGeneracion = new RipsGeneracion();
        ripsGeneracion.setCodEstablecimiento(establecimiento.getEstablecimientoPK().getCodEstablecimiento());
        ripsGeneracion.setCodInstitucion(establecimiento.getEstablecimientoPK().getCodInstitucion());
        ripsGeneracion.setNombreCaf(establecimiento.getNombre());
//        ripsControl.setNombreCiudad(establecimiento.getMunicipios().getNombre());
        ripsGeneracion.setUsuarioResponsable(usuarios.getUsuariosPK().getDocumentoUsuario());
        ripsGeneracion.setNombreResponsable(usuarios.getNombre() + " " + usuarios.getApellido());
//        ripsControl.setNombreUsuarioResponsable(usuarios.getUsuario());
        ripsGeneracion.setFecha(new Date());
        ripsGeneracion.setFechaInicio(this.fechaInicial);
        ripsGeneracion.setFechaFinal(this.fechaFinal);
        ripsGeneracion.setTotalRegistros(0);
        ripsGeneracion.setTotalRegistrosAf(0);
        ripsGeneracion.setTotalRegistrosCt(0);
        ripsGeneracion.setTotalRegistrosUs(0);
        ripsGeneracion.setTotalRegistrosAc(0);
        ripsGeneracion.setTotalRegistrosAd(0);
        ripsGeneracion.setTotalRegistrosAp(0);
        ripsGeneracion.setCodEapb("APS02");

//        if (!codigoEapb.equals("TODOS")) {
//            ripsControl.setNombreEapb(getListaEapb().get(codigoEapb).getNombre());
//        }
        // Cargar el codigo prestador de servicios
        String codigoPrestadorServicios = "123456";
        String codRips = "1";
        try {
//            String codigoEmpresa = establecimiento.getEstablecimientoPK().getCodInstitucion();
//            codigoPrestadorServicios = ripsDao.getCodigoPrestadorServicios(codigoEmpresa);
//            codRips = ripsDao.getCodigoRips(ripsControl.getNombreCiudad());
            if (codigoPrestadorServicios.equals("")) {
                erroresGenerados = true;
                cadenaErrores.add("El codigo del prestador de servicios está vacio.");
            }
        } catch (Exception ex) {
            System.out.println(this.toString() + " ErrorSql:" + ex.getMessage());
            erroresGenerados = true;
            cadenaErrores.add(ex.getMessage());
        }

        if (!erroresGenerados) {
            try {
                List<Citas> citasList = new ArrayList<>();
                citasList.addAll(gestorCitas.cargarCitasFacturadas(establecimiento, fechaInicial, fechaFinal));
                if(citasList.isEmpty()){
                    throw new Exception("No hay atenciones en el periodo seleccionado.", UtilLog.TW_VALIDACION);
                }
                //cargamos información adicional
                for (Citas c : citasList) {
                    c.getFacturas().setEntidades(gestorEntidades.cargarEntidad(establecimiento, c.getFacturas().getCodEntidad()));
                    ripsGeneracion.setNombreEapb(c.getFacturas().getEntidades().getNombre());
                }

                // Crear Archivo AF:
                try {
//                String codigoEmpresa = ResourceBundle.getBundle(IPSUtil.CONFIGURACION_PATH).getString("empresaPrincipal");
//                codigoPrestadorServicios = ripsDao.getCodigoPrestadorServicios(codigoEmpresa);
                    //codRips = ripsDao.getCodigoRips(ripsControl.getNombreCiudad());
                    archivoAF = gestorRips.cargarRegistrosAF(establecimiento, ripsGeneracion.getCodEapb(), ripsGeneracion.getFechaInicio(), ripsGeneracion.getFechaFinal(), codRips, citasList);
                } catch (Exception ex) {
                    System.out.println(this.toString() + ":ErrorSQL:" + ex.getMessage());
                    erroresGenerados = true;
                    cadenaErrores.add(ex.getMessage());
                }

                // Validar Archivo AF:
                if (!erroresGenerados && validarArchivo(archivoAF, RegistroAF.class)) {

                    // Actualizar cantidades de AF
                    ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + archivoAF.size());
                    ripsGeneracion.setTotalRegistrosAf(ripsGeneracion.getTotalRegistrosAf() + archivoAF.size());

                    // Agregar RegistroCT correspondiente a los registros AF
                    RegistroCT tmpCT = new RegistroCT();
                    tmpCT.getCodigoArchivo().setValor("AF");
                    tmpCT.getCodigoPrestadorServicios().setValor(codRips);
                    tmpCT.setFechaRemision(new Date());
                    tmpCT.getTotalRegistros().setValor(ripsGeneracion.getTotalRegistrosAf());
                    archivoCT.add(tmpCT);

                    // Actualizar cantidades de CT
                    ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 1);
                    ripsGeneracion.setTotalRegistrosCt(ripsGeneracion.getTotalRegistrosCt() + 1);

                    // Crear Archivo US
                    try {
                        archivoUS = gestorRips.cargarRegistrosUS(establecimiento, ripsGeneracion.getCodEapb(), ripsGeneracion.getFechaInicio(), ripsGeneracion.getFechaFinal(), citasList);
                    } catch (Exception ex) {
                        System.out.println(this.toString() + ":ErrorSQL:" + ex.getMessage());
                        erroresGenerados = true;
                        cadenaErrores.add(ex.getMessage());
                    }

                    // Validar Archivo US:
                    if (!erroresGenerados && validarArchivo(archivoUS, RegistroUS.class)) {

                        // Actualizar cantidades de US
                        ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + archivoUS.size());
                        ripsGeneracion.setTotalRegistrosUs(ripsGeneracion.getTotalRegistrosUs() + archivoUS.size());

                        // Agregar RegistroCT correspondiente a los registros AF
                        tmpCT = new RegistroCT();
                        tmpCT.getCodigoArchivo().setValor("US");
                        tmpCT.getCodigoPrestadorServicios().setValor(codRips);
                        tmpCT.setFechaRemision(new Date());
                        tmpCT.getTotalRegistros().setValor(ripsGeneracion.getTotalRegistrosUs());

                        archivoCT.add(tmpCT);

                        // Actualizar cantidades de CT
                        ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 1);
                        ripsGeneracion.setTotalRegistrosCt(ripsGeneracion.getTotalRegistrosCt() + 1);

                        // Crear archivo AD
//                    try {
//
//                        archivoAD = ripsDao.cargarRegistrosAD(ripsControl.getCodigoCaf(),
//                                ripsControl.getCodigoEapb(), ripsControl.getFechaInicial(),
//                                ripsControl.getFechaFinal(), codRips);
//
//                    } catch (SQLException ex) {
//
//                        System.out.println(
//                                this.toString() + ":ErrorSQL:" + ex.getMessage());
//
//                        erroresGenerados = true;
//
//                        cadenaErrores.add(ex.getMessage());
//
//                    }
                        // Validar archivo AD
                        if (!erroresGenerados && validarArchivo(archivoAD, RegistroAD.class)) {

                            // Actualizar cantidades de AD
                            ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 2/*archivoAD.size()*/);

                            ripsGeneracion.setTotalRegistrosAd(2/*ripsControl.getTotalRegistrosAD() + archivoAD.size()*/);

                            // Agregar RegistroCT correspondiente a los registros AD
                            tmpCT = new RegistroCT();
                            tmpCT.getCodigoArchivo().setValor("AD");
                            tmpCT.getCodigoPrestadorServicios().setValor(codRips);
                            tmpCT.setFechaRemision(new Date());
                            tmpCT.getTotalRegistros().setValor(ripsGeneracion.getTotalRegistrosAd());

                            archivoCT.add(tmpCT);

                            // Actualizar cantidades de CT
                            ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 1);

                            ripsGeneracion.setTotalRegistrosCt(ripsGeneracion.getTotalRegistrosCt() + 1);

                            // Crear archivo AC
                            try {
                                archivoAC = gestorRips.cargarRegistrosAC(establecimiento, ripsGeneracion.getCodEapb(), ripsGeneracion.getFechaInicio(), ripsGeneracion.getFechaFinal(), codRips, citasList);
                            } catch (Exception ex) {
                                System.out.println(this.toString() + ":ErrorSQL:" + ex.getMessage());
                                erroresGenerados = true;
                                cadenaErrores.add(ex.getMessage());
                            }

                            // Validar archivo AC
                            if (!erroresGenerados && validarArchivo(archivoAC, RegistroAC.class)) {

                                // Actualizar cantidades de AC
                                ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + archivoAC.size());

                                ripsGeneracion.setTotalRegistrosAc(ripsGeneracion.getTotalRegistrosAc() + archivoAC.size());

                                // Agregar RegistroCT correspondiente a los registros AC
                                tmpCT = new RegistroCT();
                                tmpCT.getCodigoArchivo().setValor("AC");
                                tmpCT.getCodigoPrestadorServicios().setValor(codRips);
                                tmpCT.setFechaRemision(new Date());
                                tmpCT.getTotalRegistros().setValor(ripsGeneracion.getTotalRegistrosAc());
                                archivoCT.add(tmpCT);

                                // Actualizar cantidades de CT
                                ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 1);
                                ripsGeneracion.setTotalRegistrosCt(ripsGeneracion.getTotalRegistrosCt() + 1);

                                // Crear archivo AP
                                try {
                                    archivoAP = gestorRips.cargarRegistrosAP(establecimiento, ripsGeneracion.getCodEapb(), ripsGeneracion.getFechaInicio(), ripsGeneracion.getFechaFinal(), codRips, citasList);
                                } catch (Exception ex) {
                                    System.out.println(this.toString() + ":ErrorSQL:" + ex.getMessage());
                                    erroresGenerados = true;
                                    cadenaErrores.add(ex.getMessage());
                                }

                                // Validar archivo AP
                                if (!erroresGenerados && validarArchivo(archivoAP, RegistroAP.class)) {

                                    // Actualizar cantidades de AP
                                    ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + archivoAP.size());

                                    ripsGeneracion.setTotalRegistrosAp(ripsGeneracion.getTotalRegistrosAp() + archivoAP.size());

                                    // Agregar RegistroCT correspondiente a los registros AP
                                    tmpCT = new RegistroCT();
                                    tmpCT.getCodigoArchivo().setValor("AP");
                                    tmpCT.getCodigoPrestadorServicios().setValor(codRips);
                                    tmpCT.setFechaRemision(new Date());
                                    tmpCT.getTotalRegistros().setValor(ripsGeneracion.getTotalRegistrosAp());
                                    archivoCT.add(tmpCT);

                                    // Actualizar cantidades de CT
                                    ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 1);

                                    ripsGeneracion.setTotalRegistrosCt(ripsGeneracion.getTotalRegistrosCt() + 1);

                                    // Crear archivo AM
                                    try {
                                        archivoAM = gestorRips.cargarRegistrosAM(establecimiento, ripsGeneracion.getCodInstitucion(), ripsGeneracion.getCodEapb(), ripsGeneracion.getFechaInicio(), ripsGeneracion.getFechaFinal(), codRips, citasList);
                                    } catch (Exception ex) {
                                        System.out.println(this.toString() + ":ErrorSQL:" + ex.getMessage());
                                        erroresGenerados = true;
                                        cadenaErrores.add(ex.getMessage());
                                    }

                                    // Validar archivo AM
                                    if (!erroresGenerados && validarArchivo(archivoAM, RegistroAM.class)) {

                                        // Actualizar cantidades de AM
                                        ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + archivoAM.size());

                                        ripsGeneracion.setTotalRegistrosAm(ripsGeneracion.getTotalRegistrosAm() + archivoAM.size());

                                        // Agregar RegistroCT correspondiente a los registros AM
                                        tmpCT = new RegistroCT();
                                        tmpCT.getCodigoArchivo().setValor("AM");
                                        tmpCT.getCodigoPrestadorServicios().setValor(codRips);
                                        tmpCT.setFechaRemision(new Date());
                                        tmpCT.getTotalRegistros().setValor(ripsGeneracion.getTotalRegistrosAm());
                                        archivoCT.add(tmpCT);

                                        // Actualizar cantidades de CT
                                        ripsGeneracion.setTotalRegistros(ripsGeneracion.getTotalRegistros() + 1);

                                        ripsGeneracion.setTotalRegistrosCt(ripsGeneracion.getTotalRegistrosCt() + 1);

                                        // Si se generaron bien los archivos guardar registro de control
                                        if (ripsGeneracion.getTotalRegistrosAf() > 0
                                                || ripsGeneracion.getTotalRegistrosUs() > 0
                                                || ripsGeneracion.getTotalRegistrosAd() > 0
                                                || ripsGeneracion.getTotalRegistrosAc() > 0
                                                || ripsGeneracion.getTotalRegistrosAp() > 0) {

                                            try {

                                                //Guardar control de rips
                                                ripsGeneracion.setCodGeneracion(gestorRips.generarCodigoGeneracion());
                                                ripsGeneracion.setCodigoArchivo(UtilTexto.lpad(String.valueOf(ripsGeneracion.getCodGeneracion()), "0", 6));
                                                gestorRips.guardarRipsGeneracion(ripsGeneracion);
                                                this.ripsGeneracionList.add(ripsGeneracion);
//                                            System.out.println(
//                                                    "CodigoArchivo:" + codigoArchivo);

                                                // Actualizar el código de archivo en los registros CT
//
//                                                ripsGeneracion.setCodigoArchivo(codigoArchivo);
//
                                                for (RegistroCT registro : archivoCT) {
                                                    registro.getCodigoArchivo().setValor(registro.getCodigoArchivo().getValor() + ripsGeneracion.getCodigoArchivo());
                                                }
                                                // Validar el archivo CT
                                                if (validarArchivo(archivoCT, RegistroCT.class)) {
                                                    // Actualizar estados
                                                    setArchivosGenerados(true);
                                                    erroresGenerados = false;
                                                    recargarListaRips = true;

                                                    sesion.setDialogo(new Dialogo("dialogos/descargarRips.xhtml", "Rips Generados", "clip"));
                                                    UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
                                                    UtilJSF.execute("PF('dialog').show();");

                                                } else {
                                                    erroresGenerados = true;
                                                }
                                            } catch (SQLException ex) {
                                                System.out.println(this.toString() + ":ErrorSQL:" + ex.getMessage());
                                                erroresGenerados = true;
                                                cadenaErrores.add("Error sql al tratar de guardar el registro de control");
                                            }
                                        } else {
                                            erroresGenerados = true;
                                            cadenaErrores.add("Se generaron cero registros");
                                        }
                                    } else {
                                        erroresGenerados = true;
                                    }
                                } else {
                                    erroresGenerados = true;
                                }
                            } else {
                                erroresGenerados = true;
                            }
                        } else {
                            erroresGenerados = true;
                        }
                    } else {
                        erroresGenerados = true;
                    }
                } else { // if (validarAF(archivoAF))
                    erroresGenerados = true;
                }
            } catch (Exception ex) {
                if (UtilLog.causaControlada(ex)) {
                    UtilMSG.addWarningMsg(ex.getMessage(), ex.getCause().getMessage());
                } else {
                    UtilLog.generarLog(this.getClass(), ex);
                    UtilMSG.addWarningMsg("Excepción no controlada", "contactanos.");
                }

            }
        }
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

    private void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);

        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }

    private void writeOutContent(final HttpServletResponse res, final File content, final String theFilename) {
        if (content == null) {
            return;
        }
        try {
            res.setHeader("Pragma", "no-cache");
            res.setDateHeader("Expires", 0);
            res.setContentType("text/xml");
            res.setHeader("Content-disposition", "attachment; filename=" + theFilename);
            fastChannelCopy(Channels.newChannel(new FileInputStream(content)),
                    Channels.newChannel(res.getOutputStream()));

        } catch (final IOException e) {
            System.out.println("Error al tratar de descargar el archivo");
        }
    }

    private HttpServletResponse getServletResponse() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) external.getResponse();
        return res;
    }

    public void descargarRips(ActionEvent actionEvent) {
        System.out.println("hola");
        descargarArchivoCt(actionEvent);
        descargarArchivoAf(actionEvent);
        descargarArchivoUs(actionEvent);
        descargarArchivoAd(actionEvent);
        descargarArchivoAc(actionEvent);
        descargarArchivoAp(actionEvent);
        descargarArchivoAm(actionEvent);
    }

    public void descargarArchivoAm(ActionEvent event) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            File tmpFile = File.createTempFile("temp", "txt");
            tmpFile.deleteOnExit();
            PrintStream printStream = new PrintStream(tmpFile);
            for (RegistroAM registro : archivoAM) {
                String linea = "";

                linea += registro.getNumeroFactura().getValor();

                linea += "," + registro.getCodigoPrestadorServicios().getValor();

                linea += "," + registro.getTipoIdentificacionUsuario().getValor();

                linea += "," + registro.getNumeroIdentificacionUsuario().getValor();

                linea += "," + registro.getNumeroAutorizacion().getValor();

                linea += "," + registro.getCodigoMedicamento().getValor();

                linea += "," + registro.getTipoMedicamento().getValor();

                linea += "," + registro.getNombreGenericoMedicamento().getValor();

                linea += "," + registro.getFormaFarmaceuticaMedicamento().getValor();

                linea += "," + registro.getConcentracionMedicamento().getValor();

                linea += "," + registro.getUnidadMedidaMedicamento().getValor();

                linea += "," + registro.getNumeroUnidadesMedicamento().getValor();

                linea += "," + "0"; // Valor unidad

                linea += "," + "0"; // Valor total

                linea += "\r\n";

                printStream.print(linea);

                //printStream.println(linea);
            }

            writeOutContent(getServletResponse(), tmpFile,
                    "AM" + this.ripsGeneracion.getCodigoArchivo() + ".txt");

            facesContext.responseComplete();

        } catch (IOException ex) {

            System.out.println(
                    "Error:No se pudo descargar el archivo AM " + ex.getMessage());

        }

    }

    public void descargarArchivoAp(ActionEvent event) {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            File tmpFile = File.createTempFile("temp", "txt");

            tmpFile.deleteOnExit();

            PrintStream printStream = new PrintStream(tmpFile);

            for (RegistroAP registro : archivoAP) {

                String linea = "";

                linea += registro.getNumeroFactura().getValor();

                linea += "," + registro.getCodigoPrestadorServicios().getValor();

                linea += "," + registro.getTipoIdentificacionUsuario().getValor();

                linea += "," + registro.getNumeroIdentificacionUsuario().getValor();

//                linea += "," + formatFechaArchivo.format(registro.getFechaProcedimiento());
                linea += "," + UtilFecha.formatoFecha(registro.getFechaProcedimiento(), null, "dd/MM/yyyy");

                linea += "," + registro.getNumeroAutorizacion().getValor();

                linea += "," + registro.getCodigoProcedimiento().getValor();

                linea += "," + registro.getAmbitoRealizacionProcedimiento().getValor();

                linea += "," + registro.getFinalidadProcedimiento().getValor();

                linea += "," + registro.getPersonalQueAtiende().getValor();

                linea += "," + registro.getDiagnosticoPrincipal().getValor();

                linea += "," + registro.getDiagnosticoRelacionado().getValor();

                linea += "," + registro.getCodigoComplicacion().getValor();

                linea += "," + registro.getFormaRealizacionActoQuirurjico().getValor();

//                linea += "," + formatDouble.format(registro.getValorProcedimiento().getValor());
                linea += "," + UtilNum.formatoDecimal(registro.getValorProcedimiento().getValor(), UtilNum.PATRON_DECIMAL_N_NN);

                linea += "\r\n";

                printStream.print(linea);

                //printStream.println(linea);
            }

            writeOutContent(getServletResponse(), tmpFile, "AP" + this.ripsGeneracion.getCodigoArchivo() + ".txt");

            facesContext.responseComplete();

        } catch (IOException ex) {

            System.out.println("Error:No se pudo descargar el archivo AP " + ex.getMessage());

        }

    }

    public void descargarArchivoAc(ActionEvent event) {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            File tmpFile = File.createTempFile("temp", "txt");

            tmpFile.deleteOnExit();

            PrintStream printStream = new PrintStream(tmpFile);

            for (RegistroAC registro : archivoAC) {

                String linea = "";

                linea += registro.getNumeroFactura().getValor();

                linea += "," + registro.getCodigoPrestadorServicios().getValor();

                linea += "," + registro.getTipoIdentificacionUsuario().getValor();

                linea += "," + registro.getNumeroIdentificacionUsuario().getValor();

//                linea += "," + formatFechaArchivo.format(registro.getFechaConsulta());
                linea += "," + UtilFecha.formatoFecha(registro.getFechaConsulta(), null, UtilFecha.PATRON_FECHA_DD_MM_YYYY);

                linea += "," + registro.getNumeroAutorizacion().getValor();

                linea += "," + registro.getCodigoConsulta().getValor();

                linea += "," + registro.getFinalidadConsulta().getValor();

                linea += "," + registro.getCausaExterna().getValor();

                linea += "," + registro.getCodigoDiagnosticoPrincipal().getValor();

                linea += "," + registro.getCodigoDiagnosticoRelacionado1().getValor();

                linea += "," + registro.getCodigoDiagnosticoRelacionado2().getValor();

                linea += "," + registro.getCodigoDiagnosticoRelacionado3().getValor();

                linea += "," + registro.getTipoDiagnosticoPrincipal().getValor();

//                linea += "," + formatDouble.format(registro.getValorConsulta().getValor());
                linea += "," + UtilNum.formatoDecimal(registro.getValorConsulta().getValor(), UtilNum.PATRON_DECIMAL_N_NN);
                linea += "," + UtilNum.formatoDecimal(registro.getValorCuotaModeradora().getValor(), UtilNum.PATRON_DECIMAL_N_NN);
                linea += "," + UtilNum.formatoDecimal(registro.getValorNetoAPagar().getValor(), UtilNum.PATRON_DECIMAL_N_NN);

                linea += "\r\n";

                printStream.print(linea);

                //printStream.println(linea);
            }

            writeOutContent(getServletResponse(), tmpFile, "AC" + this.ripsGeneracion.getCodigoArchivo() + ".txt");

            facesContext.responseComplete();

        } catch (IOException ex) {

            System.out.println("Error:No se pudo descargar el archivo AC " + ex.getMessage());

        }

    }

    public void descargarArchivoAd(ActionEvent event) {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            File tmpFile = File.createTempFile("temp", "txt");

            tmpFile.deleteOnExit();

            PrintStream printStream = new PrintStream(tmpFile);

            String codRips = this.ripsGeneracion.getCodGeneracion().toString();
//            try {
//                codRips = ripsDao.getCodigoRips(ripsControl.getNombreCiudad());
//            } catch (SQLException ex) {
//                Logger.getLogger(UIRips.class.getName()).log(Level.SEVERE, null, ex);
//            }

            String linea = "";
            linea += "0";
            linea += "," + codRips;
            linea += "," + "01";
            linea += "," + archivoAC.size();
            linea += "," + "0";
            linea += "," + "0";
            linea += "\r\n";

            linea += "0";
            linea += "," + codRips;
            linea += "," + "03";
            linea += "," + archivoAP.size();
            linea += "," + "0";
            linea += "," + "0";
            linea += "\r\n";

            printStream.print(linea);

            writeOutContent(getServletResponse(), tmpFile, "AD" + this.ripsGeneracion.getCodigoArchivo() + ".txt");

            facesContext.responseComplete();

        } catch (IOException ex) {

            System.out.println("Error:No se pudo descargar el archivo AD " + ex.getMessage());

        }

    }

    public void descargarArchivoUs(ActionEvent event) {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            File tmpFile = File.createTempFile("temp", "txt");

            tmpFile.deleteOnExit();

            PrintStream printStream = new PrintStream(tmpFile);

            for (RegistroUS registro : archivoUS) {

                String linea = "";

                linea += registro.getTipoIdentificacionUsuario().getValor();

                linea += "," + registro.getNumeroIdentificacionUsuario().getValor();

                linea += "," + registro.getCodigoEapb().getValor();

                linea += "," + registro.getTipoUsuario().getValor();

                linea += "," + registro.getPrimerApellidoUsuario().getValor();

                linea += "," + registro.getSegundoApellidoUsuario().getValor();

                linea += "," + registro.getPrimerNombreUsuario().getValor();

                linea += "," + registro.getSegundoNombreUsuario().getValor();

                linea += "," + registro.getEdad().getValor();

                linea += "," + registro.getUnidadMedidaEdad().getValor();

                linea += "," + registro.getSexo().getValor();

                linea += "," + registro.getCodigoDeptoResidenciaHabitual().getValor();

                linea += "," + registro.getCodigoMunicipioResidenciaHabitual().getValor();

                linea += "," + registro.getZonaResidenciaHabitual().getValor();

                linea += "\r\n";

                printStream.print(linea);

                //printStream.println(linea);
            }

            writeOutContent(getServletResponse(), tmpFile, "US" + this.ripsGeneracion.getCodigoArchivo() + ".txt");

            facesContext.responseComplete();

        } catch (IOException ex) {

            System.out.println("Error:No se pudo descargar el archivo US " + ex.getMessage());

        }

    }

    public void descargarArchivoAf(ActionEvent event) {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            File tmpFile = File.createTempFile("temp", "txt");

            tmpFile.deleteOnExit();

            PrintStream printStream = new PrintStream(tmpFile);

            for (RegistroAF registro : archivoAF) {

                String linea = "";

                linea += registro.getCodigoPrestadorServicios().getValor();

                linea += "," + registro.getRazonSocial().getValor();

                linea += "," + registro.getTipoIdentificacion().getValor();

                linea += "," + registro.getNumeroIdentificacion().getValor();

                linea += "," + registro.getNumeroFactura().getValor();

                if (registro.getNumeroFactura().getValor() != null && registro.getNumeroFactura().getValor().equals("0")) {
                    linea += ",00/00/0000";
                } else {
                    linea += "," + UtilFecha.formatoFecha(registro.getFechaExpedicionFactura(), null, UtilFecha.PATRON_FECHA_DD_MM_YYYY);
                }

                linea += "," + UtilFecha.formatoFecha(registro.getFechaInicio(), null, UtilFecha.PATRON_FECHA_DD_MM_YYYY);
                linea += "," + UtilFecha.formatoFecha(registro.getFechaFinal(), null, UtilFecha.PATRON_FECHA_DD_MM_YYYY);
                linea += "," + registro.getCodigoEapb().getValor();
                linea += "," + registro.getNombreEapb().getValor();
                linea += "," + registro.getNumeroContrato().getValor();
                linea += "," + registro.getDescripcionPlanBeneficios().getValor();
                linea += "," + registro.getNumeroPoliza().getValor();
                linea += "," + UtilNum.formatoDecimal(registro.getValorTotalCopago().getValor(), UtilNum.PATRON_DECIMAL_N_NN);
                linea += "," + UtilNum.formatoDecimal(registro.getValorComision().getValor(), UtilNum.PATRON_DECIMAL_N_NN);
                linea += "," + UtilNum.formatoDecimal(registro.getValorTotalDescuentos().getValor(), UtilNum.PATRON_DECIMAL_N_NN);
                linea += "," + UtilNum.formatoDecimal(registro.getValorNeto().getValor(), UtilNum.PATRON_DECIMAL_N_NN);

                linea += "\r\n";
                printStream.print(linea);
            }

            writeOutContent(getServletResponse(), tmpFile, "AF" + this.ripsGeneracion.getCodigoArchivo() + ".txt");
            facesContext.responseComplete();
        } catch (IOException ex) {

            System.out.println("Error:No se pudo descargar el archivo AF " + ex.getMessage());
        }

    }

    public void descargarArchivoCt(ActionEvent event) {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        try {

            File tmpFile = File.createTempFile("temp", "txt");

            tmpFile.deleteOnExit();

            PrintStream printStream = new PrintStream(tmpFile);

            for (RegistroCT registro : archivoCT) {

                String linea = "";

                linea += registro.getCodigoPrestadorServicios().getValor();

                linea += "," + UtilFecha.formatoFecha(registro.getFechaRemision(), null, UtilFecha.PATRON_FECHA_DD_MM_YYYY);

                linea += "," + registro.getCodigoArchivo().getValor();

                linea += "," + registro.getTotalRegistros().getValor();

                linea += "\r\n";

                printStream.print(linea);

                //printStream.println(linea);
            }

            writeOutContent(getServletResponse(), tmpFile, "CT" + this.ripsGeneracion.getCodigoArchivo() + ".txt");

            facesContext.responseComplete();

        } catch (IOException ex) {

            System.out.println("Error:No se pudo descargar el archivo AF " + ex.getMessage());

        }

    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the ripsGeneracionList
     */
    public List<RipsGeneracion> getRipsGeneracionList() {
        return ripsGeneracionList;
    }

    /**
     * @param ripsGeneracionList the ripsGeneracionList to set
     */
    public void setRipsGeneracionList(List<RipsGeneracion> ripsGeneracionList) {
        this.ripsGeneracionList = ripsGeneracionList;
    }

    /**
     * @return the gestorRips
     */
    public GestorRips getGestorRips() {
        return gestorRips;
    }

    /**
     * @param gestorRips the gestorRips to set
     */
    public void setGestorRips(GestorRips gestorRips) {
        this.gestorRips = gestorRips;
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
     * @return the gestorEntidades
     */
    public GestorEntidades getGestorEntidades() {
        return gestorEntidades;
    }

    /**
     * @param gestorEntidades the gestorEntidades to set
     */
    public void setGestorEntidades(GestorEntidades gestorEntidades) {
        this.gestorEntidades = gestorEntidades;
    }

    /**
     * @return the erroresGenerados
     */
    public boolean isErroresGenerados() {
        return erroresGenerados;
    }

    /**
     * @param erroresGenerados the erroresGenerados to set
     */
    public void setErroresGenerados(boolean erroresGenerados) {
        this.erroresGenerados = erroresGenerados;
    }

    /**
     * @return the cadenaErrores
     */
    public List<String> getCadenaErrores() {
        return cadenaErrores;
    }

    /**
     * @param cadenaErrores the cadenaErrores to set
     */
    public void setCadenaErrores(List<String> cadenaErrores) {
        this.cadenaErrores = cadenaErrores;
    }

    /**
     * @return the archivosGenerados
     */
    public boolean isArchivosGenerados() {
        return archivosGenerados;
    }

    /**
     * @param archivosGenerados the archivosGenerados to set
     */
    public void setArchivosGenerados(boolean archivosGenerados) {
        this.archivosGenerados = archivosGenerados;
    }

    /**
     * @return the gestorEstablecimiento
     */
    public GestorEstablecimiento getGestorEstablecimiento() {
        return gestorEstablecimiento;
    }

    /**
     * @param gestorEstablecimiento the gestorEstablecimiento to set
     */
    public void setGestorEstablecimiento(GestorEstablecimiento gestorEstablecimiento) {
        this.gestorEstablecimiento = gestorEstablecimiento;
    }
}
