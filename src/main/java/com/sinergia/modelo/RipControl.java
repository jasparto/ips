
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package ips.modelo.rips;

import java.util.Date;

//~--- classes ----------------------------------------------------------------

/**
 *
 * @author dany
 */
public class RipControl {

    /** Field description */
    private String cod_caf = new String();

    /** Field description */
    private String cod_eapb = new String();

    /** Field description */
    private int cod_entidad;

    /** Field description */
    private String cod_ips = new String();

    /** Field description */
    private String codigo_archivo = new String();

    /** Field description */
    private int estado;

    /** Field description */
    private int estadoCaratula;

    /** Field description */
    private int estadoCopiar;

    /** Field description */
    private int estadoEditar;

    /** Field description */
    private int estadoGenerar;

    /** Field description */
    private Date fecha_final;

    /** Field description */
    private Date fecha_inicio;

    /** Field description */
    private Date fecha_remision;

    /** Field description */
    private String nombre_eapb = new String();

    /** Field description */
    private String nombre_ips = new String();

    /** Field description */
    private String nombre_responsable = new String();

    /** Field description */
    private String numero_id = new String();

    /** Field description */
    private int numero_remision;

    /** Field description */
    private String telefono_responsable = new String();

    /** Field description */
    private int tipo_id;

    /** Field description */
    private int total_registros;

    /** Field description */
    private String valor_estado;

    /** Field description */
    private String valor_tipo_id = new String();

    /** Field description */
    private String vigencia = new String();

    //~--- constructors -------------------------------------------------------

    /**
     * Constructs ...
     *
     */
    public RipControl() {}

    /**
     * Constructs ...
     *
     *
     * @param ripControl
     */
    public RipControl(RipControl ripControl) {
        this.cod_caf              = ripControl.cod_caf;
        this.vigencia             = ripControl.vigencia;
        this.numero_remision      = ripControl.numero_remision;
        this.cod_eapb             = ripControl.cod_eapb;
        this.nombre_responsable   = ripControl.nombre_responsable;
        this.telefono_responsable = ripControl.telefono_responsable;
        this.codigo_archivo       = ripControl.codigo_archivo;
        this.total_registros      = ripControl.total_registros;
        this.fecha_remision       = ripControl.fecha_remision;
        this.fecha_inicio         = ripControl.fecha_inicio;
        this.fecha_final          = ripControl.fecha_final;
        this.estado               = ripControl.estado;
        this.valor_estado         = ripControl.valor_estado;
        this.nombre_eapb          = ripControl.nombre_eapb;
        this.cod_entidad          = ripControl.cod_entidad;
    }

    //~--- enums --------------------------------------------------------------

//  private BtnRips btnGrid = new BtnRips(estado);
//  /**
//   * @return the btnGrid
//   */
//  public BtnRips getBtnGrid() {
//      return btnGrid;
//  }
//
//  /**
//   * @param btnGrid the btnGrid to set
//   */
//  public void setBtnGrid(BtnRips btnGrid) {
//      this.btnGrid = btnGrid;
//  }

    /**
     * Enum description
     *
     */
    public enum EstadosRIPSControl {
        SIN_VERIFICAR(48), VERIFICADO(49), GENERADO(50);

        /** Field description */
        public final int Cod;

        //~--- constructors ---------------------------------------------------

        /**
         * Constructs ...
         *
         *
         * @param cod
         */
        private EstadosRIPSControl(int cod) {
            this.Cod = cod;
        }
    }

    //~--- get methods --------------------------------------------------------

    /**
     * @return the cod_caf
     */
    public String getCod_caf() {
        return cod_caf;
    }

    /**
     * @return the cod_eapb
     */
    public String getCod_eapb() {
        return cod_eapb;
    }

    /**
     * @return the cod_entidad
     */
    public int getCod_entidad() {
        return cod_entidad;
    }

    /**
     * @return the cod_ips
     */
    public String getCod_ips() {
        return cod_ips;
    }

