/*
 * (C) Copyright 2016 Greensoft Pty Ltd, Australia (ACN 148 917 999). All rights reserved.
 */
package com.webel.jsf;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;

/**
 *
 * @author darrenkelly
 */
@Named(value = "javaUtil")
//@Dependent
@ApplicationScoped
public class JavaUtil implements Serializable {

    /**
     * Creates a new instance of JavaHelper
     */
    public JavaUtil() {
    }

    public String getJavaVersion() {
     return System.getProperty("java.version");
    }
}
