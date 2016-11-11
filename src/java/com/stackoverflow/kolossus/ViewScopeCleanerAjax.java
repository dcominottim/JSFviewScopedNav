/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackoverflow.kolossus;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Adapted from a second answer from kolossus
 * (http://stackoverflow.com/users/1530938/kolossus) from 02 Jun 2015 to a
 * question by Darren Kelly (Webel) at
 * http://stackoverflow.com/questions/30410601/how-detect-and-remove-during-a-session-unused-viewscoped-beans-that-cant-be
 * (How detect and remove (during a session) unused @ViewScoped beans that can't
 * be garbage collected).
 *
 * @author kolossus (Stackoverflow), Darren Kelly (webel)
 */
public class ViewScopeCleanerAjax implements PhaseListener {

    private static final Logger logger = Logger.getLogger(ViewScopeCleanerAjax.class.getName());

    static private void echo(String method, String name, Object value) {
        logger.log(Level.INFO, "{0}.{1}: {2}({3})", new Object[]{ViewScopeCleanerAjax.class.getSimpleName(), method, name, value});
    }

    static private void echo(String method, String message) {
        logger.log(Level.INFO, "{0}.{1}: {2}", new Object[]{ViewScopeCleanerAjax.class.getSimpleName(), method, message});
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        String $i = "afterPhase";

        echo($i, "event", event);
        echo($i, "event.phaseId", event.getPhaseId());
        FacesContext ctxt = event.getFacesContext();
        boolean isAjax = ctxt.getPartialViewContext().isAjaxRequest(); //determine that it's an ajax request
        echo($i, "isAjax", isAjax);
        Object target = ctxt.getExternalContext().getRequestParameterMap().get("target"); //get the destination URL
        echo($i, "target", target);
        if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES) {
            if (target != null && !target.toString().equals("") && isAjax) {
                NavigationHandler navHandler = ctxt.getApplication().getNavigationHandler();
                ctxt.getViewRoot().getViewMap().clear(); //clear the map
                navHandler.handleNavigation(ctxt, null, target.toString());//navigate
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        //return PhaseId.APPLY_REQUEST_VALUES;
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        //TODO?
    }
}
