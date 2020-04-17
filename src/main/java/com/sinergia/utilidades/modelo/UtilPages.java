/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.utilidades.modelo;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Julian D Osorio G
 */
@ManagedBean
@SessionScoped
public class UtilPages {

    private String currentNav;

    public UtilPages() {
        currentNav = "/inicio/paginas.xhtml"; //default page to load
    }

    public String updateNav() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        currentNav = (String) map.get("currentNav");
        currentNav = currentNav.replace("#", "");
        System.out.println("nav::" + currentNav);
        return (currentNav + "?faces-redirect=false");
    }
    
    public String lastNav() {
        return (currentNav + "?faces-redirect=false");
    }

    public String getCurrentNav() {
        return currentNav;
    }

    public void setCurrentNav(String currentNav) {
        this.currentNav = currentNav;
    }
}
