/* * To change this template, choose Tools | Templates * and open the template in the editor. */package com.sinergia.modelo;/** * Registro de descripción agrupada de los servicios de salud prestados * * @author Juan */public class RegistroAD {    private RipsString numeroFactura = new RipsString("", 20);    private RipsString codigoPrestadorServicios = new RipsString("", 12);    private RipsString codigoConcepto = new RipsString("", 10);    private RipsInt cantidad = new RipsInt(0, 15);    private RipsDouble valorUnitario = new RipsDouble(0, 15);    private RipsDouble valorTotalPorConcepto = new RipsDouble(0, 15);    public RegistroAD() {    }    /**     * @return the numeroFactura     */    public RipsString getNumeroFactura() {        return numeroFactura;    }    /**     * @param numeroFactura the numeroFactura to set     */    public void setNumeroFactura(RipsString numeroFactura) {        this.numeroFactura = numeroFactura;    }    /**     * @return the codigoPrestadorServicios     */    public RipsString getCodigoPrestadorServicios() {        return codigoPrestadorServicios;    }    /**     * @param codigoPrestadorServicios the codigoPrestadorServicios to set     */    public void setCodigoPrestadorServicios(RipsString codigoPrestadorServicios) {        this.codigoPrestadorServicios = codigoPrestadorServicios;    }    /**     * @return the codigoConcepto     */    public RipsString getCodigoConcepto() {        return codigoConcepto;    }    /**     * @param codigoConcepto the codigoConcepto to set     */    public void setCodigoConcepto(RipsString codigoConcepto) {        this.codigoConcepto = codigoConcepto;    }    /**     * @return the cantidad     */    public RipsInt getCantidad() {        return cantidad;    }    /**     * @param cantidad the cantidad to set     */    public void setCantidad(RipsInt cantidad) {        this.cantidad = cantidad;    }    /**     * @return the valorUnitario     */    public RipsDouble getValorUnitario() {        return valorUnitario;    }    /**     * @param valorUnitario the valorUnitario to set     */    public void setValorUnitario(RipsDouble valorUnitario) {        this.valorUnitario = valorUnitario;    }    /**     * @return the valorTotalPorConcepto     */    public RipsDouble getValorTotalPorConcepto() {        return valorTotalPorConcepto;    }    /**     * @param valorTotalPorConcepto the valorTotalPorConcepto to set     */    public void setValorTotalPorConcepto(RipsDouble valorTotalPorConcepto) {        this.valorTotalPorConcepto = valorTotalPorConcepto;    }}