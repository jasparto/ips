package general.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author mauricio
 */
@ManagedBean(name = "menu")
@SessionScoped
public class UIMenu {

    private String paginaXhtml = "todos.xhtml";
    private String modalActual = "Menu";
    private String url="";
    public UIMenu() {
        super();
    }

    /**
     * @return the paginaXhtml
     */
    public String getPaginaXhtml() {
        return paginaXhtml;
    }

    /**
     * @param paginaXhtml the paginaXhtml to set
     */
    public void setPaginaXhtml(String paginaXhtml) {
        this.paginaXhtml = paginaXhtml;
    }

    /**
     * @return the modalActual
     */
    public String getModalActual() {
        return modalActual;
    }

    /**
     * @param modalActual the modalActual to set
     */
    public void setModalActual(String modalActual) {
        this.modalActual = modalActual;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}