package com.webel.jsf;

import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Uses JSF2.0 style @ManagedBean (not CDI compatible).
 * 
 * @author darrenkelly
 */

@ManagedBean
@ViewScoped
public class Jsf20ViewBean extends AbstractViewBean {

    private static final Logger logger = Logger.getLogger(Jsf20ViewBean.class.getName());
    
    static protected Logger getLogger() {
        return logger;
    }
    
    /**
     * Creates a new instance.
     */
    public Jsf20ViewBean() {
    }
    
}
