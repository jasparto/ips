/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.modelo;

/**
 *
 * @author Juan
 */
public class RipsString {

    private String valor = "";
    private int longitud;

    public RipsString(String valorString, int longitud) {
        this.valor = valorString;
        this.longitud = longitud;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valorStr) {
        if (valorStr == null) {
            this.valor = "";
        } else {
            this.valor = valorStr;
        }
    }

    /**
     * @return the longitud
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
