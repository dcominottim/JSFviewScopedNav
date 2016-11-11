/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackoverflow.kolossus;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

/**
 * Adapted from this initial answer 28 May 2015 from kolossus
 *  http://stackoverflow.com/users/1530938/kolossus to a question by Darren Kelly (Webel) at
 * http://stackoverflow.com/questions/30410601/how-detect-and-remove-during-a-session-unused-viewscoped-beans-that-cant-be
 * (How detect and remove (during a session) unused @ViewScoped beans that can't
 * be garbage collected).
 * 
 * See also the faces-config.xml to enable it.
 * 
 * Webel concluded 28 May 2015 that if fails, as the handleNavigation 
 * is never called for the GET-related link/button types.
 *
 * @author kolossus (Stackoverflow).
 */
public class ViewScopeCleaner extends ConfigurableNavigationHandler {

    private static final Logger logger = Logger.getLogger(ViewScopeCleaner.class.getName());
    
    private final NavigationHandler concreteHandler;

    public ViewScopeCleaner(NavigationHandler concreteHandler) {
        this.concreteHandler = concreteHandler;
    }

    static private void echo(String method, String name, Object value) {
        logger.log(Level.INFO, "{0}.{1}: {2}({3})", new Object[]{ViewScopeCleaner.class.getSimpleName(), method, name, value});
    }

    static private void echo(String method, String message) {
        logger.log(Level.INFO, "{0}.{1}: {2}", new Object[]{ViewScopeCleaner.class.getSimpleName(), method, message});
    }
    
    @Override
    public void handleNavigation(FacesContext ctxt, String fromAction, String outcome) {
        String $i = "handleNavigation";
        boolean isPostback = ctxt.isPostback();
        echo($i, "fromAction", fromAction);
        echo($i, "outcome", outcome);
        echo($i, "isPostback", isPostback);
        
        if (!isPostback) {
            // Destroys the entire viewmap for this context, and all the beans therein.
            ctxt.getViewRoot().getViewMap().clear();
            echo($i, "Not postback, view map cleared");
        }

        // Proceed with normal navigation.
        concreteHandler.handleNavigation(ctxt, fromAction, outcome);
    }

    /**
     * Adapted from BalusC http://stackoverflow.com/questions/5416070/jsf-2-and-post-redirect-get.
     * 
     * @param context
     * @param fromAction
     * @param outcome
     * @return 
     */
    @Override
    public NavigationCase getNavigationCase(FacesContext context, String fromAction, String outcome) {
        if (concreteHandler instanceof ConfigurableNavigationHandler) {
            return ((ConfigurableNavigationHandler) concreteHandler).getNavigationCase(context, fromAction, outcome);
        } else {
            return null;
        }
    }

    /**
     * Adapted from BalusC http://stackoverflow.com/questions/5416070/jsf-2-and-post-redirect-get.
     * 
     * @return 
     */
    @Override
    public Map<String, Set<NavigationCase>> getNavigationCases() {
        if (concreteHandler instanceof ConfigurableNavigationHandler) {
            return ((ConfigurableNavigationHandler) concreteHandler).getNavigationCases();
        } else {
            return null;
        }
    }
    
}
