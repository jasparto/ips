/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oasys.ips;

import general.controlador.GestorJava;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author juliano
 */
@ManagedBean
@SessionScoped
public class java {

    public void hola() throws Exception {
        RequestContext context = RequestContext.getCurrentInstance();
        GestorJava gestorJava = new GestorJava();
        String dato = gestorJava.cargarDato();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Hola desde java - bd : " + dato);
        context.showMessageInDialog(message);
    }
}
