package com.fullcontact.api.libs.fullcontact4j.http.cardreader.model;

/**
 * @author Skylar Lowery (skylar@fullcontact.com)
 * @date 4/9/14
 *
 * If a parse error occurs when parsing
 * an unverifiedField from the Card Reader API
 * response, this will be thrown and may indicate
 * that one should pull the raw fields instead.
 *
 * (Hopefully this does not happen, as it may
 * indicate a bug in the library)
 *
 */
public class InvalidUnverifiedFieldException extends RuntimeException {

    public InvalidUnverifiedFieldException(String itemName, Throwable cause) {
        super("Error parsing [" + itemName + "] in unverifiedFields in Card Reader API json response", cause);
    }

}
