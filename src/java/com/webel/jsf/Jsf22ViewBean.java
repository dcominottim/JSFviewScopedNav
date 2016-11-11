package com.webel.jsf;

import java.util.logging.Logger;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped; // CDI-compatible JSF2.2 version

/**
 * Uses JSF2.2 style CDI compatible @ManagedBean.
 * 
 * @author darrenkelly
 */
@Named
@ViewScoped
public class Jsf22ViewBean extends AbstractViewBean {

    private static final Logger logger = Logger.getLogger(Jsf22ViewBean.class.getName());
    
    static protected Logger getLogger() {
        return logger;
    }
    
    /**
     * Creates a new instance.
     */
    public Jsf22ViewBean() {
    }
    
}
