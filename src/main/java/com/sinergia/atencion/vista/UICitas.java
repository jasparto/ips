/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.vista;

import com.sinergia.atencion.controlador.GestorCitas;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.atencion.modelo.Citas_PK;
import com.sinergia.atencion.modelo.SeccionDetalleCombos;
import com.sinergia.paciente.controlador.GestorPacientes;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.paciente.modelo.PacientesPK;
import com.sinergia.utilidades.modelo.UtilJSF;
import com.sinergia.utilidades.modelo.UtilLog;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian
 */
@ManagedBean(name = "uiCitas")
@SessionScoped
public class UICitas {

    public final static String ESTADO_AGENDADO = "AGENDADO";
    public final static String ESTADO_CANCELADO = "CANCELADO";
    public final static String ESTADO_INGRESADO = "INGRESADO";
    public final static String ESTADO_INASISTIDO = "INASISTIDO";
    public final static String ESTADO_GUARDADO = "GUARDADO";
    public static final String ESTADO_FINALIZADO = "FINALIZADO";

    /**
     * @return the ESTADO_FINALIZADO
     */
    public String getESTADO_FINALIZADO() {
        return ESTADO_FINALIZADO;
    }

    @ManagedProperty("#{gestorCitas}")
    private GestorCitas gestorCitas;
    @ManagedProperty("#{gestorPacientes}")
    private GestorPacientes gestorPacientes;

   

    public boolean estadoAbrirHistoria(String estado) {
        return !(estado.equalsIgnoreCase(ESTADO_GUARDADO)
                || estado.equalsIgnoreCase(ESTADO_FINALIZADO)
                || estado.equalsIgnoreCase(ESTADO_INGRESADO));
    }

    public void guardarCita() {
        try {
            Citas citas = (Citas) UtilJSF.getBean("citas");
            Citas_PK pk = new Citas_PK();
            long codCita = gestorCitas.generarCodigoCita();

            //paciente
            Pacientes pacientes = new Pacientes(new PacientesPK(1, citas.getDocumentoBeneficiario()));
            pacientes.setNombre("JULIAN DAVID");
            pacientes.setApellido("OSORIO GONZALEZ");
            pacientes.setSexo("M");
            GregorianCalendar gregorianCalendar = new GregorianCalendar(1988, 3, 3);
            pacientes.setFechaNacimiento(gregorianCalendar.getTime());
            pacientes.setCodCategoria(2);
            gestorCitas.guardarPaciente(pacientes);

            pk.setCodInstitucion(1);
            pk.setCodEstablecimiento(1);
            pk.setCodCita(codCita);

            citas.setPk(pk);
            citas.setFecha(new Date());
            citas.setEstado("PR");
            citas.setObservacion("prueba generacion.");
            citas.setSincroniza("N");
            citas.setCodServicio(Long.MIN_VALUE);

            SeccionDetalleCombos seccionDetalleCombos = new SeccionDetalleCombos(1, 1, new Long(Long.MIN_VALUE), new Long(Long.MIN_VALUE), new Long(Long.MIN_VALUE));
            Set<SeccionDetalleCombos> hashset = new HashSet<>();
            hashset.add(seccionDetalleCombos);

            citas.setSeccionDetalleCombosList(hashset);
            gestorCitas.guardarCita(citas, null);

            System.out.println("guardar");
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {

            } else {
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
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

}