    /**
     * @return the codigo_archivo
     */
    public String getCodigo_archivo() {
        return codigo_archivo;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @return the estadoCaratula
     */
    public boolean getEstadoCaratula() {
        this.estadoCaratula = estado;

        boolean valor = false;

        if (estadoCaratula == EstadosRIPSControl.SIN_VERIFICAR.Cod) {
            valor = false;
        } else {
            if (estadoCaratula == EstadosRIPSControl.VERIFICADO.Cod) {
                valor = false;
            } else {
                if (estadoCaratula == EstadosRIPSControl.GENERADO.Cod) {
                    valor = true;
                }
            }
        }

        return valor;
    }

    /**
     * @return the estadoCopiar
     */
    public boolean getEstadoCopiar() {
        this.estadoCopiar = estado;

        boolean valor = false;

        if (estadoCopiar == EstadosRIPSControl.SIN_VERIFICAR.Cod) {
            valor = false;
        } else {
            if (estadoCopiar == EstadosRIPSControl.VERIFICADO.Cod) {
                valor = false;
            } else {
                if (estadoCopiar == EstadosRIPSControl.GENERADO.Cod) {
                    valor = true;
                }
            }
        }

        return valor;
    }

    /**
     * @return the estadoEditar
     */
    public boolean getEstadoEditar() {
        this.estadoEditar = estado;

        boolean valor = false;

        // System.out.println("getEstadoEditar:"+this.estadoEditar);
        if (estadoEditar == EstadosRIPSControl.SIN_VERIFICAR.Cod) {
            valor = true;
        } else {
            if (estadoEditar == EstadosRIPSControl.VERIFICADO.Cod) {
                valor = false;
            } else {
                if (estadoEditar == EstadosRIPSControl.GENERADO.Cod) {
                    valor = false;
                }
            }
        }

        return valor;
    }

    /**
     * @return the estadoGenerar
     */
    public boolean getEstadoGenerar() {
        this.estadoGenerar = estado;

        boolean valor = false;

        if (estadoGenerar == EstadosRIPSControl.SIN_VERIFICAR.Cod) {
            valor = true;
        } else {
            if (estadoGenerar == EstadosRIPSControl.VERIFICADO.Cod) {
                valor = true;
            } else {
                if (estadoGenerar == EstadosRIPSControl.GENERADO.Cod) {
                    valor = false;
                }
            }
        }

        return valor;
    }

    /**
     * Method description
     *
     *
     * @param estado
     *
     * @return
     */
    public int getEstadosRIPSControl(String estado) {
        int valor = 0;

        if (estado.equals("VERIFICADO")) {
            valor = EstadosRIPSControl.VERIFICADO.Cod;
        } else {
            if (estado.equals("GENERADO")) {
                valor = EstadosRIPSControl.GENERADO.Cod;
            } else {
                valor = EstadosRIPSControl.SIN_VERIFICAR.Cod;
            }
        }

        return valor;
    }

    /**
     * @return the fecha_final
     */
    public Date getFecha_final() {
        return fecha_final;
    }

    /**
     * @return the fecha_inicio
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @return the fecha_remision
     */
    public Date getFecha_remision() {
        return fecha_remision;
    }

    /**
     * @return the nombre_eapb
     */
    public String getNombre_eapb() {
        return nombre_eapb;
    }

    /**
     * @return the nombre_ips
     */
    public String getNombre_ips() {
        return nombre_ips;
    }

    /**
     * @return the nombre_responsable
     */
    public String getNombre_responsable() {
        return nombre_responsable;
    }

    /**
     * @return the numero_id
     */
    public String getNumero_id() {
        return numero_id;
    }

    /**
     * @return the numero_remision
     */
    public int getNumero_remision() {
        return numero_remision;
    }

    /**
     * @return the telefono_responsable
     */
    public String getTelefono_responsable() {
        return telefono_responsable;
    }

    /**
     * @return the tipo_id
     */
    public int getTipo_id() {
        return tipo_id;
    }

    /**
     * @return the total_registros
     */
    public int getTotal_registros() {
        return total_registros;
    }

    /**
     * @return the valor_estado
     */
    public String getValor_estado() {
        return valor_estado;
    }

    /**
     * @return the valor_tipo_id
     */
    public String getValor_tipo_id() {
        return valor_tipo_id;
    }

    /**
     * @return the vigencia
     */
    public String getVigencia() {
        return vigencia;
    }

    //~--- set methods --------------------------------------------------------

    /**
     * @param cod_caf the cod_caf to set
     */
    public void setCod_caf(String cod_caf) {
        this.cod_caf = cod_caf;
    }

    /**
     * @param cod_eapb the cod_eapb to set
     */
    public void setCod_eapb(String cod_eapb) {
        this.cod_eapb = cod_eapb;
    }

    /**
     * @param cod_entidad the cod_entidad to set
     */
    public void setCod_entidad(int cod_entidad) {
        this.cod_entidad = cod_entidad;
    }

    /**
     * @param cod_ips the cod_ips to set
     */
    public void setCod_ips(String cod_ips) {
        this.cod_ips = cod_ips;
    }

    /**
     * @param codigo_archivo the codigo_archivo to set
     */
    public void setCodigo_archivo(String codigo_archivo) {
        this.codigo_archivo = codigo_archivo;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {

//      setBtnGrid(new BtnRips(estado));
        this.estado = estado;
    }

    /**
     * @param estadoCaratula the estadoCaratula to set
     */
    public void setEstadoCaratula(int estadoCaratula) {
        this.estadoCaratula = estadoCaratula;
    }

    /**
     * @param estadoCopiar the estadoCopiar to set
     */
    public void setEstadoCopiar(int estadoCopiar) {
        this.estadoCopiar = estadoCopiar;
    }

    /**
     * @param estadoEditar the estadoEditar to set
     */
    public void setEstadoEditar(int estadoEditar) {

        // System.out.println("setEstadoEditar:"+estadoEditar);
        this.estadoEditar = estadoEditar;

        // System.out.println("this->setEstadoEditar:"+this.estadoEditar);
    }

    /**
     * @param estadoGenerar the estadoGenerar to set
     */
    public void setEstadoGenerar(int estadoGenerar) {
        this.estadoGenerar = estadoGenerar;
    }

    /**
     * @param fecha_final the fecha_final to set
     */
    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @param fecha_remision the fecha_remision to set
     */
    public void setFecha_remision(Date fecha_remision) {
        this.fecha_remision = fecha_remision;
    }

    /**
     * @param nombre_eapb the nombre_eapb to set
     */
    public void setNombre_eapb(String nombre_eapb) {
        this.nombre_eapb = nombre_eapb;
    }

    /**
     * @param nombre_ips the nombre_ips to set
     */
    public void setNombre_ips(String nombre_ips) {
        this.nombre_ips = nombre_ips;
    }

    /**
     * @param nombre_responsable the nombre_responsable to set
     */
    public void setNombre_responsable(String nombre_responsable) {
        this.nombre_responsable = nombre_responsable;
    }

    /**
     * @param numero_id the numero_id to set
     */
    public void setNumero_id(String numero_id) {
        this.numero_id = numero_id;
    }

    /**
     * @param numero_remision the numero_remision to set
     */
    public void setNumero_remision(int numero_remision) {
        this.numero_remision = numero_remision;
    }

    /**
     * @param telefono_responsable the telefono_responsable to set
     */
    public void setTelefono_responsable(String telefono_responsable) {
        this.telefono_responsable = telefono_responsable;
    }

    /**
     * @param tipo_id the tipo_id to set
     */
    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    /**
     * @param total_registros the total_registros to set
     */
    public void setTotal_registros(int total_registros) {
        this.total_registros = total_registros;
    }

    /**
     * @param valor_estado the valor_estado to set
     */
    public void setValor_estado(String valor_estado) {
        this.valor_estado = valor_estado;
    }

    /**
     * @param valor_tipo_id the valor_tipo_id to set
     */
    public void setValor_tipo_id(String valor_tipo_id) {
        this.valor_tipo_id = valor_tipo_id;
    }

    /**
     * @param vigencia the vigencia to set
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
}
