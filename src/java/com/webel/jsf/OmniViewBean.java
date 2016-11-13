package com.webel.jsf;

import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped; // 3rd party CDI-compatible

/**
 *
 * Visit <a href="http://showcase.omnifaces.org/cdi/ViewScoped">OmniFaces @ViewScoped</a>.
 * 
 * @author darrenkelly
 */
@Named
@ViewScoped
public class OmniViewBean extends AbstractViewBean {

    /**
     * Creates a new instance.
     */
    public OmniViewBean() {
    }
    
}
