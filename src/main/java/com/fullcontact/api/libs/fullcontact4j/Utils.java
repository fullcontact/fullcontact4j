package com.fullcontact.api.libs.fullcontact4j;


import com.fullcontact.api.libs.fullcontact4j.config.Constants;

import java.util.logging.Level;

public class Utils {

    public static void log(Level l, String log) {
        if(l.intValue() >= FullContact.logLevel.intValue()) {
            System.out.println(Constants.LOG_PREFIX + " " + log);
        }
    }

    /**
     * Log important events like client startup, exceptions, etc.
     * @param log string to log
     */
    public static void info(String log) {
        log(Level.INFO, log);
    }

    /**
     * Log workflow-related events, like sending a request, debug info, etc.
     * @param log string to log
     */
    public static void verbose(String log) {
        log(Level.FINE, log);
    }

}
