/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author juliano
 */
public class Roles implements Serializable {

    protected RolesPK rolesPK;
    private String nombre;
    private Set<Usuarios> relUsuariosRolesSet = new HashSet<>();
    private Institucion institucion;
    private Set<RolesMenus> rolesMenusSet;
    private List<Menus> menusList = new ArrayList<>();
    private Menus menus = new Menus();

    public Roles() {
    }

    public Roles(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Roles(int codInstitucion, Long codigoRol) {
        this.rolesPK = new RolesPK(codInstitucion, codigoRol);
    }

    public Roles(int codInstitucion, Long codigoRol, String nombre) {
        this.rolesPK = new RolesPK(codInstitucion, codigoRol);
        this.nombre = nombre;
    }

    public RolesPK getRolesPK() {
        return rolesPK;
    }

    public void setRolesPK(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the relUsuariosRolesSet
     */
    public Set<Usuarios> getRelUsuariosRolesSet() {
        return relUsuariosRolesSet;
    }

    /**
     * @param relUsuariosRolesSet the relUsuariosRolesSet to set
     */
    public void setRelUsuariosRolesSet(Set<Usuarios> relUsuariosRolesSet) {
        this.relUsuariosRolesSet = relUsuariosRolesSet;
    }

    /**
     * @return the institucion
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the rolesMenusSet
     */
    public Set<RolesMenus> getRolesMenusSet() {
        return rolesMenusSet;
    }

    /**
     * @param rolesMenusSet the rolesMenusSet to set
     */
    public void setRolesMenusSet(Set<RolesMenus> rolesMenusSet) {
        this.rolesMenusSet = rolesMenusSet;
    }

    /**
     * @return the menusList
     */
    public List<Menus> getMenusList() {
        return menusList;
    }

    /**
     * @param menusList the menusList to set
     */
    public void setMenusList(List<Menus> menusList) {
        this.menusList = menusList;
    }

    /**
     * @return the menus
     */
    public Menus getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(Menus menus) {
        this.menus = menus;
    }

}
