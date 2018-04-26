/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.facturacion.controlador;

import com.sinergia.atencion.dao.CitasDAO;
import com.sinergia.atencion.modelo.Citas;
import com.sinergia.facturacion.dao.RipsDAO;
import com.sinergia.facturacion.modelo.Facturas;
import com.sinergia.facturacion.modelo.RipsGeneracion;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.modelo.RegistroAC;
import com.sinergia.modelo.RegistroAF;
import com.sinergia.modelo.RegistroAM;
import com.sinergia.modelo.RegistroAP;
import com.sinergia.modelo.RegistroUS;
import com.sinergia.paciente.modelo.Pacientes;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.vista.UIRips;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliano
 */
public class GestorRips extends Gestor {

    private String unidadMedidaEdad;

    public GestorRips() throws Exception {
        super();
    }

    public long generarCodigoGeneracion() throws Exception {
        try {
            this.abrirConexion();
            return new RipsDAO(this.session).generarCodigoGeneracion();
        } finally {
            this.cerrarConexion();
        }
    }

    public int edad(Date fechaNacimiento) {
        try {
            Calendar calendarBirth = Calendar.getInstance();
            calendarBirth.setTime(fechaNacimiento);
            Calendar calendarDateOfAdmission = Calendar.getInstance();
            calendarDateOfAdmission.setTime(new Date());
            int days = 0;

//          Checking the Birth date is coming after the Admission Date or not. If Yes then return empty string. Otherwise Process the Following
            if (calendarBirth.before(calendarDateOfAdmission)) {

//              Checking the Years and Months of both Dates are same or not. If yes then Calculate Only Days. Otherwise Process the Else Pert
                if ((calendarDateOfAdmission.get(Calendar.YEAR) == calendarBirth.get(Calendar.YEAR))
                        && (calendarDateOfAdmission.get(Calendar.MONTH) == calendarBirth.get(Calendar.MONTH))) {
                    days = calendarDateOfAdmission.get(Calendar.DATE) - calendarBirth.get(Calendar.DATE);
                    this.unidadMedidaEdad = UIRips.UNIDAD_MEDIDA_EDAD_DIAS;
                    return days;
                } else {
//                  calculate age as the difference in years.
                    int age = calendarDateOfAdmission.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR);
                    int month = 0;

//                  if that date has not occurred yet, subtract one from age
                    calendarBirth.set(Calendar.YEAR, calendarDateOfAdmission.get(Calendar.YEAR));

                    if (calendarDateOfAdmission.before(calendarBirth)) {
                        age = age - 1;
                        month = calendarBirth.get(Calendar.MONTH) - calendarDateOfAdmission.get(Calendar.MONTH);
                        month = 12 - month;

//                      if that date has not occurred yet, subtract one from month
                        calendarBirth.set(Calendar.MONTH, calendarDateOfAdmission.get(Calendar.MONTH));

                        if (calendarDateOfAdmission.before(calendarBirth)) {
                            month = month - 1;
                        }
                    } else {
                        month = calendarDateOfAdmission.get(Calendar.MONTH) - calendarBirth.get(Calendar.MONTH);

//                      if that date has not occurred yet, subtract one from month
                        calendarBirth.set(Calendar.MONTH, calendarDateOfAdmission.get(Calendar.MONTH));

                        if (calendarDateOfAdmission.before(calendarBirth)) {
                            month = month - 1;
                        }
                    }

                    // return age + " A " + month + " M";
                    this.unidadMedidaEdad = UIRips.UNIDAD_MEDIDA_EDAD_AÑOS;
                    return age;
                }
            } // If the Birth Date is coming after the Admission Date then return Empty String
            else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    private String determinaRegimen(Pacientes pacientes) {
        String regimenContributivo = "1";
        String regimenSubsididado = "2";
        if (pacientes != null && pacientes.getRegimen() != null) {
            switch (pacientes.getRegimen()) {
                case "C":
                    return regimenContributivo;
                case "S":
                    return regimenSubsididado;
            }
        }
        return regimenContributivo;
    }

    public List<RegistroAF> cargarRegistrosAF(Establecimiento establecimiento, String codigoEapb, Date fechaInicial, Date fechaFinal, String codRips, List<Citas> citasList) throws Exception {

        List<RegistroAF> registrosAF = new ArrayList<RegistroAF>();

//            if (codigoEapb.equals("TODOS")) {
//                String strSql = "select eapb.nombre as nombre_eapb, cod_eapb from ips.eapb";
//                getBd().conectar(this.getUsuario(), this.getClave());
//                ResultSet entidades = getBd().ejecutar(strSql);
//                while (entidades.next()) {
//                    System.out.println("sqlAF - Registro para: " + entidades.getString("nombre_eapb"));
//                    registrosAF.addAll(this.RegistrosAFxEntidad(codigoCaf, entidades.getString("cod_eapb"), fechaInicial, fechaFinal, codigoEmpresa, codRips));
//                }
//            } else {
        registrosAF.addAll(this.RegistrosAFxEntidad(establecimiento, codigoEapb, fechaInicial, fechaFinal, establecimiento.getEstablecimientoPK().getCodInstitucion(), codRips, citasList));
//            }

        return registrosAF;
    }

    private List<RegistroAF> RegistrosAFxEntidad(Establecimiento establecimiento, String codigoEapb, Date fechaInicial, Date fechaFinal, int codigoEmpresa, String codRips, List<Citas> citasList) throws SQLException {
        List<RegistroAF> lista = new ArrayList<>();
//        SimpleDateFormat formatFechaDB = new SimpleDateFormat("yyyy-MM-dd");
//        String strSql;
        for (Citas c : citasList) {

//            }
//            while (resultSet.next()) {
//                facturado = true;
            RegistroAF registro = new RegistroAF();
            //registro.getCodigoPrestadorServicios().setValor(resultSet.getString("cod_eapb"));
            registro.getCodigoPrestadorServicios().setValor(codRips);

//                registro.getRazonSocial().setValor(resultSet.getString("razon_social"));
            registro.getRazonSocial().setValor(establecimiento.getNombre());
            registro.getTipoIdentificacion().setValor("NI");
            registro.getNumeroIdentificacion().setValor(establecimiento.getNit());
            registro.getNumeroFactura().setValor(c.getFacturas().getNumeroFactura());
            registro.setFechaExpedicionFactura(c.getFacturas().getFechaRegistro());
            registro.setFechaInicio(fechaInicial);
            registro.setFechaFinal(fechaFinal);
            registro.getCodigoEapb().setValor(codigoEapb);

//                String nombre_eapb = resultSet.getString("nombre_eapb");
//                if (nombre_eapb.indexOf('-') > 0) {
//                    nombre_eapb = nombre_eapb.substring(nombre_eapb.indexOf('-') + 1);
//                    nombre_eapb = nombre_eapb.trim();
//                    registro.getNombreEapb().setValor(nombre_eapb);
//                } else {
//                    registro.getNombreEapb().setValor(nombre_eapb);
//                }
            registro.getNombreEapb().setValor(c.getFacturas().getEntidades().getNombre());//cargar
            registro.getNumeroContrato().setValor("");
//                registro.getDescripcionPlanBeneficios().setValor(findPlanBeneficios(resultSet.getInt("cod_servicio")));
            registro.getDescripcionPlanBeneficios().setValor("Consulta medico general");
            registro.getNumeroPoliza().setValor("");
            registro.getValorTotalCopago().setValor(c.getFacturas().getTotalAnticipo());
            registro.getValorComision().setValor(0);
            registro.getValorTotalDescuentos().setValor(c.getFacturas().getTotalDescuento());
            registro.getValorNeto().setValor(c.getFacturas().getTotalFactura());
            lista.add(registro);
        }

        return lista;

    }

    public List<RegistroUS> cargarRegistrosUS(Establecimiento establecimiento, String codigoEapb, Date fechaInicial, Date fechaFinal, List<Citas> citasList) throws Exception {

//        System.out.println(this.toString() + ":sqlUS:" + strSql);
        try {
            List<RegistroUS> lista = new ArrayList<>();
            SimpleDateFormat formatFechaDB = new SimpleDateFormat("yyyy-MM-dd");
            String strSql;

            this.abrirConexion();
            CitasDAO citasDAO = new CitasDAO(session);
//            List<Citas> citasList = citasDAO.cargarCitas(establecimiento, fechaInicial, fechaFinal);
//        if (!codigoEapb.equals("TODOS")) {
//
//            strSql = "select dls.valor as tipo_ident, p.documento_beneficiario, eapb.cod_eapb, "
//                    + " split_part(p2.nombre, ' ', 1) as nombre,  trim(substring(p2.nombre from ' .*')) as nombre2,"
//                    + " split_part(p2.apellido, ' ', 1) as apellido, trim(substring(p2.apellido from ' .*')) as apellido2, p2.sexo, "
//                    + " date_part('year',age(current_date,p2.fecha_nacimiento)) as edad_años, "
//                    + " date_part('month',age(current_date,p2.fecha_nacimiento)) as edad_meses, "
//                    + " date_part('day',age(current_date,p2.fecha_nacimiento)) as edad_dias, "
//                    + " lpad(dldpto.cod_detallelista,2,'0') as cod_depto_residencia_habitual, "
//                    + " substring(dlmpio.cod_detallelista from length(dldpto.cod_detallelista)+1 for length(dlmpio.cod_detallelista)) as cod_mpio_residencia_habitual, "
//                    + " case when max(e.regimen)='C' then '1' else '2' end as regimen "
//                    + " from ips.cita c"
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad "
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario "
//                    + " left join pacientes p2 on p2.documento_beneficiario=c.documento_beneficiario "
//                    + " left join entidades e on (c.cod_entidad=e.cod_entidad and e.cod_empresa=5) "
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista "
//                    + " left join ips.detalle_lista dldpto on (dldpto.cod_lista, dldpto.cod_detallelista)=(p.departamento_codlista, p.departamento_detallelista) "
//                    + " left join ips.detalle_lista dlmpio on (dlmpio.cod_lista, dlmpio.cod_detallelista)=(p.municipio_codlista, p.municipio_detallelista) "
//                    + " where (c.cod_sucursal='" + codigoCaf + "') "
//                    + " and (eapb.cod_eapb='" + codigoEapb + "')"
//                    + " and (c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA') "
//                    + " group by 1,2,3,4,5,6,7,8,9,10,11,12,13 "
//                    + " order by p.documento_beneficiario, eapb.cod_eapb ";
//        } else {
//            strSql = "select distinct dls.valor as tipo_ident, p.documento_beneficiario, eapb.cod_eapb, "
//                    + " split_part(p2.nombre, ' ', 1) as nombre,  trim(substring(p2.nombre from ' .*')) as nombre2,"
//                    + " split_part(p2.apellido, ' ', 1) as apellido, trim(substring(p2.apellido from ' .*')) as apellido2, p2.sexo, "
//                    + " date_part('year',age(current_date,p2.fecha_nacimiento)) as edad_años, "
//                    + " date_part('month',age(current_date,p2.fecha_nacimiento)) as edad_meses, "
//                    + " date_part('day',age(current_date,p2.fecha_nacimiento)) as edad_dias, "
//                    + " lpad(dldpto.cod_detallelista,2,'0') as cod_depto_residencia_habitual, "
//                    + " substring(dlmpio.cod_detallelista from length(dldpto.cod_detallelista)+1 for length(dlmpio.cod_detallelista)) as cod_mpio_residencia_habitual, "
//                    + " case when max(e.regimen)='C' then '1' else '2' end as regimen "
//                    + " from ips.cita c"
//                    + " left join entidades using (cod_empresa,cod_entidad)"
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad "
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario "
//                    + " left join pacientes p2 on p2.documento_beneficiario=c.documento_beneficiario "
//                    + " left join entidades e on (c.cod_entidad=e.cod_entidad and e.cod_empresa=5) "
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista "
//                    + " left join ips.detalle_lista dldpto on (dldpto.cod_lista, dldpto.cod_detallelista)=(p.departamento_codlista, p.departamento_detallelista) "
//                    + " left join ips.detalle_lista dlmpio on (dlmpio.cod_lista, dlmpio.cod_detallelista)=(p.municipio_codlista, p.municipio_detallelista) "
//                    + " where (c.cod_sucursal='" + codigoCaf + "') "
//                    + " and (c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA') group by 1,2,3,4,5,6,7,8,9,10,11,12,13"
//                    + " --order by p2.nombre group by 1,2,3,4,5,6,7,8,9,10,11,12,13 "
//                    + " order by p.documento_beneficiario, eapb.cod_eapb ";
//        }

//        System.out.println(this.toString() + ":sqlUS:" + strSql);
            try {

//            getBd().conectar(this.getUsuario(), this.getClave());
//            resultSet = getBd().ejecutar(strSql);
                String tmpString, tmpString2;
                String[] apellidos, nombres;
                int conta = 1;
                for (Citas c : citasList) {

                    RegistroUS registro = new RegistroUS();

                    registro.getTipoIdentificacionUsuario().setValor(c.getPacientes().getDocumentosIdentidad().getIniciales());
                    registro.getNumeroIdentificacionUsuario().setValor(c.getDocumentoBeneficiario());
                    registro.getCodigoEapb().setValor(codigoEapb);
                    registro.getTipoUsuario().setValor(this.determinaRegimen(c.getPacientes()));

//                tmpString = resultSet.getString("apellido");
//                if (tmpString != null) {
//                    tmpString = tmpString.replace('?', 'Ñ');
//                }
                    String[] apellido = c.getPacientes().getApellido().split(" ");
                    registro.getPrimerApellidoUsuario().setValor(this.separaCadena(c.getPacientes().getApellido(), 0));

//                tmpString2 = resultSet.getString("apellido2");
//                if (tmpString2 != null) {
//                    tmpString2 = tmpString2.replace('?', 'Ñ');
//                }
//                
//                if (tmpString2==null) {
//                    tmpString2 = "";
//                    apellidos = tmpString.split(" ", 0);
//                    for (int i = 1; i<apellidos.length;i++)
//                        tmpString2 = tmpString2 + apellidos[i] + " ";
//                }
                    registro.getSegundoApellidoUsuario().setValor(this.separaCadena(c.getPacientes().getApellido(), 1));

//                tmpString = resultSet.getString("nombre");
//                System.out.println("nombre: " + tmpString+"numero: "+ conta);
//                conta++;
//                if (tmpString != null) {
//                    tmpString = tmpString.replace('?', 'Ñ');                    
//                }
//                nombres = tmpString.split(" ", 0);
                    registro.getPrimerNombreUsuario().setValor(this.separaCadena(c.getPacientes().getNombre(), 0));

//                tmpString2 = null;
//                if (tmpString2==null) {
//                    tmpString2 = "";
//                    nombres = tmpString.split(" ", 0);
//                    for (int i = 1; i<nombres.length;i++)
//                        tmpString2 = tmpString2 + nombres[i] + " ";
//                }
//
//                tmpString2 = tmpString2.replace('?', 'Ñ');
                    registro.getSegundoNombreUsuario().setValor(this.separaCadena(c.getPacientes().getNombre(), 1));
//
//                    String edadAños = resultSet.getString("edad_años");
//                    String edadMeses = resultSet.getString("edad_meses");
//                    String edadDias = resultSet.getString("edad_dias");

//                    registro = this.determinaEdadPaciente(registro, c.getPacientes());
                    registro.getEdad().setValor(this.edad(c.getPacientes().getFechaNacimiento()));
                    registro.getUnidadMedidaEdad().setValor(this.unidadMedidaEdad);

//                    if (edadAños != null && !edadAños.trim().equals("")
//                            && edadMeses != null && !edadMeses.trim().equals("")
//                            && edadDias != null && !edadDias.trim().equals("")) {
//                        if (edadAños.equals("0")) {
//                            if (edadMeses.equals("0")) {
//                                registro.getEdad().setValor(Integer.parseInt(edadDias));
//                                registro.getUnidadMedidaEdad().setValor("3");
//                            } else {
//                                registro.getEdad().setValor(Integer.parseInt(edadMeses));
//                                registro.getUnidadMedidaEdad().setValor("2");
//                            }
//                        } else {
//                            registro.getEdad().setValor(Integer.parseInt(edadAños));
//                            registro.getUnidadMedidaEdad().setValor("1");
//                        }
//
//                    } else {
//                        registro.getEdad().setValor(0);
//                        registro.getUnidadMedidaEdad().setValor("3");
//                    }
                    registro.getSexo().setValor(c.getPacientes().getSexo());

                    registro.getCodigoDeptoResidenciaHabitual().setValor(c.getPacientes().getCodDepartamentoNacimiento());

                    //String codigoMunicipio = resultSet.getString("cod_mpio_residencia_habitual");
                    //registro.getCodigoMunicipioResidenciaHabitual().setValor((codigoMunicipio == null) ? "" : codigoMunicipio.substring(2));
                    registro.getCodigoMunicipioResidenciaHabitual().setValor(c.getPacientes().getCodMunicipioNacimiento());

                    registro.getZonaResidenciaHabitual().setValor("U"); // To check

                    lista.add(registro);
                }
            } catch (Exception ex) {
                throw ex;
            }
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(GestorRips.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private String determinaApellidoPaciente(Pacientes pacientes, int i) {
        switch (i) {
            case 0:
                return pacientes.getApellido().split(" ")[i];
            case 1:
                String[] p = pacientes.getApellido().split(" ");
                switch (p.length) {
                    case 1:
                        return p[1];
                    case 2:
                        return p[1] + " " + p[2];
                }
                break;
        }
        return null;
    }

    private String separaCadena(String cadena, int i) {
        switch (i) {
            case 0:
                return cadena.split(" ")[i];
            case 1:
                String[] p = cadena.split(" ");
                switch (p.length) {
                    case 2:
//                        return p[1] + " " + p[2];
                        return p[1];
                }
                break;
        }
        return null;
    }

    public List<RegistroAC> cargarRegistrosAC(Establecimiento establecimiento, String codigoEapb, Date fechaInicial, Date fechaFinal, String codRips, List<Citas> citasList) throws Exception {
        List<RegistroAC> lista = new ArrayList<>();
        SimpleDateFormat formatFechaDB = new SimpleDateFormat("yyyy-MM-dd");

        String sqlString;
        String causaExterna;

//        if (!codigoEapb.equals("TODOS")) {
//
//            sqlString = "select coalesce(num_factura,'0') as num_factura, dls.valor as tipo_ident, p.documento_beneficiario"
//                    + " ,c.fecha as fecha_consulta, f.numero_autorizacion, c.cod_cita as codigo_consulta"
//                    + " , hcg.cod_diagnostico_principal, hcg.cod_diagnostico_relacionado1, hcg.cod_diagnostico_relacionado2"
//                    + " , dc.cod_itemlista as tipo_diagnostico, dc2.cod_itemlista as causa_externa, coalesce(f.total_factura, 0) as valor_consulta, eapb.cod_eapb"
//                    + " , coalesce(f.total_anticipo, 0) as valor_cuota_moderadora"
//                    + " , coalesce(f.total_factura, 0) - coalesce(f.total_anticipo, 0) + coalesce(f.total_iva, 0) as valor_neto"
//                    + " from ips.cita c "
//                    + " inner join ips.servicio s using (cod_servicio)"
//                    + " left join ips.fact_facturacion f on (f.cod_cita=c.cod_cita and f.cod_empresa=c.cod_empresa) "
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad"
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario"
//                    + " left join pacientes p2 on p2.documento_beneficiario=c.documento_beneficiario"
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista"
//                    + " left join ips.hc_general hcg on (c.cod_cita=hcg.cod_cita and c.cod_empresa=hcg.cod_empresa)"
//                    + " left join ips.detallecombo dc on (c.cod_empresa=dc.cod_empresa and c.cod_cita=dc.cod_cita and dc.cod_seccion in (6,10) and dc.conse_detalle=10)"
//                    + " left join ips.detallecombo dc2 on (c.cod_empresa=dc2.cod_empresa and c.cod_cita=dc2.cod_cita and dc2.cod_seccion in (1,14) and dc2.conse_detalle=20)"
//                    + " where (c.cod_sucursal='" + codigoCaf + "')"
//                    + " and (eapb.cod_eapb='" + codigoEapb + "')"
//                    + " and ( f.fecha_registro BETWEEN '" + formatFechaDB.format(fechaInicial) + "'"
//                    + " and '" + formatFechaDB.format(fechaFinal) + "'"
//                    + " or c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "'  "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA')"
//                    + " and (f.estado='F' or f.estado is null)"
//                    + " and s.cod_servicio in (select cod_servicio from ips.equivalencia_servicio "
//                    + "left join ips.configuracion_servicio using (cod_configuracion) where nombre = 'AC - Consultas')";
//
//        } else {
//            sqlString = "select coalesce(num_factura,'0') as num_factura, dls.valor as tipo_ident, p.documento_beneficiario"
//                    + ",c.fecha as fecha_consulta, f.numero_autorizacion, c.cod_cita as codigo_consulta"
//                    + ", hcg.cod_diagnostico_principal, hcg.cod_diagnostico_relacionado1, hcg.cod_diagnostico_relacionado2"
//                    + ", dc.cod_itemlista as tipo_diagnostico, dc2.cod_itemlista as causa_externa, coalesce(f.total_factura, 0) as valor_consulta, eapb.cod_eapb"
//                    + ", coalesce(f.total_anticipo, 0) as valor_cuota_moderadora"
//                    + ", coalesce(f.total_factura, 0) - coalesce(f.total_anticipo, 0) + coalesce(f.total_iva, 0) as valor_neto"
//                    + " from ips.cita c "
//                    + " inner join ips.servicio s using (cod_servicio)"
//                    + " left join ips.fact_facturacion f on (f.cod_cita=c.cod_cita and f.cod_empresa=c.cod_empresa)"
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad"
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario"
//                    + " left join pacientes p2 on p2.documento_beneficiario=c.documento_beneficiario"
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista"
//                    + " left join ips.hc_general hcg on (c.cod_cita=hcg.cod_cita and c.cod_empresa=hcg.cod_empresa)"
//                    + " left join ips.detallecombo dc on (c.cod_empresa=dc.cod_empresa and c.cod_cita=dc.cod_cita and dc.cod_seccion in (6,10) and dc.conse_detalle=10)"
//                    + " left join ips.detallecombo dc2 on (c.cod_empresa=dc2.cod_empresa and c.cod_cita=dc2.cod_cita and dc2.cod_seccion in (1,14) and dc2.conse_detalle=20)"
//                    + " where (c.cod_sucursal='" + codigoCaf + "') "
//                    + " and ( f.fecha_registro BETWEEN '" + formatFechaDB.format(fechaInicial) + "'"
//                    + " and '" + formatFechaDB.format(fechaFinal) + "'"
//                    + " or c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "'  "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA')"
//                    + " and (f.estado='F' or f.estado is null)"
//                    + " and s.cod_servicio in (select cod_servicio from ips.equivalencia_servicio "
//                    + "left join ips.configuracion_servicio using (cod_configuracion) where nombre = 'AC - Consultas')";
//        }
//        System.out.println(this.toString() + ":sqlAC:" + sqlString);
        try {
//            getCBd().conectar(this.getUsuario(), this.getClave());
//            resultSet = getBd().ejecutar(sqlString);
//            this.abrirConexion();
//            CitasDAO citasDAO = new CitasDAO(session);
//            List<Citas> citasList = citasDAO.cargarCitas(establecimiento, fechaInicial, fechaFinal);

            for (Citas c : citasList) {

                RegistroAC registro = new RegistroAC();
                registro.getNumeroFactura().setValor(c.getFacturas().getNumeroFactura());
                //registro.getCodigoPrestadorServicios().setValor(resultSet.getString("cod_eapb"));
                registro.getCodigoPrestadorServicios().setValor(codRips);
                registro.getTipoIdentificacionUsuario().setValor(c.getPacientes().getDocumentosIdentidad().getIniciales());
                registro.getNumeroIdentificacionUsuario().setValor(c.getPacientes().getPacientesPK().getDocumentoBeneficiario());
                registro.setFechaConsulta(c.getFecha());
                registro.getNumeroAutorizacion().setValor(c.getFacturas().getNumeroAutorizacion());
                registro.getCodigoConsulta().setValor(String.valueOf(c.getPk().getCodCita()));
                registro.getFinalidadConsulta().setValor("10"); // To check

//                causaExterna = resultSet.getString("causa_externa");
//                if (causaExterna == null) {
//                    causaExterna = "";
//                } else if (causaExterna.equals("1")) {
//                    causaExterna = "13";
//                } else if (causaExterna.equals("2")) {
//                    causaExterna = "14";
//                } else if (causaExterna.equals("3")) {
//                    causaExterna = "1";
//                } else if (causaExterna.equals("4")) {
//                    causaExterna = "2";
//                } else if (causaExterna.equals("5")) {
//                    causaExterna = "15";
//                }
                registro.getCausaExterna().setValor(null);

                registro.getCodigoDiagnosticoPrincipal().setValor("0");
                registro.getCodigoDiagnosticoRelacionado1().setValor("0");
                registro.getCodigoDiagnosticoRelacionado2().setValor("0");
                registro.getCodigoDiagnosticoRelacionado3().setValor("0"); // To check
//                registro.getTipoDiagnosticoPrincipal().setValor(resultSet.getString("tipo_diagnostico"));
                registro.getTipoDiagnosticoPrincipal().setValor(null);
                registro.getValorConsulta().setValor(c.getFacturas().getTotalAnticipo() + c.getFacturas().getTotalFactura());
                registro.getValorCuotaModeradora().setValor(c.getFacturas().getTotalAnticipo());
                registro.getValorNetoAPagar().setValor(c.getFacturas().getTotalFactura());
                lista.add(registro);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }

    public List<RegistroAP> cargarRegistrosAP(Establecimiento establecimiento, String codigoEapb, Date fechaInicial, Date fechaFinal, String codRips, List<Citas> citasList) throws Exception {
        List<RegistroAP> lista = new ArrayList<RegistroAP>();

//        SimpleDateFormat formatFechaDB = new SimpleDateFormat("yyyy-MM-dd");
//
//        String sqlString;
//
//        if (!codigoEapb.equals("TODOS")) {
//
//            sqlString = "select s.cod_servicio, '2' as finalidad ,"
//                    + " coalesce(f.num_factura,'0') as num_factura, dls.valor as tipo_ident, p.documento_beneficiario"
//                    + " , c.fecha as fecha_procedimiento, f.numero_autorizacion, c.cod_cita as codigo_procedimiento"
//                    + " , hcg.cod_diagnostico_principal, hcg.cod_diagnostico_relacionado1"
//                    + " , coalesce(f.total_factura,'0') as valor_procedimiento, eapb.cod_eapb "
//                    + " from ips.cita c"
//                    + " left join ips.fact_facturacion f using (cod_empresa,cod_cita) "
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad "
//                    + " inner join ips.servicio s using (cod_servicio) "
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario "
//                    + " left join pacientes p2 on p2.documento_beneficiario=c.documento_beneficiario "
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista "
//                    + " left join ips.hc_general hcg on (c.cod_cita=hcg.cod_cita and c.cod_empresa=hcg.cod_empresa)"
//                    + " left join ips.detallecombo dc on (c.cod_empresa=dc.cod_empresa and c.cod_cita=dc.cod_cita and dc.cod_seccion=6 and dc.conse_detalle=10) "
//                    + " where (c.cod_sucursal='" + codigoCaf + "')"
//                    + " and (eapb.cod_eapb='" + codigoEapb + "')"
//                    + " and (f.fecha_registro BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "' "
//                    + " or c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA')"
//                    + " and (f.estado='F' or f.estado is null)"
//                    + " and s.cod_servicio in (select cod_servicio from ips.equivalencia_servicio "
//                    + " left join ips.configuracion_servicio using (cod_configuracion) where nombre = 'AP - Codigo Procedimiento')";
//        } else {
//            sqlString = "select s.cod_servicio, '2' as finalidad ,"
//                    + " coalesce(f.num_factura,'0') as num_factura, dls.valor as tipo_ident, p.documento_beneficiario"
//                    + " , c.fecha as fecha_procedimiento, f.numero_autorizacion, c.cod_cita as codigo_procedimiento"
//                    + " , hcg.cod_diagnostico_principal, hcg.cod_diagnostico_relacionado1"
//                    + " , coalesce(f.total_factura,'0') as valor_procedimiento, eapb.cod_eapb "
//                    + " from ips.cita c"
//                    + " left join ips.fact_facturacion f using (cod_empresa,cod_cita) "
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad "
//                    + " inner join ips.servicio s using (cod_servicio) "
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario "
//                    + " left join pacientes p2 on p2.documento_beneficiario=c.documento_beneficiario "
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista "
//                    + " left join ips.hc_general hcg on (c.cod_cita=hcg.cod_cita and c.cod_empresa=hcg.cod_empresa) "
//                    + " left join ips.detallecombo dc on (c.cod_empresa=dc.cod_empresa and c.cod_cita=dc.cod_cita and dc.cod_seccion=6 and dc.conse_detalle=10) "
//                    + " where (c.cod_sucursal='" + codigoCaf + "')"
//                    + " and (f.fecha_registro BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "' "
//                    + " or c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA')"
//                    + " and (f.estado='F' or f.estado is null)"
//                    + " and s.cod_servicio in (select cod_servicio from ips.equivalencia_servicio "
//                    + " left join ips.configuracion_servicio using (cod_configuracion) where nombre = 'AP - Codigo Procedimiento')";
//        }
//        System.out.println(this.toString() + ":sqlAP:" + sqlString);
        try {
//            this.abrirConexion();
//            CitasDAO citasDAO = new CitasDAO(session);
//            this.cargarCodigoProcedimiento();

//            getBd().conectar(this.getUsuario(), this.getClave());
//            resultSet = getBd().ejecutar(sqlString);
//            Collection<Citas> citasList = (Collection<Citas>) citasDAO.cargarCitas(establecimiento, fechaInicial, fechaFinal);
            for (Citas c : citasList) {
                RegistroAP registro = new RegistroAP();

                registro.getNumeroFactura().setValor(c.getFacturas().getNumeroFactura());

                //registro.getCodigoPrestadorServicios().setValor(resultSet.getString("cod_eapb"));
                registro.getCodigoPrestadorServicios().setValor(codRips);
                registro.getTipoIdentificacionUsuario().setValor(c.getPacientes().getDocumentosIdentidad().getIniciales());
                registro.getNumeroIdentificacionUsuario().setValor(c.getPacientes().getPacientesPK().getDocumentoBeneficiario());
                registro.setFechaProcedimiento(c.getFecha());
                registro.getNumeroAutorizacion().setValor(c.getFacturas().getNumeroAutorizacion());
                registro.getCodigoProcedimiento().setValor(String.valueOf(c.getCodServicio()));
                registro.getAmbitoRealizacionProcedimiento().setValor("1"); // To check

                //registro.getFinalidadProcedimiento().setValor(resultSet.getString("finalidad")); // To check
                registro.getFinalidadProcedimiento().setValor("2"); // To check
                registro.getPersonalQueAtiende().setValor("");
                registro.getDiagnosticoPrincipal().setValor("0");
                registro.getDiagnosticoRelacionado().setValor("0");
                registro.getCodigoComplicacion().setValor(""); // To check
                registro.getFormaRealizacionActoQuirurjico().setValor("1"); // To check
                registro.getValorProcedimiento().setValor(c.getFacturas().getTotalAnticipo() + c.getFacturas().getTotalFactura());
                lista.add(registro);

            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    public List<RegistroAM> cargarRegistrosAM(Establecimiento establecimiento, int codigoEmpresa, String codigoEapb, Date fechaInicial, Date fechaFinal, String codRips, List<Citas> citasList) {
        List<RegistroAM> lista = new ArrayList<RegistroAM>();
        SimpleDateFormat formatFechaDB = new SimpleDateFormat("yyyy-MM-dd");
        String sqlString;

//        if (!codigoEapb.equals("TODOS")) {
//
//            sqlString = "select coalesce(f.num_factura, '0') as num_factura, ppd.nap,dls.valor as tipo_ident, p.documento_beneficiario, me.cod_comercial, vg.pos"
//                    + ", vg.nombre as nombre_generico, ff.nombre as forma_farmaceutica, vg.concen, mm.medida, me.cantidad,"
//                    + "  eapb.cod_eapb"
//                    + " from ips.cita c "
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad"
//                    + " natural join ips.medicamentos_enfermeria me "
//                    + " left join ips.programacion_pendiente pp on (c.cod_empresa=pp.cod_empresa and c.cod_cita=pp.cod_cita) "
//                    + " inner join ips.programacion_pendiente_detalle ppd on (pp.cod_empresa=ppd.cod_empresa and pp.cod_programacion=ppd.cod_programacion) "
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario"
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista"
//                    + " left join vademecum_comercial vc on me.cod_comercial=vc.cod_comercial and c.cod_empresa=vc.cod_empresa "
//                    + " left join vademecum_generico vg on vc.cod_generico=vg.cod_generico and vc.cod_empresa=vg.cod_empresa "
//                    + " left join formas_farmaceuticas ff on vg.cod_forma=ff.cod_forma"
//                    + " left join medidas_medicamento mm on vg.cod_medida=mm.cod_medida"
//                    + " left join ips.fact_facturacion f on (f.cod_cita=c.cod_cita and f.cod_empresa=c.cod_empresa)"
//                    + " where "
//                    + " eapb.cod_eapb='" + codigoEapb + "'"
//                    + " and c.cod_sucursal='" + codigoCaf + "'"
//                    + " and (c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA')"
//                    + " group by 1,2,3,4,5,6,7,8,9,10,11,12"
//                    + " order by p.documento_beneficiario,me.cod_comercial ";
//        } else {
//            sqlString = "select coalesce(f.num_factura, '0') as num_factura, ppd.nap,dls.valor as tipo_ident, p.documento_beneficiario, me.cod_comercial, vg.pos"
//                    + ", vg.nombre as nombre_generico, ff.nombre as forma_farmaceutica, vg.concen, mm.medida, me.cantidad,"
//                    + "  eapb.cod_eapb "
//                    + " from ips.cita c "
//                    + " inner join ips.eapb eapb on c.cod_entidad=eapb.cod_entidad"
//                    + " natural join ips.medicamentos_enfermeria me "
//                    + " left join ips.programacion_pendiente pp on (c.cod_empresa=pp.cod_empresa and c.cod_cita=pp.cod_cita) "
//                    + " inner join ips.programacion_pendiente_detalle ppd on (pp.cod_empresa=ppd.cod_empresa and pp.cod_programacion=ppd.cod_programacion) "
//                    + " left join ips.paciente p on c.documento_beneficiario=p.documento_beneficiario"
//                    + " left join ips.detalle_listasimple dls on p.tipo_identificacion=dls.cod_detallelista"
//                    + " left join vademecum_comercial vc on me.cod_comercial=vc.cod_comercial and c.cod_empresa=vc.cod_empresa "
//                    + " left join vademecum_generico vg on vc.cod_generico=vg.cod_generico and vc.cod_empresa=vg.cod_empresa "
//                    + " left join formas_farmaceuticas ff on vg.cod_forma=ff.cod_forma"
//                    + " left join medidas_medicamento mm on vg.cod_medida=mm.cod_medida"
//                    + " left join ips.fact_facturacion f on (f.cod_cita=c.cod_cita and f.cod_empresa=c.cod_empresa)"
//                    + " where "
//                    + " c.cod_sucursal='" + codigoCaf + "'"
//                    + " and (c.fecha::date BETWEEN '" + formatFechaDB.format(fechaInicial) + "' "
//                    + " and '" + formatFechaDB.format(fechaFinal) + "')"
//                    + " and (c.estado='FI' or estado_facturacion='FA')"
//                    + " group by 1,2,3,4,5,6,7,8,9,10,11,12"
//                    + " order by p.documento_beneficiario,me.cod_comercial ";
//        }
//
//        System.out.println(this.toString() + ":sqlAM:" + sqlString);
        try {

//            getBd().conectar(this.getUsuario(), this.getClave());
//            resultSet = getBd().ejecutar(sqlString);
            for (Citas c : citasList) {

                RegistroAM registro = new RegistroAM();

                registro.getNumeroFactura().setValor(c.getFacturas().getNumeroFactura());

                //registro.getCodigoPrestadorServicios().setValor(resultSet.getString("cod_eapb"));
                registro.getCodigoPrestadorServicios().setValor(codRips);

                registro.getTipoIdentificacionUsuario().setValor(c.getPacientes().getDocumentosIdentidad().getIniciales());

                registro.getNumeroIdentificacionUsuario().setValor(c.getPacientes().getPacientesPK().getDocumentoBeneficiario());

                registro.getNumeroAutorizacion().setValor(c.getFacturas().getNumeroAutorizacion());

                registro.getCodigoMedicamento().setValor("0");

//                String pos = (resultSet.getString("pos") == null) ? "" : resultSet.getString("pos");
//                registro.getTipoMedicamento().setValor((pos.equals("S")) ? 1 : 2);
                registro.getTipoMedicamento().setValor(0);
                registro.getNombreGenericoMedicamento().setValor("");
                registro.getFormaFarmaceuticaMedicamento().setValor("");
                registro.getConcentracionMedicamento().setValor("");
                registro.getUnidadMedidaMedicamento().setValor("");
                registro.getNumeroUnidadesMedicamento().setValor(0);
                registro.getValorUnitarioMedicamento().setValor(0);
                registro.getValorTotalMedicamentos().setValor(0);
                lista.add(registro);

            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void guardarRipsGeneracion(RipsGeneracion ripsGeneracion) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            this.session.saveOrUpdate(ripsGeneracion);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends RipsGeneracion> cargarRipsGeneracion(Establecimiento establecimiento) {
        Object[] p = {establecimiento.getEstablecimientoPK().getCodInstitucion(), establecimiento.getEstablecimientoPK().getCodEstablecimiento()};
        return getHibernateTemplate().find("FROM RipsGeneracion RG"
                + " WHERE RG.codInstitucion=? AND RG.codEstablecimiento=?"
                + " ORDER BY RG.codGeneracion DESC", p);
    }

}
